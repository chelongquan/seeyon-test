package main.seeyon.crcutil;



import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.CRC32;

public class File2CRCUtil {
	/**
	 * 获取文件CRC32码
	 * 5A83C543
	 * @return String
	 * */
	public static String getCRC32(String uri) {
		CRC32 crc32 = new CRC32();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(new File(uri));
			byte[] buffer = new byte[8192];
			int length;
			while ((length = fileInputStream.read(buffer)) != -1) {
				crc32.update(buffer, 0, length);
			}
			return Long.toHexString(crc32.getValue()).toUpperCase()+ "";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (fileInputStream != null)
					fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		String uri = "D:\\hotfixFile";
		String tmpUri = "D:\\temp";
		Date startDate = new Date();
		System.out.println("开始时间2"+new Date());
		func(new File(uri));
		//func(new File(tmpUri));
		 long cost= System.currentTimeMillis()- startDate.getTime();
		System.out.println("获取seeyon目录crc耗时"+formatTime(cost) );
		
	}
	static int num = 0;
	private static void func(File file){
		File[] filelist = file.listFiles();
		for(File f:filelist){
			if(f.isDirectory()&& !f.getName().equals("autoinstall")&& !f.getName().equals("help"))	//若是目录，则递归打印该目录下的文件
				func(f);
			if(f.isFile())		//若是文件，直接打印
				System.out.println("文件名称="+f.getAbsolutePath()+"=========="+getCRC32(f.getAbsolutePath())+"文件数量=="+ num++);
			/*if(f.getName().contains(".jar")&& f.getName().startsWith("seeyon")){
				decompress(f.getAbsolutePath(),"E:\\temp\\"+f.getName().substring(0, f.getName().length()-3));
			}*/
				
		}
	}
	
    public static String formatTime(Long ms) {  
        Integer ss = 1000;  
        Integer mi = ss * 60;  
        Integer hh = mi * 60;  
        Integer dd = hh * 24;  
        Long day = ms / dd;  
        Long hour = (ms - day * dd) / hh;  
        Long minute = (ms - day * dd - hour * hh) / mi;  
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;  
        StringBuffer sb = new StringBuffer();  
        if(day > 0) {  
            sb.append(day+"天");  
        }  
        if(hour > 0) {  
            sb.append(hour+"小时");  
        }  
        if(minute > 0) {  
            sb.append(minute+"分");  
        }  
        if(second > 0) {  
            sb.append(second+"秒");  
        }  
        return sb.toString();  
    } 
     
    /**
	 * 解压jar文件
	 */
	public static synchronized void decompress(String fileName,String outputPath){
		
			if (!outputPath.endsWith(File.separator)) {
				outputPath += File.separator;
			}
			File dir = new File(outputPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			JarFile jf = null;
		try{
			jf =  new JarFile(fileName);
			for (Enumeration<JarEntry> e = jf.entries(); e.hasMoreElements();) {
				JarEntry je = (JarEntry) e.nextElement();
				String outFileName = outputPath + je.getName();
				File f = new File(outFileName);
				if(je.isDirectory()){
					if(!f.exists()){
						f.mkdirs();
					}
				}else{
					File pf = f.getParentFile();
					if(!pf.exists()){
						pf.mkdirs();
					}
					InputStream in = jf.getInputStream(je);
					OutputStream out = new BufferedOutputStream(
							new FileOutputStream(f));
					byte[] buffer = new byte[2048];
					int nBytes = 0;
					while ((nBytes = in.read(buffer)) > 0) {
						out.write(buffer, 0, nBytes);
					}
					out.flush();
					out.close();
					in.close();
				}
			}
		}catch(Exception e){
			System.out.println("解压"+fileName+"出错---"+e.getMessage());
		}
	}
}
