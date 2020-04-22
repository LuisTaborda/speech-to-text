package com.javatechie.spring.cloud.api.controller;

import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;

import java.io.File;

public class AudioConfig {

    private File file;
    // The language of the supplied audio
    private final String languageCode = "pt-BR";
    // Sample rate in Hertz of the audio data sent
    private int sampleRateHertz;
    private final RecognitionConfig.AudioEncoding encoding = RecognitionConfig.AudioEncoding.LINEAR16 ;


    public void setSampleRateHertz(int sampleRateHertz) {
        this.sampleRateHertz = sampleRateHertz;
    }
    public void setFile(File file) {
        this.file = file;
    }

    public AudioEncoding getEncoding() { return encoding; }
    public File getFile() {
        return file;
    }
    public String getLanguageCode() { return languageCode; }
    public int getSampleRateHertz() {
        return sampleRateHertz;
    }


    AudioConfig(){ }

    AudioConfig(File file, int sampleRateHertz){
        setFile(file);
        setSampleRateHertz(sampleRateHertz);
    }
}
