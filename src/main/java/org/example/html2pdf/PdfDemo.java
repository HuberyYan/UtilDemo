package org.example.html2pdf;
import io.woo.htmltopdf.HtmlToPdf;
import io.woo.htmltopdf.HtmlToPdfObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 这个实操使用过
 */
public class PdfDemo {
    public static void main(String[] args) {
        //urlToPdf();
        htmlToPdf();
    }

    /**
     * 根据Html转换为pdf文件 支持多个str
     */
    private static void htmlToPdf() {
        // 读取  templates/议题池.html 文件内容，转为String
        String htmlContent = readFileToString("/templates/result.html");
        HtmlToPdf.create()
                //.object(HtmlToPdfObject.forHtml(htmlContent).defaultEncoding("utf8"))
                .object(HtmlToPdfObject.forHtml(htmlContent).defaultEncoding("utf8")).convert("htmltest.pdf");
    }
    public static String readFileToString(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        URL resource = PdfDemo.class.getResource(filePath);
        if (resource == null) {
            System.err.println("文件未找到: " + filePath);
            return null;
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.openStream()))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return contentBuilder.toString();
    }

    /**
     * 根据url转换为pdf文件 支持多个url html和url可以混合使用
     */
    private static  void urlToPdf() {
        HtmlToPdf.create()
                .object(HtmlToPdfObject.forUrl("https://blog.51cto.com/itShareArea/6182341"))
                .object(HtmlToPdfObject.forUrl("https://blog.51cto.com/itShareArea/6179253"))
                .convert("D:\\Test\\url-html.pdf");
    }

}
