package io.github.vort2014.spring.controllers;

import io.github.vort2014.spring.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * Created on 26.05.2017.
 */
@Controller // @RestController doesn't work with freemarker
public class FilesControllerImpl implements FilesController {

    @Autowired
    private PoiService poiService;

    @Override
    @GetMapping("/files")
    public String getFilesList(Map<String, Object> model) throws Exception {
        List<String> files = Files.walk(Paths.get("."))
                .filter(Files::isRegularFile)
                .map(path -> path.getFileName().toString())
                .collect(toList());
        model.put("files", files);
        return "index";
    }

    @Override
    @GetMapping("/files/{filename:.+}")
    public void getFile(@PathVariable String filename, HttpServletResponse httpResponse) throws Exception {
        Path path = Paths.get(filename);
        if (Files.notExists(path)) {
            String errorMessage = "Sorry. The file <b>" + filename + "</b> you are looking for does not exist";
            httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            OutputStream out = httpResponse.getOutputStream();
            out.write(errorMessage.getBytes(StandardCharsets.UTF_8));
            out.close();
            return;
        }
        httpResponse.setContentType(Files.probeContentType(path));
        httpResponse.addHeader("Content-Length", String.valueOf(Files.size(path)));
        httpResponse.addHeader("Content-Disposition", "attachment;filename=" + filename);
        FileCopyUtils.copy(Files.newInputStream(path), httpResponse.getOutputStream());
    }

    @Override
    @GetMapping("/files/excel/simple")
    public void getExcel(HttpServletResponse httpResponse) throws Exception {
        Path path = poiService.getExcel();
        httpResponse.setContentType(Files.probeContentType(path));
        httpResponse.addHeader("Content-Length", String.valueOf(Files.size(path)));
        httpResponse.addHeader("Content-Disposition", "attachment;filename=" + path.getFileName().toString());
        FileCopyUtils.copy(Files.newInputStream(path), httpResponse.getOutputStream());
        // remove temp file
        Files.delete(path);
    }

    @Override
    @GetMapping("/files/excel/withlockedcells")
    public void getExcelWithLockedCells(HttpServletResponse httpResponse) throws Exception {
        Path path = poiService.getExcelWithLockedCells();
        httpResponse.setContentType(Files.probeContentType(path));
        httpResponse.addHeader("Content-Length", String.valueOf(Files.size(path)));
        httpResponse.addHeader("Content-Disposition", "attachment;filename=" + path.getFileName().toString());
        FileCopyUtils.copy(Files.newInputStream(path), httpResponse.getOutputStream());
        // remove temp file
        Files.delete(path);
    }
}
