package com.example.demo.controller;

import com.example.demo.model.dto.DeploymentCreateDTO;
import com.example.demo.model.dto.ImageDTO;
import com.example.demo.model.vo.ResultVO;
import io.kubernetes.client.custom.Quantity;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.KubeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;

@RestController
@RequestMapping("/deployment")
public class DeploymentController {

    @PutMapping("/")
    public ResultVO createDeployment(@RequestBody DeploymentCreateDTO deploymentCreateDTO){

        int replicaNum = deploymentCreateDTO.getReplicaNum();
        Map<String,String> labels=deploymentCreateDTO.getLabels();
        Map<String,String> seletorLabels = deploymentCreateDTO.getSelectorLabels();
        ImageDTO[] imageInfos = deploymentCreateDTO.getImageInfos();
        String namespace = deploymentCreateDTO.getNamespace();

        try {

            String kubeConfigPath = "./src/main/resources/config";
            // 创建 ApiClient 对象，用于连接到 Kubernetes 集群
            ApiClient client = ClientBuilder
                    .kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath)))
                    .build();
            Configuration.setDefaultApiClient(client);

            // 创建 AppsV1Api 对象，用于与 Apps API 进行交互
            AppsV1Api api = new AppsV1Api();

            List<V1Container> containers = new ArrayList<V1Container>();
            for(int n=0;n<imageInfos.length;n++){

                String imageName = imageInfos[n].getImageName();
                String containerName = imageInfos[n].getContainerName();
                int containerPort = imageInfos[n].getContainerPort();

                V1Container container = new V1Container();
                container.setName(containerName);
                container.setImage(imageName);
                container.setPorts(
                        Arrays.asList(new V1ContainerPort().containerPort(containerPort)));
                V1ResourceRequirements resReq = new V1ResourceRequirements();
                resReq.setLimits(singletonMap("cpu", Quantity.fromString("1")));
                container.setResources(resReq);
                containers.add(container);
            }

            // 创建 Deployment 对象
            V1Deployment deployment = new V1Deployment()
                    .metadata(new V1ObjectMeta().name("example-deployment").labels(labels))
                    .spec(new V1DeploymentSpec()
                            .replicas(replicaNum) // 副本数
                            .selector(new V1LabelSelector()
                                    .matchLabels(seletorLabels)) // 选择器
                            .template(new V1PodTemplateSpec()
                                    .metadata(new V1ObjectMeta()
                                            .labels(seletorLabels))
                                    .spec(new V1PodSpec()
                                            .containers(containers)
                                    )
                            )
                    );


            // 使用 API 创建 Deployment
            V1Deployment createdDeployment = api.createNamespacedDeployment(namespace, deployment, null, null, null);
            System.out.println("Deployment created: " + createdDeployment.getMetadata().getName());
            return ResultVO.ok(createdDeployment);
        } catch (ApiException e) {
            System.err.println("Error creating Deployment: " + e.getMessage());
            e.printStackTrace();
            return ResultVO.fail("Error creating Deployment: " + e.getMessage());
        } catch (FileNotFoundException e) {
            ResultVO.fail("File Not Found: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //return ResultVO.fail("Uncaught error!");
    }

}

