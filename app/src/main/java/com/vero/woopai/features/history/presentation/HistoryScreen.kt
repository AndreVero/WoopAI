@file:OptIn(ExperimentalMaterial3Api::class)

package com.vero.woopai.features.history.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vero.woopai.R
import com.vero.woopai.core.presentation.components.PlanComponent
import com.vero.woopai.ui.theme.AppBarStyle
import com.vero.woopai.ui.theme.BackgroundColor
import com.vero.woopai.ui.theme.TextColor

@Composable
fun HistoryScreen(
    navigateBack: () -> Unit,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize().background(BackgroundColor)) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.history),
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
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(
                items = viewModel.state.plans,
                key = { it.id }
            ) {
                PlanComponent(plan = it)
            }
        }
    }
}