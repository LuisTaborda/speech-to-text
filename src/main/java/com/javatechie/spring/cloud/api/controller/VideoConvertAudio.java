package com.javatechie.spring.cloud.api.controller;


import ws.schild.jave.*;

import java.io.File;

public class VideoConvertAudio {

    /**
     * Converts Mp4 file to MP3
     *
     * @param video
     * @return true if it has been converted
     */
    public static File mp4ToMp3(File video)
    {
        File fileAudio = new File("C:\\Users\\usuario\\Documents\\workspace\\speech-to-text\\src\\main\\resources\\audios\\audio-extraido.wav");
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("pcm_s16le");
        //audio.setCodec("libmp3lame"); não funciona com wav    
        audio.setBitRate(new Integer(128000));
        audio.setChannels(new Integer(1));
        audio.setSamplingRate(new Integer(44100));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("wav");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        try
        {
            encoder.encode(new MultimediaObject(video), fileAudio, attrs);
            System.out.println("File MP4 convertito in MP3");
            return fileAudio;
        }
        catch (IllegalArgumentException | EncoderException e)
        {
            System.out.println("File non convertito");
            System.out.println(e.getMessage());
            return null;
        }
    }
}
