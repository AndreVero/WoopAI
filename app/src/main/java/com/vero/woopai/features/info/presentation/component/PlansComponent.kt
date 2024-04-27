package com.vero.woopai.features.info.presentation.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vero.woopai.R
import com.vero.woopai.core.domain.model.PlanModel
import com.vero.woopai.core.presentation.components.DefaultButton
import com.vero.woopai.core.presentation.components.PlanComponent
import com.vero.woopai.ui.theme.ButtonStyle
import com.vero.woopai.ui.theme.TextColor

@Composable
fun PlansComponent(
    plan: PlanModel?,
    savePlans: () -> Unit,
    makeNewSuggestions: () -> Unit,
) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            Text(
                text = stringResource(id = R.string.gemini_suggestions),
                style = ButtonStyle,
                color = TextColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        plan?.let {
            item { PlanComponent(plan = plan) }
        }

        item {
            DefaultButton(
                onClick = savePlans,
                text = R.string.save_plans
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            DefaultButton(
                onClick = makeNewSuggestions,
                text = R.string.make_new_suggestions
            )
        }
    }
}