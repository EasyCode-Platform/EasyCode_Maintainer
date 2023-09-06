# kubernetes-controller
> a small Springboot application to control kubernetes computing resources for a low code develop platform

## RUN
1. copy kubernetes configuration file from /etc/kubernetes/admin.conf to ~/src/main/resources/config
2. RUN `mvn clean package` and `java -jar target/demo-0.0.1-SNAPSHOT.jar` to RUN this application on your computer

## Api description
### Node
#### GET /node/
Request: None

Response: A data describe a list of nodes

example:
``` json
{
    "apiVersion": "v1",
    "items": [
        {
            "apiVersion": "v1",
            "kind": "Node",
            "metadata": {
                "annotations": {
                    "kubeadm.alpha.kubernetes.io/cri-socket": "/var/run/dockershim.sock",
                    "node.alpha.kubernetes.io/ttl": "0",
                    "volumes.kubernetes.io/controller-managed-attach-detach": "true"
                },
                "creationTimestamp": "2023-09-01T10:52:53Z",
                "labels": {
                    "beta.kubernetes.io/arch": "amd64",
                    "beta.kubernetes.io/os": "linux",
                    "kubernetes.io/arch": "amd64",
                    "kubernetes.io/hostname": "master",
                    "kubernetes.io/os": "linux",
                    "node-role.kubernetes.io/control-plane": "",
                    "node-role.kubernetes.io/master": "",
                    "node.kubernetes.io/exclude-from-external-load-balancers": ""
                },
                "name": "master",
                "resourceVersion": "34036",
                "uid": "80e9f97e-5dc3-4f12-89ad-5670d39bf825"
            },
            "spec": {
                "taints": [
                    {
                        "effect": "NoSchedule",
                        "key": "node-role.kubernetes.io/master"
                    }
                ]
            },
            "status": {
                "addresses": [
                    {
                        "address": "192.168.17.138",
                        "type": "InternalIP"
                    },
                    {
                        "address": "master",
                        "type": "Hostname"
                    }
                ],
                "allocatable": {
                    "cpu": "8",
                    "ephemeral-storage": "66050017581",
                    "hugepages-1Gi": "0",
                    "hugepages-2Mi": "0",
                    "memory": "8014940Ki",
                    "pods": "110"
                },
                "capacity": {
                    "cpu": "8",
                    "ephemeral-storage": "71668856Ki",
                    "hugepages-1Gi": "0",
                    "hugepages-2Mi": "0",
                    "memory": "8117340Ki",
                    "pods": "110"
                },
                "conditions": [
                    {
                        "lastHeartbeatTime": "2023-09-03T13:41:43Z",
                        "lastTransitionTime": "2023-09-01T10:52:53Z",
                        "message": "kubelet has sufficient memory available",
                        "reason": "KubeletHasSufficientMemory",
                        "status": "False",
                        "type": "MemoryPressure"
                    },
                    {
                        "lastHeartbeatTime": "2023-09-03T13:41:43Z",
                        "lastTransitionTime": "2023-09-01T10:52:53Z",
                        "message": "kubelet has no disk pressure",
                        "reason": "KubeletHasNoDiskPressure",
                        "status": "False",
                        "type": "DiskPressure"
                    },
                    {
                        "lastHeartbeatTime": "2023-09-03T13:41:43Z",
                        "lastTransitionTime": "2023-09-01T10:52:53Z",
                        "message": "kubelet has sufficient PID available",
                        "reason": "KubeletHasSufficientPID",
                        "status": "False",
                        "type": "PIDPressure"
                    },
                    {
                        "lastHeartbeatTime": "2023-09-03T13:41:43Z",
                        "lastTransitionTime": "2023-09-01T10:52:56Z",
                        "message": "kubelet is posting ready status. AppArmor enabled",
                        "reason": "KubeletReady",
                        "status": "True",
                        "type": "Ready"
                    }
                ],
                "daemonEndpoints": {
                    "kubeletEndpoint": {
                        "Port": 10250
                    }
                },
                "images": [
                    {
                        "names": [
                            "illasoft/illa-builder@sha256:cca09267763afacb4b599d9606356dfd9d4364a5abecf98c05adc22bd54d1115",
                            "illasoft/illa-builder:latest"
                        ],
                        "sizeBytes": 1457518567
                    },
                    {
                        "names": [
                            "kicbase/stable@sha256:bf2d9f1e9d837d8deea073611d2605405b6be904647d97ebd9b12045ddfe1106",
                            "kicbase/stable:v0.0.39"
                        ],
                        "sizeBytes": 1052720355
                    },
                    {
                        "names": [
                            "chatbox:latest"
                        ],
                        "sizeBytes": 1013581532
                    },
                    {
                        "names": [
                            "\u003cnone\u003e@\u003cnone\u003e",
                            "\u003cnone\u003e:\u003cnone\u003e"
                        ],
                        "sizeBytes": 1008177264
                    },
                    {
                        "names": [
                            "php@sha256:a0a0df5711e1caef1120ed9206fce24d3ea8549fc6e066104a1bc0175226e2f0",
                            "php:8.0-fpm"
                        ],
                        "sizeBytes": 461121498
                    },
                    {
                        "names": [
                            "k8s.gcr.io/etcd:3.5.7-0"
                        ],
                        "sizeBytes": 295724043
                    },
                    {
                        "names": [
                            "registry.aliyuncs.com/google_containers/etcd@sha256:9ce33ba33d8e738a5b85ed50b5080ac746deceed4a7496c550927a7a19ca3b6d",
                            "registry.aliyuncs.com/google_containers/etcd:3.5.0-0"
                        ],
                        "sizeBytes": 294536887
                    },
                    {
                        "names": [
                            "nginx@sha256:0d17b565c37bcbd895e9d92315a05c1c3c9a29f762b011a10c54a66cd53c9b31",
                            "nginx:latest"
                        ],
                        "sizeBytes": 141479488
                    },
                    {
                        "names": [
                            "registry.aliyuncs.com/google_containers/kube-apiserver@sha256:460f00380eadc5bb6239e0cb0e6108ab84e4fd3bc19556682f69362f1cfaee32",
                            "registry.aliyuncs.com/google_containers/kube-apiserver:v1.22.17"
                        ],
                        "sizeBytes": 128376381
                    },
                    {
                        "names": [
                            "registry.aliyuncs.com/google_containers/kube-controller-manager@sha256:48053335aba5ec857829396fe6ab299c10ea65c8e0248149e02d96cbd0b581aa",
                            "registry.aliyuncs.com/google_containers/kube-controller-manager:v1.22.17"
                        ],
                        "sizeBytes": 122064738
                    },
                    {
                        "names": [
                            "k8s.gcr.io/kube-apiserver:v1.27.4"
                        ],
                        "sizeBytes": 120653626
                    },
                    {
                        "names": [
                            "k8s.gcr.io/kube-controller-manager:v1.27.4"
                        ],
                        "sizeBytes": 112507033
                    },
                    {
                        "names": [
                            "registry.aliyuncs.com/google_containers/kube-proxy@sha256:cfb5210283cd2c1aca30f8f89e64b9ca72a5d3f6bd82810897a1612c6f9c8536",
                            "registry.aliyuncs.com/google_containers/kube-proxy:v1.22.17"
                        ],
                        "sizeBytes": 103636854
                    },
                    {
                        "names": [
                            "netcorecore/weave-kube@sha256:3d04dfb38e965daa9258fc4dd14a91f9f6471b73c0d3127652b97c08ccdc8ddb",
                            "netcorecore/weave-kube:v2.8.1"
                        ],
                        "sizeBytes": 89037656
                    },
                    {
                        "names": [
                            "k8s.gcr.io/kube-proxy:v1.27.4"
                        ],
                        "sizeBytes": 71122088
                    },
                    {
                        "names": [
                            "flannel/flannel@sha256:5f83f1243057458e27249157394e3859cf31cc075354af150d497f2ebc8b54db",
                            "flannel/flannel:v0.22.0"
                        ],
                        "sizeBytes": 69830322
                    },
                    {
                        "names": [
                            "k8s.gcr.io/kube-scheduler:v1.27.4"
                        ],
                        "sizeBytes": 58390668
                    },
                    {
                        "names": [
                            "k8s.gcr.io/coredns:1.10.1"
                        ],
                        "sizeBytes": 53612153
                    },
                    {
                        "names": [
                            "registry.aliyuncs.com/google_containers/kube-scheduler@sha256:bd7642f0d25996df2daa0b355cf4f1886c72a9573a2f0772c214f32a3441b277",
                            "registry.aliyuncs.com/google_containers/kube-scheduler:v1.22.17"
                        ],
                        "sizeBytes": 52715349
                    },
                    {
                        "names": [
                            "registry.aliyuncs.com/google_containers/coredns@sha256:6e5a02c21641597998b4be7cb5eb1e7b02c0d8d23cce4dd09f4682d463798890",
                            "registry.aliyuncs.com/google_containers/coredns:v1.8.4"
                        ],
                        "sizeBytes": 47554275
                    },
                    {
                        "names": [
                            "netcorecore/weave-npc@sha256:2be329164796241e72c530c4c8df5faf4e82fead28372a8cdbb651e74d4dba0a",
                            "netcorecore/weave-npc:v2.8.1"
                        ],
                        "sizeBytes": 39273789
                    },
                    {
                        "names": [
                            "flannel/flannel-cni-plugin@sha256:bf4b62b131666d040f35a327d906ee5a3418280b68a88d9b9c7e828057210443",
                            "flannel/flannel-cni-plugin:v1.1.2"
                        ],
                        "sizeBytes": 7966554
                    },
                    {
                        "names": [
                            "kicbase/echo-server@sha256:127ac38a2bb9537b7f252addff209ea6801edcac8a92c8b1104dacd66a583ed6",
                            "kicbase/echo-server:latest"
                        ],
                        "sizeBytes": 4939776
                    },
                    {
                        "names": [
                            "k8s.gcr.io/pause:3.9"
                        ],
                        "sizeBytes": 743952
                    },
                    {
                        "names": [
                            "registry.aliyuncs.com/google_containers/pause@sha256:1ff6c18fbef2045af6b9c16bf034cc421a29027b800e4f9b68ae9b1cb3e9ae07",
                            "registry.aliyuncs.com/google_containers/pause:3.5"
                        ],
                        "sizeBytes": 682696
                    }
                ],
                "nodeInfo": {
                    "architecture": "amd64",
                    "bootID": "6887f59d-3a35-404e-8fc3-b3299dab5e44",
                    "containerRuntimeVersion": "docker://20.10.21",
                    "kernelVersion": "5.4.0-150-generic",
                    "kubeProxyVersion": "v1.22.4",
                    "kubeletVersion": "v1.22.4",
                    "machineID": "cca4f88bb4f2419e80d3762d7ced7abc",
                    "operatingSystem": "linux",
                    "osImage": "Ubuntu 18.04.6 LTS",
                    "systemUUID": "96424d56-f906-3804-ff3f-2d695ff63ade"
                }
            }
        },
        {
            "apiVersion": "v1",
            "kind": "Node",
            "metadata": {
                "annotations": {
                    "kubeadm.alpha.kubernetes.io/cri-socket": "/var/run/dockershim.sock",
                    "node.alpha.kubernetes.io/ttl": "0",
                    "volumes.kubernetes.io/controller-managed-attach-detach": "true"
                },
                "creationTimestamp": "2023-09-03T12:44:59Z",
                "labels": {
                    "beta.kubernetes.io/arch": "amd64",
                    "beta.kubernetes.io/os": "linux",
                    "kubernetes.io/arch": "amd64",
                    "kubernetes.io/hostname": "node2",
                    "kubernetes.io/os": "linux"
                },
                "name": "node2",
                "resourceVersion": "33920",
                "uid": "b611a609-26ba-4a70-a724-0b219e24c676"
            },
            "spec": {},
            "status": {
                "addresses": [
                    {
                        "address": "192.168.17.136",
                        "type": "InternalIP"
                    },
                    {
                        "address": "node2",
                        "type": "Hostname"
                    }
                ],
                "allocatable": {
                    "cpu": "2",
                    "ephemeral-storage": "52023567889",
                    "hugepages-1Gi": "0",
                    "hugepages-2Mi": "0",
                    "memory": "8014940Ki",
                    "pods": "110"
                },
                "capacity": {
                    "cpu": "2",
                    "ephemeral-storage": "56449184Ki",
                    "hugepages-1Gi": "0",
                    "hugepages-2Mi": "0",
                    "memory": "8117340Ki",
                    "pods": "110"
                },
                "conditions": [
                    {
                        "lastHeartbeatTime": "2023-09-03T13:40:17Z",
                        "lastTransitionTime": "2023-09-03T12:44:59Z",
                        "message": "kubelet has sufficient memory available",
                        "reason": "KubeletHasSufficientMemory",
                        "status": "False",
                        "type": "MemoryPressure"
                    },
                    {
                        "lastHeartbeatTime": "2023-09-03T13:40:17Z",
                        "lastTransitionTime": "2023-09-03T12:44:59Z",
                        "message": "kubelet has no disk pressure",
                        "reason": "KubeletHasNoDiskPressure",
                        "status": "False",
                        "type": "DiskPressure"
                    },
                    {
                        "lastHeartbeatTime": "2023-09-03T13:40:17Z",
                        "lastTransitionTime": "2023-09-03T12:44:59Z",
                        "message": "kubelet has sufficient PID available",
                        "reason": "KubeletHasSufficientPID",
                        "status": "False",
                        "type": "PIDPressure"
                    },
                    {
                        "lastHeartbeatTime": "2023-09-03T13:40:17Z",
                        "lastTransitionTime": "2023-09-03T12:45:09Z",
                        "message": "kubelet is posting ready status. AppArmor enabled",
                        "reason": "KubeletReady",
                        "status": "True",
                        "type": "Ready"
                    }
                ],
                "daemonEndpoints": {
                    "kubeletEndpoint": {
                        "Port": 10250
                    }
                },
                "images": [
                    {
                        "names": [
                            "nginx@sha256:0d17b565c37bcbd895e9d92315a05c1c3c9a29f762b011a10c54a66cd53c9b31",
                            "nginx:latest"
                        ],
                        "sizeBytes": 141479488
                    },
                    {
                        "names": [
                            "registry.aliyuncs.com/google_containers/kube-proxy@sha256:cfb5210283cd2c1aca30f8f89e64b9ca72a5d3f6bd82810897a1612c6f9c8536",
                            "registry.aliyuncs.com/google_containers/kube-proxy:v1.22.17"
                        ],
                        "sizeBytes": 103636854
                    },
                    {
                        "names": [
                            "netcorecore/weave-kube@sha256:3d04dfb38e965daa9258fc4dd14a91f9f6471b73c0d3127652b97c08ccdc8ddb",
                            "netcorecore/weave-kube:v2.8.1"
                        ],
                        "sizeBytes": 89037656
                    },
                    {
                        "names": [
                            "netcorecore/weave-npc@sha256:2be329164796241e72c530c4c8df5faf4e82fead28372a8cdbb651e74d4dba0a",
                            "netcorecore/weave-npc:v2.8.1"
                        ],
                        "sizeBytes": 39273789
                    },
                    {
                        "names": [
                            "registry.aliyuncs.com/google_containers/pause@sha256:1ff6c18fbef2045af6b9c16bf034cc421a29027b800e4f9b68ae9b1cb3e9ae07",
                            "registry.aliyuncs.com/google_containers/pause:3.5"
                        ],
                        "sizeBytes": 682696
                    }
                ],
                "nodeInfo": {
                    "architecture": "amd64",
                    "bootID": "11aab2e0-1084-47cc-ba65-6e32e2c6393f",
                    "containerRuntimeVersion": "docker://20.10.21",
                    "kernelVersion": "5.4.0-150-generic",
                    "kubeProxyVersion": "v1.22.4",
                    "kubeletVersion": "v1.22.4",
                    "machineID": "35bf797fb3074e4c83bed8930a44f184",
                    "operatingSystem": "linux",
                    "osImage": "Ubuntu 18.04.6 LTS",
                    "systemUUID": "395d4d56-d3ae-acb6-9321-1166d39c8ecd"
                }
            }
        }
    ],
    "kind": "List",
    "metadata": {
        "resourceVersion": ""
    }
}


```
### Pod
GET /pod/
Request:None
Response: A data that describe a list of pods
example:
``` json
{
    "apiVersion": "v1",
    "items": [
        {
            "apiVersion": "v1",
            "kind": "Pod",
            "metadata": {
                "creationTimestamp": "2023-09-01T10:54:13Z",
                "name": "pod-12",
                "namespace": "default",
                "resourceVersion": "29583",
                "uid": "018e8622-bbc8-44bb-a459-93d07e66ca9e"
            },
            "spec": {
                "containers": [
                    {
                        "image": "nginx:latest",
                        "imagePullPolicy": "Always",
                        "name": "nginx-container",
                        "ports": [
                            {
                                "containerPort": 8053,
                                "protocol": "TCP"
                            }
                        ],
                        "resources": {
                            "limits": {
                                "cpu": "1"
                            },
                            "requests": {
                                "cpu": "1"
                            }
                        },
                        "terminationMessagePath": "/dev/termination-log",
                        "terminationMessagePolicy": "File",
                        "volumeMounts": [
                            {
                                "mountPath": "/var/run/secrets/kubernetes.io/serviceaccount",
                                "name": "kube-api-access-ws6pj",
                                "readOnly": true
                            }
                        ]
                    },
                    {
                        "image": "mysql:latest",
                        "imagePullPolicy": "Always",
                        "name": "mysql-container",
                        "ports": [
                            {
                                "containerPort": 8053,
                                "protocol": "TCP"
                            }
                        ],
                        "resources": {
                            "limits": {
                                "cpu": "1"
                            },
                            "requests": {
                                "cpu": "1"
                            }
                        },
                        "terminationMessagePath": "/dev/termination-log",
                        "terminationMessagePolicy": "File",
                        "volumeMounts": [
                            {
                                "mountPath": "/var/run/secrets/kubernetes.io/serviceaccount",
                                "name": "kube-api-access-ws6pj",
                                "readOnly": true
                            }
                        ]
                    },
                    {
                        "image": "mongodb:latest",
                        "imagePullPolicy": "Always",
                        "name": "mongodb-container",
                        "ports": [
                            {
                                "containerPort": 8053,
                                "protocol": "TCP"
                            }
                        ],
                        "resources": {
                            "limits": {
                                "cpu": "1"
                            },
                            "requests": {
                                "cpu": "1"
                            }
                        },
                        "terminationMessagePath": "/dev/termination-log",
                        "terminationMessagePolicy": "File",
                        "volumeMounts": [
                            {
                                "mountPath": "/var/run/secrets/kubernetes.io/serviceaccount",
                                "name": "kube-api-access-ws6pj",
                                "readOnly": true
                            }
                        ]
                    }
                ],
                "dnsPolicy": "ClusterFirst",
                "enableServiceLinks": true,
                "preemptionPolicy": "PreemptLowerPriority",
                "priority": 0,
                "restartPolicy": "Always",
                "schedulerName": "default-scheduler",
                "securityContext": {},
                "serviceAccount": "default",
                "serviceAccountName": "default",
                "terminationGracePeriodSeconds": 30,
                "tolerations": [
                    {
                        "effect": "NoExecute",
                        "key": "node.kubernetes.io/not-ready",
                        "operator": "Exists",
                        "tolerationSeconds": 300
                    },
                    {
                        "effect": "NoExecute",
                        "key": "node.kubernetes.io/unreachable",
                        "operator": "Exists",
                        "tolerationSeconds": 300
                    }
                ],
                "volumes": [
                    {
                        "name": "kube-api-access-ws6pj",
                        "projected": {
                            "defaultMode": 420,
                            "sources": [
                                {
                                    "serviceAccountToken": {
                                        "expirationSeconds": 3607,
                                        "path": "token"
                                    }
                                },
                                {
                                    "configMap": {
                                        "items": [
                                            {
                                                "key": "ca.crt",
                                                "path": "ca.crt"
                                            }
                                        ],
                                        "name": "kube-root-ca.crt"
                                    }
                                },
                                {
                                    "downwardAPI": {
                                        "items": [
                                            {
                                                "fieldRef": {
                                                    "apiVersion": "v1",
                                                    "fieldPath": "metadata.namespace"
                                                },
                                                "path": "namespace"
                                            }
                                        ]
                                    }
                                }
                            ]
                        }
                    }
                ]
            },
            "status": {
                "conditions": [
                    {
                        "lastProbeTime": null,
                        "lastTransitionTime": "2023-09-01T10:54:13Z",
                        "message": "0/2 nodes are available: 1 Insufficient cpu, 1 node(s) had taint {node-role.kubernetes.io/master: }, that the pod didn't tolerate.",
                        "reason": "Unschedulable",
                        "status": "False",
                        "type": "PodScheduled"
                    }
                ],
                "phase": "Pending",
                "qosClass": "Burstable"
            }
        },
        {
            "apiVersion": "v1",
            "kind": "Pod",
            "metadata": {
                "creationTimestamp": "2023-09-01T12:45:14Z",
                "labels": {
                    "name": "myapp1-backend"
                },
                "name": "pod-1234",
                "namespace": "default",
                "resourceVersion": "29623",
                "uid": "d28e23d4-a103-4924-af54-50b5bf77c987"
            },
            "spec": {
                "containers": [
                    {
                        "image": "nginx:latest",
                        "imagePullPolicy": "Always",
                        "name": "nginx-container",
                        "ports": [
                            {
                                "containerPort": 8053,
                                "protocol": "TCP"
                            }
                        ],
                        "resources": {
                            "limits": {
                                "cpu": "1"
                            },
                            "requests": {
                                "cpu": "1"
                            }
                        },
                        "terminationMessagePath": "/dev/termination-log",
                        "terminationMessagePolicy": "File",
                        "volumeMounts": [
                            {
                                "mountPath": "/var/run/secrets/kubernetes.io/serviceaccount",
                                "name": "kube-api-access-mbm7l",
                                "readOnly": true
                            }
                        ]
                    },
                    {
                        "image": "mysql:latest",
                        "imagePullPolicy": "Always",
                        "name": "mysql-container",
                        "ports": [
                            {
                                "containerPort": 8053,
                                "protocol": "TCP"
                            }
                        ],
                        "resources": {
                            "limits": {
                                "cpu": "1"
                            },
                            "requests": {
                                "cpu": "1"
                            }
                        },
                        "terminationMessagePath": "/dev/termination-log",
                        "terminationMessagePolicy": "File",
                        "volumeMounts": [
                            {
                                "mountPath": "/var/run/secrets/kubernetes.io/serviceaccount",
                                "name": "kube-api-access-mbm7l",
                                "readOnly": true
                            }
                        ]
                    },
                    {
                        "image": "mongodb:latest",
                        "imagePullPolicy": "Always",
                        "name": "mongodb-container",
                        "ports": [
                            {
                                "containerPort": 8053,
                                "protocol": "TCP"
                            }
                        ],
                        "resources": {
                            "limits": {
                                "cpu": "1"
                            },
                            "requests": {
                                "cpu": "1"
                            }
                        },
                        "terminationMessagePath": "/dev/termination-log",
                        "terminationMessagePolicy": "File",
                        "volumeMounts": [
                            {
                                "mountPath": "/var/run/secrets/kubernetes.io/serviceaccount",
                                "name": "kube-api-access-mbm7l",
                                "readOnly": true
                            }
                        ]
                    }
                ],
                "dnsPolicy": "ClusterFirst",
                "enableServiceLinks": true,
                "preemptionPolicy": "PreemptLowerPriority",
                "priority": 0,
                "restartPolicy": "Always",
                "schedulerName": "default-scheduler",
                "securityContext": {},
                "serviceAccount": "default",
                "serviceAccountName": "default",
                "terminationGracePeriodSeconds": 30,
                "tolerations": [
                    {
                        "effect": "NoExecute",
                        "key": "node.kubernetes.io/not-ready",
                        "operator": "Exists",
                        "tolerationSeconds": 300
                    },
                    {
                        "effect": "NoExecute",
                        "key": "node.kubernetes.io/unreachable",
                        "operator": "Exists",
                        "tolerationSeconds": 300
                    }
                ],
                "volumes": [
                    {
                        "name": "kube-api-access-mbm7l",
                        "projected": {
                            "defaultMode": 420,
                            "sources": [
                                {
                                    "serviceAccountToken": {
                                        "expirationSeconds": 3607,
                                        "path": "token"
                                    }
                                },
                                {
                                    "configMap": {
                                        "items": [
                                            {
                                                "key": "ca.crt",
                                                "path": "ca.crt"
                                            }
                                        ],
                                        "name": "kube-root-ca.crt"
                                    }
                                },
                                {
                                    "downwardAPI": {
                                        "items": [
                                            {
                                                "fieldRef": {
                                                    "apiVersion": "v1",
                                                    "fieldPath": "metadata.namespace"
                                                },
                                                "path": "namespace"
                                            }
                                        ]
                                    }
                                }
                            ]
                        }
                    }
                ]
            },
            "status": {
                "conditions": [
                    {
                        "lastProbeTime": null,
                        "lastTransitionTime": "2023-09-01T12:45:14Z",
                        "message": "0/2 nodes are available: 1 Insufficient cpu, 1 node(s) had taint {node-role.kubernetes.io/master: }, that the pod didn't tolerate.",
                        "reason": "Unschedulable",
                        "status": "False",
                        "type": "PodScheduled"
                    }
                ],
                "phase": "Pending",
                "qosClass": "Burstable"
            }
        }
    ],
    "kind": "List",
    "metadata": {
        "resourceVersion": ""
    }
}
```

### Service


### Namespace
GET /namespace/
Request: None

Response: A data containing a list of namespaces:
```json
{
    "apiVersion": "v1",
    "items": [
        {
            "apiVersion": "v1",
            "kind": "Namespace",
            "metadata": {
                "creationTimestamp": "2023-09-01T10:52:55Z",
                "labels": {
                    "kubernetes.io/metadata.name": "default"
                },
                "name": "default",
                "resourceVersion": "193",
                "uid": "f75847fb-c1b5-4969-874c-ed98843e003f"
            },
            "spec": {
                "finalizers": [
                    "kubernetes"
                ]
            },
            "status": {
                "phase": "Active"
            }
        },
        {
            "apiVersion": "v1",
            "kind": "Namespace",
            "metadata": {
                "creationTimestamp": "2023-09-01T10:52:53Z",
                "labels": {
                    "kubernetes.io/metadata.name": "kube-node-lease"
                },
                "name": "kube-node-lease",
                "resourceVersion": "16",
                "uid": "5c04d8c0-d671-4ffb-a155-b93bc3149c02"
            },
            "spec": {
                "finalizers": [
                    "kubernetes"
                ]
            },
            "status": {
                "phase": "Active"
            }
        },
        {
            "apiVersion": "v1",
            "kind": "Namespace",
            "metadata": {
                "creationTimestamp": "2023-09-01T10:52:53Z",
                "labels": {
                    "kubernetes.io/metadata.name": "kube-public"
                },
                "name": "kube-public",
                "resourceVersion": "13",
                "uid": "afcdaf23-f2f6-49a5-b5c6-14cefe89980b"
            },
            "spec": {
                "finalizers": [
                    "kubernetes"
                ]
            },
            "status": {
                "phase": "Active"
            }
        },
        {
            "apiVersion": "v1",
            "kind": "Namespace",
            "metadata": {
                "creationTimestamp": "2023-09-01T10:52:53Z",
                "labels": {
                    "kubernetes.io/metadata.name": "kube-system"
                },
                "name": "kube-system",
                "resourceVersion": "10",
                "uid": "7cf88801-65f2-442a-9126-6c94663337db"
            },
            "spec": {
                "finalizers": [
                    "kubernetes"
                ]
            },
            "status": {
                "phase": "Active"
            }
        },
        {
            "apiVersion": "v1",
            "kind": "Namespace",
            "metadata": {
                "creationTimestamp": "2023-09-01T12:31:57Z",
                "labels": {
                    "kubernetes.io/metadata.name": "namespace1"
                },
                "name": "namespace1",
                "resourceVersion": "7692",
                "uid": "57df9483-9f09-45da-9477-fd3a691b89f2"
            },
            "spec": {
                "finalizers": [
                    "kubernetes"
                ]
            },
            "status": {
                "phase": "Active"
            }
        },
        {
            "apiVersion": "v1",
            "kind": "Namespace",
            "metadata": {
                "creationTimestamp": "2023-09-01T12:38:33Z",
                "labels": {
                    "kubernetes.io/metadata.name": "namespace123"
                },
                "name": "namespace123",
                "resourceVersion": "8178",
                "uid": "0d3d4d5b-9d39-42ef-80c4-05ddf4825e99"
            },
            "spec": {
                "finalizers": [
                    "kubernetes"
                ]
            },
            "status": {
                "phase": "Active"
            }
        }
    ],
    "kind": "List",
    "metadata": {
        "resourceVersion": ""
    }
}

```
