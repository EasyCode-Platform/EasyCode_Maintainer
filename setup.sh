sudo kubeadm reset
sudo kubeadm init --image-repository=registry.aliyuncs.com/google_containers
sudo rm -rf $HOME/.kube
sudo mkdir -p $HOME/.kube
sudo cp /etc/kubernetes/admin.conf $HOME/.kube/config
sudo cp /etc/kubernetes/admin.conf /home/ragan/demo/src/main/resources/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config
sudo chown $(id -u):$(id -g) /home/ragan/demo/src/main/resources/config