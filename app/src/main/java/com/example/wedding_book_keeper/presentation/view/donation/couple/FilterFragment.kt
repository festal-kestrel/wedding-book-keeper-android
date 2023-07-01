package com.example.wedding_book_keeper.presentation.view.donation.couple

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wedding_book_keeper.databinding.FragmentFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterFragment(val onFilterApplied: (String?) -> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtTotalSide.setOnClickListener {
            onFilterApplied(null)
            dismiss()
        }

        binding.txtGroomSide.setOnClickListener {
            onFilterApplied("신랑측")
            Log.d("hong","error")
            dismiss()
        }

        binding.txtBridalSide.setOnClickListener {
            onFilterApplied("신부측")
            dismiss()
        }
    }

    companion object {
        const val TAG = "MoreActionModal"

        fun newInstance(onFilterApplied: (String?) -> Unit): FilterFragment {
            return FilterFragment(onFilterApplied)
        }
    }
}
