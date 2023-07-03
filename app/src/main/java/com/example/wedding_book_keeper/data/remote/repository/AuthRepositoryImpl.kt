package com.example.wedding_book_keeper.data.remote.repository

import com.example.wedding_book_keeper.data.remote.datasource.auth.AuthRemoteDataSource
import com.example.wedding_book_keeper.data.remote.mapper.toVerificationCode
import com.example.wedding_book_keeper.domain.entity.wedding.VerificationCode
import com.example.wedding_book_keeper.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(
    private val authRemoteDatasource: AuthRemoteDataSource
) : AuthRepository {
    override fun getPartnerVerificationCode(): Flow<Result<VerificationCode>> {
        return flow {
            authRemoteDatasource.getPartnerVerificationCode()
                .map { it.toVerificationCode() }
        }
    }
}
