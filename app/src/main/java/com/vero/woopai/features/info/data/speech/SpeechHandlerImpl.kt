package com.vero.woopai.features.info.data.speech

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import com.vero.woopai.features.info.domain.speech.SpeechHandler
import com.vero.woopai.features.info.domain.speech.SpeechRecognizerListener
import java.util.Locale


class SpeechHandlerImpl(private val context: Context) : SpeechHandler {

    private var speechRecognizer: SpeechRecognizer? = null
    private var listener: SpeechRecognizerListener? = null

    override fun init() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
    }

    override fun startRecognition() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        speechRecognizer?.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                listener?.onReadyForSpeech()
            }

            override fun onBeginningOfSpeech() {}

            override fun onRmsChanged(rmsdB: Float) {}

            override fun onBufferReceived(buffer: ByteArray?) {}

            override fun onEndOfSpeech() {
                listener?.onStop()
            }

            override fun onError(error: Int) {
                Log.d("Error in SpeechRecognizer, code:", error.toString())
            }

            override fun onResults(results: Bundle?) {
                val data = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                data?.getOrNull(0)?.let { text ->
                    listener?.onResult(text)
                }
            }

            override fun onPartialResults(partialResults: Bundle?) {}

            override fun onEvent(eventType: Int, params: Bundle?) {}
        })

        speechRecognizer?.startListening(intent)
    }

    override fun setListener(listener: SpeechRecognizerListener) {
        this.listener = listener
    }

    override fun onDestroy() {
        speechRecognizer?.stopListening()
        speechRecognizer?.destroy()
    }

}