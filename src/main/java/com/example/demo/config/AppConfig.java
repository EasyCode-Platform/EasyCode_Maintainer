package com.example.demo.config;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.auth.ApiKeyAuth;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;

@org.springframework.context.annotation.Configuration
public class AppConfig {
    @Bean
    public CoreV1Api coreV1Api() throws Exception {

        String kubeConfigPath = "./src/main/resources/config";
        ApiClient client = ClientBuilder
                .kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath)))
                .build();
        io.kubernetes.client.openapi.Configuration.setDefaultApiClient(client);
        return new CoreV1Api();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
