package com.google.testcase;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.google.Base.BaseClass;
import com.google.utilities.Xls_Reader;

public class DD_TC_Search extends BaseClass {


	@Test
	public void SearchTest() throws InterruptedException, IOException {

		driver.get("https://google.com");
		Thread.sleep(3000);


		//Excel read data
		Xls_Reader reader = new Xls_Reader("./src/test/java/com/google/testdata/SearchData.xlsx");
		String sheetName = "Sheet1";

		int rowCount = reader.getRowCount(sheetName);
		@SuppressWarnings("unused")
		int colCount = reader.getColumnCount(sheetName);

		for(int rowNum=2; rowNum<=rowCount; rowNum++){
			String searchKeyword = reader.getCellData(sheetName, "Search Keyword", rowNum);
			
			//Google Search
			WebElement searchbox = driver.findElement(By.name("q"));
			WebElement searchbtn = driver.findElement(By.name("btnK"));

			searchbox.clear();
			searchbox.sendKeys(searchKeyword);
			Thread.sleep(2000);
			searchbtn.click();

			driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div/div/div[1]/a/h3")).click();
			
			/*
			 * Message to be written in the Excel
			 * Implement pass or fail
			 */
			//Excel write data
			reader.setCellData(sheetName, "Result", rowNum, "DONE");
		}

	}

}	


