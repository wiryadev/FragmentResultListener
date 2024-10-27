package com.wiryadev.fragmentresultlistener

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wiryadev.fragmentresultlistener.model.ConfirmationDialogData

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val content = remember {
                mutableStateListOf(*SampleItemContent.entries.toTypedArray())
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 24.dp,
                        start = 20.dp,
                        end = 20.dp,
                    ),
            ) {
                items(content) { item ->
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = { onContentClick(item) },
                    ) {
                        Text(
                            text = item.name.split("_").joinToString(" "),
                            modifier = Modifier
                                .padding(12.dp)
                        )
                    }
                }
            }
        }

        initFragmentResultListener(
            ConfirmationBottomSheetFragment.REQUEST_KEY,
        ) { bundle ->
            when (bundle.getString(ConfirmationBottomSheetFragment.RESULT_KEY)) {
                ConfirmationBottomSheetFragment.POSITIVE_BUTTON_CLICK -> {
                    showToast("Positive Clicked")
                }

                ConfirmationBottomSheetFragment.NEGATIVE_BUTTON_CLICK -> {
                    showToast("Negative Clicked")
                }
            }
        }

    }

    private fun onContentClick(content: SampleItemContent) {
        when (content) {
            SampleItemContent.CORRECT_SIMPLE_CONFIRMATION -> {
                ConfirmationBottomSheetFragment
                    .newInstance(ConfirmationDialogData(title = R.string.simple_dialog))
                    .show(supportFragmentManager, null)
            }

            SampleItemContent.CORRECT_MULTI_CONFIRMATION -> {
                startActivity(
                    Intent(this@MainActivity, MultiConfirmActivity::class.java)
                )
            }
        }
    }
}