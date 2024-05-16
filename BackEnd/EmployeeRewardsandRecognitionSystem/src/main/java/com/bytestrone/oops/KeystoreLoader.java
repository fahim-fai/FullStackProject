package com.bytestrone.oops;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class KeystoreLoader {

    private final ResourceLoader resourceLoader;

    public KeystoreLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void loadKeystore() {
        Resource keystoreResource = resourceLoader.getResource("classpath:localhost.p12");
        try {
            System.out.println("Keystore location: " + keystoreResource.getURL());
        } catch (IOException e) {
            System.err.println("Error loading keystore: " + e.getMessage());
        }
    }
}

