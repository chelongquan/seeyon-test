package main.seeyon.file;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by amosli on 14-7-2.
 */
public class DownUtilTest {

//    public static void main(String args[]) throws Exception {
////        final DownUtil downUtil = new DownUtil("http://mirrors.cnnic.cn/apache/tomcat/tomcat-7/v7.0.54/bin/apache-tomcat-7.0.54.zip", "tomcat-7.0.54.zip", 3);
//    	String u = "http://localhost:8090/server2/hotfix.do?method=downloadHotfix&versionParam=v8.0_2020-05-28";
//    	u="http://192.168.225.47:8080/server2/hotfix.do?method=downloadHotfix&versionParam=v1.1_big";
//    	final DownUtil downUtil = new DownUtil(u,"d://", 20);
//        downUtil.download();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(downUtil.getCompleteRate()<1){
//                    System.out.println("已完成:"+downUtil.getCompleteRate());
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        }).start();
//    }
	public static void main(String[] args) {
		try {
			// "http://192.168.225.47:8080/server2/hotfix.do?method=downloadHotfix&versionParam=v8.0_2020-05-28"
			URL url = new URL("http://localhost:8090/server2/hotfix.do?method=downloadHotfix&versionParam=v8.0_2020-05-28");
//			URL url = new URL("http://192.168.225.47:8080/server2/hotfix.do?method=downloadHotfix&versionParam=v7.0_2019-01-01");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "*/*");
			conn.setRequestProperty("Accept-Language", "zh-CN");
			conn.setRequestProperty("Charset", "UTF-8");
			InputStream inStream = conn.getInputStream();
			
			System.out.println(conn.getResponseCode());
			
			int fileSize = conn.getContentLength();
			if (fileSize < 1) {
				return;
			}
			// 文件名
			String fileName = URLDecoder.decode(conn.getHeaderField("filename"), "UTF-8");
			// 补丁描述
			String readMe = URLDecoder.decode(conn.getHeaderField("fileReadme"), "UTF-8");
			byte[] buffer = new byte[fileSize];
			int hasRead = 0;
			RandomAccessFile currentPart = new RandomAccessFile("d://" + fileName, "rw");
			// 读取网络数据，并写入本地文件
			int length = 0;
			while ((hasRead = inStream.read(buffer)) != -1) {
				currentPart.write(buffer, 0, hasRead);
				// 累计该线程下载的总大小
				length += hasRead;
				System.out.println("当前位置" + length + ",当前进度" + (length * 0.1 / fileSize));
			}
			currentPart.close();
			inStream.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
