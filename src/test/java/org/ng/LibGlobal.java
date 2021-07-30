package org.ng;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class LibGlobal {
public WebDriver driver;
	
	public void getdriver(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\ELCOT\\eclipse-workspace\\Arjunan\\TestNg\\driver\\chromedriver.exe");
				driver=new ChromeDriver();
			}
				else if (browserName.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.chrome.driver", "C:\\Users\\ELCOT\\eclipse-workspace\\Arjunan\\FacebookSignUp\\driver\\chromedriver.exe");
					driver=new FirefoxDriver();
				}
				else if (browserName.equalsIgnoreCase("edge")) {
					System.setProperty("webdriver.edge.driver", "C:\\Users\\ELCOT\\eclipse-workspace\\Arjunan\\FacebookSignUp\\driver\\chromedriver.exe");
					driver=new EdgeDriver();
				}
					else {
						System.out.println("Invalid browser");		
					}
				
			} catch (Exception e) {
				System.out.println(e);	
        }
	}
	public void launchUrl(String Url) {
		try {
			driver.get(Url);
			
		} catch (Exception e) {
			System.out.println(e);
		}}
		public WebElement findById(String data,String id) {
			WebElement findElement=null;
			try {
				if (data.equalsIgnoreCase("id")) {
					 findElement = driver.findElement(By.id(id));
				}
				else if (data.equalsIgnoreCase("xpath")) {
				     findElement = driver.findElement(By.xpath(id));
					
				}else if (data.equalsIgnoreCase("classname")) {
					 findElement = driver.findElement(By.name(id));
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
			return findElement;
		}
		
		
		public void typeText(WebElement element,String data) {
			
			try {
				element.sendKeys(data);
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
			public void btnClick(WebElement element) {
				try {
					element.click();
				} catch (Exception e) {
					System.out.println(e);
					
				}
	}
		public String getDataFromExcel(String pathName,String SheetName,int rowNo,int cellNo ) {
			
          String value=null;
          try {
			File file=new File(pathName);
			FileInputStream stream=new FileInputStream(file);
			Workbook workbook=new XSSFWorkbook(stream);
			Sheet sheet=workbook.getSheet(SheetName);
			Row row = sheet.getRow(rowNo);
			Cell cell = row.getCell(cellNo);
			int cellType = cell.getCellType();
			if (cellType==1) {
				value=cell.getStringCellValue();
				
			}else if (DateUtil.isCellDateFormatted(cell)) {
				Date dateCellValue = cell.getDateCellValue();
				SimpleDateFormat dateformat=new SimpleDateFormat("dd-mmm-yyyy");
				value=dateformat.format(dateCellValue);
				
			}
			else {
				double numericCellValue = cell.getNumericCellValue();
				long l=(long)numericCellValue;
				value=String.valueOf(l);
			}	
			
		} catch (Exception e) {
			
		}
		return value;
		}
		public void moveElement(WebElement target) {
			try {
				Actions acc=new Actions(driver);
				acc.moveToElement(target).perform();
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}

		public void selectByDropDown(String name,WebElement element,String data) {
			try {
				Select select=new Select(element);
				if (name.equalsIgnoreCase("index")) {
					select.selectByIndex(Integer.parseInt(data));	
				}else if (name.equalsIgnoreCase("value")) {
					select.selectByValue(data);
					
				}else if (name.equalsIgnoreCase("visibleText")) {
					select.deselectByVisibleText(data);
					
				}
			} catch (Exception e) {
				System.out.println(e);
			}

		}
		
		public void writeDataToExcel(String pathName,String sheetName,int rowNo,int cellNo, String value) {
			try {
				File file=new File(pathName);
				FileInputStream filInputStream=new FileInputStream(file);
				Workbook workbook=new XSSFWorkbook(filInputStream);
				Sheet sheet=workbook.getSheet(sheetName);
				Row createRow = sheet.createRow(rowNo);
				Cell createCell = createRow.createCell(cellNo);
				createCell.setCellValue(value);
				FileOutputStream fileOutputStream=new FileOutputStream(file);
				workbook.write(fileOutputStream);
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}

}



