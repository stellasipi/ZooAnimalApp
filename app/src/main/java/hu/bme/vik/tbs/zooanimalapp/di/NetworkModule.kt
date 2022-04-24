package hu.bme.vik.tbs.zooanimalapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.vik.tbs.zooanimalapp.network.AnimalService
import hu.bme.vik.tbs.zooanimalapp.network.RequestInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://zoo-animal-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(/*GsonHelper.getGson()*/))
            .build()
    }

    @Provides
    @Singleton
    fun provideAnimalService(retrofit: Retrofit): AnimalService {
        return retrofit.create(AnimalService::class.java)
    }
}