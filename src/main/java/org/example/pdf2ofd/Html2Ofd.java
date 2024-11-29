package org.example.pdf2ofd;

import org.ofdrw.converter.ofdconverter.PDFConverter;
import org.ofdrw.converter.ofdconverter.TextConverter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author HuberyYan
 * @data 2024/11/29
 * @apiNote
 * jar包源代码地址：
 * https://gitee.com/ofdrw/ofdrw/blob/master/ofdrw-converter/src/main/java/org/ofdrw/converter/ofdconverter/PDFConverter.java
 */
public class Html2Ofd {

    public static void main(String[] args) throws IOException {
        String pdfPath = "htmltest.pdf";
        Path src = Paths.get(pdfPath);
        Path dst = Paths.get("htmltest.ofd");
        try (PDFConverter converter = new PDFConverter(dst)) {
            converter.convert(src);
        }
        System.out.println(">> " + dst.toAbsolutePath());
    }
}
