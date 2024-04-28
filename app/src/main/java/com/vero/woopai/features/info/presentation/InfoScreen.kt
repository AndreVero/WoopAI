@file:OptIn(ExperimentalMaterial3Api::class)

package com.vero.woopai.features.info.presentation

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.vero.woopai.R
import com.vero.woopai.core.presentation.components.LoadingAnimation
import com.vero.woopai.features.info.presentation.component.InfoComponent
import com.vero.woopai.features.info.presentation.component.PlanSuggestionComponent
import com.vero.woopai.ui.theme.AppBarStyle
import com.vero.woopai.ui.theme.BackgroundColor
import com.vero.woopai.ui.theme.TextColor
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.new_plan),
                    style = AppBarStyle
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = BackgroundColor,
                titleContentColor = TextColor,
                navigationIconContentColor = TextColor,
                actionIconContentColor = TextColor
            ),
            navigationIcon = {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = "History icon"
                    )
                }
            },
        )
        AnimatedContent(
            targetState = state.currentScreenState,
            label = "screen_animation",
            transitionSpec = {
                (fadeIn(animationSpec = tween(220, delayMillis = 90)) +
                        slideInHorizontally(
                            initialOffsetX = { size -> size * 2 },
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = LinearEasing
                            )
                        )
                )
                    .togetherWith(
                        fadeOut(animationSpec = tween(100)) +
                                slideOutHorizontally(
                                    targetOffsetX = { size -> -(size * 2) },
                                    animationSpec = tween(
                                        durationMillis = 300,
                                        easing = LinearEasing
                                    )
                                )
                    )
            }
        ) { screen ->
            when (screen) {
                ScreenState.Plan -> PlanSuggestionComponent(
                    plan = state.plan,
                    makeNewSuggestions = { viewModel.onEvent(InfoEvent.GeneratePlans) },
                    savePlans = { viewModel.onEvent(InfoEvent.SavePlans) }
                )

                ScreenState.Loading -> LoadingAnimation()
                else -> InfoComponent(
                    value = state.text,
                    screenState = state.currentScreenState,
                    onValueChanged = { viewModel.onEvent(InfoEvent.UpdateText(it)) },
                    onValueSaved = { viewModel.onEvent(InfoEvent.SaveText) },
                    modifier = Modifier.weight(1f),
                    startSpeechRecognition = { viewModel.onEvent(InfoEvent.StartSpeechToText) },
                    isMicWorking = state.isMicWorking
                )
            }
        }
    }
}