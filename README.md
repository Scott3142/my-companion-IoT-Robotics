# My Companion

# Installing and Running
* Create a Catkin workspace following the guide on the [ROS wiki](http://wiki.ros.org/catkin/Tutorials/create_a_workspace)
* Clone the this repo into the **src** of your workspace
* Source your workspace **source ~/[workspacedir]/devel/setup.bash**
* Run the launch file **roslaunch my_companion boot.launch**
* cd into my_companion_electron and run **npm install**
* Run **npm start**
* Run Mycroft using **~/[mycroftdir]/./start-mycroft debug**
* Run Twitter node **rosrun my_companion twitter.py**


# Development using VM
If you don't have a Raspberry PI you can setup a VirtualBox Virtual Machine using Vagrant by following the steps below.
## Setting up a VM
This process could take a while as the vagrant base box is quite large at around 3.5GB. Additionally, the below Vagrantfile will be reguarly updated throughout the project so may take some time to run and setup the VM.
* Create a new folder
``` bash
mkdir mycompanion_vm
```
* cd into the new folder and create a Vagrantfile by running the following code in a terminal
``` bash
cd mycompanion_vm
vagrant init
```
* Open and paste the following code into the Vagrantfile
``` ruby
# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure("2") do |config|
  # The most common configuration options are documented and commented below.
  # For a complete reference, please see the online documentation at
  # https://docs.vagrantup.com.

  # Every Vagrant development environment requires a box. You can search for
  # boxes at https://vagrantcloud.com/search.
  config.vm.box = "injones/xenial64-roskinetic-full"
  config.vm.box_version = "0.0.1"

  # Disable automatic box update checking. If you disable this, then
  # boxes will only be checked for updates when the user runs
  # `vagrant box outdated`. This is not recommended.
  # config.vm.box_check_update = false

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine. In the example below,
  # accessing "localhost:8080" will access port 80 on the guest machine.
  # NOTE: This will enable public access to the opened port
  # config.vm.network "forwarded_port", guest: 80, host: 8080

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine and only allow access
  # via 127.0.0.1 to disable public access
  # config.vm.network "forwarded_port", guest: 80, host: 8080, host_ip: "127.0.0.1"

  # Create a private network, which allows host-only access to the machine
  # using a specific IP.
  # config.vm.network "private_network", ip: "192.168.33.10"

  # Create a public network, which generally matched to bridged network.
  # Bridged networks make the machine appear as another physical device on
  # your network.
  # config.vm.network "public_network"

  # Share an additional folder to the guest VM. The first argument is
  # the path on the host to the actual folder. The second argument is
  # the path on the guest to mount the folder. And the optional third
  # argument is a set of non-required options.
  # config.vm.synced_folder "../data", "/vagrant_data"

  # Provider-specific configuration so you can fine-tune various
  # backing providers for Vagrant. These expose provider-specific options.
  # Example for VirtualBox:
  #
  config.vm.provider "virtualbox" do |vb|
    # Display the VirtualBox GUI when booting the machine
    vb.gui = true

    # Customize the amount of memory on the VM:
    vb.memory = "2048"
  end
  #
  # View the documentation for the provider you are using for more
  # information on available options.

  # Enable provisioning with a shell script. Additional provisioners such as
  # Puppet, Chef, Ansible, Salt, and Docker are also available. Please see the
  # documentation for more information about their specific syntax and use.
  config.vm.provision "shell", privileged: false, inline: <<-SHELL
    sudo apt-get update -y
    sudo apt-get upgrade -y
    source /opt/ros/kinetic/setup.bash
    mkdir -p /home/vagrant/dev/catkin_ws/src
    cd /home/vagrant/dev/catkin_ws/src
    catkin_init_workspace
    cd /home/vagrant/dev/catkin_ws
    catkin_make
    source devel/setup.bash
    cd /home/vagrant/dev/catkin_ws/src
    git clone https://github.com/injones/led_package_test.git
    cd /home/vagrant/dev/catkin_ws
    catkin_make
    mkdir -p /home/vagrant/dev/ros_web_ws
    cd /home/vagrant/dev/ros_web_ws
    git clone https://github.com/injones/electron-ros.git
    cd /home/vagrant/dev/ros_web_ws/electron-ros
    git checkout roslibjs
    npm install
    sudo apt-get install ros-kinetic-rosbridge-suite -y
    sudo apt-get install ros-kinetic-audio-common -y
    sudo apt-get install python-pip -y
    pip3 install pyttsx3
    pip install aiml
    cd /home/vagrant/dev
    git clone https://github.com/MycroftAI/mycroft-core.git
    cd /tmp
    wget https://services.gradle.org/distributions/gradle-5.2.1-bin.zip
    unzip -d /opt/gradle gradle-5.2.1-bin.zip
    sudo apt-get update
    sudo apt-get install curl apt-transport-https lsb-release gpg
    curl -sL https://packages.microsoft.com/keys/microsoft.asc | \
    gpg --dearmor | \
    sudo tee /etc/apt/trusted.gpg.d/microsoft.asc.gpg > /dev/null
    AZ_REPO=$(lsb_release -cs)
    echo "deb [arch=amd64] https://packages.microsoft.com/repos/azure-cli/ $AZ_REPO main" | \
    sudo tee /etc/apt/sources.list.d/azure-cli.list
    sudo apt-get update
    sudo apt-get install azure-cli
    pip3 install pyyaml
    pip3 install websocket-client
    pip3 install cssselect
    pip install pyyaml
  SHELL
end
```
* In the same terminal run the following command to startup the VM (password is **vagrant**)
```  bash
vagrant up
```
* (**Optional**) For better performance in the VM and as a requirement to setup shared folders, Virtualbox Guest Additions will need to be installed. You could decided to do this manually or install the **vagrant-vbguest** plugin for Vagrant using the following command. This plugin will install Guest Additions automatically when the **vagrant up** command is run.
``` bash
vagrant plugin install vagrant-vbguest
vagrant up
```

# Tech Stack Overview

## Raspberry PI 3:
* Ubuntu (Mate or Xenial) running as OS
* ROS Kinetic (Master)
* Electron desktop application
* MyCroft AI Voice Assistant

## Spring Boot Server:
* Spring Boot hosted on Azure
* API (CRUD) user accounts
* API post sensor data
* Run machine learning algorithms (TensorFlow, Keras) ?
* Connect to third party APIs
* Web scraper to collect data where APIs aren't provided

## SQL Database
* SQL DB hosted on Azure
* Store user account data e.g. social media links
* Store sensor data
* Store user/system configuration

## ~~Mobile application~~ ??
* React Native
* Family members and caretakers
* Monitor live sensor data
* See live feed from camera

# ROS Kinetic (Robot Operating System)

## What is ROS?
ROS is a meta-operating system or an SDK used to write software for robot systems. The main reason for using it in the project is for its communicating infrastructure that provides a publisher subscriber design pattern for message communicate between scripts which are run on **nodes**. Each node should have a single responsiblity and is setup and maintained by a master node. [More about ROS and its features](http://www.ros.org/core-components/)

## How does it fit into the tech stack?
ROS provides the basic infrastructure used to allow seperate scripts and source files (nodes) to communicate with each other. Additionally, ROS provides a JavaScript library which allows our Electron application to communicate with the nodes on the ROS network through ROS topics.

## ROS Workspace
A ROS workspace is a directory where all ROS packages (Catkin packages) used by a system should be installed. [Read more about ROS and Catkin workspaces](http://wiki.ros.org/catkin/workspaces)

### Creating a workspace
* Create the following directory structure
``` bash
mkdir -p ~/dev/catkin_ws/src
```
* Change to the src directory and create a new catkin workspace
``` bash
cd ~/dev/catkin_ws/src
catkin_init_workspace
```
* Move up a directory and run a catkin build
``` bash
cd ..
catkin_make
```
You should then see a directory structure similar to below
```
dev/
    catkin_ws/
        build/
        devel/
        src/
```
All required ROS packages must be added under the **src** directory.

## ROS Package **my_companion**
**my_companion** is a ROS package used for the MyCompanion system. It consists of a combinaton of Python and C++ files that can be run as ROS nodes in a ROS network.

### Nodes in **my_companion**
* Joke
* Game/Trivia
* Social media
* Text-to-Speech

## **my_companion** File Structure
```
launch/ - All launch files used for project.
src/   -  All source C++ files
scripts/ - All Python files
    tts/ - All Python scripts used for TTS (Text-to-Speech)
    test/ - All Python unit test scripts
    helpers/ - Any helper Python scripts
    example.py - Basic example ROS node that subscribes to 'example_topic'
msg/ - All message definitions
srv/ - All service definitions
test/ - All ROS test scripts (written in Python)
package.xml - Used to define package dependencies
CMakeLists.txt - Used to define package source and tests

```

## Creating Nodes
Nodes in ROS Kinetic can be written in C++ and Python 2/3.

### Creating a Python Node
**Note:** Writing Python scripts in Windows applications such as Atom and Visual Studio Code will add additional characters to the line endings which will not be recognised when the script is run in a Linux environment.
* Create a new Python script inside the scripts/ folder in the package root folder.
``` bash
touch my_companion/scripts/mynewscript.py
```
* Add the following line to the top of the new script
``` python
#!/usr/bin/env python
```
* Once you have added your code in a terminal execute the following line to make the script executable
``` bash
chmod +x my_companion/scripts/mynewscript.py
```
* In a terminal start the ROS master with the following line in the same terminal
``` bash
roscore
```
* As Python scripts are intepreted you don't need to build the package after creating a new script. Simply ensure you have sourced your catkin workspace using the following code in a new terminal
``` bash
source devel/setup.bash
```
* Once the ROS master is running and you have sourced the catkin workspace. Create a new ROS node using the following code
``` bash
rosrun my_companion mynewscript.py
```
## Communicating with Electron
The Electron application uses the roslibjs library to send and recieve JSON data for ROS topics and services. This requires a bridge to convert JSON data to ROS message data and vice versa. ROS provides a rosbridge_server library which sets up a web socket for communciation between ROS and JavaScript.

### rosbridge_server
* Install rosbridge_server
``` bash
sudo apt-get install ros-kinetic-rosbridge-server
```
* Launch rosbridge_server to startup the web socket
``` bash
roslaunch rosbridge_server rosbridge_websocket.launch
```
A web socket will then be running at localhost:9090 which will be able to send and recieve JSON data

## **my_companion** ROS Parameter Server
The ROS parameter server is used to store the user's system configuration and account data and settings such as the user's uuid, wake word, etc.

## **my_companion**  ROS Messages
All Message formats for the package are defined under the **msg** directory and will have the **.msg** file extenstion. All ROS messages that will be sent to the backend server must have the user's **uuid** as shown below.
```
int64 uuid
```
## Testing
### Python
To test Python nodes you can use the **unittest** framework.
### C++
To test C++ nodes you can use the **gtest** framework
### ROS Nodes
To test ROS nodes you can use the **rostest** framework

# Mycroft AI

## What is Mycroft AI
[Mycroft](https://mycroft.ai/documentation/) is an opensource AI voice assistant similar to that of Amazon Alexa and Siri. New skills can be downloaded and installed from their website as well as GitHub repositories.

## How Mycroft fits into the stack
Mycroft provides functionality for both speech-to-text and text-to-speech

# Electron Desktop Application
This is a JavaScript project that runs on NodeJS and provides users with a GUI to interface with the ROS system in a user friendly way

## Node Modules:

### Electron
JavaScript framework to create desktop applications using HTML, CSS and JavaScript

### React
JavaScript framework for developing frontend UIs using JavaScript, JSX and CSS.

### roslib.js
JavaScript library provided by ROS to communicate with ROS using JavaScript via JSON (requires ROS bridge server which setups and provides a websocket on ROS)

## Communicating with ROS
To communicate with ROS from Electron the roslibjs library is used along with a rosbridge server allowing Electron and ROS to send and recieve JSON data about ROS topics and services via a web socket. This means that there is very little delay or lag in communication between the frontend and ROS.

# Spring Boot Server
Spring Boot is used for the backend server of MyCompanion providing necessary APIs to interact with other backend services such as the database and machine learning server.

## REST API Endpoints
The server provides restful APIs that clients will use to send and retrieve data from the backend database via JSON. All API endpoints will begin with **/api** and will consume and/or produce JSON data. For example, **/api/users/1** is a restful API endpoint that will return JSON data for the user with a uuid of 1.

## Server Structure
Below is a quick overview of the three levels/layers of the server
### Controller Level
This level will define API endpoints for a domain such as users. This level will interact with the service level of the server. Each controller should be service implementation agnostic.
### Service Level
This level will define business logic for specific domains/topics such as users. This level will interact with the data level of the server. Each service should be data implementation agnostic.
### Data Level
This level will define methods used to directly interact with the backend database of the project.

## Creating APIs
API endpoints can be defined within Spring Controllers, each domain should have its own Controller consisting of all the APIs for that domain/topic. For example, the domains/topics **users**, **temperature sensors**, **light sensors** etc will have their own Controller.

### Controllers
Each Controller should follow the structure shown below
``` java
@RestController
@RequestMapping("/api/temperatures")
public class TemperatureController {

    private TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService tService){
        temperatureService = tService;
    }

    @CrossOrigin
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TemperatureDTO> saveTemperature(@RequestBody TemperatureDTO newTemp){
        TemperatureDTO temp = temperatureService.saveTemperature(newTemp);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(temp, httpHeaders, HttpStatus.CREATED);
    }
}
```

| Annotation  | Usage |
|---------- | ----------|
| @RestController | Controller will only return JSON  |
| @RequestMapping(path="/api/temperatures")  | Specify that this controller handles all requests to /api/temperatures. All controller APIs should be preceded with /api. The topic/domain should be plural. |
| @Autowired | All dependencies added via the controller constructor will be handled automatically at startup |
| @CrossOrigin | Allow cross origin requests |
| @PostMapping(path = "/", consumes = "application/json", produces = "application/json")  | Specify that this method will handle a POST request to /api/temperatures/, and will accept and produce JSON data. |
| @RequestBody | Specify that the consuming JSON data must match the structure of the specified Java oject |

## Services
An interface is used to define the external methods that controllers will use
``` java
public interface TemperatureService {
    TemperatureDTO saveTemperature(TemperatureDTO newTemp);
}
```
Each service should follow the structure below
``` java
@Service
public class TemperatureServiceImpl implements TemperatureService {

    private TemperatureRepository temperatureRepository;
    private UserRepository userRepository;

    @Autowired
    public TemperatureServiceImpl(TemperatureRepository tRepo, UserRepository uRepo){
        temperatureRepository = tRepo;
        userRepository = uRepo;
    }

    @Override
    public TemperatureDTO saveTemperature(TemperatureDTO newTemp) {
        User user = userRepository.findByUuid(newTemp.getUuid());
        Temperature temperature = temperatureRepository.saveAndFlush(Temperature.convertFromDto(newTemp, user));
        return new TemperatureDTO(temperature.getId(), temperature.getUser().getUuid(), temperature.getName(),
                temperature.getTemperature(), temperature.getHumidity(), temperature.getTimestamp().format(DateTimeFormatter.ISO_DATE_TIME));
    }
}
```

## Data Structure
Domain data has two main structures an **Entity** and **DTO** structure.
### Entity Object
A domain Entity object should match its internal database strcuture and acts as the internal view of the data. For example, user entities would have values for the user's UUID, Unique database ID, username and password matching the the structure stored in the database.
### DTO (Data Transfer Object)
A domain DTO will not match its internal database structure as it acts as the external view of the data and therefore shouldn't expose any internal structures or sensitive data. For example, user DTOs would have values for the user's UUID and username.

# Azure Cloud Hosting
## SQL Database
The project is currently running with a 250GB SQL database which comes free for 12 months as part of Azure's free trial.
## Spring Server
### Deloying to Azure
To deploy the Spring Boot server to Azure the [Azure CLI](https://docs.microsoft.com/en-us/cli/azure/?view=azure-cli-latest) tool is used, if you are using a computer that is locked down and thus cannot install Azure CLI then the [Development Environment](development-environment-vm "Development Environment (VM)") can be used.

### Gradle
At the current time Azure doesn't natively support deployment through Gradle, however there is a third party tool called [azure-gradle-plugins](https://github.com/lenala/azure-gradle-plugins) which can be used similarly to the official [Maven Azure Plugin](https://github.com/Microsoft/azure-maven-plugins) provided by Microsoft.
