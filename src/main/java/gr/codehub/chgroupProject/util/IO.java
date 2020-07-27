package gr.codehub.chgroupProject.util;

import gr.codehub.chgroupProject.model.Applicant;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;


public class IO {

    public static Workbook createWorkbook (String filename) throws IOException, InvalidFormatException
    {
        File workbookFile = new File(filename);
        FileInputStream file = new FileInputStream(workbookFile);
        return WorkbookFactory.create(file);

    }
}
