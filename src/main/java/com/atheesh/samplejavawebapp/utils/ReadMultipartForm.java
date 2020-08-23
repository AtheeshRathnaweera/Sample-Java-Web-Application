package com.atheesh.samplejavawebapp.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ReadMultipartForm {

    //get the data from multipart enc type form
    public static List<List<Object>> getMultipartFormDataFromTheRequest(HttpServletRequest request) {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        List<Object> fieldNames = new ArrayList<>();
        List<Object> fieldValues = new ArrayList<>();

        List<List<Object>> foundedValues = new ArrayList<>();

        try {
            for (FileItem item : upload.parseRequest(request)) {
                if (!item.isFormField()) {
                    fieldNames.add(item.getFieldName());
                    fieldValues.add(item);
                } else {
                    fieldNames.add(item.getFieldName());
                    fieldValues.add(item.getString());
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in " + e);
        }

        foundedValues.add(fieldNames);
        foundedValues.add(fieldValues);

        return foundedValues;
    }
}
