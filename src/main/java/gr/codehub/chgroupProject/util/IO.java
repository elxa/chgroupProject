package gr.codehub.chgroupProject.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class IO {
    /**
     *
     * @param filename
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static Workbook createWorkbook (String filename) throws IOException, InvalidFormatException {
        File workbookFile = new File(filename);
        FileInputStream file = new FileInputStream(workbookFile);
        return WorkbookFactory.create(file);  //todo auto edw eprepe na einai me kefalaio??????
    }
}
