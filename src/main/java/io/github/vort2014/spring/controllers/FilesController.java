package io.github.vort2014.spring.controllers;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created on 26.05.2017.
 */
public interface FilesController {

    String getFilesList(Map<String, Object> model) throws Exception;

    void getFile(String filename, HttpServletResponse httpResponse) throws Exception;

    void getExcel(HttpServletResponse httpResponse) throws Exception;

    void getExcelWithLockedCells(HttpServletResponse httpResponse) throws Exception;
}
