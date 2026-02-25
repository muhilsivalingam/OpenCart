package utilities;
import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=".\\testData\\opencart_LoginData.xlsx";
		ExcelUtility xlutil=new ExcelUtility(path);
		int totalrows=xlutil.getRowCount("LoginData");
		int totalcols=xlutil.getCellCount("LoginData",1);
		String logindata[][]=new String[totalrows][totalcols];
		for(int i=1;i<=totalrows;i++)//excel header part ignored starting from 1
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]=xlutil.getCellData("LoginData", i, j);
			}
		}
		return logindata;
	}

}
