package com.javatechie.spring.cloud.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ws.schild.jave.EncoderException;

import java.io.File;

@Controller
public class SpeechToTextController {


    private final String localpath = "C:\\Users\\usuario\\Documents\\workspace\\speech-to-text\\src\\main\\resources\\audios\\video.mp4";

    @RequestMapping(value="/speech", method = RequestMethod.GET)
    public String speech( @RequestParam( name = "transcript", required = false, defaultValue = "Error Failed load audio") String transcript, Model model) throws Exception, EncoderException {

        //Gerar configurações apartir do seu formato do arquivo
        AudioConfig audioFile = AudioUtilities.audioToSpeech(new File(localpath));
        //Enviar para o serviço da google cloud - speech to text
        transcript = SampleRecognizeLocal.transcript(audioFile);
        //Exibir na Tela
        model.addAttribute("transcript",transcript);
        return "speech";
    }

}
