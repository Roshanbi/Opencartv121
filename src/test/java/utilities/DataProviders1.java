package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders1 {
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException {
		
		String path=".//testData//Opencart_LoginData.xlsx";
		
		ExcelUtility excelUtil=new ExcelUtility(path);
		
	int totalrowCount=	excelUtil.getRowCount("Sheet1");
	int totalcellCount=excelUtil.getCellCount("Sheet1", 1);
	
	String loginData[][]=new String[totalrowCount][totalcellCount];
	
	for(int i=1;i<=totalrowCount;i++) {
		
		for(int j=0;j<totalcellCount;j++) {
			
			loginData[i-1][j]=excelUtil.getCellData("Sheet1",i,j);
			
		}
		
	}
		return loginData;
	}

}
