package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelUtil {
	
	
	 static public Object[][] readFromXL(String sheetName)
	{
		File file=new File("src/test/resources/Data/TestData.xlsx");
		Object[][] obj=null;
		  try
		  {
			  InputStream is=new FileInputStream(file);
			  XSSFWorkbook workbook=new XSSFWorkbook(is);
			  XSSFSheet sheet=workbook.getSheet(sheetName);
			  
			  obj=new Object[sheet.getLastRowNum()][];
			  
			  for(int i=1;i<=sheet.getLastRowNum();i++)   // i=1 bcz 0 is header
			  {
				obj[i-1]=new Object[sheet.getRow(i).getPhysicalNumberOfCells()];
				
				  for(int j=0;j<sheet.getRow(i).getPhysicalNumberOfCells();j++)
				  {
					obj[i-1][j]=sheet.getRow(i).getCell(j).getStringCellValue();
			        System.out.print(sheet.getRow(i).getCell(j).getStringCellValue()+"\t\t");
			      }
				  System.out.println("");
			  }
			  workbook.close();
		  }
		  catch (FileNotFoundException e) {
			  e.printStackTrace();
			  
		  }
		  catch (IOException e) {
			  e.printStackTrace();
			  
		  }
		  return obj;
	}

}
