package com.justeattakeawayexercise.di

import android.app.Application
import com.justeattakeawayexercise.ApplicationClass
import com.justeattakeawayexercise.BuildConfig
import com.justeattakeawayexercise.data.MyRepositoryImpl
import com.justeattakeawayexercise.data.source.remote.Mapper
import com.justeattakeawayexercise.data.source.remote.MyRemoteDataSource
import com.justeattakeawayexercise.provider.ResourceProvider
import com.justeattakeawayexercise.ui.restaurants.viewmodel.RestaurantActivityViewModel
import com.justeattakeawayexercise.ui.restaurants.viewmodel.RestaurantFragmentViewModel
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    factory { MyRemoteDataSource(get(), Mapper()) }
    factory { MyRepositoryImpl(get()) }
}

val viewModelModule = module {
    factory { RestaurantActivityViewModel(get()) }
    factory { RestaurantFragmentViewModel(get()) }
}

val resourceModule = module {
    single { ApplicationClass() }
    single { ResourceProvider(get()) }
}


fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient() = OkHttpClient().newBuilder().build()
