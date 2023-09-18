package com.example.demo.controller;

import com.example.demo.model.dto.NamespaceCreateDTO;
import com.example.demo.model.vo.ResultVO;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import io.kubernetes.client.proto.V1;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@RestController
@RequestMapping("/namespace")
public class NamespaceController {

    @PutMapping("/")
    public ResultVO createNamespace(@RequestBody NamespaceCreateDTO namespaceCreateDTO) throws IOException {


        //// 创建ApiClient对象
        String kubeConfigPath = "./src/main/resources/config";
        ApiClient client = ClientBuilder
                .kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath)))
                .build();
        Configuration.setDefaultApiClient(client);
        CoreV1Api api = new CoreV1Api();

        // 创建命名空间对象
        V1Namespace namespace = new V1Namespace();
        V1ObjectMeta metadata = new V1ObjectMeta();
        metadata.setName(namespaceCreateDTO.getNamespace());
        System.out.println(namespaceCreateDTO.getNamespace());
        namespace.setMetadata(metadata);
        try {
            V1Namespace ret =  api.createNamespace(namespace, null, null, null);
            return ResultVO.ok(ret);
        } catch (ApiException e) {
            e.printStackTrace();
            return ResultVO.fail(e.getCode()+e.getResponseBody()+e.getResponseHeaders());
        }

    }

}
