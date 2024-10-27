package com.wiryadev.fragmentresultlistener

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.compose.content
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        data = arguments?.getParcelable(DATA)
        return content {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    data?.let { stringResource(id = it.title) } ?: "",
                    style = MaterialTheme.typography.titleMedium,
                )
                Row(
                    modifier = Modifier.padding(top = 24.dp)
                ) {
                    OutlinedButton(
                        onClick = ::onNegativeClick,
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(
                            data?.negativeButtonText?.let(::getString) ?: "Negative"
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = ::onPositiveClick,
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(
                            data?.positiveButtonText?.let(::getString) ?: "Positive"
                        )
                    }
                }
            }
        }
    }

    private fun onPositiveClick() {
        setFragmentResult(
            REQUEST_KEY,
            bundleOf(
                RESULT_KEY to data?.positiveActionResultKey.orEmpty()
                    .ifEmpty { POSITIVE_BUTTON_CLICK },
            )
        )
        dismiss()
    }

    private fun onNegativeClick() {
        setFragmentResult(
            REQUEST_KEY,
            bundleOf(
                RESULT_KEY to data?.negativeActionResultKey.orEmpty()
                    .ifEmpty { NEGATIVE_BUTTON_CLICK },
            )
        )
        dismiss()
    }

}