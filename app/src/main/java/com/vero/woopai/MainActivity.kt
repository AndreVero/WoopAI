package com.vero.woopai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vero.woopai.navigation.NavigationRoot
import com.vero.woopai.ui.theme.WoopAITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WoopAITheme {
                NavigationRoot()
            }
        }
    }
}