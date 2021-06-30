/**
 * Author seeyon-chelq
 * Rev
 * Date: 2021-05-20 13:07
 * Copyright (C) 2021 Seeyon, Inc. All rights reserved.
 * This software is the proprietary information of Seeyon, Inc.
 * Use is subject to license terms.
 */
package main.seeyon.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 *
 * @date 2021-05-20 13:07
 * @since seeyon-test
 * @author seeyon-chelq
 */
public class FileTest {
	public static void main(String[] args) throws IOException {
//		File backFileFolder = new File("D:\\testfile");
//		Long s = System.currentTimeMillis();
//		Collection<File> backFileList = FileUtils.listFiles(backFileFolder, new IOFileFilter() {
//			@Override
//			public boolean accept(File file, String fileName) {
//
//				return fileName.endsWith("Mw6q.doc") && fileName.startsWith("CpC");
//			}
//			@Override
//			public boolean accept(File file) {
//				String fileName = file.getName();
//				return fileName.endsWith("Mw6q.doc") && fileName.startsWith("CpC");
//			}
//		}, null);
//
//		System.out.println(backFileList+"---"+(System.currentTimeMillis()-s)+"ms");
		
		
		String p = "D:\\testfile\\";
		for(int i=0;i<1500;i++){
			String tmp = CodeGenUtil.getString(""+i, 10)+".pdf";
			File testFile = new File(p+tmp);
			if (!testFile.exists()) {
				testFile.createNewFile();// 能创建多级目录
				FileUtils.copyFile(new  File("D:\\testfile\\t1.pdf"), testFile);
				System.out.println("----------------------------------"+tmp);
			}else{
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+tmp);
			}

		}
		
		
		
	}
}
