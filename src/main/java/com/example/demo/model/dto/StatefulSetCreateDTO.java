package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatefulSetCreateDTO {
    private String statefulSetName;
    private int replicaNum;
    private Map<String,String> labels;
    private Map<String,String> selectorLabels;
    private ImageDTO[] imageInfos;

    private VolumeMountsInfoDTO[] volumeMountsInfos; // 一一对应
    private String namespace;



}
