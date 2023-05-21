# qa_library_selenium (Integrated with API, DB and UI)

qa_library_selenium is a Selenium with Java library for dealing with web app automation

## Installation

Make sure you have following environment
1. NodeJS 16 LTS (https://nodejs.org/en)
2. Java 11 and up (11 is recommend) (https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
3. Maven (https://maven.apache.org/download.cgi)
4. Docker (https://www.docker.com/)
5. Git (https://git-scm.com/downloads) (For Windows, Mac and Linux run below command)

Linux
```bash
sudo apt-get install git
```

Mac (Make sure that you brew installed)
```bash
brew install git
```

Now, once you checked out this project, you will need to switch to develop branch by running below command
```bash
git checkout develop
```
Once you are in the develop branch, now we need to install all of our pkgs by running bellow command
```bash
mvn clean install
```
Note: It may take a moment.

Once all the pkgs are installed for maven, now we have to navigate to the <live-app/fake-api-with-db> and run below command
```bash
npm install
```
This will install all the required pkgs for the application.

Once All the pkg are installed, now we will need to start our mysql server in docker container, for that navigate to the <live-app/docker-files> and execute below command
```bash
docker-compose -f start_bank_app.yml up -d
```

Once container is up, you should able to access the mysql database using bellow credentials
1. username: root
2. pwd: admin_123
3. Database: qa_db
4. Host: localhost:3306/qa_db

Now we will need to start out App Server, to do that navigate to the <live-app/fake-api-with-db> and run below command
```bash
npm start
```
After running above command you should be able to access the application at https://localhost:8001

Default username and pwd is: johndoe/password

If above steps are too complicated, then follow below steps.
1. cd into <src/test/java/com/qa/server>
2. Right click and Main Function from Start_Server.java

It will automate the whole process

## API
You can api call using below endpoints

baseURI = localhost:8001
1. /users
2. /user/:ID
3. /user/:username
4. /restore-database

Note: GET, DELETE, POST, PUT are supported.

## Execution of existing test cases
I have created simple test cases for this project to make sure that, it is working as expected. You can the default test cases by Navigating to <suites/e2e> and running <e2e_runner>.xml file or You can run below command
```bash
mvn clean test -DsuiteXmlFile=suites/e2e/e2e_runner.xml
```

## Contributing
Feel free to add your ideas and library, if you think something needs to be changed, do so by creating a branch and pull request...



Please make sure to update tests as appropriate in <com/qa/e2e/integration>..

## License

[MIT](https://choosealicense.com/licenses/mit/)