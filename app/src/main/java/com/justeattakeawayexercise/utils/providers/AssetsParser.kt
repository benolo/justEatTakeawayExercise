package com.justeattakeawayexercise.utils.providers

import com.google.gson.Gson
import com.justeattakeawayexercise.api.model.RestaurantsInfoRaw
import java.io.InputStream
import java.lang.Exception

/**
 * Utility to read and parse the mocked Json data locally stored
 *
 * @param resourceProvider A utility that expose the resources to use.
 * ######
 * See [ResourceProvider]
 */
class AssetsParser(private val resourceProvider: ResourceProvider) {

    fun read(fileName: String): RestaurantsInfoRaw{
        return try {
            val inputStream = resourceProvider.getAssets().open(fileName)
            parse(inputStream)
        }catch (e: Exception) {
            RestaurantsInfoRaw(listOf())
        }
    }

    private fun parse(inputStream: InputStream): RestaurantsInfoRaw {
        val json = getString(inputStream)
        return Gson().fromJson(json, RestaurantsInfoRaw::class.java)
    }

    private fun getString(inputStream: InputStream): String {
        val size = inputStream.available()
        val buffer = ByteArray(size)

        inputStream.read(buffer)
        inputStream.close()

        return String(buffer, Charsets.UTF_8)
    }
}