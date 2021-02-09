package com.justeattakeawayexercise.utils.providers

import android.app.Application
import android.content.res.AssetManager
import android.content.res.Resources

class ResourceProvider(application: Application) {
    private val resources: Resources = application.resources

    fun getAssets(): AssetManager = resources.assets
}