package com.wiryadev.fragmentresultlistener

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wiryadev.fragmentresultlistener.model.ConfirmationDialogData

class MultiConfirmActivity : AppCompatActivity() {

    companion object {
        private const val STILL_ALIVE = "multi_confirm_activity_still_alive"
        private const val DEAD_INSIDE = "multi_confirm_activity_dead_inside"
        private const val LIKE_PET = "multi_confirm_activity_like_pet"
        private const val HATE_PET = "multi_confirm_activity_hate_pet"
        private const val STIRRED_PORRIDGE = "multi_confirm_activity_stirred_porridge"
        private const val UNSTIRRED_PORRIDGE = "multi_confirm_activity_unstirred_porridge"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(onClick = ::onConfirmLife) {
                    Text("Still alive?")
                }
                Button(onClick = ::onConfirmPet) {
                    Text("Like pet?")
                }
                Button(onClick = ::onPickPorridge) {
                    Text("How you enjoy Porridge?")
                }
            }
        }

        initFragmentResultListener(ConfirmationBottomSheetFragment.REQUEST_KEY) { bundle ->
            when (bundle.getString(ConfirmationBottomSheetFragment.RESULT_KEY)) {
                STILL_ALIVE -> showToast(getString(R.string.still_alive))
                DEAD_INSIDE -> showToast(getString(R.string.dead_inside))
                LIKE_PET -> showToast(getString(R.string.like_pet))
                HATE_PET -> showToast(getString(R.string.hate_pet))
                STIRRED_PORRIDGE -> showToast(getString(R.string.stirred))
                UNSTIRRED_PORRIDGE -> showToast(getString(R.string.not_stirred))
            }
        }
    }

    private fun onConfirmLife() {
        ConfirmationBottomSheetFragment
            .newInstance(
                ConfirmationDialogData(
                    title = R.string.choose_alive_status,
                    positiveActionResultKey = STILL_ALIVE,
                    negativeActionResultKey = DEAD_INSIDE,
                )
            )
            .show(supportFragmentManager, null)
    }

    private fun onConfirmPet() {
        ConfirmationBottomSheetFragment
            .newInstance(
                ConfirmationDialogData(
                    title = R.string.do_you_like_pet,
                    positiveActionResultKey = LIKE_PET,
                    negativeActionResultKey = HATE_PET,
                )
            )
            .show(supportFragmentManager, null)
    }

    private fun onPickPorridge() {
        ConfirmationBottomSheetFragment
            .newInstance(
                ConfirmationDialogData(
                    title = R.string.how_enjoy_porridge,
                    positiveButtonText = R.string.stirred,
                    negativeButtonText = R.string.not_stirred,
                    positiveActionResultKey = STIRRED_PORRIDGE,
                    negativeActionResultKey = UNSTIRRED_PORRIDGE,
                )
            )
            .show(supportFragmentManager, null)
    }
}