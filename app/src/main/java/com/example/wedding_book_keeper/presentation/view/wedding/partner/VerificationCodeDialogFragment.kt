package com.example.wedding_book_keeper.presentation.view.wedding.partner

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.FragmentVerificationCodeDialogBinding

class VerificationCodeDialogFragment : DialogFragment() {

    private var _binding: FragmentVerificationCodeDialogBinding? = null
    private val binding get() = _binding!!
    private var onSubmitClickListener: OnVerificationCodeEnteredListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_verification_code_dialog, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setLayout();
        binding.btnSubmit.setOnClickListener {
            val verificationCode = binding.editVerificationCode.text.toString().trim()
            onSubmitClickListener?.onVerificationCodeEntered(verificationCode)
            Toast.makeText(context, "인증번호 : $verificationCode", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun setLayout() {
        requireNotNull(dialog).apply {
            requireNotNull(window).apply {
                setLayout(
                    (resources.displayMetrics.widthPixels * 0.78).toInt(),
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                setBackgroundDrawableResource(R.drawable.bg_rect_white_r18)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setOnVerificationCodeEnteredListener(listener: OnVerificationCodeEnteredListener) {
        this.onSubmitClickListener = listener
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
