package com.justeattakeawayexercise.provider

import android.app.Application

class ResourceProvider(application: Application) {

    private val resources = application.resources

    fun getString(id: Int) = resources.getString(id)
}