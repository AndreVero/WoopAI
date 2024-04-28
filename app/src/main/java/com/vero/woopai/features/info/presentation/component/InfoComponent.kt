package com.vero.woopai.features.info.presentation.component

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.vero.woopai.core.presentation.components.DefaultButton
import com.vero.woopai.core.utils.hasAudioPermissions
import com.vero.woopai.features.info.presentation.ScreenState
import com.vero.woopai.features.info.presentation.utils.ScreenStateToTextParser
import com.vero.woopai.ui.theme.BlackTextColor
import com.vero.woopai.ui.theme.TextFieldBackground
import kotlinx.coroutines.delay
import java.lang.IllegalStateException

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

    val context = LocalContext.current
    val hint = ScreenStateToTextParser.screenStateToHint(screenState)
    val buttonText = ScreenStateToTextParser.screenStateToButtonText(screenState)

    val launcherAudioPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { startSpeechRecognition() }
    )

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = Unit) {
        delay(200)
        try {
            focusRequester.requestFocus()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    Column(modifier = modifier.padding(16.dp)) {
        Box {
            TextField(
                value = value,
                onValueChange = onValueChanged,
                minLines = 5,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 8.dp)
                    .focusRequester(focusRequester),
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
                ),
            )
            Box(modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 16.dp, end = 16.dp)) {
                IconButton(onClick = {
                    if (context.hasAudioPermissions())
                        startSpeechRecognition()
                    else launcherAudioPermission.launch(Manifest.permission.RECORD_AUDIO)
                }) {
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