package com.lms.worldoflol.common

fun getBaseUrl(region: String) = when (region) {
    Region.EUNE -> { BaseUrl.EUNE.url }
    Region.KOREA -> { BaseUrl.KOREA.url }
    Region.BRAZIL -> { BaseUrl.BRAZIL.url }
    Region.EUW -> { BaseUrl.EUW.url }
    Region.LAN -> { BaseUrl.LAN.url }
    Region.LAS -> { BaseUrl.LAS.url }
    Region.NORTH_AMERICA -> { BaseUrl.NORTH_AMERICA.url }
    Region.OCEANIA -> { BaseUrl.OCEANIA.url }
    Region.RUSSIA -> { BaseUrl.RUSSIA.url }
    Region.TURKEY -> { BaseUrl.TURKEY.url }
    Region.JAPAN -> { BaseUrl.JAPAN.url }
    else -> { BaseUrl.EUNE.url }
}

fun getSummonerUrl(baseUrl: String, summonerName: String): String{
    return baseUrl + "summoner/v4/summoners/by-name/" + summonerName + "?api_key=" + API_KEY
}

fun getEntriesUrl(baseUrl: String, summonerId: String): String{
    return baseUrl + "league/v4/entries/by-summoner/" + summonerId + "?api_key=" + API_KEY
}