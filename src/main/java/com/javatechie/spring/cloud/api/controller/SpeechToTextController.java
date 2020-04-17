package com.javatechie.spring.cloud.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpeechToTextController {


    @RequestMapping(value="/speech", method = RequestMethod.GET)
    public String speech( @RequestParam( name = "transcript", required = false, defaultValue = "Error Failed load audio") String transcript, Model model) throws Exception {
        transcript = SampleRecognizeLocal.transcript("C:\\Users\\usuario\\Documents\\workspace\\speech-to-text\\src\\main\\resources\\audios\\9b2g.wav");
        model.addAttribute("transcript",transcript);
        return "speech";
    }


}
