package com.wiryadev.fragmentresultlistener

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.compose.content
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wiryadev.fragmentresultlistener.model.ConfirmationDialogData

class NestedBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        const val REQUEST_KEY = "nested_bottom_sheet_fragment_request_key"
        const val RESULT_KEY = "nested_bottom_sheet_fragment_result_key"
        const val ON_DISMISSED = "nested_bottom_sheet_fragment_on_dismissed"

        fun newInstance() = NestedBottomSheetFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = content {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "Nested Sheet",
                style = MaterialTheme.typography.displayMedium,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = ::onShowConfirmation,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Show Confirmation Bottom Sheet")
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = ::dismiss,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Dismiss")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragmentResultListener(ConfirmationBottomSheetFragment.REQUEST_KEY) { bundle ->
            when (bundle.getString(ConfirmationBottomSheetFragment.RESULT_KEY)) {
                ConfirmationBottomSheetFragment.POSITIVE_BUTTON_CLICK -> {
                    context?.showToast("Simple Positive Clicked Received by Nested Sheet")
                }

                ConfirmationBottomSheetFragment.NEGATIVE_BUTTON_CLICK -> {
                    context?.showToast("Simple Negative Clicked Received by Nested Sheet")
                }
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        setFragmentResult(REQUEST_KEY, bundleOf(RESULT_KEY to ON_DISMISSED))
        super.onDismiss(dialog)
    }

    private fun onShowConfirmation() {
        ConfirmationBottomSheetFragment
            .newInstance(ConfirmationDialogData(title = R.string.nested_confirmation_dialog))
            .show(childFragmentManager, null)
    }
}