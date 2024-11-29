package org.example.html2pdf;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import java.io.*;

/**
 * @author HuberyYan
 * @data 2024/11/25
 * @apiNote
 */
public class HtmlToPdfConverter {
    public static InputStream convertToPdf(String html) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();

        try {
            // 设置HTML内容
            builder.withHtmlContent(html, null);
            // 设置PDF输出流
            builder.toStream(outputStream);
            // 执行转换
            builder.run();

            // 返回输入流
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void writePdfToFile(InputStream pdfInputStream, String filePath) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = pdfInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pdfInputStream != null) {
                    pdfInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String htmlContent = PdfDemo.readFileToString("/templates/aaaaa.html");
        InputStream pdfInputStream = convertToPdf(htmlContent);
        // pdfInputStream写出文件
        writePdfToFile(pdfInputStream, "result.pdf");
    }
}
