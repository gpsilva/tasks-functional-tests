package br.sp.gpsilva.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

// import junit.framework.Assert;

public class TasksTest {

	public WebDriver acessarAplicacao() throws MalformedURLException {
//		WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.15.64:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.0.104:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefasComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
				
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium 3");
			
			driver.findElement(By.id("dueDate")).sendKeys("29/04/2022");
			
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		} finally {
		
		driver.quit();
		}
	}
	
	@Test
	public void naodeveSalvarTarefaSemSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
				
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.id("dueDate")).sendKeys("29/04/2022");
			
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
		} finally {
		
		driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
				
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium 3");
			
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
		} finally {
		
		driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
				
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium 3");
			
			driver.findElement(By.id("dueDate")).sendKeys("18/12/2000");
			
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
		} finally {
		
		driver.quit();
		}
	}
}
