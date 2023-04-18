import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestDateError31 {

	public static void main(String[] args) throws Exception {
		// Set the path to the ChromeDriver executable
		System.setProperty("webdriver.chrome.driver", "D:\\webdriver\\chromedriver2.exe");

		// Create a new instance of the ChromeDriver
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();

		// Navigate to the web page
		driver.get(
				"http://localhost/Get%20Day%20Name%20From%20Date%20%20(Show%20Error)%20(%E0%B9%81%E0%B8%9A%E0%B8%9A%E0%B8%9B%E0%B8%A3%E0%B8%B1%E0%B8%9A%E0%B8%9B%E0%B8%A3%E0%B8%B8%E0%B8%87%E0%B9%83%E0%B8%AB%E0%B9%89%E0%B8%AA%E0%B8%A7%E0%B8%A2%E0%B8%87%E0%B8%B2%E0%B8%A1%E0%B8%82%E0%B8%B6%E0%B9%89%E0%B8%99)/");

		// Find the day, month, and year drop-down lists
		WebElement dayDropdown = driver.findElement(By.id("day"));
		WebElement monthDropdown = driver.findElement(By.id("month"));
		WebElement yearInput = driver.findElement(By.id("year"));

		// Select a day, month, and year
		dayDropdown.sendKeys("31");
		monthDropdown.sendKeys("เมษายน");
		yearInput.sendKeys("2023");

		// Submit the form
		driver.findElement(By.cssSelector("input[type='submit']")).click();

		// Get the resulting date
		WebElement result = driver.findElement(By.tagName("body"));
		String dateString = result.getText().trim();

		// Parse the date string into a LocalDate object
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'วัน'EEEE' ที่' d' 'MMMM' ค.ศ. 'yyyy");
		LocalDate date = null;
		try {
			date = LocalDate.parse(dateString, formatter);
		} catch (DateTimeException e) {
			System.out.println("Error parsing date: " + e.getMessage());
		}

		// Print out the date's weekday, day, month, and year
		if (date != null) {
			System.out.println("Weekday: " + date.getDayOfWeek());
			System.out.println("Day: " + date.getDayOfMonth());
			System.out.println("Month: " + date.getMonth());
			System.out.println("Year: " + date.getYear());
		}

		// Close the browser
		String title = driver.getTitle();
		System.out.println(title);

		Thread.sleep(5000);
		driver.quit();
	}

}
