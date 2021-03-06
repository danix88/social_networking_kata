[![Build Status](https://travis-ci.org/danix88/social_networking_kata.svg?branch=master)](https://travis-ci.org/danix88/social_networking_kata)

Social Networking Kata
----------------------
This is an implementation of the excercise proposed at:

https://github.com/sandromancuso/social_networking_kata/

### Technologies:
 * Spring Boot 2;
 * JPA
 * H2 in-memory DB

### Build:
Run the maven command _mvn clean package_.

Please compile with Java 8 version.
### Run:
Run the command _java -jar demo-1.0.0_, ensure you are using a Java 8 JRE. 

Please wait for the welcome message to be printed before starting using the application.
### Usage:
Run the application, type a command and press enter.

Enter 'exit' in order to quit.

Please note that commands must be written in lower case and usernames cannot contain spaces.

### Allowed commands:
 * \<user name\> -> \<message\>
  
 * \<user name\>
  
 * \<user name\> follows \<another user\>
  
 * \<user name\> wall
  
 * exit

Please refer to the following project README for further examples:

https://github.com/sandromancuso/social_networking_kata/

### Logging:
The application will create a log file _socialnetworkingkata.log_.
