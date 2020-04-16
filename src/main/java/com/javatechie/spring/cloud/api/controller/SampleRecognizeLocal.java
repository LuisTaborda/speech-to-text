package com.javatechie.spring.cloud.api.controller;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SampleRecognizeLocal {
    /**
     * Transcribe a short audio file using synchronous speech recognition
     *
     * @param localFilePath Path to local audio file, e.g. /path/audio.wav
     */
    public static String transcript(String localFilePath) {
        try (SpeechClient speechClient = SpeechClient.create()) {

            // The language of the supplied audio
            String languageCode = "pt-BR";

            // Sample rate in Hertz of the audio data sent
            int sampleRateHertz = 44100;

            // Encoding of audio data sent. This sample sets this explicitly.
            // This field is optional for FLAC and WAV audio formats.
            RecognitionConfig.AudioEncoding encoding = RecognitionConfig.AudioEncoding.ENCODING_UNSPECIFIED;
            RecognitionConfig config = RecognitionConfig.newBuilder().setLanguageCode(languageCode).setSampleRateHertz(sampleRateHertz).setEncoding(encoding).build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(AudioToByteString(localFilePath)).build();

            //Passa o audio em ByteString para API do google processar.
            RecognizeRequest request = RecognizeRequest.newBuilder().setConfig(config).setAudio(audio).build();

            RecognizeResponse response = speechClient.recognize(request);

            for (SpeechRecognitionResult result : response.getResultsList()) {
                // First alternative is the most probable result
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

    /**
     * Está função retorna um arquvio de audio em ByteString, padrão da api google cloud plataform
     *
     * @param localFilePath Path to local audio file, e.g. /path/audio.wav
     */
    public static ByteString AudioToByteString(String localFilePath) throws IOException {
        Path path = Paths.get(localFilePath);
        byte[] data = Files.readAllBytes(path);
        return ByteString.copyFrom(data);
    }

}
