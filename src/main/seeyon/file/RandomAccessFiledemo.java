package main.seeyon.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class RandomAccessFiledemo {
	public static void main(String[] args)
	{
	    try
	    {
	    	 RandomAccessFile raf = new RandomAccessFile("d://a.txt","rw");
	    	 byte[] buffer = new byte[1024];
	    	 raf.skipBytes(10);
	    	 raf.read(buffer,0,10);
	    	 
	    	 
//	    	 for (int i = 0; i < 10000; i++)
//	         {
//	    		 raf.write(String.valueOf(i+",").getBytes());
//	         }
//	    	 raf.close();
	    	 System.out.println();
	    	 RandomAccessFile b = new RandomAccessFile("d://b.txt","rw");
	    	 b.seek(10);
	    	 b.write(buffer);
//	        insert("d:/out.txt",0,"插入的内容");
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	}

	private static void insert(String fileName,long pos,String content) throws IOException
	{
	    //创建临时空文件
	    File tempFile = File.createTempFile("temp",null);
	    //在虚拟机终止时，请求删除此抽象路径名表示的文件或目录
	    tempFile.deleteOnExit();
	    FileOutputStream fos = new FileOutputStream(tempFile);
	    RandomAccessFile raf = new RandomAccessFile(fileName,"rw");
	    raf.seek(pos);
	    byte[] buffer = new byte[4];
	    int num = 0;
	    while(-1 != (num = raf.read(buffer)))
	    {
	        fos.write(buffer,0,num);
	    }
	    raf.seek(pos);
	    raf.write(content.getBytes());
	    FileInputStream fis = new FileInputStream(tempFile);
	    while(-1 != (num = fis.read(buffer)))
	    {
	        raf.write(buffer,0,num);
	    }
	}  

}
