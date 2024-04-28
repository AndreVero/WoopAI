package com.vero.woopai.features.info.domain.speech

interface SpeechHandler {

    fun init()

    fun startRecognition()

    fun setListener(listener: SpeechRecognizerListener)

    fun onDestroy()

}

interface SpeechRecognizerListener {
    fun onStop()

    fun onResult(result: String)

    fun onReadyForSpeech()
}