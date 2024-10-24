package com.wiryadev.fragmentresultlistener

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wiryadev.fragmentresultlistener.databinding.ActivityMultiConfirmBinding
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

    private lateinit var binding: ActivityMultiConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMultiConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportFragmentManager.setFragmentResultListener(
            ConfirmationBottomSheetFragment.REQUEST_KEY,
            this
        ) { _, bundle ->
            when (bundle.getString(ConfirmationBottomSheetFragment.RESULT_KEY)) {
                STILL_ALIVE -> showToast(getString(R.string.still_alive))
                DEAD_INSIDE -> showToast(getString(R.string.dead_inside))
                LIKE_PET -> showToast(getString(R.string.like_pet))
                HATE_PET -> showToast(getString(R.string.hate_pet))
                STIRRED_PORRIDGE -> showToast(getString(R.string.stirred))
                UNSTIRRED_PORRIDGE -> showToast(getString(R.string.not_stirred))
            }
        }

        with(binding) {
            btnConfirmLife.setOnClickListener {
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
            btnConfirmPet.setOnClickListener {
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
            btnPickPorridge.setOnClickListener {
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
    }
}