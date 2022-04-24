package hu.bme.vik.tbs.zooanimalapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import hu.bme.vik.tbs.zooanimalapp.network.AnimalService
import hu.bme.vik.tbs.zooanimalapp.persistence.AnimalDao
import hu.bme.vik.tbs.zooanimalapp.ui.main.MainRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(animalService: AnimalService, animalDao: AnimalDao): MainRepository {
        return MainRepository(animalService, animalDao)
    }
}