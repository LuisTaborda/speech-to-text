package com.javatechie.spring.cloud.api.controller;

import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;

import java.io.File;

public class AudioFile {

    private File file;
    // The language of the supplied audio
    private String languageCode;
    // Sample rate in Hertz of the audio data sent
    private int sampleRateHertz;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public int getSampleRateHertz() {
        return sampleRateHertz;
    }

    public void setSampleRateHertz(int sampleRateHertz) {
        this.sampleRateHertz = sampleRateHertz;
    }

    AudioFile(){ }

    AudioFile(File file, int sampleRateHertz){
        setFile(file);
        setSampleRateHertz(sampleRateHertz);
        setLanguageCode("pt-BR");
    }
}
