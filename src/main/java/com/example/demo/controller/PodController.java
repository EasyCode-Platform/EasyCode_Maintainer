package com.example.demo.controller;

import com.google.gson.GsonBuilder;
import com.example.demo.model.vo.ResultVO;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;

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

    @GetMapping("/")
    public ResultVO getAllPods() throws Exception {
        String kubeConfigPath = "./src/main/resources/config";
        ApiClient client = ClientBuilder
                .kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath)))
                .build();
        Configuration.setDefaultApiClient(client);
        CoreV1Api api = new CoreV1Api();

        // 调用客户端API取得所有pod信息
        V1PodList v1PodList = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
        return ResultVO.ok(new GsonBuilder().setPrettyPrinting().create().toJson(v1PodList));

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

        return v1PodList;
    }

}
