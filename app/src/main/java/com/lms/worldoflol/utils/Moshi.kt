package com.lms.worldoflol.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class Moshi<T>(javaClass: Class<T>){

    var moshi: Moshi = Moshi.Builder().build()
    var jsonAdapter: JsonAdapter<T> = moshi.adapter(javaClass).lenient()

    fun toJson(objectClass: T) : String =
        jsonAdapter.toJson(objectClass)


    fun fromJson(jsonString: String): T =
        jsonAdapter.fromJson(jsonString)!!

}