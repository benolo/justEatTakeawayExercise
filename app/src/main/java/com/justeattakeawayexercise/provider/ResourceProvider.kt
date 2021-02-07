package com.justeattakeawayexercise.provider

import android.app.Application
import androidx.core.content.res.ResourcesCompat

class ResourceProvider(application: Application) {

    private val resources = application.resources

    fun getString(id: Int) = resources.getString(id)

    fun getDrawable(id: Int) = ResourcesCompat.getDrawable(resources, id, null)
}