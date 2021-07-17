package com.automation.framework.utilities;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.pojs.TestDriver;


/**
 * The Class ExcelAccess.
 */
public class CsvUtil {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(CsvUtil.class);

	/** The wb. */
	private static BufferedReader br;

	/**
	 * This method is to open a csv.
	 * 
	 * @param workbookPath
	 *            the workbook path
	 * @return the workbook
	 * @throws DriverScriptException
	 */

	public void openCsv(TestDriver testDriver,File csvFilePath) throws DriverScriptException {
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","openCsv(File) - start"); //$NON-NLS-1$
		}

		closeIfOpen(testDriver);
		DriverScript.logMessage(testDriver,"info","Opening csv file");
		try {
				br = new BufferedReader(new FileReader(csvFilePath));

		} catch (FileNotFoundException exception) {
			throw new DriverScriptException(csvFilePath+" CSV file path not found");
		} catch (Exception e) {
			DriverScript.logMessage(testDriver,"error","openCsv(File)"+ e); //$NON-NLS-1$

			DriverScript.logMessage(testDriver,"error", "openCsv opening " + csvFilePath + ", "
					+ e);
		} 

		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","openCsv(File) - end"); //$NON-NLS-1$
		}
	}

	
	
	/**
	 * Close if open.
	 */
	private void closeIfOpen(TestDriver testDriver) {
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","closeIfOpen() - start"); //$NON-NLS-1$
		}
		
		try {
			if (br != null) {
				// BufferedReader
				br.close();
			}
		} catch (Exception e) {
			DriverScript.logMessage(testDriver,"error","closeIfOpen()" + e); //$NON-NLS-1$

			DriverScript.logMessage(testDriver,"error", "closeIfOpen " + e);
		}

		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","closeIfOpen() - end"); //$NON-NLS-1$
		}
	}

	/**
	 * This method is used to get the cell value for string format.
	 *
	 * @param sheetName
	 *            the sheet name
	 * @param iRow
	 *            the row no
	 * @param iCol
	 *            the column no
	 * @return the string
	 */
	

	public String getEachRowOfCSV(TestDriver testDriver) {
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","geting row of csv getEachRowOfCSV - start"); //$NON-NLS-1$
		}

		String line = "";
		
		try {
				line = br.readLine();
		if(line.contains("\""))
		{
			line = line.replaceAll("\"", "");
//			System.out.println("double quotes removed................"+line);
		}
		else
		{
//			System.out.println("double quotes ................"+line);
		}
		} catch (IOException e) {
			DriverScript.logMessage(testDriver,"error","getEachRowOfCSV()" + e); //$NON-NLS-1$

			DriverScript.logMessage(testDriver,"error", "getEachRowOfCSV " + e);
		}
		if (line != null) {
			if (LOG.isDebugEnabled()) {
				DriverScript.logMessage(testDriver,"info","getEachRowOfCSV(String) - end"); //$NON-NLS-1$
			}
			return line;
		} else {
			if (LOG.isDebugEnabled()) {
				DriverScript.logMessage(testDriver,"info","getCellValue(String) - end"); //$NON-NLS-1$
			}
			return "-EOF-";
		}
	}

	public boolean checkIfXapthHasComma(TestDriver testDriver, String xpath) {
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","checkIfXapthHasComma(string) - start"); //$NON-NLS-1$
		}
		
        String s = xpath;
        int counter1 = 0;
        int counter2 = 0;
        boolean flag = false;
        
		try {
            
            for( int i=0; i<s.length(); i++ ) {
                if( s.charAt(i) == '[' ) {
                    counter1++;
                } 
                if( s.charAt(i) == ']' ) {
                    counter2++;
                } 
            }

            if (counter1 != counter2){
            		flag = true;
            }
        
		} catch (Exception e) {
			DriverScript.logMessage(testDriver,"error","checkIfXapthHasComma(string)" + e); //$NON-NLS-1$

			DriverScript.logMessage(testDriver,"error", "checkIfXapthHasComma(string) " + e);
		}

		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","checkIfXapthHasComma(string) - end"); //$NON-NLS-1$
		}
		return flag;
	}
	


}
