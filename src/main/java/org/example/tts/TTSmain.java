//package org.example.tts;
//
//import com.jacob.activeX.ActiveXComponent;
//import com.jacob.com.Dispatch;
//import com.jacob.com.Variant;
//
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
///**
// * @author HuberyYan
// * @data 2024/9/24
// * @apiNote
// */
//public class TTSmain {
//
//    public static void main(String[] args) {
//        //调用windowsApi 的 com组件，Sapi.spVoice是 windows com组件名称
//        ActiveXComponent activeXComponent = new ActiveXComponent("Sapi.SpVoice");
//        //从com组件中获得调度目标
//        Dispatch dis = activeXComponent.getObject();
//        try {
//            //设置语言组件属性
//            // 音量 0-100
//            activeXComponent.setProperty("Volume", new Variant(100));
//            // 语音朗读速度 -10 到 +10
//            activeXComponent.setProperty("Rate", new Variant(-2));
//            // 读取windows指定目录的txt文件内容
//            String content = "";
//            // 读取txt文件内容
//            try {
//                content = new String(Files.readAllBytes(Paths.get("D:\\MyProject\\Demo\\src\\txt\\text.txt")));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            //调用speak方法，传入txt文件内容，从指定位置开始读取
//            Variant speak = Dispatch.call(dis, "Speak", new Variant(content), new Variant(0));
//            // 实时获取当前播报的位置
//
//            //Dispatch.call(dis, "Speak", new Variant(content), new Variant(1));
//
//
//            //Variant speak = Dispatch.call(dis, "Speak", new Variant(content), new Variant(0));
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally {
//            dis.safeRelease();
//            activeXComponent.safeRelease();
//        }
//
//    }
//
//}
