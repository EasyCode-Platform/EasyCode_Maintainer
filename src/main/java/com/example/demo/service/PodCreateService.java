package com.example.demo.service;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PodCreateService {

    @Autowired
    private CoreV1Api coreV1Api;

    public V1Pod createPod(String imageName,String containerName) throws ApiException {
        V1Pod pod = new V1PodBuilder()
                .withNewMetadata()
                .withName("my-pod")
                .endMetadata()
                .withNewSpec()
                .addNewContainer()
                .withName(containerName)
                .withImage(imageName)
                .endContainer()
                .endSpec()
                .build();
        try {
            V1Pod ret = coreV1Api.createNamespacedPod("default", pod, null, null, null);
            return ret;
        }catch(ApiException e){
            e.printStackTrace();
            return null;
        }

    }
}