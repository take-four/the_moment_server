package com.takefour.themoment.themoment.config;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;

/**
 * Created by hanbyeol on 2018. 1. 10..
 */
@Configuration
public class FirebaseConfig {

	@Value("${firebase.config.path}")
	private String configPath;

	@Value("${firebase.database.url}")
	private String databaseUrl;

	@PostConstruct
	public void init() throws IOException {

		FileInputStream serviceAccount = new FileInputStream(configPath);

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
				.setDatabaseUrl(databaseUrl)
				.build();

		FirebaseApp.initializeApp(options);
	}
}
