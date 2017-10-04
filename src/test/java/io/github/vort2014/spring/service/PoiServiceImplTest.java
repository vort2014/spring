package io.github.vort2014.spring.service;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created on 08.08.2017.
 */
public class PoiServiceImplTest {

    private Path excelWithLockedCells;
    private Path excel;

    @Before
    public void setUp() throws Exception {
        PoiServiceImpl poiServiceImpl = new PoiServiceImpl();
        excelWithLockedCells = poiServiceImpl.getExcelWithLockedCells();
        excel = poiServiceImpl.getExcel();
    }

    @After
    public void tearDown() throws Exception {
        Files.delete(excelWithLockedCells);
        Files.delete(excel);
    }

    @Test
    public void testGetExcelWithLockedCells() throws Exception {

        // run
        InputStream inputStream = Files.newInputStream(excelWithLockedCells);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet spreadsheet = xssfWorkbook.getSheetAt(0);
        boolean lockedCell = spreadsheet
                .getRow(1)
                .getCell(1)
                .getCellStyle()
                .getLocked();
        boolean unlockedCell = spreadsheet
                .createRow(10)
                .createCell(0)
                .getCellStyle()
                .getLocked();
    }

    @Test
    public void testGetExcel() throws Exception {
        // run
        InputStream inputStream = Files.newInputStream(excel);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet spreadsheet = xssfWorkbook.getSheetAt(0);
        boolean lockedCell = spreadsheet
                .getRow(0)
                .getCell(0)
                .getCellStyle()
                .getLocked();
        boolean unlockedCell = spreadsheet
                .createRow(6)
                .createCell(0)
                .getCellStyle()
                .getLocked();
    }
}