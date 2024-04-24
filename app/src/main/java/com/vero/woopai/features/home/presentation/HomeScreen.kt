@file:OptIn(ExperimentalMaterial3Api::class)

package com.vero.woopai.features.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vero.woopai.R
import com.vero.woopai.core.presentation.components.PlanComponent

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    openHistoryScreen: () -> Unit
) {

    val state = viewModel.state

    Column {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            navigationIcon = {
                IconButton(onClick = openHistoryScreen) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "History icon"
                    )
                }
            },
        )
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.plans) {plan ->
                PlanComponent(plan = plan)
            }
        }
    }
}