package com.justeattakeawayexercise.di

import androidx.room.Room
import com.justeattakeawayexercise.BaseApplication
import com.justeattakeawayexercise.BuildConfig
import com.justeattakeawayexercise.api.ApiService
import com.justeattakeawayexercise.data.MockModeProvider
import com.justeattakeawayexercise.data.RepositoryImpl
import com.justeattakeawayexercise.data.database.RestaurantDatabase
import com.justeattakeawayexercise.data.source.local.RestaurantLocalDataSource
import com.justeattakeawayexercise.data.source.mock.MockLocalDataSource
import com.justeattakeawayexercise.data.source.remote.Mapper
import com.justeattakeawayexercise.data.source.remote.RestaurantsRemoteDataSource
import com.justeattakeawayexercise.ui.restaurants.mapper.RestaurantMapper
import com.justeattakeawayexercise.ui.restaurants.viewmodel.RestaurantActivityViewModel
import com.justeattakeawayexercise.ui.restaurants.viewmodel.RestaurantFragmentViewModel
import com.justeattakeawayexercise.utils.providers.AssetsParser
import com.justeattakeawayexercise.utils.providers.ResourceProvider
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    factory { provideApiService(get()) }
}

val dataModule = module {
    single { RestaurantsRemoteDataSource(get(), get()) }
    single { MockLocalDataSource(get(), get(), get()) }
    single { MockModeProvider(get()) }
    factory { Mapper() }
    single { RestaurantLocalDataSource(get()) }
    single { RepositoryImpl(get(), get(), get(), get()) }
    factory { RestaurantMapper() }
}

val resourceModule = module {
    single { BaseApplication() }
    single { ResourceProvider(get()) }
    single { AssetsParser(get()) }
}

val roomModule = module {
    single {
        Room.databaseBuilder(androidContext(), RestaurantDatabase::class.java, "restaurant")
            .build()
    }
}

val viewModelModule = module {
    viewModel { RestaurantActivityViewModel() }
    viewModel { RestaurantFragmentViewModel(get(), get(), get()) }
}


private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideOkHttpClient() = OkHttpClient().newBuilder().build()

fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)