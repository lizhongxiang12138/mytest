package com.lzx.controller;

import com.lzx.common.PDFUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by admin on 2018/1/25.
 */
@RestController
public class PDFController {

    @RequestMapping("/getPDF")
    public void getPDF(HttpServletResponse response){
        OutputStream out = null;
        try {
            response.setContentType("application/pdf;charset=utf-8");
//            response.addHeader("Content-Disposition", "attachment;filename=" + new String("hhhhh".getBytes("GBK"), "ISO8859-1"));
            response.setCharacterEncoding("utf-8");
            out = response.getOutputStream();
            String[] headers = new String[]{"名字","年龄"};
            List<String> lists = Arrays.asList(new String[]{"我,23", "你,20"});
            PDFUtils.getExcelPDF(headers,lists,out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null!=out){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
