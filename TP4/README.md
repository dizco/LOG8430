# LOG8430-TP4

## What is this?
This project was built to experiment with Spark configurations. It demoes a simple API where you can add receipts, and subsequently query for the most frequent items in the receipts. The objective was to leverage the load balancing capabilities of using a Spark cluster. Although we didn't manage to deploy a cluster on multiple Virtual Machines, we experimented with the configurations on a single VM. We made this choice due to the material and temporal constraints.

We choose to use python and mongodb because some members of our group have better skills with this software than with java or Cassandra, that none of us knews. Because we don't use java, we didn't need Tomcat.

## Demo
View a demo video [here](https://youtu.be/CxD-PIUVOQg).

## Getting started
### Install Ubuntu
1. Download [Ubuntu 18.04.1](https://www.ubuntu.com/download/desktop)
2. Mount iso with a VM

### Install pip
```
sudo apt-get install software-properties-common
sudo apt-add-repository universe
sudo apt-get update
sudo apt-get install python-pip
```



### Install mongodb
```
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 9DA31620334BD75D9DCB49F368818C72E52529D4
echo "deb [ arch=amd64 ] https://repo.mongodb.org/apt/ubuntu bionic/mongodb-org/4.0 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-4.0.list
sudo apt-get update
sudo apt-get install -y mongodb-org
```

#### Start mongo service
```
sudo service mongod start
```

### Install Java
```
sudo add-apt-repository ppa:webupd8team/java
sudo apt update
sudo apt install oracle-java8-installer
sudo apt install oracle-java8-set-default
```

### Install scala
```
sudo apt-get install scala
```

### Install git
```
sudo apt-get install git
```

### Install Spark
1. Download [spark 2.3.1](https://www.apache.org/dyn/closer.lua/spark/spark-2.3.1/spark-2.3.1-bin-hadoop2.7.tgz) from website
2. Extract the tar
    ```
    sudo tar xvf spark-2.3.1-bin-hadoop2.7.tgz -C /usr/local
    ```
3. Edit path variables
    1. Open bashrc in editor
        ```
        nano ~/.bashrc
        ```
    2. Write
        ```
        SPARK_HOME=/usr/local/spark-2.3.1-bin-hadoop2.7
        export PATH=$SPARK_HOME/bin:$PATH
        ```
    3. Source new bashrc
        ```
        source ~/.bashrc
        ```

### Copy spark configuration
```
cp spark-env.sh /usr/local/spark-2.3.1-bin-hadoop2.7/conf/
```

### Run server
Running this command will start the flask API as well as start the Spark master.
```
python ./server.py
```

### Start Spark slaves
```
sudo $SPARK_HOME/sbin/start-slave.sh spark://localhost:7077
```

## Run client
1. Install Postman via Ubuntu Software
2. Import provided Postman collection (`LOG8430-TP4.postman_collection.json`)
3. Execute requests and enjoy the show :)
