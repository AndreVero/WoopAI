package com.vero.woopai.core.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vero.woopai.R
import com.vero.woopai.ui.theme.ButtonBackgroundColor
import com.vero.woopai.ui.theme.ButtonStyle
import com.vero.woopai.ui.theme.TextColor

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    @StringRes text: Int = R.string.continue_button,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp
        ),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ButtonBackgroundColor
        )
    ) {
        Text(
            text = stringResource(id = text),
            color = TextColor,
            style = ButtonStyle
        )
    }
}