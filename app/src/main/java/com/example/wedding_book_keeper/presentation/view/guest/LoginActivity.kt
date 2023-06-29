package com.example.wedding_book_keeper.presentation.view.guest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.ActivityLoginBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.kakao.sdk.auth.model.OAuthToken
//import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
//import com.kakao.sdk.v2.common.BuildConfig
//import kotlinx.coroutines.flow.internal.NoOpContinuation.context
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

//import kotlinx.coroutines.flow.internal.NoOpContinuation.context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.wedding_book_keeper.BuildConfig
import com.example.wedding_book_keeper.presentation.view.donation.guest.GuestMainActivity
import com.example.wedding_book_keeper.presentation.view.guest.api.LoginService
import com.example.wedding_book_keeper.presentation.view.guest.dto.TokenResponse
import com.kakao.sdk.common.KakaoSdk
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var loginService: LoginService

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)

        binding.btnKakao.setOnClickListener {

            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Log.e("LOGIN", "카카오계정으로 로그인 실패", error)
                } else if (token != null) {
                    val intent = Intent(this, GuestMainActivity::class.java)
                    startActivity(intent)
                    finish()
                    sendMemberToken(token.accessToken)
                    Log.i("LOGIN", "카카오계정으로 로그인 성공 ${token.accessToken}")
                }
            }

            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    if (error != null) {
                        Log.e("LOGIN", "카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                    } else if (token != null) {
                        Log.i("LOGIN", "카카오톡으로 로그인 성공 ${token.accessToken}")
                        val intent = Intent(this, GuestMainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }

    private fun sendMemberToken(token: String) {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        loginService = retrofit.create(LoginService::class.java)
        val call = loginService.authorize(token)

        call.enqueue(object : Callback<TokenResponse> {
            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                if (response.isSuccessful) {
                    val result: TokenResponse? = response.body()
                    // TODO: JWT 토큰 로컬에 저장
                    Log.d("LOGIN", "jwtToken============= ${result}")
                } else {
                    Log.d("LOGIN", "${response}")
                }
            }

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                Log.e("LOGIN", "API call failed: ${t.message}")
            }

        })
    }
}

