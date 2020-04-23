package com.javatechie.spring.cloud.api.controller;

import java.io.File;

public class AudioConfig {

    private File file;
    private int sampleRateHertz;

    public void setSampleRateHertz(int sampleRateHertz) {
        this.sampleRateHertz = sampleRateHertz;
    }
    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
    public int getSampleRateHertz() {
        return sampleRateHertz;
    }

    AudioConfig(){ }
    AudioConfig(File file, int sampleRateHertz){
        setFile(file);
        setSampleRateHertz(sampleRateHertz);
    }
}
