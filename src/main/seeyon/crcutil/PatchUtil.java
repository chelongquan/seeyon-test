package main.seeyon.crcutil;
///**
// * Author yuancj
// * Rev
// * Date: 2017年2月17日 上午11:23:34
// * Copyright (C) 2017 Seeyon, Inc. All rights reserved.
// * This software is the proprietary information of Seeyon, Inc.
// * Use is subject to license terms.
// */
//package main.seeyon;
//
//import com.seeyon.common.base.ServerInfoFactory;
//import com.seeyon.ctp.util.Strings;
//import com.seeyon.sfu.common.utils.Base64Util;
//import com.seeyon.sfu.common.utils.DateUtil;
//import com.seeyon.sfu.server.apps.patchupdate.domain.Patch;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.TreeSet;
//import java.util.concurrent.atomic.AtomicBoolean;
//
///**
// * <p>Title:补丁包工具类 </p>
// * <p>Description: </p>
// * <p>Copyright: Copyright (c) 2017</p>
// * <p>Company: seeyon.com</p>
// * <p>since Seeyon </p>
// */
//public class PatchUtil {
//    
//    /**
//     * 日志
//     */
//    private static final Log             LOG                = LogFactory.getLog(PatchUtil.class);
//	public static AtomicBoolean generatingCRC = new AtomicBoolean(false);// 标识是否正在生成产品的CRC文件
//	public static AtomicBoolean installingPatch = new AtomicBoolean(false);// 标识是否正在安装补丁包
//	private static final Set<String> fileWhiteList = new TreeSet<String>();
//	private static final Set<String> dicWhiteList = new TreeSet<String>();
//	
//	static {
//		dicWhiteList.add("WEB-INF/cfgHome/plugin/cip/thirdpartyinterface");
//		dicWhiteList.add("WEB-INF/cfgHome/plugin/xiaoz/base");
//		dicWhiteList.add("WEB-INF/cfgHome/plugin/xiaoz/config");
//		fileWhiteList.add("ajaxStub.js");
//		fileWhiteList.add("i18n_en.js");
//		fileWhiteList.add("i18n_init_en.js");
//		fileWhiteList.add("i18n_init_zh_CN.js");
//		fileWhiteList.add("i18n_init_zh_TW.js");
//		fileWhiteList.add("i18n_zh_CN.js");
//		fileWhiteList.add("i18n_zh_TW.js");
//		fileWhiteList.add("messageLinkConstants.js");
//		fileWhiteList.add("all-min.css");
//		fileWhiteList.add("newCollaboration-all-min.css");
//		fileWhiteList.add("content-min.js");
//		fileWhiteList.add("meeting-view-min.js");
//		fileWhiteList.add("meeting-editor-min.js");
//		fileWhiteList.add("meeting-min.js");
//		fileWhiteList.add("v3x-debug-min.js");
//		fileWhiteList.add("V3X-min.js");
//		fileWhiteList.add("ckeditor.js");
//		fileWhiteList.add("summary-min.js");
//		fileWhiteList.add("newCollaboration-all-min.js");
//		fileWhiteList.add("portal-min.js");
//		fileWhiteList.add("sectiontpls-min.js");
//		fileWhiteList.add("section-min.js");
//		fileWhiteList.add("front_common-min.js");
//		fileWhiteList.add("cap3-flow-min.js");
//		fileWhiteList.add("cap4-form-min.js");
//		fileWhiteList.add("jquery.comp-min.js");
//		fileWhiteList.add("jquery-debug-min.js");
//		fileWhiteList.add("atwho-min.js");
//		fileWhiteList.add("show-min.js");
//		fileWhiteList.add("ckeditor.js");
//		fileWhiteList.add("summaryDetail-min.js");
//		fileWhiteList.add("colList-min.js");
//		fileWhiteList.add("workflowDesigner-min.js");
//		fileWhiteList.add("workflowDesigner_api-min.js");
//		fileWhiteList.add("newColl-min.js");
//		fileWhiteList.add("coll-min.js");
//		fileWhiteList.add("all-min.js");
//		fileWhiteList.add("mobileResourceConfig.txt");
//		fileWhiteList.add("65.zip");
//	}
//	
//	private static boolean excludeFile(String path) {
//		File f = new File(path);
//		if(f.exists()) {
//			for(String dic : dicWhiteList) {
//				if(f.getParent().indexOf(dic) > -1) {
//					LOG.info("OA 校验crc 排除:"+path);
//					return true;
//				}
//			}
//			if(fileWhiteList.contains(f.getName())) {
//				LOG.info("OA 校验crc 排除:"+path);
//				return true;
//			}
//		}
//		return false;
//	}
//    /**
//     * 获取S1安装路径(不通过注册表的方式)
//     * @return
//     */
//    public static String getS1InstallPath() {
//        File file = new File(System.getProperty("user.dir"));
//        return file.getParent();
//    }
//
//    /**
//     * 获取补丁包上传路径
//     * @return
//     */
//    public static String getPatchUploadPath() {
//        return getS1InstallPath() + File.separator + "patch" + File.separator + "uploaded";
//    }
//    
//    /**
//     * 获取V5补丁包备份目录
//     * @return
//     */
//    public static String getV5PatchBackupPath(){
//        return getS1InstallPath() + File.separator + "patch" + File.separator + "backups" + File.separator + "v5";
//    }
//    
//    /**
//     * OA的CRC文件路径
//     * @return
//     */
//    public static String getA8CrcFilePath() {
//    	return ServerInfoFactory.getOAPath() + File.separator + "ApacheJetspeed" + File.separator + "check.crc";
//    }
//
//    /**
//     * 将字符串转换成Javascript，将对\r \n < > & 空格进行转换
//     *
//     * @param str
//     * @return
//     */
//    public static String escapeJavascript(String str) {
//
//        if (str == null) {
//            return str;
//        }
//
//        StringBuilder out = new StringBuilder();
//
//        int sz;
//        sz = str.length();
//        for (int i = 0; i < sz; i++) {
//            char ch = str.charAt(i);
//
//            if (ch < 32) {
//                switch (ch) {
//                    case '\b':
//                        out.append('\\');
//                        out.append('b');
//                        break;
//                    case '\n':
//                        out.append('\\');
//                        out.append('n');
//                        break;
//                    case '\t':
//                        out.append('\\');
//                        out.append('t');
//                        break;
//                    case '\f':
//                        out.append('\\');
//                        out.append('f');
//                        break;
//                    case '\r':
//                        out.append('\\');
//                        out.append('r');
//                        break;
//                    default:
//                        if (ch > 0xf) {
//                            out.append("\\u00" + hex(ch));
//                        }
//                        else {
//                            out.append("\\u000" + hex(ch));
//                        }
//                        break;
//                }
//            }
//            else {
//                switch (ch) {
//                    case '\'':
//                        out.append('\\');
//                        out.append('\'');
//                        break;
//                    case '"':
//                        out.append("\\\"");
//                        break;
//                    case '\\':
//                        out.append('\\');
//                        out.append('\\');
//                        break;
//                    case '/':
//                        out.append("\\/");
//                        break;
//                    default:
//                        out.append(ch);
//                        break;
//                }
//            }
//        }
//
//        return out.toString();
//    }
//
//    private static String hex(char ch) {
//        return Integer.toHexString(ch).toUpperCase();
//    }
//    
//    public static String crcVerify(List<Patch> patchList) {
//    	for (Patch patch : patchList) {
//    		try {
//    			File srcFile = new File(patch.getSavePath());
//    			//计算补丁包下文件的CRC
//    			Map<String,String> sourceMap = new HashMap<String, String>();
//    			calculateFileCRC(srcFile.getAbsolutePath(),srcFile,sourceMap);
//    			//校验补丁包的CRC文件
//    			Map<String,String> targetList = parseCRCFile(srcFile.getParent() + File.separator + "check.crc");
//    			if(!compareMap(sourceMap,targetList)) {
//    				LOG.info("更新包"+patch.getPatchName() + "校验不通过，停止安装！");
//    				return "更新包检测不通过，可能更新包下载异常或更新包被篡改，更新包的安装已终止！";
//    			}
//    			//校验将要覆盖的运行环境的CRC文件
//    	        if(!checkRuntimeCRC(sourceMap)) {
//    	        	LOG.info("产品校验不通过，停止安装！");
//    				return "产品检测不通过，可能通过其他方式修改过产品程序，更新包的安装已终止！";
//    	        }
//			} catch (IOException e) {
//				LOG.error("", e);
//			}
//    	}
//    	return "";
//    }
//    
//    /**
//     * A8是否有CRC文件，并且不为空
//     * @return
//     */
//    public static boolean checkA8CRC() {
//    	try {
//			String crcFilePath = PatchUtil.getA8CrcFilePath();
//			File systemCRCFile = new File(crcFilePath);
//			if (systemCRCFile.exists()) {
//				String testKey = com.seeyon.sfu.util.file.FileUtils.getStringFormFile(crcFilePath);
//				if(Strings.isBlank(testKey)) {
//					return false;
//				}
//			} else {
//				return false;
//			}
//			return true;
//		} catch (IOException e) {
//			LOG.error("", e);
//		}
//    	return false;
//    }
//    
//    /**
//     * 是否有CRC文件，并且不为空
//     * @param productEdition 产品线 v5，m3，search，officetrances
//     * @return
//     */
//    public static boolean checkProductCRC(String productEdition) {
//    	try {
//    		String crcFilePath = getCrcFilePath(productEdition.toLowerCase());
//			File systemCRCFile = new File(crcFilePath);
//			if (systemCRCFile.exists()) {
//				String testKey = com.seeyon.sfu.util.file.FileUtils.getStringFormFile(crcFilePath);
//				if(Strings.isBlank(testKey)) {
//					return false;
//				}
//			} else {
//				return false;
//			}
//			return true;
//		} catch (IOException e) {
//			LOG.error("", e);
//		}
//    	return false;
//    }
//    private static boolean isOa(String productEdition) {
//    	return productEdition.contains("v5") || productEdition.contains("a8") || productEdition.contains("a6") || productEdition.contains("g6");
//    }
//	private static String getCrcFilePath(String productEdition) {
//		String crcFilePath = "";
//		productEdition = productEdition.toLowerCase();
//		if(isOa(productEdition)) {
//			crcFilePath = ServerInfoFactory.getOAPath() + File.separator + "ApacheJetspeed" + File.separator + "check.crc";
//		} else if("search".equals(productEdition)) {
//			crcFilePath = ServerInfoFactory.getSearchServiceRegInfo().getPath() + File.separator + "Search" + File.separator + "check.crc";
//		} else if("m3".equals(productEdition)) {
//			crcFilePath = ServerInfoFactory.getM3ServiceRegInfo().getPath() + File.separator + "M3" + File.separator + "check.crc";
//		} else if("officetrans".equals(productEdition)) {
//			crcFilePath = ServerInfoFactory.getOfficeTransServiceRegInfo().getPath() + File.separator + "OfficeTrans" + File.separator + "check.crc";
//		}
//		return crcFilePath;
//	}
//
//    public static void regenerateCrcFile(String productEdition) {
//    	String productPath = getProductPath(productEdition);
//    	String productSubPath = getProductSubPath(productEdition);
//    	Map<String,String> crcMap = new HashMap<String, String>();
//		calculateFileCRC(productPath,new File(productSubPath),crcMap);
//		//更新后的CRC值，写回文件
//		String crcFilePath = getCrcFilePath(productEdition);
//		writeCRC2File(crcMap,crcFilePath);
//    }
//    /**
//     * 读取产品的代码根目录
//     * @param productEdition 产品线
//     * @return
//     */
//	private static String getProductPath(String productEdition) {
//		String productSubPath = "";
//		productEdition = productEdition.toLowerCase();
//		if(isOa(productEdition)) {
//			productSubPath = ServerInfoFactory.getOAPath();
//		} else if("search".equals(productEdition)) {
//			productSubPath = ServerInfoFactory.getSearchServiceRegInfo().getPath();
//		} else if("m3".equals(productEdition)) {
//			productSubPath = ServerInfoFactory.getM3ServiceRegInfo().getPath();
//		} else if("officetrances".equals(productEdition)) {
//			productSubPath = ServerInfoFactory.getOfficeTransServiceRegInfo().getPath();
//		}
//		return productSubPath;
//	}
//	/**
//	 * 获取产品的安装路径，通过注册表读取
//	 * @param productEdition 产品线
//	 * @return
//	 */
//	private static String getProductSubPath(String productEdition) {
//		String productPath = "";
//		productEdition = productEdition.toLowerCase();
//		if(isOa(productEdition)) {
//			productPath = ServerInfoFactory.getOAPath() + File.separator + "ApacheJetspeed";
//		} else if("search".equals(productEdition)) {
//			productPath = ServerInfoFactory.getSearchServiceRegInfo().getPath() + File.separator + "Search";
//		} else if("m3".equals(productEdition)) {
//			productPath = ServerInfoFactory.getM3ServiceRegInfo().getPath() + File.separator + "tomcat";
//		} else if("officetrances".equals(productEdition)) {
//			productPath = ServerInfoFactory.getOfficeTransServiceRegInfo().getPath() + File.separator + "OfficeTrans";
//		}
//		return productPath;
//	}
//
//	private static boolean checkRuntimeCRC(Map<String, String> sourceMap) throws FileNotFoundException, IOException {
//		// v5安装路径
//        String v5Path = ServerInfoFactory.getOAPath();
//        Map<String, String> crcMap = getA8CrcMap(v5Path);
//        for(String key : sourceMap.keySet()) {
//        	File file = new File(v5Path + File.separator + Base64Util.decodeStr(key));
//        	if(file.exists()){
//        		String oldCRCValue = crcMap.get(key);
//        		String crcValue = File2CRCUtil.getCRC32(file.getAbsolutePath());
//        		if(Strings.isNotBlank(oldCRCValue) && !crcValue.equals(oldCRCValue)) {
//                    LOG.info(file.getAbsolutePath()+"文件校验未通过！oldCRCValue： "+oldCRCValue+"  crcValue： "+crcValue);
//        			return false;
//        		}
//        	}
//        }
//		return true;
//	}
//
//	private static Map<String, String> getA8CrcMap(String v5Path) throws FileNotFoundException, IOException {
//		String crcFilePath = getA8CrcFilePath();
//        Map<String,String> crcMap = parseCRCFile(crcFilePath);
//		return crcMap;
//	}
//
//	private static void calculateFileCRC(String sourcePath, File srcFile, Map<String, String> sourceMap) {
//		for(File file : srcFile.listFiles()){
//			//若是目录，则递归打印该目录下的文件
//			if(file.isDirectory() && filter(file)) {
//				calculateFileCRC(sourcePath,file,sourceMap);
//			}
//			if(file.isFile()) {
//				String absolutePath = file.getAbsolutePath();
//				String path = absolutePath.replace(sourcePath, "");
//				path = path.substring(1, path.length());
//				path = path.replace("\\", "/");
//				sourceMap.put(Base64Util.encodeStr(path), File2CRCUtil.getCRC32(absolutePath));
//			}
//		}
//	}
//
//	private static boolean compareMap(Map<String, String> sourceMap, Map<String, String> targetMap) {
//		if(sourceMap.size() != targetMap.size()) {
//			return false;
//		}
//		for (String key : sourceMap.keySet()) {
//			if(!sourceMap.get(key).equals(targetMap.get(key))) {
//				LOG.error(Base64Util.decodeStr(key));
//				return false;
//			}
//		}
//		return true;
//	}
//
//	private static Map<String, String> parseCRCFile(String targetFile) throws FileNotFoundException, IOException {
//		Map<String,String> map = new HashMap<String, String>();
//		File crcFile = new File(targetFile);
//		if (crcFile.exists()) {
//			BufferedReader reader = null;
//			FileReader f = null;
//			try {
//				f = new FileReader(crcFile);
//				reader = new BufferedReader(f);
//				String line = null;
//				while((line = reader.readLine()) != null) {
//                    String[] crcArray = line.split(":");
//                    if(crcArray != null && crcArray.length > 1) {
//                    	map.put(crcArray[0], crcArray[1]);
//                    }
//                }
//			} catch (Throwable e) {
//				LOG.error(e);
//			} finally {
//				if(null != f) {
//					f.close();
//				}
//				if (null!=reader){
//					reader.close();
//				}
//			}
//		}
//		return map;
//	}
//	
//	/**
//	 * 过滤掉不要计算CRC的目录和文件
//	 * @param file
//	 * @return
//	 */
//    private static boolean filter(File file) {
//    	String absolutePath = file.getAbsolutePath().toLowerCase();
//    	String fileName = file.getName().toLowerCase();
//    	if(fileName.equals("autoinstall")) {
//    		if(absolutePath.endsWith("seeyon" + File.separator + "autoinstall")) {
//    			return false;
//    		}
//    	}
//    	if(fileName.equals("help")) {
//    		if(absolutePath.endsWith("seeyon" + File.separator + "help")) {
//    			return false;
//    		}
//    	}
//    	if(fileName.equals("logs_sy") ) {
//    		if(absolutePath.endsWith("apachejetspeed" + File.separator + "logs_sy")) {
//    			return false;
//    		}
//    	}
//    	if(fileName.equals("logs")) {
//    		//新版本的OA日志路径
//    		if(absolutePath.endsWith("apachejetspeed" + File.separator + "logs")) {
//    			return false;
//    		}
//    		//老版本的OA日志路径
//    		if(absolutePath.endsWith("seeyon" + File.separator + "logs")) {
//    			return false;
//    		}
//    		//M3的日志路径
//    		if(absolutePath.endsWith("mobile_portal" + File.separator + "logs")) {
//    			return false;
//    		}
//    		//全文检索日志路径
//    		if(absolutePath.endsWith("search" + File.separator + "logs")) {
//    			return false;
//    		}
//    	}
//    	if(fileName.equals("work")) {
//    		if(absolutePath.endsWith("apachejetspeed" + File.separator + "work")) {
//    			return false;
//    		}
//    	}
//    	if(fileName.equals("temp")) {
//    		if(absolutePath.endsWith("apachejetspeed" + File.separator + "temp")) {
//    			return false;
//    		}
//    	}
//    	if(fileName.equals("user-data")) {
//    		if(absolutePath.endsWith("seeyon" + File.separator + "user-data")) {
//    			return false;
//    		}
//    	}
//		return true;
//	}
//
//    /**
//     * 用已经被覆盖的文件计算新的CRC值，覆盖保存到产品的CRC文件中
//     * @param patch
//     * @throws FileNotFoundException
//     * @throws IOException
//     */
//    public static void updateA8CRC(Patch patch) throws FileNotFoundException, IOException {
//		String v5Path = ServerInfoFactory.getOAPath();
//		Map<String, String> crcMap = getA8CrcMap(v5Path);
//		Map<String,File> patchFileMap = new HashMap<String,File>();
//        PatchFileUtil.traverseFolder(PatchFileUtil.getSubFolderPath(patch.getSavePath()), patchFileMap);
//		for(String pathURI : patchFileMap.keySet()) {
//			pathURI = pathURI.replace(patch.getSavePath() + File.separator, "");
//			pathURI = pathURI.replace("\\", "/");
//			String key = Base64Util.encodeStr(pathURI);
//			String value = File2CRCUtil.getCRC32(v5Path + File.separator + pathURI);
//			crcMap.put(key, value);
//		}
//		//更新后的CRC值，写回文件
//		writeCRC2File(crcMap,getCrcFilePath(patch.getProductEdition()));
//	}
//
//	private static synchronized void writeCRC2File(Map<String, String> crcMap,String crcPath) {
//		FileWriter fw = null;
//        BufferedWriter writer = null;
//		try {
//			File f = new File(crcPath);
//			fw = new FileWriter(f);
//	        writer = new BufferedWriter(fw);
//			for(String crcKey : crcMap.keySet()) {
//				writer.write(crcKey + ":" + crcMap.get(crcKey));
//				writer.newLine();
//			}
//		} catch (Throwable e) {
//			LOG.error("", e);
//		} finally {
//			try {
//				writer.flush();
//				fw.flush();
//				if(writer != null) {
//					writer.close();
//				}
//				if(fw != null) {
//					fw.close();
//				}
//			} catch (IOException e) {
//				LOG.error("", e);
//			}
//		}
//	}
//
//	public static boolean checkA8CRCUninstall(String uri) throws FileNotFoundException, IOException {
//		File file = new File(uri);
//		Map<String, String> crcMap = getA8CrcMap(ServerInfoFactory.getOAPath());
//		return verifyFileCRC(uri,file, crcMap);
//	}
//	
//	private static boolean verifyFileCRC(String uri, File srcFile, Map<String, String> crcMap) {
//		boolean flag = true;
//		for(File file : srcFile.listFiles()){
//			//若是目录，则递归打印该目录下的文件
//			if (file.isDirectory() && filter(file)) {
//				if(!verifyFileCRC(uri,file,crcMap)) {
//					return false;
//				}
//			}
//			if (file.isFile()) {
//				String absolutePath = file.getAbsolutePath();
//				if(excludeFile(absolutePath)) {
//					continue;
//				}
//				String path = absolutePath.replace(uri, "");
//				path = path.substring(1, path.length());
//				path = path.replace("\\", "/");
//				String crcKey = Base64Util.encodeStr(path);
//
//				String patchFileCRC = File2CRCUtil.getCRC32(ServerInfoFactory.getOAPath()+File.separator+path);
//				String installFileCRC = crcMap.get(crcKey);
//				
//				if(!patchFileCRC.equals(installFileCRC)) {
//					LOG.info(ServerInfoFactory.getOAPath()+File.separator+path+" 文件CRC校验未通过！"  );
//					LOG.info("patchFileCRC:" + patchFileCRC+"    installFileCRC"+installFileCRC );
//					flag = false;
//				}
//			}
//		}
//		return flag;
//	}
//	
//	/**
//	 * 获取BuildId
//	 * @return
//	 */
//	public static String getBuildId() {
//        try {
//        	String dateStr = DateUtil.format(ServerInfoFactory.getOASystemInfo().getDate(), "yyMMdd");
//            String appVersion = ServerInfoFactory.getOASystemInfo().getAppsversion();
//            
//            String ctpVerson = ServerInfoFactory.getOASystemInfo().getCtpversion();
//            
//            String buildId = "B" + dateStr + "." + appVersion + ".CTP" + ctpVerson;
//            
//            // 判断获取的buildID是否为空
//            if (StringUtils.isEmpty(appVersion) || StringUtils.isEmpty(ctpVerson)) {
//            	return null;
//            }
//            return buildId;
//        } catch (Exception e) {
//            LOG.error("", e);
//            return null;
//        }
//	}
//
//}
