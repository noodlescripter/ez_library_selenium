# qa_library_selenium (Integrated with API, DB and UI)

qa_library_selenium is a Selenium with Java library for dealing with web app automation

## Installation

Make sure you have following environment
1. NodeJS 16 LTS (https://nodejs.org/en)
2. Java 11 and up (11 is recommend) (https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
3. Maven (https://maven.apache.org/download.cgi)
4. Docker (https://www.docker.com/)
5. Git (https://git-scm.com/downloads) (For Windows)

Mac and Linux run below command to install git if already not installed.

Linux
```bash
sudo apt-get install git
```

Mac (Make sure that you have brew installed)
```bash
brew install git
```
Checkout the project by running below command
```bash
git clone https://github.com/alamhamim/ez_library_selenium.git
```

Now, once you checked out this project, you will need to switch to develop branch by running below command
```bash
git checkout develop
```
Once you are in the develop branch, now we need to install all of our pkgs by running bellow command
```bash
mvn clean install
```
Note: It may take a moment (If build is failing, no worries)

Once all the pkgs are installed for maven, now we have to navigate to the <live-app/fake-api-with-db> and run below command
```bash
npm install
```
This will install all the required pkgs for the application.

Once all the pkgs are installed, we will need to start our mysql server in docker container, for that navigate to the <live-app/docker-files> and execute below command

** Before running docker-compose file, make sure that you have the latest version of the docker and docker-compose installed!!!
```bash
docker-compose -f start_bank_app.yml up -d
```

Once container is up, you should be able to access the mysql database using bellow credentials
1. username: root
2. pwd: admin_123
3. Database: qa_db
4. Host: localhost:3306/qa_db

** Feel free to change the password in docker-compose file located in Dockerfiles dir.

Now we will need to start our back-end and UI, to do that navigate to the <live-app/fake-api-with-db> and run below command
```bash
npm start
```
** It will install all the pkgs if required, then seed the database and UI will launch including back-end.

After running above command you should be able to access the application at https://localhost:8001

Default username and pwd is: johndoe/password

** Note: You can create users and check in database, and make API calls.

If above steps are too complicated, then follow below steps.
1. cd into <src/test/java/com/qa/server>
2. Right click and run Main function from Start_Server.java

It will automate the whole process

## API
You can make api call using below endpoints

baseURI = localhost:8001
1. /users
2. /user/:ID
3. /user/:username
4. /restore-database

Note: GET, DELETE, POST, PUT are supported.

## Execution of existing test cases
I have created test cases for this project to make sure that, it is working as expected. You can run the default test cases by Navigating to <suites/e2e> and running <e2e_runner.xml> file or You can run below command
```bash
mvn clean test -DsuiteXmlFile=suites/e2e/e2e_runner.xml
```

## Main Library
Main Library is located in src/main/lib/LIB.java.
You can add more util functions as needed. Also if you want to automate any other application feel free to do so. This framework is not depended on one application.

## Application Summary
This application was created to give you good understanding in UI and Back-end. All are integrated in one single application.
Not only Automation, but it is recommended to be familiar with the application, know the flow e.g.
Create users, make api calls, verify in DB etc.

## Contributing
Feel free to add your ideas and library, if you think something needs to be changed, do so by creating a branch and pull request...



** Please make sure to update tests as appropriate in <com/qa/e2e/integration>..

## [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Copyright (c) [2023] [Hamim Alam]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
