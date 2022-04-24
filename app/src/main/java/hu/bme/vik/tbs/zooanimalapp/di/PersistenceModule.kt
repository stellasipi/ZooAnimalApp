package hu.bme.vik.tbs.zooanimalapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.vik.tbs.zooanimalapp.R
import hu.bme.vik.tbs.zooanimalapp.persistence.AnimalDao
import hu.bme.vik.tbs.zooanimalapp.persistence.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                application.getString(R.string.database)
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAnimalDao(appDatabase: AppDatabase): AnimalDao {
        return appDatabase.animalDao()
    }
}