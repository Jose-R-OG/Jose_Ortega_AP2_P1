package com.example.jose_ortega_ap2_p1.di

import com.example.jose_ortega_ap2_p1.data.Amonestacion.repository.AmonestacionRepositoryImpl
import com.example.jose_ortega_ap2_p1.domain.repository.AmonestacionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAmonestacionRepository(
        amonestacionRepositoryImpl: AmonestacionRepositoryImpl
    ): AmonestacionRepository
}