package com.wiryadev.fragmentresultlistener

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wiryadev.fragmentresultlistener.databinding.ActivityMainBinding
import com.wiryadev.fragmentresultlistener.model.ConfirmationDialogData

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
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
                ConfirmationBottomSheetFragment.POSITIVE_BUTTON_CLICK -> {
                    showToast("Positive Clicked")
                }

                ConfirmationBottomSheetFragment.NEGATIVE_BUTTON_CLICK -> {
                    showToast("Negative Clicked")
                }
            }
        }

        with(binding) {
            btnConfirmationModal.setOnClickListener {
                ConfirmationBottomSheetFragment
                    .newInstance(ConfirmationDialogData(title = R.string.simple_dialog))
                    .show(supportFragmentManager, null)
            }
            btnMultiConfirmationPage.setOnClickListener {
                startActivity(
                    Intent(this@MainActivity, MultiConfirmActivity::class.java)
                )
            }
        }
    }
}