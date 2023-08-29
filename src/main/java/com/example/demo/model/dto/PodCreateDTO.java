package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PodCreateDTO {
    private  String podName;

    private ImageDTO[] imageInfos;

//    private  String containerName;
//    private String imageName;


}
