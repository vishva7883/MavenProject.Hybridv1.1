package library.data.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {
	
	static XSSFWorkbook wb;
	static XSSFSheet sheetAt;
	static XSSFCell cell = null;
	
	public void writedata(String dataSheetName, String val) throws IOException {
		
		File src = new File("./DataSource/"+dataSheetName+".xlsx");
		FileInputStream finput = new FileInputStream(src);
		
		wb = new XSSFWorkbook(finput);
		sheetAt = wb.getSheetAt(0);
		
		int rc = sheetAt.getLastRowNum();
		System.out.println("Total Rows in the Sheet : "+rc);
		
		XSSFRow headrow = sheetAt.getRow(0);
		int cc = headrow.getLastCellNum();
		System.out.println("Total Columns in the Sheet : "+cc);
		
		for(int i=1; i<=rc; i++)
		{
			//cell = sheetAt.getRow(i).getCell(0);
			sheetAt.getRow(i).createCell(0).setCellValue(val);
		}
		finput.close();
		
		
		FileOutputStream foutput = new FileOutputStream(src);
		wb.write(foutput);
		foutput.close();
		}
}
