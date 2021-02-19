package lib.dataProviders;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.remote.RemoteWebDriver;

import lib.pom.lightning.AppLauncherPage;
import lib.reports.Reports;

import org.apache.poi.ss.usermodel.DataFormatter;

public class ReadExcel extends Reports{


	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFCell cell;
	
	public Object[][] readdata(String dataSheetName) throws IOException {
		
		XSSFWorkbook wb = new XSSFWorkbook("./DataSource/"+dataSheetName+".xlsx");
		XSSFSheet sheetAt = wb.getSheetAt(0);
		int rc = sheetAt.getLastRowNum();
		
		XSSFRow headrow = sheetAt.getRow(0);
		int cc = headrow.getLastCellNum();
		
		Object[][] data = new Object[rc][cc];
		DataFormatter formatter = new DataFormatter();
		
		for (int i = 1; i<=rc; i++)
		{
			XSSFRow rowdata = sheetAt.getRow(i);
			
			for (int j=0; j<cc; j++)
			{
				XSSFCell columndata = rowdata.getCell(j);
				
				Object xlvalue1 = columndata.getStringCellValue();
				String xlvalue = String.valueOf(xlvalue1);

				data[i-1][j] = xlvalue;		
			}
		}
		wb.close();
		return data;
	}

	@Override
	public String takeSnap() {
		// TODO Auto-generated method stub
		return null;
	}

}
