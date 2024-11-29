package org.example.fillhtml;

import com.alibaba.fastjson.JSONArray;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author HuberyYan
 * @data 2024/11/18
 * @apiNote
 */
public class FileHtmlUtil {

    public static String fillTemplate(String templateName, Map<String, Object> data) throws IOException, TemplateException {
        // 配置 FreeMarker
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(FileHtmlUtil.class, "/templates");
        // 加载模板
        Template template = cfg.getTemplate(templateName);
        // 输出字符串结果
        StringWriter writer = new StringWriter();
        template.process(data, writer);
        // 写入到 HTML 文件
        Writer fileWriter = new FileWriter("result.html");
        template.process(data, fileWriter);
        fileWriter.flush();
        fileWriter.close();
        return writer.toString();
    }
    public static String writeHtmlFile(String filePath, String fileInfo) {
        // 随机生成文件名
        //String uuid = UUID.randomUUID().toString().replace("-", "") + ".html";
        File file = new File(filePath);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileInfo.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public static void main(String[] args) {
        try {
            List<Person> list = new ArrayList<>();
            list.add(new Person("1", "asdfasdf", "同意"));
            list.add(new Person("2", "asdfasdf", "同意"));
            list.add(new Person("3", "asdfasdf", "同意"));
            JSONArray jsonArray = JSONArray.parseArray(JSONArray.toJSONString(list));
            Map<String, Object> data = new HashMap<>();
            data.put("spList", jsonArray);
            data.put("lxList", list);
            data.put("zyld", "张三");
            data.put("bmld", "张三");
            data.put("fgld", "张三");
            data.put("csyj", "张三");
            data.put("hq", "张三");
            data.put("bgshqyj", "张三");
            data.put("yjyj", "张三");
            //data.put("ngbm", "张三");
            //data.put("ngr_name", "张三");
            //data.put("telephone", "张三");
            //data.put("ng_date", "张三");
            //data.put("spd_code", "张三");
            //data.put("title", "张三");
            //data.put("rwly", "张三");
            //data.put("qwkh_date", 25);
            //data.put("sfxxldysp", 25);
            //data.put("sxqd", 25);
            //data.put("sxlx", 25);
            //data.put("sxbm", 25);
            //data.put("hysx", 25);
            //data.put("fwyj", 25);
            //data.put("fwyj_file", 25);
            //data.put("miji", 25);
            //data.put("mgxxlx", 25);
            //data.put("bmnx", 25);
            //data.put("fjxzqxfw", 25);
            //data.put("gldyyt", 25);
            //data.put("glhyhyt", 25);
            data.put("sfbgzw", 25);
            data.put("sftqyj", 25);
            data.put("tqyjqk", 25);
            data.put("sfhgglsx", 25);
            data.put("zlzsfzs", 25);
            data.put("lxbm", 25);
            data.put("hqbm", 25);
            data.put("hj", 25);
            data.put("xzyj", 25);
            data.put("yjyy", 25);
            data.put("yjwj_file", 25);
            data.put("tjzw_file", 25);
            data.put("hgscb_file", 25);
            data.put("tqyjqk_file", 25);
            data.put("ytjy", 25);
            data.put("shjg", 25);
            String result = fillTemplate("议题池.html", data);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Person {
        private String num;
        private String name;
        private String opinion;

        public Person(String num, String name, String opinion) {
            this.num = num;
            this.name = name;
            this.opinion = opinion;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOpinion() {
            return opinion;
        }

        public void setOpinion(String opinion) {
            this.opinion = opinion;
        }

        @Override
        public String toString() {
            return "{" +
                    "num='" + num + '\'' +
                    ", name='" + name + '\'' +
                    ", opinion='" + opinion + '\'' +
                    '}';
        }
    }
}
