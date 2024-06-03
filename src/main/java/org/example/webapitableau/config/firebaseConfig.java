package org.example.webapitableau.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class firebaseConfig {
    @Bean
    FirebaseApp createFireBaseApp() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/firebase-adminsdk.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        return FirebaseApp.initializeApp(options);
    }
}