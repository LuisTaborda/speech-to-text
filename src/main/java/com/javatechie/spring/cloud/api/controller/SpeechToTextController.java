package com.javatechie.spring.cloud.api.controller;

import com.javatechie.spring.cloud.api.entity.AudioConfig;
import com.javatechie.spring.cloud.api.service.AudioConfigService;
import com.javatechie.spring.cloud.api.service.TranscriptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
@Controller
public class SpeechToTextController {



    @RequestMapping(value="/transcript-default", method = RequestMethod.GET)
    public String transcript( @RequestParam( name = "transcript", required = false, defaultValue = "Error Failed load audio") String transcript, Model model){
        String localpath = "C:\\Users\\usuario\\Documents\\workspace\\speech-to-text\\src\\main\\resources\\audios\\video.mp4";

        model.addAttribute("transcript",transcript(localpath));
        return "transcript";
    }

    @RequestMapping(value = "/transcript", method = RequestMethod.POST )
    public String submit(@RequestParam("path") String path, @RequestParam( name = "transcript", required = false, defaultValue = "Error Failed load audio") String transcript, Model model) {
        model.addAttribute("transcript",transcript(path));
        return "transcript";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET )
    public String home() {
        return "home";
    }

    public String transcript(String path){
        File file = new File(path);
        //Gerar configurações apartir do seu formato do arquivo
        AudioConfig audioFile = AudioConfigService.audioToSpeech(file);
        //Enviar para o serviço da google cloud - speech to text
        return TranscriptService.transcript(audioFile);
    }

}

