package api.utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	
	@DataProvider(name="dp")
	public static Object[][] getData() {

		ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\UserData.xlsx");
		String sheetName="Data";

		return DataUtil.getData(excel,sheetName);
	}

	@DataProvider(name="dp1")
	public static Object[][] getUserName() {

		ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\UserData.xlsx");
		String sheetName="UserName";

		return DataUtil.getData(excel,sheetName);
	}
	



}
