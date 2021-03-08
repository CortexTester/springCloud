0) Do you get this error 'org.springframework.cloud.config.server.environment.NoSuchLabelException: No such label: master? Add this in application.properties: 'spring.cloud.config.server.git.default-label=main

(1) Does the URL http://localhost:8888/limits-service/default work? If the URL does not work, check if you have the same name for limits-service in (a) spring.application.name in bootstrap.properties (b) in the URL (c) in the name of the property file

(2) Check if the name in @ConfigurationProperties("limits-service") matches the prefix of property values in application.properties. limits-service.minimum=9 limits-service.maximum=999

(3) Check if you have @EnableConfigServer enabled on SpringCloudConfigServerApplication class

(4) Check if you have the right repository url in /spring-cloud-config-server/src/main/resources/application.properties - spring.cloud.config.server.git.uri=file:///in28Minutes/git/spring-micro-services/03.microservices/git-localconfig-repo

(5) Do not have any spaces in your git repository path.

(6) If you are on windows, make sure that you are using one of these formats for your git repository

file:///C:/microservices/git-localconfig-repo
file:\\C:/WORKSPACE/GIT/git-localconfig-repo
file:///C:/Users/Gautham/Documents/workspace-sts-3.9.4.RELEASE/git-localconfig-repo
file:\\C:/Users/Gautham/Documents/workspace-sts-3.9.4.RELEASE/git-localconfig-repo

(7) Make sure that you have the right code - Compare against the code for Step 01 to Step 08 below.

(8) Make sure that you have committed all the code to GIT Local Repo

If everything is fine

(1) Stop all the servers

(2) Launch Config Server First

(3) Launch Limits Service

(4) Wait for 2 minutes

If you still have a problem, post a question including all the details:

(1) What Step was code working until?

(2) What is the step where you are facing a Problem?

(3) Response for http://localhost:8080/limits

(4) Response for http://localhost:8888/limits-service/default

(5) Response for http://localhost:8888/limits-service/dev

(6) Start up logs for limits-service and spring cloud config server with debug mode enabled

(7) All code for files shown below from Step 01 to Step 08.
