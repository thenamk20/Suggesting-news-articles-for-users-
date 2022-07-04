package com.thenam.project2server.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelHandlingUtils {

    public Map<Integer, List<String>> excelHandling(String fileLocation) {

        File tmp = new File(fileLocation);
        Map<Integer, List<String>> data = new HashMap<>();

        try {

            FileInputStream file = new FileInputStream(tmp);
            Workbook workbook = new HSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(0);

            int i = 0;
            for (Row row : sheet) {
                data.put(i, new ArrayList<String>());
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            data.get(i).add(cell.getRichStringCellValue().getString());
                            break;

                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                data.get(i).add(cell.getDateCellValue() + "");
                            } else {
                                data.get(i).add(cell.getNumericCellValue() + "");
                            }
                            break;
                        case BOOLEAN:
                            data.get(i).add(cell.getBooleanCellValue() + "");
                            break;
                        case FORMULA:
                            data.get(i).add(cell.getCellFormula() + "");
                            break;
                        default: data.get(i).add(" ");
                    }
                }
                i++;
            }

        } catch (Exception e) {
            System.out.println("Error message:" + e);
        }

        return data;
    }

}
