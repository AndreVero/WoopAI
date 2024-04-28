package com.vero.woopai.features.info.presentation.component

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.vero.woopai.R
import com.vero.woopai.core.presentation.utils.hasAudioPermissions
import com.vero.woopai.ui.theme.BlackTextColor

@Composable
fun AudioComponent(
    isMicWorking: Boolean,
    startSpeechRecognition: () -> Unit,
) {

    val context = LocalContext.current

    val launcherAudioPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { startSpeechRecognition() }
    )

    AnimatedContent(targetState = isMicWorking, label = "mic_animation") {
        if (it) {
            AudioAnimation()
        } else {
            IconButton(onClick = {
                if (context.hasAudioPermissions())
                    startSpeechRecognition()
                else launcherAudioPermission.launch(Manifest.permission.RECORD_AUDIO)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_mic),
                    contentDescription = "Start speech to text recognition",
                    tint = BlackTextColor
                )
            }
        }
    }
}