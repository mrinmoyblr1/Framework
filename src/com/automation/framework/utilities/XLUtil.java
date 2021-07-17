package com.automation.framework.utilities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public class XLUtil {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(XLUtil.class);

	/** The wb. */
	private static Workbook wb;

	/**
	 * This method is to open a workbook excel.
	 * 
	 * @param workbookPath
	 *            the workbook path
	 * @return the workbook
	 * @throws DriverScriptException
	 */

	public void openWorkBook(TestDriver testDriver,File workbookPath) throws DriverScriptException {
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","openWorkBook(File) - start"); //$NON-NLS-1$
		}

		closeIfOpen(testDriver);
		InputStream inputStream = null;
		DriverScript.logMessage(testDriver,"info","Opening workbook");
		try {
			inputStream = new BufferedInputStream(new FileInputStream(
					workbookPath));
			wb = WorkbookFactory.create(inputStream);

		} catch (FileNotFoundException exception) {
			throw new DriverScriptException(workbookPath+" WorkBook file path not found");
		} catch (Exception e) {
			DriverScript.logMessage(testDriver,"error","openWorkBook(File)"+ e); //$NON-NLS-1$

			DriverScript.logMessage(testDriver,"error", "openWorkBook opening " + workbookPath + ", "
					+ e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();

				} catch (Exception e) {
					DriverScript.logMessage(testDriver,"error","openWorkBook(File)"+ e); //$NON-NLS-1$

					DriverScript.logMessage(testDriver,"error", "openWorkBook Closing" + workbookPath
							+ ", " + e);

				}
			}
		}

		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","openWorkBook(File) - end"); //$NON-NLS-1$
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
			if (wb != null) {
				// WorkbookFactory.

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

	public String getCellValue(TestDriver testDriver,String sheetName, int iRow, int iCol) {
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getCellValue(String, int, int) - start"); //$NON-NLS-1$
		}

		Cell xlCell = null;
		String cellValue = null;
		try {
			xlCell = getCell(testDriver,wb, sheetName, iRow, iCol);

			if (xlCell == null) {
				cellValue = "";
			} else {
				cellValue = xlCell.getStringCellValue();
			}
		} catch (IllegalStateException e) {
			cellValue = String.valueOf((int) xlCell.getNumericCellValue());
		} catch (java.lang.Exception e) {
			cellValue = "";
		}
		if (cellValue != null) {
			if (LOG.isDebugEnabled()) {
				DriverScript.logMessage(testDriver,"info","getCellValue(String, int, int) - end"); //$NON-NLS-1$
			}
			return cellValue;
		} else {
			if (LOG.isDebugEnabled()) {
				DriverScript.logMessage(testDriver,"info","getCellValue(String, int, int) - end"); //$NON-NLS-1$
			}
			return "-EOF-";
		}
	}

	/**
	 * This method is used to get the cell value for numeric format.
	 *
	 * @param sheetName
	 *            the sheet name
	 * @param iRow
	 *            the row no
	 * @param iCol
	 *            the column no
	 * @return the double
	 */

	public double getCellNumericValue(TestDriver testDriver,String sheetName, int iRow, int iCol) {
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getCellNumericValue(String, int, int) - start"); //$NON-NLS-1$
		}

		Cell xlCell;
		double cellValue = 0;
		try {
			xlCell = getCell(testDriver,wb, sheetName, iRow, iCol);
			if (xlCell == null) {
				cellValue = 0;
			} else {
				cellValue = xlCell.getNumericCellValue();
			}
		} catch (NullPointerException e) {
			DriverScript.logMessage(testDriver,"error","getCellNumericValue(String, int, int)" +e ); //$NON-NLS-1$

			cellValue = 0;
		}

		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getCellNumericValue(String, int, int) - end"); //$NON-NLS-1$
		}
		return cellValue;
	}

	/**
	 * This method is used to get the total row count in the sheet.
	 *
	 * @param sheetName
	 *            the sheet name
	 * @return the int
	 * @throws DriverScriptException 
	 */

	public int getTotalRowCount(TestDriver testDriver,String sheetName) throws DriverScriptException {
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getTotalRowCount(String) - start"); //$NON-NLS-1$
		}

		Sheet xlSheet;
		xlSheet = wb.getSheet(sheetName);
		if(xlSheet==null){
			throw new DriverScriptException(new Throwable( sheetName+" does not exists"));
		}
		int returnint = xlSheet.getLastRowNum();
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getTotalRowCount(String) - end"); //$NON-NLS-1$
		}
		return returnint;
	}

	/**
	 * This method is used to get the total column count in the sheet.
	 *
	 * @param sheetName
	 *            the sheet name
	 * @return the int
	 */
		public int getTotalColumnCount(TestDriver testDriver,String sheetName,int row) {
			if (LOG.isDebugEnabled()) {
				DriverScript.logMessage(testDriver,"info","getTotalColumnCount(String) - start"); //$NON-NLS-1$
			}

			int columncount = 0;
			Sheet xlSheet;
			xlSheet = wb.getSheet(sheetName);
		Row row2 = xlSheet.getRow(row);
			for (int colcnt = 0; colcnt <= row2.getLastCellNum(); colcnt++) {
				columncount = columncount + 1;
			}
			if (LOG.isDebugEnabled()) {
				DriverScript.logMessage(testDriver,"info","getTotalColumnCount(String) - end"); //$NON-NLS-1$
			}
			return columncount;
		}
	
	/**
	 * This method is used to get the total column count in the sheet.
	 *
	 * @param sheetName
	 *            the sheet name
	 * @return the int
	 */
	public int getTotalColumnsInRow(TestDriver testDriver,String sheetName) {
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getTotalColumnCount(String) - start"); //$NON-NLS-1$
		}

		int columncount = 0;
		Sheet xlSheet;
		xlSheet = wb.getSheet(sheetName);
		columncount = xlSheet.getRow(0).getLastCellNum();
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getTotalColumnCount(String) - end"); //$NON-NLS-1$
		}
		return columncount;
	}

	/**
	 * Gets the number of sheets.
	 * 
	 * @return the number of sheets
	 */
	public int getNumberOfSheets(TestDriver testDriver) {
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getNumberOfSheets() - start"); //$NON-NLS-1$
		}

		int returnint = wb.getNumberOfSheets();
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getNumberOfSheets() - end"); //$NON-NLS-1$
		}
		return returnint;
	}

	/**
	 * isSheetExists.
	 *
	 * @param sheetName
	 *            the sheet name
	 * @return true, if is sheet exists
	 * @returns whether sheet exists
	 */
	public boolean isSheetExists(TestDriver testDriver,String sheetName) {
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","isSheetExists(String) - start"); //$NON-NLS-1$
		}

		Sheet xlSheet = null;
		if (sheetName != null) {

			xlSheet = wb.getSheet(sheetName);
		}
		if (xlSheet != null) {
			if (LOG.isDebugEnabled()) {
				DriverScript.logMessage(testDriver,"info","isSheetExists(String) - end"); //$NON-NLS-1$
			}
			return true;
		} else {
			if (LOG.isDebugEnabled()) {
				DriverScript.logMessage(testDriver,"info","isSheetExists(String) - end"); //$NON-NLS-1$
			}
			return false;
		}
	}

	/**
	 * Gets the sheet names.
	 * 
	 * @param index
	 *            the index
	 * @return the sheet names
	 */
	public String getSheetNames(TestDriver testDriver,int index) {
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getSheetNames(int) - start"); //$NON-NLS-1$
		}

		String sheetName = null;
		sheetName = wb.getSheetName(index);

		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getSheetNames(int) - end"); //$NON-NLS-1$
		}
		return sheetName;
	}

	/**
	 * Gets the sheet count.
	 *
	 * @return the sheet count
	 */
	public int getSheetCount(TestDriver testDriver) {
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getSheetCount() - start"); //$NON-NLS-1$
		}

		int sheetCount = 0;
		sheetCount = wb.getNumberOfSheets();

		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getSheetCount() - end"); //$NON-NLS-1$
		}
		return sheetCount;

	}

	/**
	 * This method is used to get the cell value in the sheet.
	 *
	 * @param wb
	 *            the workbook excel
	 * @param sheetName
	 *            the sheet name
	 * @param iRow
	 *            the row no
	 * @param iCol
	 *            the column no
	 * @return the cell
	 */

	public Cell getCell(TestDriver testDriver,Workbook wb, String sheetName, int iRow, int iCol) {
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getCell(Workbook, String, int, int) - start"); //$NON-NLS-1$
		}

		Sheet xlSheet;
		Row oRow;
		Cell oCell;

		xlSheet = wb.getSheet(sheetName);
		oRow = xlSheet.getRow(iRow);
		oCell = oRow.getCell(iCol);

		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getCell(Workbook, String, int, int) - end"); //$NON-NLS-1$
		}
		return oCell;
	}

	/**
	 * This method is used to get the row number for a particular ALMID and Business Component
	 * @param sheetName
	 *             the sheet name
	 * @param idValue
	 *             the ALMID value
	 * @param businessComponentValue
	 *             the business component value
	 * @return
	 *             the row number of ALMID
	 */
	public int getRowNumberForId(TestDriver testDriver,String sheetName, String idValue, String businessComponentValue){
		Sheet xlSheet;
		int rowNum = 0;

		xlSheet = wb.getSheet(sheetName);
		for (Row oRow : xlSheet) {
			for (Cell oCell : oRow) {
				if (oCell.getStringCellValue() != null) {
					String idCellString = oCell.getRichStringCellValue().getString().trim();
					if (idCellString.equals(idValue) ) {
						for (Cell innerRowCell : oRow) {
							if (innerRowCell.getStringCellValue() != null) {
								String businessComponentString = innerRowCell.getRichStringCellValue().getString().trim();
								if(businessComponentString.equals(businessComponentValue)){
									rowNum=oRow.getRowNum();  
								}
							}
						}
					}
				}
			}       
		}
		return rowNum;
	}
	
	/**
	 * This method is used to get the set of values(key,value) for a particular ALMID and Business Component
	 * @param sheetName
	 *             the sheet name
	 * @param columnCount
	 *             the number of columns to be fetched
	 * @param idValue
	 *              the ALMID value
	 * @param businessComponentValue
	 *              the business component value
	 * @return
	 *              map consisting of all the values for the particular row.
	 */
	public Hashtable<String, String> getFieldValues(TestDriver testDriver,String sheetName, int columnCount, String idValue, String businessComponentValue){
		int rowNum = getRowNumberForId(testDriver,sheetName, idValue, businessComponentValue);
		Hashtable<String, String> fieldValues = new Hashtable<String, String>();
		//int columnCount = xlSheet.getRow(rowNum).getPhysicalNumberOfCells();
		/*Start from third column(i.e,colNum=2) since field values are in*/
		for(int i=2;i<columnCount+2;i++){
			String cellValue = getCellValue(testDriver,sheetName, rowNum, i);
			String[] mapValue = cellValue.split("=");
			String key = mapValue[0];
			String value = mapValue[1];
			fieldValues.put(key,value);
		}

		return fieldValues;
	}
	
	/**
	 * This method is used to get the data from global parameters sheet.
	 * @param sheetName
	 * @return
	 */
	public Hashtable<String, String> getGlobalParams(TestDriver testDriver,String sheetName){
		Sheet xlSheet;
		xlSheet = wb.getSheet(sheetName);
		Hashtable<String, String> globalParams = new Hashtable<String, String>();
		for (Row selectedRow : xlSheet) {
			String key = getCellValue(testDriver,sheetName, selectedRow.getRowNum(), 0);
			String data = getCellValue(testDriver,sheetName, selectedRow.getRowNum(), 1);
			globalParams.put(key, data);
		}

		return globalParams;
	}

	public int getTotalRowCountWithExecuteFlag(TestDriver testDriver,String sheetName) throws DriverScriptException{
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getTotalRowCount(String) - start"); //$NON-NLS-1$
		}

		Sheet xlSheet;
		xlSheet = wb.getSheet(sheetName);
		if(xlSheet==null){
			throw new DriverScriptException(new Throwable( sheetName+" does not exists"));
		}
		int totalExecutableRows =0;
		int returnint = xlSheet.getLastRowNum();
		for(int i=1; i<=returnint;i++){
			if("Y".equalsIgnoreCase(getCellValue(testDriver,sheetName, i, 0))){
				totalExecutableRows++;
			} else if("EndOfSheet".equalsIgnoreCase(getCellValue(testDriver,sheetName, i, 0))){
				break;
			}
		}
		if (LOG.isDebugEnabled()) {
			DriverScript.logMessage(testDriver,"info","getTotalRowCount(String) - end"); //$NON-NLS-1$
		}
		return totalExecutableRows;
	}

}
