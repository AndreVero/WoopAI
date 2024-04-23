package com.vero.woopai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.vero.woopai.ui.theme.WoopAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-pro-latest",
            apiKey = BuildConfig.API_KEY
        )


        setContent {
            var text by remember { mutableStateOf("Test") }
            LaunchedEffect(key1 = true) {
                val inputContent = content {
                    text("Are you working")
                }

                val response = generativeModel.generateContent(inputContent)
                text = response.text ?: ""
            }

            WoopAITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Text(text)
                }
            }
        }
    }
}