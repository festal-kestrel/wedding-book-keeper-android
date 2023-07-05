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
import com.example.wedding_book_keeper.databinding.FragmentApprovalDialogBinding

class ApprovalDialogFragment : DialogFragment() {

    private var _binding: FragmentApprovalDialogBinding? = null
    private val binding get() = _binding!!
    private var onConfirmClickListener: OnApprovalListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_approval_dialog, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnConfirm.setOnClickListener{
            onConfirmClickListener?.onApproval()
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            onConfirmClickListener?.onCancel()
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setOnApprovalListener(listener: OnApprovalListener) {
        this.onConfirmClickListener = listener
    }

    interface OnApprovalListener {
        fun onApproval()
        fun onCancel()
    }

    companion object {
        const val TAG = "ApprovalDialogFragment"

        fun newInstance(): ApprovalDialogFragment {
            return ApprovalDialogFragment()
        }
    }
}
