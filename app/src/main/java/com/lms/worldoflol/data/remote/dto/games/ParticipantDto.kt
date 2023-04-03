package com.lms.worldoflol.data.remote.dto.games

import android.hardware.usb.UsbEndpoint
import com.lms.worldoflol.common.Rune
import com.lms.worldoflol.common.SummonerSpell
import com.lms.worldoflol.domain.model.local.Participant

data class ParticipantDto(
    val assists: Int,
    val championId: Int,
    val championName: String,
    val champLevel: Int,
    val deaths: Int,
    val item0: Int,
    val item1: Int,
    val item2: Int,
    val item3: Int,
    val item4: Int,
    val item5: Int,
    val item6: Int,
    val kills: Int,
    val lane: String,
    val role: String,
    val perks: Perks,
    val puuid: String,
    val summoner1Id: Int,
    val summoner2Id: Int,
    val summonerId: String,
    val summonerLevel: Int,
    val summonerName: String,
    val teamId: Int,
    val win: Boolean,
    val neutralMinionsKilled: Int,
    val totalMinionsKilled: Int
)

fun ParticipantDto.toParticipant(participant: ParticipantDto, me: ParticipantDto) = Participant(
    assists = assists,
    deaths = deaths,
    kills = kills,
    championImage = getChampionImageUrl(participant),
    champLevel = champLevel,
    items = getItems(item0, item1, item2, item3, item4, item5, item6),
    laneImage = getLaneImage(participant.lane, participant.role),
    puuid = puuid,
    summonerSpells = getSummonerSpell(summoner1Id, summoner2Id),
    summonerName = summonerName,
    runes = getRunes(perks.styles[0].selections[0].perk, perks.styles[1].style),
    cs = (neutralMinionsKilled + totalMinionsKilled).toString(),
    conditionColor = getConditionColor(getGameCondition(participant)),
    isMe = participant == me
)

fun getItems(vararg items: Int): List<String> {
    val itemImageUrls = ArrayList<String>()
    items.forEach { id ->
        if (id != 0) itemImageUrls.add("http://ddragon.leagueoflegends.com/cdn/13.4.1/img/item/$id.png")
        else itemImageUrls.add("")
    }
    return itemImageUrls
}

fun getSummonerSpell(vararg summonerSpells: Int): List<String> {
    val summonerSpellImageUrls = ArrayList<String>()
    summonerSpells.forEach { id ->
        summonerSpellImageUrls.add(
            getSummonerSpellImageUrl(
                getSummonerSpellName(id)
            )
        )
    }
    return summonerSpellImageUrls
}

fun getSummonerSpellName(spellId: Int) =
    when (spellId) {
        1 -> SummonerSpell.CLEANSE
        3 -> SummonerSpell.EXHAUST
        4 -> SummonerSpell.FLASH
        6 -> SummonerSpell.GHOST
        7 -> SummonerSpell.HEAL
        11 -> SummonerSpell.SMITE
        12 -> SummonerSpell.TELEPORT
        13 -> SummonerSpell.CLARITY
        14 -> SummonerSpell.IGNITE
        21 -> SummonerSpell.BARRIER
        30 -> SummonerSpell.PORO_RECALL
        31 -> SummonerSpell.PORO_THROW
        32 -> SummonerSpell.SNOWBALL
        39 -> SummonerSpell.SNOWBALL_URF
        else -> SummonerSpell.DISABLED_1
    }

fun getRunes(vararg runeIds: Int): List<String>{
    val runeImageUrls = ArrayList<String>()
    runeIds.forEach { id ->
        runeImageUrls.add(
            getRuneImageUrl(
                getRuneEndpoint(id)
            )
        )
    }
    return runeImageUrls
}

fun getRuneEndpoint(runeId: Int) =
    when(runeId){
        8100 -> Rune.DOMINATION
        8300 -> Rune.INSPIRATION
        8000 -> Rune.PRECISION
        8400 -> Rune.RESOLVE
        8200 -> Rune.SORCERY
        8112 -> Rune.ELECTROCUTE
        8124 -> Rune.PREDATOR
        8128 -> Rune.DARK_HARVEST
        9923 -> Rune.HAIL_OF_BLADES
        8351 -> Rune.GLACIAL_AUGMENT
        8360 -> Rune.SPELL_BOOK
        8369 -> Rune.INSPIRATION // missing something
        8005 -> Rune.PRESS_THE_ATTACK
        8008 -> Rune.LETHAL_TEMPO
        8021 -> Rune.FLEET_FOOTWORK
        8010 -> Rune.CONQUEROR
        8437 -> Rune.GRASP
        8439 -> Rune.AFTERSHOCK
        8465 -> Rune.GUARDIAN
        8214 -> Rune.SUMMON_AERY
        8229 -> Rune.COMET
        8230 -> Rune.PHASE_RUSH
        else -> ""
    }

fun getRuneImageUrl(endpoint: String) =
    "https://ddragon.canisback.com/img/$endpoint"

fun getSummonerSpellImageUrl(name: String) =
    "http://ddragon.leagueoflegends.com/cdn/8.11.1/img/spell/Summoner$name.png"


