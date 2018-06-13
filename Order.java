package com.weborder;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Order {
	
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/haticeevci/Documents/selenium dependencies/drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		driver.findElement(By.linkText("Order")).click();
		//driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/Process.aspx");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(String.valueOf((int)((Math.random() *100)+1)));
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys("John " +randomMiddleNameLetter()+ " Smith");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("123 Any st"); //street
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Anytown");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys("Virginia");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(String.valueOf((int)((Math.random() *100000)+5)));//zip
	

		List<WebElement> radios = driver.findElements(By.xpath("//*[starts-with(@id, 'ctl00_MainContent_fmwOrder_cardList_')]"));
		int cardType=(int)(Math.random() * 3);
		radios.get(cardType).click();
		
		if(cardType==2) {
			  driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("3"+String.valueOf((int)((Math.random() *1000000000)+1))+String.valueOf((int)((Math.random() *100000)+1))); //AE
		}else if(cardType==1) {
	          driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("5"+String.valueOf((int)((Math.random() *1000000000)+1))+String.valueOf((int)((Math.random() *1000000)+1))); //MASTER
		}else driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("4"+String.valueOf((int)((Math.random() *1000000000)+1))+String.valueOf((int)((Math.random() *1000000)+1))); //VIS
		
		
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys("01/21"); 
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
		Thread.sleep(2000);
		
		String expected = "New order has been successfully added.";
		String actual = driver.getPageSource();
		
		if(actual.contains(expected)){
		System.out.println("Verified");
		}else {
		System.out.println("NOT Verified");
		}
		
		Thread.sleep(2000);
		driver.close();
	}
	
	    public static String randomMiddleNameLetter() {	
		final String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		int index = random.nextInt(24);
		return ""+abc.charAt(index)+".";
		}
	}
