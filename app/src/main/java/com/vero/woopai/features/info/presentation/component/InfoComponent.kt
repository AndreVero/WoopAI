package com.vero.woopai.features.info.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.vero.woopai.core.presentation.components.DefaultButton
import com.vero.woopai.features.info.presentation.ScreenState
import com.vero.woopai.features.info.presentation.utils.ScreenStateToTextParser
import com.vero.woopai.ui.theme.BlackTextColor
import com.vero.woopai.ui.theme.TextFieldBackground

@Composable
fun InfoComponent(
    screenState: ScreenState,
    onValueSaved: () -> Unit,
    value: String,
    onValueChanged: (String) -> Unit,
    startSpeechRecognition: () -> Unit,
    speechButtonColor: Color,
    modifier: Modifier = Modifier
) {

    val hint = ScreenStateToTextParser.screenStateToHint(screenState)
    val buttonText = ScreenStateToTextParser.screenStateToButtonText(screenState)

    Column(modifier = modifier.padding(16.dp)) {
        Box {
            TextField(
                value = value,
                onValueChange = onValueChanged,
                minLines = 5,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 8.dp),
                placeholder = {
                    Text(
                        text = stringResource(id = hint),
                        color = BlackTextColor
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { onValueSaved() }),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = TextFieldBackground,
                    unfocusedContainerColor = TextFieldBackground,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = BlackTextColor,
                    unfocusedTextColor = BlackTextColor
                )
            )
            Box(modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 16.dp, end = 16.dp)) {
                IconButton(onClick = startSpeechRecognition) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Start speech to text recognition",
                        tint = speechButtonColor
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        DefaultButton(
            onClick = onValueSaved,
            text = buttonText
        )
    }
}