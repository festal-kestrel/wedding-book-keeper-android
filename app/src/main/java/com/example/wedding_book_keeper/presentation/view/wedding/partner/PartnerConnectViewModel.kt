package com.example.wedding_book_keeper.presentation.view.wedding.partner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wedding_book_keeper.domain.repository.AuthRepository
import com.example.wedding_book_keeper.presentation.view.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PartnerConnectViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : BaseViewModel() {

    private val _verificationCode = MutableLiveData<String>()
    val verificationCode: LiveData<String> get() = _verificationCode

    init {
        fetchVerificationCode()
    }

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State>
        get() = _state.asStateFlow()

    private fun fetchVerificationCode() {
        viewModelScope.launch {
            authRepository.getPartnerVerificationCode()
                .onSuccess {
                    verificationCode ->
                    _state.value = state.value.copy(
                        verificationCode = verificationCode.verificationCode
                    )
//                    _verificationCode.value = it.verificationCode
                }
                .onFailure {
                    _verificationCode.value = "error"
                }
        }
    }

    data class State(
        val verificationCode: String = ""
    ) {

    }
}
