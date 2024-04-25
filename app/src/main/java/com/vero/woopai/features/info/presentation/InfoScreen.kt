@file:OptIn(ExperimentalMaterial3Api::class)

package com.vero.woopai.features.info.presentation

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.vero.woopai.R
import com.vero.woopai.features.info.presentation.component.InfoComponent
import com.vero.woopai.features.info.presentation.component.PlanComponent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun InfoScreen(
    viewModel: InfoViewModel = hiltViewModel(),
    openHomeScreen: () -> Unit,
    navigateBack: () -> Unit,
) {

    val context = LocalContext.current
    val state = viewModel.state

    LaunchedEffect(true) {
        viewModel.uiEvent.collectLatest { event ->
           when (event) {
               InfoUiEvent.OpenHomeScreen -> openHomeScreen()
               is InfoUiEvent.ShowError -> {
                   Toast.makeText(context, event.error, Toast.LENGTH_LONG).show()
               }
           }
        }
    }

    Column {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            navigationIcon = {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "History icon"
                    )
                }
            },
        )
        AnimatedContent(
            targetState = state.currentScreenState,
            label = "screen_animation"
        ) { screen ->
            when (screen) {
                ScreenState.Plan -> PlanComponent()
                else -> InfoComponent()
            }
        }
    }

}