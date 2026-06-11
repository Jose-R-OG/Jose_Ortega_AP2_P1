package com.example.jose_ortega_ap2_p1.di

import android.content.Context
import androidx.room.Room
import com.example.jose_ortega_ap2_p1.data.Amonestacion.local.AmonestacionDao
import com.example.jose_ortega_ap2_p1.data.database.ParcialDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideRegistroDb(@ApplicationContext context: Context): ParcialDatabase {
        return Room.databaseBuilder(
            context,
            ParcialDatabase::class.java,
            "parcial_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideBorrameDao(database: ParcialDatabase): AmonestacionDao {
        return database.AmonestacionDao()
    }
}