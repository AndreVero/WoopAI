@file:OptIn(ExperimentalMaterial3Api::class)

package com.vero.woopai.features.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vero.woopai.R
import com.vero.woopai.core.presentation.components.DefaultButton
import com.vero.woopai.core.presentation.components.PlanComponent
import com.vero.woopai.ui.theme.AppBarStyle
import com.vero.woopai.ui.theme.BackgroundColor
import com.vero.woopai.ui.theme.ButtonStyle
import com.vero.woopai.ui.theme.TextColor

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    openHistoryScreen: () -> Unit
) {

    val state = viewModel.state

    Column(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundColor)
        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
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
                IconButton(onClick = openHistoryScreen) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "History icon",
                    )
                }
            }
        )
        LazyColumn(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.plans) { plan ->
                PlanComponent(plan = plan)
            }
        }
        DefaultButton(
            onClick = {},
            text = R.string.add_plan
        )
    }
}