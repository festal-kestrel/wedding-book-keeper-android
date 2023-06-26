package com.example.wedding_book_keeper.presentation.view.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.databinding.FragmentChangeRoleBinding
import com.example.wedding_book_keeper.presentation.config.BaseFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChangeRoleFragment (
val onClick: (Long) -> Unit,
) : BottomSheetDialogFragment(){
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.


    }

    override fun onDestroyView() {
        super.onDestroyView()
    }



    companion object {
        const val TAG = "MoreActionModal"

        fun newInstance(
            onClick: (Long) -> Unit,
        ): ChangeRoleFragment {
            val modal = ChangeRoleFragment( onClick)
//            modal.setStyle(DialogFragment.STYLE_NORMAL, R.style.RoundCornerBottomSheetDialogTheme)
            return modal
        }
    }
}
