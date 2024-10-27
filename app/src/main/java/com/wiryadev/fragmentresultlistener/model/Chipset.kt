package com.wiryadev.fragmentresultlistener.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chipset(
    val id: String,
    val label: String,
    val manufacturer: String,
    val socket: String,
) : Parcelable

fun getChipsets() = listOf(
    Chipset(
        id = "z890",
        label = "Z890",
        manufacturer = "Intel",
        socket = "LGA 1851",
    ),
    Chipset(
        id = "x870e",
        label = "X870E",
        manufacturer = "AMD",
        socket = "AM5",
    ),
    Chipset(
        id = "b850",
        label = "B850",
        manufacturer = "AMD",
        socket = "AM5",
    ),
    Chipset(
        id = "z790",
        label = "Z790",
        manufacturer = "Intel",
        socket = "LGA 1700",
    ),
    Chipset(
        id = "b760",
        label = "B760",
        manufacturer = "Intel",
        socket = "LGA 1700",
    ),
    Chipset(
        id = "x570",
        label = "X570",
        manufacturer = "AMD",
        socket = "AM4",
    ),
)
