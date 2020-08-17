package com.spring.cloud.api.entity;

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

    public AudioConfig(){ }
    public AudioConfig(File file, int sampleRateHertz){
        setFile(file);
        setSampleRateHertz(sampleRateHertz);
    }
}
