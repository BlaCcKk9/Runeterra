package com.lms.worldoflol.common

const val API_KEY = "RGAPI-be3814b6-d422-4387-9646-b84ce2e8add2"

object Tier {
    const val IRON = "IRON"
    const val BRONZE = "BRONZE"
    const val SILVER = "SILVER"
    const val GOLD = "GOLD"
    const val PLATINUM = "PLATINUM"
    const val DIAMOND = "DIAMOND"
    const val GRANDMASTER = "GRANDMASTER"
    const val MASTER = "MASTER"
    const val CHALLLANGER = "CHALLLANGER"
}

object Region {
    const val EUNE = "Europe Nordic & East"
    const val KOREA = "Korea"
    const val BRAZIL = "Brazil"
    const val EUW = "Europe West"
    const val LAN = "LAN"
    const val LAS = "LAS"
    const val NORTH_AMERICA = "North America"
    const val OCEANIA = "Oceania"
    const val RUSSIA = "Russia"
    const val TURKEY = "Turkey"
    const val JAPAN = "Japan"
}

object RegionRouting {
    const val AMERICAS = "americas"
    const val ASIA = "asia"
    const val EUROPE = "europe"
    const val SEA = "sea"
}

object Role {
    const val ASSASSIN = "Assassin"
    const val FIGHTER = "Fighter"
    const val MAGE = "Mage"
    const val MARKSMAN = "Marksman"
    const val SUPPORT = "Support"
    const val TANK = "Tank"
    const val FILL = ""
}

object Lane{
    const val TOP = "TOP"
    const val JUNGLE = "JUNGLE"
    const val MID = "MIDDLE"
    const val BOTTOM = "BOTTOM"
}

object LaneRole{
    const val CARRY = "CARRY"
    const val SUPPORT = "SUPPORT"
}


object QueueType {
    const val SOLO = "RANKED_SOLO_5x5"
    const val FLEX = "RANKED_FLEX_SR"
}

object Queue {
    const val SOLO = "Ranked Solo/Duo"
    const val FLEX = "Ranked Flex"
    const val NORMAL = "Normal"
    const val ARAM = "ARAM"
}

object GameCondition{
    const val VICTORY = "VICTORY"
    const val DEFEAT = "DEFEAT"
    const val REMAKE = "REMAKE"
}

object SummonerSpell{
    const val BARRIER = "Barrier"
    const val CLEANSE = "Boost"
    const val DISABLED_1 = "DarkStarChampSelect1"
    const val IGNITE = "Dot"
    const val EXHAUST = "Exhaust"
    const val FLASH = "Flash"
    const val GHOST = "Haste"
    const val HEAL = "Heal"
    const val CLARITY = "Mana"
    const val PORO_RECALL = "PoroRecall"
    const val PORO_THROW = "PoroThrow"
    const val SMITE = "Smite"
    const val SNOWBALL_URF = "SnowURFSnowball_Mark"
    const val SNOWBALL = "Snowball"
    const val TELEPORT = "Teleport"
}

object Rune{
    const val DOMINATION = "perk-images/Styles/7200_Domination.png"
    const val ELECTROCUTE = "perk-images/Styles/Domination/Electrocute/Electrocute.png"
    const val PREDATOR = "perk-images/Styles/Domination/Predator/Predator.png"
    const val DARK_HARVEST = "perk-images/Styles/Domination/DarkHarvest/DarkHarvest.png"
    const val HAIL_OF_BLADES = "perk-images/Styles/Domination/HailOfBlades/HailOfBlades.png"
    const val INSPIRATION = "perk-images/Styles/7203_Whimsy.png"
    const val GLACIAL_AUGMENT = "perk-images/Styles/Inspiration/GlacialAugment/GlacialAugment.png"
    const val SPELL_BOOK = "perk-images/Styles/Inspiration/UnsealedSpellbook/UnsealedSpellbook.png"
    const val MASTER_KEY = "perk-images/Styles/Inspiration/MasterKey/MasterKey.png"
    const val PRECISION = "perk-images/Styles/7201_Precision.png"
    const val PRESS_THE_ATTACK = "perk-images/Styles/Precision/PressTheAttack/PressTheAttack.png"
    const val LETHAL_TEMPO = "perk-images/Styles/Precision/LethalTempo/LethalTempoTemp.png"
    const val FLEET_FOOTWORK = "perk-images/Styles/Precision/FleetFootwork/FleetFootwork.png"
    const val CONQUEROR = "perk-images/Styles/Precision/Conqueror/Conqueror.png"
    const val RESOLVE = "perk-images/Styles/7204_Resolve.png"
    const val GRASP = "perk-images/Styles/Resolve/GraspOfTheUndying/GraspOfTheUndying.png"
    const val AFTERSHOCK = "perk-images/Styles/Resolve/VeteranAftershock/VeteranAftershock.png"
    const val GUARDIAN = "perk-images/Styles/Resolve/Guardian/Guardian.png"
    const val SORCERY = "perk-images/Styles/7202_Sorcery.png"
    const val SUMMON_AERY = "perk-images/Styles/Sorcery/SummonAery/SummonAery.png"
    const val COMET = "perk-images/Styles/Sorcery/ArcaneComet/ArcaneComet.png"
    const val PHASE_RUSH = "perk-images/Styles/Sorcery/PhaseRush/PhaseRush.png"
}

object Param {
    const val SUMMONER_ID = "summoner_id"
    const val REGION = "region"
}
