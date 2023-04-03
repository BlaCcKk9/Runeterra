package com.lms.worldoflol.domain.use_case.get_champion_details

import android.util.Log
import com.lms.worldoflol.R
import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.common.Resource
import com.lms.worldoflol.common.Role
import com.lms.worldoflol.data.remote.dto.ChampionDetailDto
import com.lms.worldoflol.data.remote.dto.getDifficulty
import com.lms.worldoflol.domain.model.remote.ChampionDetail
import com.lms.worldoflol.domain.model.remote.Skin
import com.lms.worldoflol.domain.model.remote.Spell
import com.lms.worldoflol.domain.repository.ChampionDetailsRepository
import com.lms.worldoflol.ui.screens.main.champions.components.Difficulty
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

const val BASE_SPLASH_URL = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/"
const val BASE_SPELL_URL = "https://ddragon.leagueoflegends.com/cdn/12.23.1/img/spell/"
const val BASE_PASSIVE_URL = "https://ddragon.leagueoflegends.com/cdn/12.23.1/img/passive/"

class GetChampionDetailsUseCase @Inject constructor(
    private val repository: ChampionDetailsRepository
) {
    operator fun invoke(championName: String): Flow<Resource<ChampionDetail>> = flow {
        try {
            val championDetailsDao = repository.getChampionDetails(true, championName)
            if (championDetailsDao == null) {
                emit(Resource.Loading())
                val championDetail = repository.getChampionDetails(false, championName)
                repository.insertChampionDetails(championDetail!!)
                emit(Resource.Success(championDetailsMapper(championDetail)))
            } else {
                emit(Resource.Success(championDetailsMapper(championDetailsDao)))
            }
        } catch (e: IOException) {
            emit(Resource.Error(ErrorType.NoInternetConnection))
        }
    }

    private fun championDetailsMapper(response: ChampionDetailDto): ChampionDetail {
        val championImage = getChampionSplashImage(response.id)
        val skins = skinsMapper(response.skins, response.id)
        val role = response.tags[0]
        val roleImage = roleImageMapper(role)
        val difficulty = getDifficulty(response.info.difficulty)
        val difficultyImage = difficultyImageMapper(difficulty)
        val spells = spellsMapper(response.spells, response.passive)

        return ChampionDetail(
            championId = response.id,
            championName = response.name,
            championTitle = response.title,
            championImage = championImage,
            lore = response.lore,
            blurb = response.blurb,
            skins = skins,
            role = role,
            roleImage = roleImage,
            difficulty = difficulty,
            difficultyImage = difficultyImage,
            spells = spells,
        )
    }

    private fun roleImageMapper(role: String) =
        when (role) {
            Role.ASSASSIN -> R.drawable.ic_champion_detail_role_assassin
            Role.FIGHTER -> R.drawable.ic_champion_detail_role_fighter
            Role.MAGE -> R.drawable.ic_champion_detail_role_mage
            Role.MARKSMAN -> R.drawable.ic_champion_detail_role_marksman
            Role.TANK -> R.drawable.ic_champion_detail_role_tank
            Role.SUPPORT -> R.drawable.ic_champion_detail_role_support
            else -> R.drawable.ic_champion_detail_role_fighter
        }

    private fun difficultyImageMapper(difficulty: String) =
        when (difficulty) {
            Difficulty.Easy.title -> R.drawable.ic_champion_detail_difficulty_low
            Difficulty.Medium.title -> R.drawable.ic_champion_detail_difficulty_moderate
            Difficulty.Hard.title -> R.drawable.ic_champion_detail_difficulty_high
            else -> R.drawable.ic_champion_detail_difficulty_low
        }

    private fun skinsMapper(list: List<ChampionDetailDto.SkinDto>, id: String): List<Skin> {
        var skins = ArrayList<Skin>()
        list.forEach {
            skins.add(
                Skin(
                    id = it.id,
                    name = it.name,
                    imageUrl = getChampionSplashSkinImage(id, it.num)
                )
            )
        }
        return skins
    }

    private fun spellsMapper(
        spellList: List<ChampionDetailDto.SpellDto>,
        passive: ChampionDetailDto.PassiveDto
    ): List<Spell> {
        var spells = ArrayList<Spell>()
        spellList.forEachIndexed { index, spell ->
            spells.add(
                Spell(
                    name = spellNameMapper(index),
                    title = spell.name,
                    description = spell.description,
                    imageUrl = getChampionSpellImage(spell.image.full),
                    isSelected = index == 0
                )
            )
        }
        spells.add(
            Spell(
                name = spellNameMapper(-1),
                title = passive.name,
                description = passive.description,
                imageUrl = getChampionPassiveImage(passive.image.full)
            )
        )
        return spells
    }

    private fun spellNameMapper(index: Int) =
        when(index){
            0 -> "Q"
            1 -> "W"
            2 -> "E"
            3 -> "R"
            else -> "P"
        }

    private fun getChampionSplashImage(id: String) = "$BASE_SPLASH_URL${id}_0.jpg"
    private fun getChampionSplashSkinImage(id: String, skinNum: Int) = "$BASE_SPLASH_URL${id}_${skinNum}.jpg"
    private fun getChampionSpellImage(full: String) = "$BASE_SPELL_URL${full}"
    private fun getChampionPassiveImage(full: String) = "$BASE_PASSIVE_URL${full}"
}