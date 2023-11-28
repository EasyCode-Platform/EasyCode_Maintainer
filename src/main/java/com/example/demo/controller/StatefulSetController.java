package com.example.demo.controller;


import com.example.demo.model.dto.ImageDTO;
import com.example.demo.model.dto.StatefulSetCreateDTO;
import com.example.demo.model.dto.VolumeMountsInfoDTO;
import com.example.demo.model.vo.ResultVO;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;

import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.proto.V1;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("statefulset")
public class StatefulSetController {

    @PutMapping("/")
    public ResultVO createStatefulSet(@RequestBody StatefulSetCreateDTO statefulSetCreateDTO){
        // data resolution
        String statefulSetName = statefulSetCreateDTO.getStatefulSetName();

        int replicaNum = statefulSetCreateDTO.getReplicaNum();

        Map<String,String> labels = statefulSetCreateDTO.getLabels();

        Map<String,String> selectorLabels = statefulSetCreateDTO.getSelectorLabels();

        ImageDTO[] imageInfos = statefulSetCreateDTO.getImageInfos();

        VolumeMountsInfoDTO[] volumeMountsInfos = statefulSetCreateDTO.getVolumeMountsInfos();

        String namespace = statefulSetCreateDTO.getNamespace();

        System.out.println(statefulSetName);
        System.out.println(replicaNum);
        System.out.println(labels);
        System.out.println(selectorLabels);
        System.out.println(volumeMountsInfos);
        System.out.println(namespace);

        // 创建 StatefulSet 对象
        V1StatefulSet statefulSet = new V1StatefulSet();

        statefulSet.setApiVersion("apps/v1");

        statefulSet.setKind("StatefulSet");

        statefulSet.setMetadata(new V1ObjectMeta().name(statefulSetName));

        V1StatefulSetSpec spec = new V1StatefulSetSpec();

        spec.setReplicas(replicaNum);

        V1LabelSelector selector = new V1LabelSelector();

        selector.setMatchLabels(selectorLabels);

        spec.setSelector(selector);

        spec.setServiceName(statefulSetName);

        V1PodTemplateSpec template = new V1PodTemplateSpec();

        template.setMetadata(new V1ObjectMeta().labels(labels));

        V1PodSpec podSpec = new V1PodSpec();

        List<V1Container> containers = new ArrayList<V1Container>();
        for(int i=0;i<imageInfos.length;i++){
            V1Container v1Container = new V1Container();
            String imageName = imageInfos[i].getImageName();
            String containerName = imageInfos[i].getContainerName();
            int containerPort = imageInfos[i].getContainerPort();

            v1Container.setImage(imageName);
            v1Container.setName(containerName);
            v1Container.setPorts(Arrays.asList(new V1ContainerPort().containerPort(containerPort)));
            v1Container.setImagePullPolicy("IfNotPresent");
            containers.add(v1Container);
        }


        List<V1VolumeMount> v1VolumeMounts = new ArrayList<>();
        List<V1Volume> v1Volumes = new ArrayList<>();

        for(int i=0;i<volumeMountsInfos.length;i++){
            V1VolumeMount volumeMount = new V1VolumeMount();
            volumeMount.setMountPath(volumeMountsInfos[i].getMountPath());
            volumeMount.setName(volumeMountsInfos[i].getVolumeName());
            v1VolumeMounts.add(volumeMount);
            V1Volume volume =  new V1Volume();
            volume.setName(volumeMountsInfos[i].getVolumeName());
//            V1HostPathVolumeSource hostPath = new V1HostPathVolumeSource();
//            hostPath.setPath(volumeMountsInfos[i].getHostPath());
//            hostPath.setType("DirectoryOrCreate");
//            volume.setHostPath(hostPath);
            V1NFSVolumeSource v1NFSVolumeSource = new V1NFSVolumeSource();
            v1NFSVolumeSource.setServer(volumeMountsInfos[i].getServerIP());
            v1NFSVolumeSource.setReadOnly(false);
            v1NFSVolumeSource.setPath(volumeMountsInfos[i].getHostPath());
            volume.setNfs(v1NFSVolumeSource);
            v1Volumes.add(volume);
        }


        for(int i=0;i<containers.size();i++){
            containers.get(i).setVolumeMounts(v1VolumeMounts);
        }
        podSpec.setContainers(containers);
        podSpec.setVolumes(v1Volumes);
        template.setSpec(podSpec);
        spec.setTemplate(template);
        statefulSet.setSpec(spec);

        try {
            String kubeConfigPath = "./src/main/resources/config";
            // 创建 ApiClient 对象，用于连接到 Kubernetes 集群
            ApiClient client = ClientBuilder
                    .kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath)))
                    .build();
            Configuration.setDefaultApiClient(client);

            // 创建 AppsV1Api 对象，用于与 Apps API 进行交互
            AppsV1Api api = new AppsV1Api();

            V1StatefulSet createdStatefulSet = api.createNamespacedStatefulSet(namespace, statefulSet, null, null, null);
            System.out.println("StatefulSet created: " + createdStatefulSet.getMetadata().getName());
            return ResultVO.ok(createdStatefulSet);
        } catch (FileNotFoundException e) {
            return ResultVO.fail("File Not Found: " + e.getMessage());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch(ApiException e){
            System.out.println(e.getCode()+e.getResponseBody()+e.getResponseHeaders());
            e.printStackTrace();
            return ResultVO.fail("Error creating StatefulSet: " + + e.getCode()+e.getResponseBody()+e.getResponseHeaders());
        }
    }

}
