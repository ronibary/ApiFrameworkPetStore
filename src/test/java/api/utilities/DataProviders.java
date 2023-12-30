package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;


/*

Example how to use data providers that read data from Excel Sheet
this is done using the (XLUtility class with the poi library)

 */

public class DataProviders {

    /*
    go over the excel sheet and take all the data
    from all the fields and all the rows
    and build it into 2 dimensional array

    this will be the data provider for the test

     */

    @DataProvider(name = "Data")
    public Object[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        XLUtility xl = new XLUtility(path);

        int rownum = xl.getRowCount("Sheet1");
        int colcount = xl.getCellCount("Sheet1", 1);

        String apidata[][] = new String[rownum][colcount];

        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colcount; j++) {
                apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
            }
        }

        return apidata;
    }

    /*
    go over all the rows in the excel sheet and build only the user names data
     */

    @DataProvider(name = "UserNames")
    public Object[] getUserNames() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        XLUtility xl = new XLUtility(path);

        int rownum = xl.getRowCount("Sheet1");

        String apidata[] = new String[rownum];

        for (int i = 1; i <= rownum; i++) {
            apidata[i - 1] = xl.getCellData("Sheet1", i, 1);
        }

        return apidata;
    }

}
