package com.example.wedding_book_keeper.presentation.view.mypage

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.wedding_book_keeper.WeddingBookKeeperApplication
import com.example.wedding_book_keeper.data.remote.WeddingBookKeeperClient
import com.example.wedding_book_keeper.data.remote.request.VerificationCodeRequest
import com.example.wedding_book_keeper.data.remote.response.VerifyManagerVerificationCodeResponse
import com.example.wedding_book_keeper.databinding.FragmentChangeRoleBinding
import com.example.wedding_book_keeper.presentation.view.donation.guest.GuestMainActivity
import com.example.wedding_book_keeper.presentation.view.donation.manager.ManagerMainActivity
import com.example.wedding_book_keeper.presentation.view.wedding.partner.PartnerConnectActivity
import com.example.wedding_book_keeper.presentation.view.wedding.partner.VerificationCodeDialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangeRoleFragment(
    val onClick: (Long) -> Unit,
) : BottomSheetDialogFragment() {
    lateinit var binding: FragmentChangeRoleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentChangeRoleBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtRoleGuest.setOnClickListener {
            val intent = Intent(requireContext(), GuestMainActivity::class.java)
            startActivity(intent)
        }

        binding.txtRoleCouple.setOnClickListener{
            val intent = Intent(requireContext(), PartnerConnectActivity::class.java)
            startActivity(intent)
        }

        binding.txtRoleAdmin.setOnClickListener {

            val dialogFragment = VerificationCodeDialogFragment.newInstance()
            dialogFragment.setOnVerificationCodeEnteredListener(object :
                VerificationCodeDialogFragment.OnVerificationCodeEnteredListener {
                override fun onVerificationCodeEntered(verificationCode: String) {
                    WeddingBookKeeperClient.authService.verifyManagerVerificationCode(
                        VerificationCodeRequest(verificationCode)
                    ).enqueue(object : Callback<VerifyManagerVerificationCodeResponse> {
                        override fun onResponse(call: Call<VerifyManagerVerificationCodeResponse>, response: Response<VerifyManagerVerificationCodeResponse>) {
                            if (response.isSuccessful) {
                                Toast.makeText(view.getContext(), "관리자 인증에 성공했습니다.", Toast.LENGTH_SHORT).show();
                                WeddingBookKeeperApplication.prefs.weddingId= response.body()?.weddingId!!
                                val intent = Intent(requireContext(), ManagerMainActivity::class.java)
                                startActivity(intent)
                                return;
                            }
                            Toast.makeText(view.getContext(), "관리자 인증에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }

                        override fun onFailure(call: Call<VerifyManagerVerificationCodeResponse>, t: Throwable) {
                            Toast.makeText(view.getContext(), "관리자 인증에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    })
                }
            })
            dialogFragment.show(getParentFragmentManager(), VerificationCodeDialogFragment.TAG)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        const val TAG = "MoreActionModal"

        fun newInstance(
            onClick: (Long) -> Unit,
        ): ChangeRoleFragment {
            val modal = ChangeRoleFragment(onClick)
            return modal
        }
    }
}
