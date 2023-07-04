package com.example.wedding_book_keeper.presentation.view.donation.manager

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.FragmentRejectionDialogBinding

class RejectionDialogFragment : DialogFragment() {

    private var _binding: FragmentRejectionDialogBinding? = null
    private val binding get() = _binding!!
    private var onConfirmClickListener: OnRejectionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rejection_dialog, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnConfirm.setOnClickListener{
            onConfirmClickListener?.onRejection()
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

    fun setOnRejectionListener(listener: OnRejectionListener) {
        this.onConfirmClickListener = listener
    }

    interface OnRejectionListener {
        fun onRejection()
    }

    companion object {
        const val TAG = "RejectionDialogFragment"

        fun newInstance(): RejectionDialogFragment {
            return RejectionDialogFragment()
        }
    }
}
