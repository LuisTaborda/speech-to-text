package com.javatechie.spring.cloud.api.controller;

import javazoom.jl.decoder.JavaLayerException;

import java.io.File;
import java.io.IOException;
import javazoom.jl.converter.Converter;

public class AudioUtilities {
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

    public static AudioConfig audioToSpeech(String path){
        File file = new File(path);
        return audioToSpeech(file);
    }

    public static AudioConfig audioToSpeech(File file){
        String path = file.getAbsolutePath();
        String type = path.substring(path.length()-3);

        AudioConfig out = null;
        try{
            switch(type.toUpperCase()){
                case "WAV":
                    out = new AudioConfig(file, 44100);
                    break;
                case "MP3":
                    out = new AudioConfig(convertMP3toWAV(file), 48000);
                    break;
                case "MP4":
                    out = new AudioConfig(VideoConvertAudio.mp4ToMp3(file), 44100);
                    break;
                default:
                    System.out.println(String.format("O Tipo [ %s ] não suportado, consulte o código", type));
                    break;
            }
        }
        catch (Exception er){
            System.out.println(er.getMessage());
        }
        finally {
            return out;
        }
    }
}