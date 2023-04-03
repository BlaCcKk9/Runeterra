package com.lms.worldoflol.common

sealed class BaseUrl(val url: String) {
    object KOREA : BaseUrl("https://kr.api.riotgames.com/lol/")
    object BRAZIL : BaseUrl("https://br1.api.riotgames.com/lol/")
    object EUNE : BaseUrl("https://eun1.api.riotgames.com/lol/")
    object EUW : BaseUrl("https://euw1.api.riotgames.com/lol/")
    object LAN : BaseUrl("https://la1.api.riotgames.com/lol/")
    object LAS : BaseUrl("https://la2.api.riotgames.com/lol/")
    object NORTH_AMERICA : BaseUrl("https://na1.api.riotgames.com/lol/")
    object OCEANIA : BaseUrl("https://oc1.api.riotgames.com/lol/")
    object RUSSIA : BaseUrl("https://ru.api.riotgames.com/lol/")
    object TURKEY : BaseUrl("https://tr1.api.riotgames.com/lol/")
    object JAPAN : BaseUrl("https://jp1.api.riotgames.com/lol/")
}



