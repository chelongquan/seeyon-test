/**
 * Author seeyon-chelq
 * Rev
 * Date: 2021-05-20 13:07
 * Copyright (C) 2021 Seeyon, Inc. All rights reserved.
 * This software is the proprietary information of Seeyon, Inc.
 * Use is subject to license terms.
 */
package main.seeyon.file;

import com.dj.ofd.reader.OFDReader;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @date 2021-05-20 13:07
 * @since seeyon-test
 * @author seeyon-chelq
 */
public class FilePageTest {
	public static void main(String[] args) throws IOException {
		
//		File destFile = new File("C:\\Users\\seeyon-chelq\\Desktop\\金山doc.doc");
//		try {
//			WordExtractor doc = new WordExtractor(new FileInputStream(destFile.getPath()));
//			int pageNum = doc.getSummaryInformation().getPageCount();//总页数
//			System.out.println("doc-wps页数："+pageNum);
//		} catch (Exception e) {
//			System.out.println(e.getStackTrace());
//		}
//
//		File pdfDestFile = new File("C:\\Users\\seeyon-chelq\\Desktop\\使用内网或者公网的webOffice，需要怎么玩？.pdf");
//		try {
//			PdfReader pdfReader = new PdfReader(new FileInputStream(pdfDestFile));
//			int pageNum = pdfReader.getNumberOfPages();
//			System.out.println("pdf页数："+pageNum);
//		} catch (Exception e) {
//			System.out.println(e.getStackTrace());
//		}
//
		File docxDestFile = new File("C:\\Users\\seeyon-chelq\\Desktop\\金山docx右键.docx");
		try(XWPFDocument docx = new XWPFDocument(POIXMLDocument.openPackage(docxDestFile.getPath()))) {
			int pageNum = docx.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();//总页数
			System.out.println("docx页数："+pageNum);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
//		try {
//			//file = EncryptCoderUtil.decryptFileToTemp(file, EncryptActionEnum.ENCRYPT_ATTACHMENT);
//			//file = CoderFactory.getInstance().decryptFileToTemp(file);
//			ZipFile zipFile = new ZipFile("C:\\Users\\seeyon-chelq\\Desktop\\ofd文档1.ofd");
//			Enumeration<? extends ZipEntry> entries = zipFile.entries();
//			int c=0;
//			while(entries.hasMoreElements()){
//				ZipEntry entry = entries.nextElement();
//				InputStream stream = zipFile.getInputStream(entry);
//				if(entry.getName().startsWith("Doc_0/Page_")){
//					c++;
//				}
//			};
//			System.out.println("ofd页数："+c);
//		} catch (Exception e) {
//			System.out.println(e);
//		}

//		Path ofdPath = Paths.get("C:\\Users\\seeyon-chelq\\Desktop\\ofd多页数.ofd");
//		try(OFDReader ofdReader = new OFDReader(ofdPath)){
//			// 获取文档总页数
//			int pageNumbers =  ofdReader.getNumberOfPages();
//			System.out.println("ofd 文档总页数为: " + pageNumbers);
//		}catch (Exception e){
//			e.printStackTrace();
//		}
		
	}
}
