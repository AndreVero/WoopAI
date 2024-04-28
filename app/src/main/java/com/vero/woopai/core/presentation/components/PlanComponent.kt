package com.vero.woopai.core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vero.woopai.core.domain.model.PlanModel
import com.vero.woopai.core.presentation.utils.getPrettyTime
import com.vero.woopai.ui.theme.BackgroundColor
import com.vero.woopai.ui.theme.DefaultText
import com.vero.woopai.ui.theme.TextColor

@Composable
fun PlanComponent(
    plan: PlanModel,
    modifier: Modifier = Modifier
) {

    var isDescriptionVisible by remember { mutableStateOf(false) }
    val rotationAnimation by animateFloatAsState(
        targetValue = if (isDescriptionVisible) 180f else 0f,
        label = "rotation_animation"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White.copy(alpha = 0.3f))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = plan.time.getPrettyTime(),
                color = TextColor,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            IconButton(
                onClick = { isDescriptionVisible = !isDescriptionVisible },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Arrow Down",
                    tint = TextColor,
                    modifier = Modifier.rotate(rotationAnimation),
                )
            }
        }
        AnimatedVisibility(visible = isDescriptionVisible) {
            Text(
                text = plan.description,
                color = TextColor,
                style = DefaultText
            )
        }
    }
}