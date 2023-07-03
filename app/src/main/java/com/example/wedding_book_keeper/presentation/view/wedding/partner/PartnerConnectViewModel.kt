package com.example.wedding_book_keeper.presentation.view.wedding.partner

import androidx.lifecycle.viewModelScope
import com.example.wedding_book_keeper.domain.usecase.GetPartnerVerificationCodeUseCase
import com.example.wedding_book_keeper.presentation.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

val String.Companion.Empty
    get() = ""

@HiltViewModel
class PartnerConnectViewModel @Inject constructor(
    private val getPartnerVerificationCodeUseCase: GetPartnerVerificationCodeUseCase
) : BaseViewModel() {

    private val _verificationCode: MutableStateFlow<String> = MutableStateFlow(String.Empty)
    val verificationCode: StateFlow<String>
        get() = _verificationCode

    init {
        fetchVerificationCode()
    }

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State>
        get() = _state.asStateFlow()

    private fun fetchVerificationCode() {

        viewModelScope.launch {
            getPartnerVerificationCodeUseCase().collect { response ->
                response.onSuccess {
                    _verificationCode.value = it.verificationCode
                }.onFailure { throwable ->
                    sendErrorMessage(throwable.message)
                }
            }
        }
    }

    data class State(
        val verificationCode: String = ""
    ) {

    }
}
