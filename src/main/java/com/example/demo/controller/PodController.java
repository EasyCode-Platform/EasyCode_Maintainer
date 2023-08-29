package com.example.demo.controller;

import com.example.demo.model.dto.ImageDTO;
import com.example.demo.model.dto.PodCreateDTO;
import com.example.demo.service.PodCreateService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.demo.model.vo.ResultVO;
import io.kubernetes.client.custom.Quantity;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.KubeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;

/*
GET（SELECT）：从服务器取出资源（一项或多项）。
POST（CREATE）：在服务器新建一个资源。
PUT（UPDATE）：在服务器更新资源（客户端提供完整资源数据）。
PATCH（UPDATE）：在服务器更新资源（客户端提供需要修改的资源数据）。
DELETE（DELETE）：从服务器删除资源。
 */

@RestController
@RequestMapping("/pod")
@Slf4j
public class   PodController {

    @Autowired
    private PodCreateService podCreateService;
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/")
    public ResultVO getAllPods() {
        String kubeConfigPath = "./src/main/resources/config";
        try {
            ApiClient client = ClientBuilder
                    .kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath)))
                    .build();
            Configuration.setDefaultApiClient(client);
            CoreV1Api api = new CoreV1Api();

            // Call the client API to retrieve all pod information
            V1PodList v1PodList = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
            System.out.println(ResultVO.ok(new GsonBuilder().setPrettyPrinting().create().toJson(v1PodList)));
            return ResultVO.ok(new GsonBuilder().setPrettyPrinting().create().toJson(v1PodList));
        } catch (ApiException e) {
            e.printStackTrace();
            return ResultVO.fail("Error occurred while retrieving pod information: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return ResultVO.fail("Error reading Kubernetes configuration file: " + e.getMessage());
        }
    }
    @RequestMapping(value = "/hello")
    public V1PodList hello() throws Exception {
        // 存放K8S的config文件的全路径
        String kubeConfigPath = "./src/main/resources/config";

        // 以config作为入参创建的client对象，可以访问到K8S的API Server
        ApiClient client = ClientBuilder
                .kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath)))
                .build();

        Configuration.setDefaultApiClient(client);

        CoreV1Api api = new CoreV1Api();

        // 调用客户端API取得所有pod信息
        V1PodList v1PodList = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);

        // 使用jackson将集合对象序列化成JSON，在日志中打印出来
        log.info("pod info \n{}", new GsonBuilder().setPrettyPrinting().create().toJson(v1PodList));
        System.out.println(v1PodList);
        return v1PodList;
    }

    @PutMapping("/multiple")
    public ResultVO createSingleContainerPod(@RequestBody PodCreateDTO podCreateDTO) throws IOException {
        ImageDTO[] imageInfos = podCreateDTO.getImageInfos();
        String podName = podCreateDTO.getPodName();
        ApiClient client = ClientBuilder
                .kubeconfig(KubeConfig.loadKubeConfig(new FileReader("./src/main/resources/config")))
                .build();
        Configuration.setDefaultApiClient(client);
        CoreV1Api api = new CoreV1Api();
        V1Pod pod = new V1Pod();
        pod.setApiVersion("v1");
        pod.setKind("Pod");
        V1ObjectMeta metadata = new V1ObjectMeta();
        metadata.setName(podName);
        pod.setMetadata(metadata);
        List<V1Container> containers = new ArrayList<V1Container>();

        for(int n=0;n<imageInfos.length;n++){
            String imageName = imageInfos[n].getImageName();
            String containerName = imageInfos[n].getContainerName();
            V1Container container = new V1Container();
            container.setName(containerName);
            container.setImage(imageName);
            container.setPorts(
                    Arrays.asList(new V1ContainerPort().containerPort(8053)));
            V1ResourceRequirements resReq = new V1ResourceRequirements();
            resReq.setLimits(singletonMap("cpu", Quantity.fromString("1")));
            container.setResources(resReq);
            containers.add(container);
        }


        V1PodSpec spec = new V1PodSpec();
        spec.setContainers(containers);

        V1PodTemplateSpec template = new V1PodTemplateSpec();
        template.setSpec(spec);

        pod.setSpec(spec);

        try {
             return ResultVO.ok(api.createNamespacedPod("default", pod, null, null, null));
        } catch (ApiException e) {
            System.err.println("Exception when calling CoreV1Api#createNamespacedPod");
            e.printStackTrace();
            return ResultVO.fail("Create container failed.");
        }
    }


    @PutMapping("test")
    public ResultVO test() throws IOException {

        ApiClient client = ClientBuilder
                .kubeconfig(KubeConfig.loadKubeConfig(new FileReader("./src/main/resources/config")))
                .build();
        Configuration.setDefaultApiClient(client);

        CoreV1Api api = new CoreV1Api();
        V1Pod pod = new V1Pod();
        pod.setApiVersion("v1");
        pod.setKind("Pod");

        V1ObjectMeta metadata = new V1ObjectMeta();
        metadata.setName("example-pod");
        pod.setMetadata(metadata);

        V1Container container = new V1Container();
        container.setName("example-container");
        container.setImage("nginx:latest");
        container.setPorts(
                Arrays.asList(new V1ContainerPort().containerPort(80)));

        V1ResourceRequirements resReq = new V1ResourceRequirements();
        resReq.setLimits(Collections.singletonMap("cpu", Quantity.fromString("1")));
        container.setResources(resReq);

        V1PodSpec spec = new V1PodSpec();
        spec.setContainers(Arrays.asList(container));

        V1PodTemplateSpec template = new V1PodTemplateSpec();
        template.setSpec(spec);

        pod.setSpec(spec);

        try {
            return ResultVO.ok(api.createNamespacedPod("default", pod, null, null, null));

        } catch (ApiException e) {
            System.err.println("Exception when calling CoreV1Api#createNamespacedPod");
            e.printStackTrace();
        }
        return ResultVO.fail("Exception when calling CoreV1Api#createNamespacedPod");
    }




}
