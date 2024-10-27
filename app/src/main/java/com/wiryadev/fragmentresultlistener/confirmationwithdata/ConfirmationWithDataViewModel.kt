package com.wiryadev.fragmentresultlistener.confirmationwithdata

import androidx.lifecycle.ViewModel
import com.wiryadev.fragmentresultlistener.model.Chipset
import com.wiryadev.fragmentresultlistener.model.getChipsets
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ConfirmationWithDataViewModel : ViewModel() {

    private val _chipsets = MutableStateFlow(getChipsets())
    val chipsets: StateFlow<List<Chipset>> = _chipsets.asStateFlow()

    fun removeChipset(chipset: Chipset) {
        _chipsets.update {
            it.toMutableList()
                .apply {
                    remove(chipset)
                }
        }
    }

    fun shuffles() {
        _chipsets.update { it.shuffled() }
    }
}