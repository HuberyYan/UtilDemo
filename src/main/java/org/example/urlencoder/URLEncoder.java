package org.example.urlencoder;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.jar.JarFile;

/**
 * @author HuberyYan
 * @data 2024/9/6
 * @apiNote
 */
public class URLEncoder {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //String encode = java.net.URLEncoder.encode("导入模板.xlsx", "UTF-8");
        //System.out.println(encode);
        //String a = "template" + File.separator + "议题池.html";
        //String substring = a.substring(0, a.lastIndexOf(File.separator));
        //System.out.println(substring);

        // 获取 MyClass 类的 Class 对象
        Class<?> clazz = URLEncoder.class;
        System.out.println(clazz.getSimpleName());
        // 获取类的资源 URL
        URL classUrl = clazz.getResource(clazz.getSimpleName() + ".class");

        if (classUrl != null) {
            try {
                // 检查是否在 JAR 文件中
                if (classUrl.getProtocol().equals("jar")) {
                    JarURLConnection jarConnection = (JarURLConnection) classUrl.openConnection();
                    JarFile jarFile = jarConnection.getJarFile();
                    File jarFilePath = new File(jarFile.getName());

                    // 输出 JAR 文件的路径
                    System.out.println("JAR 文件路径: " + jarFilePath.getAbsolutePath());
                } else {
                    // 获取类的文件路径
                    String classFilePath = classUrl.getPath();

                    // 获取类所在的目录
                    File classDir = new File(classFilePath).getParentFile();

                    // 输出类所在的目录
                    System.out.println("类所在目录: " + classDir.getAbsolutePath());
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("无法获取类的资源路径: " + e.getMessage());
            }
        } else {
            System.out.println("无法获取类的资源 URL");
        }

    }
}
