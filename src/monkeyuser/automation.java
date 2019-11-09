package com.selenium.testcases;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

		import org.openqa.selenium.WebElement;

		import org.openqa.selenium.chrome.ChromeDriver;

		import org.openqa.selenium.support.ui.ExpectedCondition;

		import org.openqa.selenium.support.ui.ExpectedConditions;

		import org.openqa.selenium.support.ui.WebDriverWait;



		public class awsActions {

			

			public static ExpectedCondition<Boolean> sourceChangedFrom(final String oldSrc) {

			    return new ExpectedCondition<Boolean>() {

			      private String currentSrc = "";



			      @Override

			      public Boolean apply(WebDriver driver) {

			    	  currentSrc = driver.findElement(By.xpath("//div[@class='contents']//img")).getAttribute("src");

			        return !oldSrc.equals(currentSrc);

			      }



			      @Override

			      public String toString() {

			        return String.format("source to be different from \"%s\". Current source: \"%s\"", oldSrc, currentSrc);

			      }

			    };

			  }



			public static void main(String[] args) {

				

				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");   

				WebDriver driver = new ChromeDriver();

				WebDriverWait wait = new WebDriverWait(driver, 20);

				

				driver.get("https://www.monkeyuser.com");

				

				WebElement currentImage = driver.findElement(By.xpath("//div[@class='contents']//img"));

				String oldValue = currentImage.getAttribute("src");

				//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

				driver.findElement(By.xpath("//img[@title='random']")).click();

//				WebDriverWait wait = new WebDriverWait(driver, 20);

//				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@title='random']")));

				

				Long started = System.nanoTime();

				while(true) {

					currentImage = driver.findElement(By.xpath("//div[@class='contents']//img"));

					String newValue = currentImage.getAttribute("src");

					if(!oldValue.equals(newValue)) {

						System.out.println("Random worked correctly");

						break; // Success

					}

					if(System.nanoTime() > started + 30*1000*1000000) {

						System.out.println("Random failed");

						break; // Failed

					}



					try {

						Thread.sleep(1000);

					} catch (InterruptedException e) {

						// TODO Auto-generated catch block

						e.printStackTrace();

					}

				}

				

				wait.until(awsActions.sourceChangedFrom(oldValue));

				//System.out.println("loggedInUser=" + loggedInUser);

				//assertEquals(loggedInUser.getText(),"random");

				//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

				

				driver.close();



			}



		}
