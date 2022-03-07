package com.spartan.step_definitions;

import com.spartan.pages.BasePage;
import com.spartan.utilities.DBUtils;
import com.spartan.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.Map;

public class ManipulateDB_StepDefs {

BasePage basePage = new BasePage();
    @When("user clicks on Web Data module")
    public void user_clicks_on_Web_Data_module() {
        basePage.webData.click();
    }

    @When("user clicks on Add Spartan")
    public void user_clicks_on_Add_Spartan() {
        basePage.addSpartan.click();

    }
    @When("user fills the information")
    public void user_fills_the_information() {
        basePage.setAddSpartan();
    }


    @Then("verify the spartan is added to database")
    public void verify_the_spartan_is_added_to_database() {
       //get info from UI
        String actualName = Driver.get().findElement(By.cssSelector("#name")).getAttribute("value");
        System.out.println("actualName = " + actualName);
        String actualGender =  Driver.get().findElement(By.cssSelector("#gender")).getAttribute("value");
        System.out.println("gender = " + actualGender);
        String actualPhone = Driver.get().findElement(By.cssSelector("#phone")).getAttribute("value");
        System.out.println("actualPhone = " + actualPhone);

        String query = "select name,phone,Gender\n" +
                "from spartans\n" +
                "where name = '"+actualName+"'";

        Map<String, Object> rowMap = DBUtils.getRowMap(query);

        String expectedName = (String) rowMap.get("NAME");
        System.out.println("expectedName = " + expectedName);
        String expectedGender = (String) rowMap.get("GENDER");
        System.out.println("expectedGender = " + expectedGender);
        String expectedPhone = (String) rowMap.get("PHONE");
        System.out.println("expectedPhone = " + expectedPhone);

        Assert.assertEquals(expectedName,actualName);
        Assert.assertEquals(expectedGender,actualGender);
        Assert.assertEquals(expectedPhone,actualPhone);
    }
}
