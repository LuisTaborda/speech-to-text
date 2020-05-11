package com.javatechie.spring.cloud.api.service;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import com.javatechie.spring.cloud.api.entity.AudioConfig;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class TranscriptService {

    public static String transcript(AudioConfig audioConfig) {
        try (SpeechClient speechClient = SpeechClient.create()) {

            RecognitionConfig.AudioEncoding encoding = RecognitionConfig.AudioEncoding.LINEAR16 ;
            RecognitionConfig config = RecognitionConfig.newBuilder().setLanguageCode("pt-BR").setSampleRateHertz(audioConfig.getSampleRateHertz()).setEncoding(encoding).build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(AudioToByteString(audioConfig.getFile())).build();
            RecognizeRequest request = RecognizeRequest.newBuilder().setConfig(config).setAudio(audio).build();

            RecognizeResponse response = speechClient.recognize(request);

            for (SpeechRecognitionResult result : response.getResultsList()) {

                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcript: %s\n", alternative.getTranscript());

                return alternative.getTranscript();
            }
        } catch (Exception exception) {
            System.err.println("Failed to create the client due to: " + exception);
            return "Failed to create the client due to: " + exception;
        }
        return "The file could not be processed, try again by changing the file format.";
    }

    public static ByteString AudioToByteString(File file) throws IOException {

        Path path = Paths.get(file.getAbsolutePath());
        byte[] data = Files.readAllBytes(path);
        return ByteString.copyFrom(data);
    }
}
