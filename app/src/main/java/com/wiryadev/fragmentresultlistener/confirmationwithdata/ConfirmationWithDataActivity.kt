package com.wiryadev.fragmentresultlistener.confirmationwithdata

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wiryadev.fragmentresultlistener.ConfirmationBottomSheetFragment
import com.wiryadev.fragmentresultlistener.R
import com.wiryadev.fragmentresultlistener.initFragmentResultListener
import com.wiryadev.fragmentresultlistener.model.Chipset
import com.wiryadev.fragmentresultlistener.model.ConfirmationDialogData
import com.wiryadev.fragmentresultlistener.model.ConfirmationDialogReturnData

class ConfirmationWithDataActivity : AppCompatActivity() {

    companion object {
        const val DELETION_DATA = "confirmation_with_data_deletion_data"
    }

    private val viewModel: ConfirmationWithDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val chipsets by viewModel.chipsets.collectAsStateWithLifecycle()
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
                    .safeContentPadding(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(
                    items = chipsets,
                    key = { it.id },
                ) { item ->
                    Card(
                        modifier = Modifier
                            .animateItem()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(
                                    "Chipset: ${item.label}",
                                    style = MaterialTheme.typography.titleMedium
                                        .copy(fontWeight = FontWeight.SemiBold),
                                )
                                Text("Socket: ${item.socket}")
                                Text("Manufacturer: ${item.manufacturer}")
                            }
                            IconButton(
                                onClick = { showDeleteConfirmation(item) }
                            ) {
                                Icon(Icons.Rounded.Delete, contentDescription = null)
                            }
                        }
                    }
                }
            }
        }

        initFragmentResultListener(ConfirmationBottomSheetFragment.REQUEST_KEY) { bundle ->
            when (bundle.getString(ConfirmationBottomSheetFragment.RESULT_KEY)) {
                ConfirmationBottomSheetFragment.POSITIVE_BUTTON_CLICK -> {
                    viewModel.shuffles()
                }

                ConfirmationBottomSheetFragment.NEGATIVE_BUTTON_CLICK -> {
                    bundle.getParcelable<Chipset>(DELETION_DATA)?.let {
                        viewModel.removeChipset(it)
                    }
                }
            }
        }
    }

    private fun showDeleteConfirmation(item: Chipset) {
        ConfirmationBottomSheetFragment
            .newInstance(
                ConfirmationDialogData(
                    title = R.string.confirm_delete,
                    positiveButtonText = R.string.shuffle,
                    negativeButtonText = R.string.delete,
                    negativeActionReturnData = ConfirmationDialogReturnData(
                        resultKey = DELETION_DATA,
                        data = item,
                    )
                )
            )
            .show(supportFragmentManager, null)
    }
}
