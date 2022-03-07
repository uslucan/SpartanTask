package com.spartan.pages;

import com.github.javafaker.Faker;
import com.spartan.utilities.BrowserUtils;
import com.spartan.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

   @FindBy(css = ".fas.fa-users")
    public WebElement webData;

    @FindBy(css = ".fas.fa-user-plus")
    public WebElement addSpartan;



    public void setAddSpartan(){
        Faker faker = new Faker();
        //enter name
        Driver.get().findElement(By.cssSelector("#name")).sendKeys(faker.name().firstName());
        //choose gender
        Select genderDropDown = new Select(Driver.get().findElement(By.cssSelector("#genderSelect")));
        genderDropDown.selectByIndex(faker.number().numberBetween(0,2));
        //enter phone number
        Driver.get().findElement(By.cssSelector("#phone")).sendKeys("0555 555 33 44");

        //submit
        Driver.get().findElement(By.cssSelector("#submit_btn")).click();


    }
}
