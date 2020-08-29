package com.javatechie.springsecurityjwtexample.services;

import com.javatechie.springsecurityjwtexample.entities.Greeting;
import com.javatechie.springsecurityjwtexample.entities.Header;
import com.javatechie.springsecurityjwtexample.repositories.GreetingRepository;
import com.javatechie.springsecurityjwtexample.repositories.HeaderRepository;
import com.javatechie.springsecurityjwtexample.services.logService.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class GreetingService {
    @Autowired
    GreetingRepository greetingRepository;

    public Greeting AddUpdateGreeting(Greeting greeting) {
        Greeting clsGreeting = null;
        try {
            clsGreeting = greetingRepository.save(greeting);
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return clsGreeting;
    }

    public Greeting findByIdGreeting(long lngHeaderId) {
        Greeting clsGreeting = null;
        try {
            clsGreeting = greetingRepository.findByGreetingId(lngHeaderId);
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return clsGreeting;
    }

    public byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

}
