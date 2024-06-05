package org.example.webapitableau.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    @Autowired
    FirebaseAuth createFireBaseAuth(FirebaseApp app) throws IOException {
        return FirebaseAuth.getInstance(app);
    }

    @Bean
    @Autowired
    FirebaseMessaging createFireBaseMessaging(FirebaseApp app) throws IOException {
        return FirebaseMessaging.getInstance(app);
    }
}