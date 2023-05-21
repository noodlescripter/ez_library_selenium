package com.qa.server;

import com.qa.lib.CommandEx;
import com.qa.lib.GetCurrDir;
import com.qa.lib.LIB;
import com.qa.lib.OSUTIL;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

public class Start_Server {

    private static String host = "localhost";

    public static void main(String[] args) throws InterruptedException {

        //Making the banking app container up
        CommandEx.EXECUTE("docker stop banking-app-container");
        CommandEx.EXECUTE("docker rm banking-app-container");
        CommandEx.EXECUTE("docker image rm banking-app");
        CommandEx.navigateToDir("live-app/banking-app");
        CommandEx.EXECUTE("docker build -t banking-app .");
        CommandEx.EXECUTE("docker-compose -f start_bank_app.yml up -d");

        //Making the fake db container up
        CommandEx.EXECUTE("docker stop mysql_container");
        CommandEx.EXECUTE("docker rm mysql_container");
        CommandEx.EXECUTE("docker image rm mysql");
        CommandEx.navigateToDir("live-app/Dockerfiles");
        CommandEx.EXECUTE("docker-compose -f start_my_sql_server.yml up -d");

        //starting the app-server
        System.out.println(
    "***********************************************************************\n" +
    "*                                                                     *\n" +
    "*           Kindly Navigate to the live-app/fake-api-with-db          *\n" +
    "*           And run npm start                                         *\n" +
    "***********************************************************************\n" +
    "\n" +
    "***********************************************************************\n" +
    "*                                                                     *\n" +
    "*                         Happy Automation                            *\n" +
    "*                                                                     *\n" +
    "***********************************************************************"
);

        Thread.sleep(5000);
        /*Check point*/
        Assert.assertTrue(CommandEx.isRunning(host, 3306));
        
    }
}
