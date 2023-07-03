package com.example.wedding_book_keeper.data.remote.di

import com.example.wedding_book_keeper.data.remote.api.AuthService
import com.example.wedding_book_keeper.data.remote.datasource.auth.AuthRemoteDataSource
import com.example.wedding_book_keeper.data.remote.datasource.auth.AuthRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(authService: AuthService): AuthRemoteDataSource {
        return AuthRemoteDataSourceImpl(authService)
    }
}
