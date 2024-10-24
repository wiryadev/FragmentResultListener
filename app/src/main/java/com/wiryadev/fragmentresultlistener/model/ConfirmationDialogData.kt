package com.wiryadev.fragmentresultlistener.model

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConfirmationDialogData(
    @StringRes val title: Int,
    @StringRes val positiveButtonText: Int? = null,
    @StringRes val negativeButtonText: Int? = null,
    val positiveActionResultKey: String = "",
    val negativeActionResultKey: String = "",
) : Parcelable
