package io.github.vort2014.spring.service;

import java.nio.file.Path;

/**
 * Created on 08.08.2017.
 */
public interface PoiService {

    Path getExcel() throws Exception;

    Path getExcelWithLockedCells() throws Exception;
}
