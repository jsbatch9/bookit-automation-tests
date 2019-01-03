package com.bookit.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bookit.utilities.Driver;

public abstract class TopNavigationBar {
	public TopNavigationBar() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(linkText="map")
	public WebElement map;
	
	@FindBy(linkText="schedule")
	public WebElement schedule;
	
	@FindBy(linkText="hunt")
	public WebElement hunt;
	
	@FindBy(linkText="my")
	public WebElement my;
	
}
