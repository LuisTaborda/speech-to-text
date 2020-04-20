package com.javatechie.spring.cloud.api.controller;

import com.sun.javafx.binding.StringFormatter;
import javazoom.jl.decoder.JavaLayerException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javazoom.jl.converter.Converter;

/**
 * Static audio utility methods.
 */
class AudioUtilities {
    public static File convertMP3toWAV(String path){
        File mp3 = new File(path);
        return convertMP3toWAV(mp3);
    }

    public static File convertMP3toWAV(File mp3){
        File temp=null;
        try {
            temp = File.createTempFile(mp3.getName().split("\\.")[0],".wav");
            temp.deleteOnExit();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Converter converter = new Converter();
        try {
            converter.convert(mp3.getAbsolutePath(), temp.getAbsolutePath());
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public static AudioFile audioToSpeech(String path){
        String type = path.substring(path.length()-3);
        AudioFile out = null;
        try{
            switch(type.toUpperCase()){
                case "WAV":
                    out = new AudioFile(new File(path), 44100);
                    break;
                case "MP3":
                    out = new AudioFile(convertMP3toWAV(path), 48000);
                    break;
                default:
                    System.out.println(String.format("O Tipo [ %s ] não suportado, consulte o código", type));
                    break;
            }
        } catch (Exception er){
            System.out.println(er.getMessage());
        }
        finally {
            return out;
        }
    }

}