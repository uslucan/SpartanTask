package com.spartan.step_definitions;

import com.spartan.utilities.ConfigurationReader;
import com.spartan.utilities.DBUtils;
import com.spartan.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before
    public void setUp(){
    Driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    Driver.get().manage().window().maximize();
    Driver.get().get(ConfigurationReader.get("url"));

    }

    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }
        Driver.closeDriver();
    }

    @Before("@db")
    public void setUpdb(){
        System.out.println("Connecting to database");
        DBUtils.createConnection();
    }

    @After("@db")
    public void tearDowndb(){
        System.out.println("DisConnecting from database");
        DBUtils.destroy();
    }
}
