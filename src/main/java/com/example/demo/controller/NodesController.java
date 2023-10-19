package com.example.demo.controller;

import com.example.demo.model.vo.ResultVO;
import com.google.gson.GsonBuilder;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1NodeList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;

@RestController
@RequestMapping("/node")
@Slf4j
public class NodesController {
    @Autowired
    private CoreV1Api coreV1Api;

    @GetMapping("/")
    public ResultVO getAllPods() throws Exception {

        // 调用客户端API取得所有Node信息
        V1NodeList v1NodeList = coreV1Api.listNode(null, null, null, null, null, null, null, null, null);
        System.out.println(v1NodeList);
        return ResultVO.ok(new GsonBuilder().setPrettyPrinting().create().toJson(v1NodeList));

    }
}
