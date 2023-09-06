package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeploymentCreateDTO {
    private String deploymentName;
    private int replicaNum;
    private Map<String,String> labels;
    private Map<String,String> selectorLabels;
    private ImageDTO[] imageInfos;
    private String namespace;


}
