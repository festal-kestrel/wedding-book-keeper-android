package com.example.wedding_book_keeper.data.remote.di

import com.example.wedding_book_keeper.data.remote.api.AuthService
import com.example.wedding_book_keeper.data.remote.datasource.auth.AuthRemoteDataSource
import com.example.wedding_book_keeper.data.remote.repository.AuthRepositoryImpl
import com.example.wedding_book_keeper.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(authService: AuthService, authRemoteDataSource: AuthRemoteDataSource): AuthRepository {
        return AuthRepositoryImpl(authRemoteDataSource)
    }
}
