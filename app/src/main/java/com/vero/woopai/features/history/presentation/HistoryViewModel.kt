package com.vero.woopai.features.history.presentation

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vero.woopai.features.history.domain.useCase.GetPlansUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getPlans: GetPlansUseCase
) : ViewModel() {

    var state by mutableStateOf(HistoryState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(plans = getPlans())
        }
    }

}
