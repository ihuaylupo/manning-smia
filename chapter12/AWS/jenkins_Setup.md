## First Create an instance 

1. Choose the Using a Linux AMI 2 with type at least m4.large 
2. In the security group open traffic on port 8080.
3. Download the key pair and execute chmod 400 to the pem file.
4. Once created log into the EC2 instance

```bash
ssh -i elk-service.pem ec2-user@<EC2_INSTANCE_IPV4>
```

5. Install updates
sudo yum update

# JAVA 11
6. Install OpenJDK 11.

```bash
#Install OpenJDK 11 from amazon-linux-extras. If you don't see the extras you chose the wrong AMI.
sudo amazon-linux-extras install java-
#Verify Java version
java -version
#Go to root as su.
sudo su -
cd ~
# Path where Java is installed.
file /etc/alternatives/java
#Update the  bash profile and add the JAVA_HOME variable.
vi .bash_profile

------------------------------------------------------------------------
export JAVA_HOME="/usr/lib/jvm/java-11-openjdk-11.0.7.10-4.amzn2.0.1.x86_64"
PATH=$PATH:$HOME/bin:$JAVA_HOME
------------------------------------------------------------------------

#Execute this command to update and load the new info.
source .bash_profile

echo $JAVA_HOME
```

# JENKINS
7. Install jenkins

```bash
sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo
sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key
sudo yum install jenkins

service jenkins start
service jenkins status

```
8. Log into Jenkins http://<EC2_INSTANCE_IPV4>:8080/

```bash
#Retrieve generated password
cat /var/lib/jenkins/secrets/initialAdminPassword
```
Use the generated password to log in.

**IMPORTANT!** Install the suggested plugins and create a new admin user.

8. Set JAVA_HOME in JENKINS WEB.
```bash
Manage Jenkins -> Global Configuration -> JDK Installations
```

 - Uncheck Install Automatically

```bash
Name: JAVA_HOME
JAVA_HOME:  <Result_ECHO_JAVA_HOME> for example: /usr/lib/jvm/java-11-openjdk-11.0.7.10-4.amzn2.0.1.x86_64
Install Automatically -> Unchecked
```

Apply -> Save

# GIT
9. Install GIT

```bash
hostname jenkins
sudo su -
yum install git -y
```

#Install Git plugins
```bash
Go to Manage Jenkins -> Manage Plugins -> Available -> Github Plugin
Go to Manage Jenkins -> Manage Plugins -> Available -> Github Integration plugin 
```

Install without restart.

10. Set git in the Global Configuration tools 
```bash
Global Configuration Tools -> GiT Installations
```

```bash
Name: git
Path to Git Executable: /usr/bin/git
Install Automatically -> Unchecked
```

Apply -> Save

# Maven
11. Setup Maven

```bash
#Download Maven bin.tar.gz in /opt
cd /opt
wget https://mirrors.ucr.ac.cr/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz


[root@jenkins opt]# ls -l
total 9284
-rw-r--r-- 1 root root 9506321 Nov 19  2019 apache-maven-3.6.3-bin.tar.gz
drwxr-xr-x 4 root root      33 May 27 00:47 aws
drwxr-xr-x 2 root root       6 Aug 16  2018 rh

#Extract info
tar -xvzf apache-maven-3.6.3-bin.tar.gz

[root@jenkins opt]# ls -l
total 9284
drwxr-xr-x 6 root root      99 Jun 22 03:09 apache-maven-3.6.3
-rw-r--r-- 1 root root 9506321 Nov 19  2019 apache-maven-3.6.3-bin.tar.gz
drwxr-xr-x 4 root root      33 May 27 00:47 aws
drwxr-xr-x 2 root root       6 Aug 16  2018 rh

#Rename folder
mv apache-maven-3.6.3 maven


vi ~/.bash_profile

------------------------------------------------------------------------
# User specific environment and startup programs
export JAVA_HOME="/usr/lib/jvm/java-11-openjdk-11.0.7.10-4.amzn2.0.1.x86_64"
M2_HOME=/opt/maven
M2=/opt/maven/bin
PATH=$PATH:$HOME/bin:$JAVA_HOME:$M2:$M2_HOME
export PATH
------------------------------------------------------------------------
#Refresh info.
source ~/.bash_profile
#Check maven version
mvn --version
```

12. Install Maven plugins: 
```bash
Manage Jenkins -> Manage Plugins -> Available -> Maven Invoker 
Manage Jenkins -> Manage Plugins -> Available -> Maven Integration
``` 

Install without restart.

13. Setup maven in global configurations.

```bash
Global Configuration Tools -> GIT Installations

Name: M2_HOME
MAVEN_HOME: /opt/maven
Install Automatically -> Unchecked
```

Apply -> Save

# DOCKER
11. Install Docker

```bash
sudo amazon-linux-extras install docker
sudo service docker start
sudo systemctl enable docker
sudo usermod -a -G docker jenkins
sudo usermod -a -G docker ec2-user
```
12. Install Docker Plugin
```bash
Manage Jenkins -> Manage Plugins -> Available -> Docker Pipeline plugin 
```

13. Install ECR Plugin
14. Create an IAM user, called **ecr-user** with the role -> **AmazonEC2ContainerRegistryFullAccess**   

**IMPORTANT!** Download the CSV with the Credentials.

15. Create Jenkins Credentials
Manage Jenkins -> Manage Credentials -> Jenkins -> Global Credentials -> Add credential (Left side option)

```bash
ID: ecr-user
Description: ecr-user
Access Key ID: <KEY_DOWNLOADED_IN_STEP_14>
Secret Access Key: <KEY_DOWNLOADED_IN_STEP_14>
```

Save.

# Kubernetes
16. Install Kubectl

```bash
sudo su -
curl -o kubectl https://amazon-eks.s3.us-west-2.amazonaws.com/1.16.8/2020-04-16/bin/linux/amd64/kubectl
chmod +x ./kubectl
sudo mv ./kubectl /usr/local/bin/kubectl
kubectl version --short --client
aws configure
aws eks --region us-east-2 update-kubeconfig --name ostock-dev-cluster
Added new context arn:aws:eks:us-east-2:8XXXXXXXXXXX3:cluster/ostock-dev-cluster to /root/kubeconfig
kubectl get svc

cat > ~/.kube/config-ostock-dev-cluster
export KUBECONFIG=$KUBECONFIG:~/.kube/config-ostock-dev-cluster
echo $KUBECONFIG
kubectl get svc
```


# Useful links
https://docs.aws.amazon.com/es_es/eks/latest/userguide/create-kubeconfig.html
https://docs.aws.amazon.com/eks/latest/userguide/create-kubeconfig.html