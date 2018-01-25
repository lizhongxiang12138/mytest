package com.lzx.common;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.OutputStream;
import java.util.*;

import static com.sun.tools.doclets.formats.html.markup.HtmlStyle.title;

/**
 * pdf 工具
 * @author lzx
 */
public class PDFUtils {

    public static void getExcelPDF(String[] headers, java.util.List<String> lists, OutputStream out) {

        PdfWriter pw = null;

        try {
            Document document = new Document(PageSize.A4,50,50,50,50);
            //必须紧跟在document创建的时候创建PdfWriter,否则导出无数据
            pw = PdfWriter.getInstance(document,out);

            document.open();
            //绝对路径
//            String fontPath = realPath + "/WEB-INF/classes/font/SIMYOU.TTF";
//            BaseFont baseFont = BaseFont.createFont(fontPath,BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
            BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",false);
            Font font = new Font(baseFont,10,Font.NORMAL);


            Paragraph p = new Paragraph("title",font);
            document.add(p);

            PdfPTable table = new PdfPTable(headers.length);
            table.setSpacingBefore(25);
            table.setSpacingAfter(25);
            //创建表头
            for (int i = 0; i < headers.length; i++) {
                PdfPCell cell = new PdfPCell(new Paragraph(headers[i],font));
                table.addCell(cell);
            }


            for (int i = 0; i < lists.size(); i++) {
                String value = "";
                if(null == lists.get(i)){
                    value = "";
                }else{
                    value = lists.get(i).toString();
                }
                table.addCell(new Paragraph(value,font));
            }


            document.add(table);
            document.close();

            pw.flush();
            pw.close();

        }catch (Exception e){

        }finally {
            if(null!=pw){pw.close();}
        }

    }
}
