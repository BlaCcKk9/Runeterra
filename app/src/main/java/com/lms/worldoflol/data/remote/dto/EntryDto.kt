package com.lms.worldoflol.data.remote.dto

import com.lms.worldoflol.R
import com.lms.worldoflol.common.Tier
import com.lms.worldoflol.domain.model.remote.Entry
import com.lms.worldoflol.ui.theme.bronze_color_gradiente
import com.lms.worldoflol.ui.theme.challenger_color_gradiente
import com.lms.worldoflol.ui.theme.diamond_color_gradiente
import com.lms.worldoflol.ui.theme.gold_color_gradiente
import com.lms.worldoflol.ui.theme.grandmaster_color_gradiente
import com.lms.worldoflol.ui.theme.iron_color_gradiente
import com.lms.worldoflol.ui.theme.master_color_gradiente
import com.lms.worldoflol.ui.theme.platinum_color_gradiente
import com.lms.worldoflol.ui.theme.silver_color_gradiente

data class EntryDto(
    val freshBlood: Boolean,
    val hotStreak: Boolean,
    val inactive: Boolean,
    val leagueId: String,
    val leaguePoints: Int,
    val losses: Int,
    val queueType: String,
    val rank: String,
    val summonerId: String,
    val summonerName: String,
    val tier: String,
    val veteran: Boolean,
    val wins: Int
)

fun EntryDto.toEntry() = Entry(
    leaguePoints = leaguePoints.toString(),
    losses = losses.toString(),
    queueType = queueType,
    rank = rank,
    tier = tier,
    wins = wins.toString(),
    winRate = getWinRatePercent(wins, losses),
    tierIcon = getTierIcon(tier),
    tierTexColor = getTierTextColor(tier)
)


fun getTierIcon(tier: String) = when (tier){
    Tier.IRON -> R.drawable.ic_tier_iron
    Tier.BRONZE -> R.drawable.ic_tier_bronze
    Tier.SILVER -> R.drawable.ic_tier_silver
    Tier.GOLD -> R.drawable.ic_tier_gold
    Tier.PLATINUM -> R.drawable.ic_tier_platinium
    Tier.DIAMOND -> R.drawable.ic_tier_diamond
    Tier.GRANDMASTER -> R.drawable.ic_tier_grandmaster
    Tier.MASTER -> R.drawable.ic_tier_master
    Tier.CHALLLANGER -> R.drawable.ic_tier_challanger
    else -> 0
}

fun getTierTextColor(tier: String) = when (tier){
    Tier.IRON -> iron_color_gradiente
    Tier.BRONZE -> bronze_color_gradiente
    Tier.SILVER -> silver_color_gradiente
    Tier.GOLD -> gold_color_gradiente
    Tier.PLATINUM -> platinum_color_gradiente
    Tier.DIAMOND -> diamond_color_gradiente
    Tier.GRANDMASTER -> grandmaster_color_gradiente
    Tier.MASTER -> master_color_gradiente
    Tier.CHALLLANGER -> challenger_color_gradiente
    else -> gold_color_gradiente
}

fun getWinRatePercent(wins: Int, losses: Int): String{
    var sum = wins + losses
    var percent = (wins.toDouble() / sum) * 100
    return "(${percent.toInt()}%)"
}