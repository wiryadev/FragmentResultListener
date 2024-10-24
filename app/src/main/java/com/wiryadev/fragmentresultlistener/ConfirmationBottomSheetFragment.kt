package com.wiryadev.fragmentresultlistener

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wiryadev.fragmentresultlistener.databinding.FragmentConfirmationBottomSheetBinding
import com.wiryadev.fragmentresultlistener.model.ConfirmationDialogData

class ConfirmationBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        const val REQUEST_KEY = "confirmation_bottom_sheet_fragment_request_key"
        const val RESULT_KEY = "confirmation_bottom_sheet_fragment_result_key"
        const val POSITIVE_BUTTON_CLICK = "confirmation_bottom_sheet_fragment_positive_button_click"
        const val NEGATIVE_BUTTON_CLICK = "confirmation_bottom_sheet_fragment_negative_button_click"

        private const val DATA = "confirmation_bottom_sheet_fragment_data"

        fun newInstance(data: ConfirmationDialogData) = ConfirmationBottomSheetFragment()
            .apply {
                arguments = bundleOf(DATA to data)
            }
    }

    private var data: ConfirmationDialogData? = null

    private var _binding: FragmentConfirmationBottomSheetBinding? = null
    val binding: FragmentConfirmationBottomSheetBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmationBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<ConfirmationDialogData>(DATA)?.let {
            data = it
        }

        data?.let {
            binding.tvTitle.text = getString(it.title)
            binding.btnPositive.text = it.positiveButtonText?.let(::getString) ?: "Positive"
            binding.btnNegative.text = it.negativeButtonText?.let(::getString) ?: "Negative"
        }

        binding.apply {
            btnPositive.setOnClickListener {
                setFragmentResult(
                    REQUEST_KEY,
                    bundleOf(
                        RESULT_KEY to data?.positiveActionResultKey.orEmpty().ifEmpty { POSITIVE_BUTTON_CLICK },
                    )
                )
                dismiss()
            }
            btnNegative.setOnClickListener {
                setFragmentResult(
                    REQUEST_KEY,
                    bundleOf(
                        RESULT_KEY to data?.negativeActionResultKey.orEmpty().ifEmpty { NEGATIVE_BUTTON_CLICK },
                    )
                )
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}