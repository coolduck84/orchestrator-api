package com.pc.demo.orchestratorapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pc.demo.orchestratorapi.service.InvokeAPI;

@RestController
@RequestMapping(path = "/test")
public class Controller {
	
	@Autowired
	InvokeAPI invokeAPI;
	
	@Autowired
	Environment env;
	
	@GetMapping
	public String getSampleResponse() {
		return "API invocation successful";
	}
	
	@PostMapping("/search")
	public String search() {
		
		String clientId = System.getenv("appId");
		String clientSecret = System.getenv("appSecret");
		String scope = System.getenv("scope");
		String grantType = System.getenv("grantType");
		String orchestratorBaseURL = System.getenv("orchestratorBaseURL");
		
		System.setProperty("https.proxyHost", System.getenv("https_proxyHost"));
		System.setProperty("https.proxyPort", System.getenv("https_proxyPort"));
		
		System.out.println("\nscope => " + scope);
		System.out.println("\ngrantType => " + grantType);
		System.out.println("\norchestratorBaseURL => " + orchestratorBaseURL);
		if (clientId != null && clientId.length() > 5) {
			System.out.println("\nclientId => " + clientId.substring(0, 4));
		} else {
			System.out.println("\nclientId not available");
		}
		if (clientSecret != null && clientSecret.length() > 5) {
			System.out.println("\nclientSecret => " + clientSecret.substring(0, 4));
		} else {
			System.out.println("\nclientSecret not available");
		}
		System.out.println("\nproxyHost => " + System.getProperty("https.proxyHost"));
		System.out.println("\nproxyPort => " + System.getProperty("https.proxyPort"));
		
		return invokeAPI.callOrchestratorAPI(clientId, clientSecret, scope, grantType, orchestratorBaseURL);
	}
}
