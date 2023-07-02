package com.example.wedding_book_keeper.data.remote.repository

import com.example.wedding_book_keeper.data.remote.datasource.auth.AuthRemoteDataSource
import com.example.wedding_book_keeper.data.remote.mapper.toVerificationCode
import com.example.wedding_book_keeper.domain.entity.wedding.VerificationCode
import com.example.wedding_book_keeper.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authRemoteDatasource: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun getPartnerVerificationCode(): Result<VerificationCode> {
        return authRemoteDatasource.getPartnerVerificationCode().map { it.data!!.toVerificationCode() }
    }
}
