package com.example.demo.controller;

import com.example.demo.model.dto.ImageDTO;
import com.example.demo.model.dto.PodCreateDTO;
import com.example.demo.service.PodCreateService;
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
import io.kubernetes.client.util.KubeConfig;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.util.Collections.*;

/*
GET（SELECT）：从服务器取出资源（一项或多项）。
POST（CREATE）：在服务器新建一个资源。
PUT（UPDATE）：在服务器更新资源（客户端提供完整资源数据）。
PATCH（UPDATE）：在服务器更新资源（客户端提供需要修改的资源数据）。
DELETE（DELETE）：从服务器删除资源。
 */

@Tag(name= "PodController" , description = "Pod management")
@RestController
@RequestMapping("/pod")
@Slf4j
public class   PodController {

    @Autowired
    private PodCreateService podCreateService;
    @Autowired
    private RestTemplate restTemplate;
    @Operation(summary = "Get all pods")
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

    @Operation(summary = "Create single pod with multiple containers")
    @PutMapping("/multiple")
    public ResultVO createSingleContainerPod(@RequestBody PodCreateDTO podCreateDTO) throws IOException {

        // get infomation
        ImageDTO[] imageInfos = podCreateDTO.getImageInfos();
        String podName = podCreateDTO.getPodName();
        String namespace = podCreateDTO.getNamespace();
        Map<String,String> labels = podCreateDTO.getLabels();

        // initialize api client
        ApiClient client = ClientBuilder
                .kubeconfig(KubeConfig.loadKubeConfig(new FileReader("./src/main/resources/config")))
                .build();
        Configuration.setDefaultApiClient(client);
        CoreV1Api api = new CoreV1Api();

        //create pods
        V1Pod pod = new V1Pod();
        pod.setApiVersion("v1");
        pod.setKind("Pod");

        //create metadata
        V1ObjectMeta metadata = new V1ObjectMeta();
        metadata.setName(podName);
        metadata.setLabels(labels);
        pod.setMetadata(metadata);

        List<V1Container> containers = new ArrayList<V1Container>();

        for(int n=0;n<imageInfos.length;n++){
            String imageName = imageInfos[n].getImageName();
            String containerName = imageInfos[n].getContainerName();
            V1Container container = new V1Container();
            container.setName(containerName);
            container.setImage(imageName);
            container.setPorts(
                    Arrays.asList(new V1ContainerPort().containerPort(8090+n)));
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







}
