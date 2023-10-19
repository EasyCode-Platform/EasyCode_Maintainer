package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodePortCreateDTO {
    private String nodePortName;
    private ArrayList<Map<String,String>> portList;
    private Map<String,String> selectLabels;

    private String namespace;

}
