import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFromExcel {

	public static void readFromExcel(String fileName) throws Exception{
		FileInputStream fileInputStream = new FileInputStream(new File(fileName));
		
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
	
		for (Row row : sheet) {
			System.out.println("Row number : "+ (row.getRowNum() +1));
			for (Cell cell : row) {
				switch (cell.getColumnIndex()){
				case 0:
					if(cell.getCellType() == Cell.CELL_TYPE_STRING)System.out.println("1 : " + cell.getStringCellValue().trim() + "\t");
					else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)System.out.println("1 : " + cell.getNumericCellValue() + "\t");
					break;
				case 1:
					if(cell.getCellType() == Cell.CELL_TYPE_STRING)System.out.println("2 : " + cell.getStringCellValue().trim() + "\t");
					else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)System.out.println("2 : " + cell.getNumericCellValue() + "\t");
					break;
				case 2:
					if(cell.getCellType() == Cell.CELL_TYPE_STRING)System.out.println("3 : " + cell.getStringCellValue().trim() + "\t");
					else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)System.out.println("3 : " + cell.getNumericCellValue() + "\t");
					break;
				case 3:
					if(cell.getCellType() == Cell.CELL_TYPE_STRING)System.out.println("4 : " + cell.getStringCellValue().trim() + "\t");
					else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)System.out.println("4 : " + cell.getNumericCellValue() + "\t");
					break;
				case 4:
					if(cell.getCellType() == Cell.CELL_TYPE_STRING)System.out.println("5 : " + cell.getStringCellValue().trim() + "\t");
					else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)System.out.println("5 : " + cell.getNumericCellValue() + "\t");
					break;
				case 5:
					if(cell.getCellType() == Cell.CELL_TYPE_STRING)System.out.println("6 : " + cell.getStringCellValue().trim() + "\t");
					else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)System.out.println("6 : " + cell.getNumericCellValue() + "\t");
					break;
				case 6:
					if(cell.getCellType() == Cell.CELL_TYPE_STRING)System.out.println("7 : " + cell.getStringCellValue().trim() + "\t");
					else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)System.out.println("7 : " + cell.getNumericCellValue() + "\t");
					break;
				 }
			}
			System.out.println("-------------");
			Thread.sleep(1000);
		}
	}
	
}
