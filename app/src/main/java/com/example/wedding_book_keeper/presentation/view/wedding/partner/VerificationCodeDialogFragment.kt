package com.example.wedding_book_keeper.presentation.view.wedding.partner

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.wedding_book_keeper.databinding.FragmentVerificationCodeDialogBinding

class VerificationCodeDialogFragment : DialogFragment() {

    private var _binding: FragmentVerificationCodeDialogBinding? = null
    private val binding get() = _binding!!

    private var onVerificationCodeEnteredListener: OnVerificationCodeEnteredListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentVerificationCodeDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnSubmit.setOnClickListener {
            val verificationCode = binding.editVerificationCode.text.toString().trim()
            onVerificationCodeEnteredListener?.onVerificationCodeEntered(verificationCode)
            Toast.makeText(context, "인증번호 : $verificationCode", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setOnVerificationCodeEnteredListener(listener: OnVerificationCodeEnteredListener) {
        this.onVerificationCodeEnteredListener = listener
    }

    interface OnVerificationCodeEnteredListener {
        fun onVerificationCodeEntered(verificationCode: String)
    }

    companion object {
        const val TAG = "VerificationCodeDialogFragment"

        fun newInstance(): VerificationCodeDialogFragment {
            return VerificationCodeDialogFragment()
        }
    }
}
