package org.example.filepath;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HuberyYan
 * @data 2024/11/25
 * @apiNote
 */
public class GetFilePath {
    public static String getClassPath() {
        // 获取类的资源 URL
        URL classUrl = GetFilePath.class.getResource("GetFilePath.class");
        if (classUrl == null) {
            throw new RuntimeException("无法获取类的资源 URL");
        }
        try {
            // 检查是否在 JAR 文件中
            //if (classUrl.getProtocol().equals("jar")) {
            //    JarURLConnection jarConnection = (JarURLConnection) classUrl.openConnection();
            //    JarFile jarFile = jarConnection.getJarFile();
            //    File jarFilePath = new File(jarFile.getName());
            //    // 输出 JAR 文件的路径
            //    System.out.println("JAR 文件路径: " + jarFilePath.getAbsolutePath());
            //    return jarFilePath.getAbsolutePath();
            //} else {
            // 获取类的文件路径
            String classFilePath = classUrl.getPath();
            // 获取类所在的目录
            File classDir = new File(classFilePath).getParentFile();
            // 输出类所在的目录
            System.out.println("类所在目录: " + classDir.getAbsolutePath());
            return classDir.getAbsolutePath();
            //}
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("无法获取类的资源路径: " + e.getMessage());
            throw new RuntimeException("无法获取类的资源路径", e);
        }
    }

    public static void main(String[] args) {
        getClassPath();
        // 获取项目根目录的绝对路径
        String projectPath = System.getProperty("user.dir");
        System.out.println("项目根目录的绝对路径: " + projectPath);
    }
}
