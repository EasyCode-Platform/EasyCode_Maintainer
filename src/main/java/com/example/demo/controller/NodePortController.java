package com.example.demo.controller;

import com.example.demo.model.dto.NodePortCreateDTO;
import com.example.demo.model.vo.ResultVO;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1ServicePort;
import io.kubernetes.client.openapi.models.V1ServiceSpec;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/nodeport")
public class NodePortController {
    @PutMapping("/")
    public ResultVO createNodeport(@RequestBody NodePortCreateDTO nodePortCreateDTO) throws FileNotFoundException {

        ArrayList<Map<String,String>> portList = nodePortCreateDTO.getPortList();
        String nodePortName = nodePortCreateDTO.getNodePortName();
        Map<String,String> selectLabels= nodePortCreateDTO.getSelectLabels();
        String namespace = nodePortCreateDTO.getNamespace();
        System.out.println(portList);
        System.out.println(nodePortName);
        System.out.println(selectLabels);
        System.out.println(namespace);
        ArrayList<V1ServicePort> ports= new ArrayList<V1ServicePort>();

        for(int i=0;i<portList.size();i++){
            ports.add(new V1ServicePort()
                    .name(portList.get(i).get("name"))
                    .port(Integer.valueOf(portList.get(i).get("port"))) // 服务端口
                    .targetPort(new IntOrString(portList.get(i).get("targetPort"))) // 目标容器端口
                    .nodePort(Integer.valueOf(portList.get(i).get("nodePort"))));// NodePort 端口)
        }


        try {
            String kubeConfigPath = "./src/main/resources/config";
            // 创建 ApiClient 对象，用于连接到 Kubernetes 集群
            ApiClient client = ClientBuilder
                    .kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath)))
                    .build();
            Configuration.setDefaultApiClient(client);

            // 创建 AppsV1Api 对象，用于与 Apps API 进行交互
            CoreV1Api api = new CoreV1Api();

            // 创建 Service 对象
            V1Service service = new V1Service()
                    .metadata(new V1ObjectMeta().name(nodePortName))
                    .spec(new V1ServiceSpec()
                            .type("NodePort") // 指定服务类型为 NodePort
                            .ports(ports)
                            .selector(selectLabels) // 选择器
                    );

            // 使用 API 创建 Service
            V1Service createdService = api.createNamespacedService(namespace, service, null, null, null);
            System.out.println("NodePort Service created: " + createdService.getMetadata().getName());
            return  ResultVO.ok(createdService);
        } catch (ApiException | IOException e) {
            System.err.println("Error creating NodePort Service: " + e.getMessage());
            e.printStackTrace();
            return ResultVO.fail("Uncatch error!\n"+e.getMessage());
        }

    }

}
