package com.spring.cloud.api.service;

import com.spring.cloud.api.entity.AudioConfig;
import javazoom.jl.decoder.JavaLayerException;

import java.io.File;
import java.io.IOException;
import javazoom.jl.converter.Converter;

public class AudioConfigService {
    /**
     * Esta função trabalha apenas com o path do arquivo, usado para retornar um audio em formato .wav
     * @param path
     * @return
     */
    public static File convertMP3toWAV(String path){
        File mp3 = new File(path);
        return convertMP3toWAV(mp3);
    }

    /**
     * Esta função trabalha com arquivo File e retorna uma conversão em .wav
     * @param mp3
     * @return
     */
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

    /**
     * Esta função retorna AudioConfig - é um padrão de configuração para google cloud
     * @param path
     * @return
     */
    public static AudioConfig audioToSpeech(String path){
        File file = new File(path);
        return audioToSpeech(file);
    }

    /**
     * Esta função retorna AudioConfig - é um padrão de configuração para google cloud
     * @param file
     * @return
     */
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
                    out = new AudioConfig(VideoService.mp4ToMp3(file), 44100);
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
