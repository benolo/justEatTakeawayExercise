package com.justeattakeawayexercise.utils.providers

import com.google.gson.Gson
import com.justeattakeawayexercise.api.model.RestaurantsInfoRaw
import java.io.InputStream
import java.lang.Exception

class AssetsParser(private val resourceProvider: ResourceProvider) {

    fun read(fileName: String): RestaurantsInfoRaw{
        return try {
            val inputStream = resourceProvider.getAssets().open(fileName)
            parse(inputStream , RestaurantsInfoRaw::class.java)
        }catch (e: Exception) {
            RestaurantsInfoRaw(listOf())
        }
    }

    private fun <T> parse(inputStream: InputStream, type: Class<T>): T {
        val json = getString(inputStream)
        return Gson().fromJson(json, type)
    }

    private fun getString(inputStream: InputStream): String {
        val size = inputStream.available()
        val buffer = ByteArray(size)

        inputStream.read(buffer)
        inputStream.close()

        return String(buffer, Charsets.UTF_8)
    }
}