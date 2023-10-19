package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PodCreateDTO {
    private String podName;

    private String namespace;

    private Map<String,String> labels;
    private ImageDTO[] imageInfos;


//    private  String containerName;
//    private String imageName;


}
