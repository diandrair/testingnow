package com.jakartalabs.utils;

import java.io.File;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class DataUtils {

	public static String env = System.getProperty("environment");

	public static String getDataFromExcel(String sheetName, String key) {
		String testdata = "error extracting testdata file";
		if (env.toLowerCase().equals("prod")) {
			testdata = "TestDataProd.xlsx";
		} else {
			testdata = "TestData.xlsx";

		}

		String valueResult = "error fetching data";

		try {
			Fillo fillo = new Fillo();

			Connection conn = fillo.getConnection(
					System.getProperty("user.dir") + File.separator + "resources" + File.separator + testdata);
			String query = "Select * from " + sheetName + " where ID='" + key + "'";

			Recordset recordset = conn.executeQuery(query);

			while (recordset.next()) {
				valueResult = recordset.getField("Value");
			}

			recordset.close();
			conn.close();
		} catch (FilloException e) {
			e.printStackTrace();
		}

		return valueResult;

	}
}
