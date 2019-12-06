# Spring-Boot-Maven-Test

This is a barebones Spring Boot project testing structural interdependencies among parent, child and sibling (Java) Maven modules, and migrating them to cloud hosting using Kubernetes.

To re-create the process:

1. Clone the entire project: https://github.com/CoalChris/Spring-Boot-Maven-Test.git
2. Modify the deployment.yaml, service.yaml files in "[service]/k8s" folder and "[service]/src/resources/application.properties" file to reflect desired ports and versions.
3. To re-compile the code and output the required JAR library, run Maven command with goals "clean package"
4. Upload to GitHub

For this project, we will migrate to Google Cloud Platform, but feel free to use another cloud services provider.

5. Create a Google Cloud Platform project
6. Enable Kubernetes Engine
7. Create a Kubernetes Cluster and assign it with 1 or more compute engines / nodes
8. Open Cloud Shell console for the project (You may have to copy the given command to connect the console to your project)
9. Clone your repository from GitHub

For each service, move to the main directory of the service (e.g. "name-service" folder) and execute the following commands:

10. This will build your Docker image, using Dockerfile, for containers to run on: "docker build -t gcr.io/[your-Google-Cloud-Platform-project-name]/[service]:[version-numbers] ."
11. Push the Docker image to the Docker Hub registry: "docker push gcr.io/[your-Google-Cloud-Platform-project-name]/[service]:[version-numbers]"
12. Move to "k8s" folder
13. Create a Kubernetes pod: "kubectl create -f deployment.yaml". Check status: "kubectl get pods"
14. Copy the NAME of the pod
15. Create a Kubernetes service: "kubectl create -f service.yaml". Check status: "kubectl get svc"
16. Make note of the service port number, between 30000 and 32767
17. Install curl command in the container: "kubectl exec [pod-name] apk add curl"
18. Check the service runs properly: "kubectl exec [pod-name] curl http://localhost:[port-number]". Default port is 8050 for name-service and 8051 for hello-service
19. (Optional) If connection is failing, go to Google Cloud Platform main menu -> VPC Network -> Firewall Rules -> Create Firewall Rule. Add a name, ensure targets is set to "All instances in the network", IP ranges is "0.0.0.0/0" and the tcp port for your Kubernetes service should be allowed. Click the "Create" button
20. Check your nodes' external IP address(es): "kubectl get nodes -o wide"
21. Test that your service is accessible: "curl http://[node-external-ip]:[service-port-number]"
22. Test via browser by accessing the URL "http://[node-external-ip]:[service-port-number]"
