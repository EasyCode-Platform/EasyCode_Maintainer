package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolumeMountsInfoDTO {

    private String mountPath; //容器里面的挂载路径
    private String volumeName; //卷名字
    private String hostPath; // 节点上的路径

}