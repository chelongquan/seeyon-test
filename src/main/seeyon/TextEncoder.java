///**
// *
// */
//package main.seeyon;
//
//import java.util.HashMap;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.seeyon.ctp.common.security.SM4Util;
//
///**
// *
// * @author <a href="mailto:tanmf@seeyon.com">Tanmf</a>
// * @version 1.0 2011-2-24
// */
//public class TextEncoder {
//	public static final String PWD_Prefix_10 = "/1.0/";
//	public static final String PWD_Prefix_24 = "/2.4/";
//	private static Log LOG = LogFactory.getLog(TextEncoder.class);
//	private static final byte KEY = 'S';
//	private static java.util.Map<String,StringEncoder> encoders = new HashMap<>();
//	static {
//		encoders.put(PWD_Prefix_10, new Base64Encoder());
//		encoders.put(PWD_Prefix_24, new SM4Encoder());
//		encoders.put("", new DefaultEncoder());
//	}
//	static class Base64Encoder implements StringEncoder{
//		@Override
//		public String encode(String text) {
//			return PWD_Prefix_10 + LightWeightEncoder.encodeString(text);
//		}
//
//		@Override
//		public String decode(String text) {
//			return LightWeightEncoder.decodeString(text.substring(PWD_Prefix_10.length()));
//		}
//	}
//	static class SM4Encoder implements StringEncoder{
//		private static final String SM4_KEY = "E6C63180C2806DD1F47B859DE501C15F";
//		@Override
//		public String encode(String text) {
//			try {
//				return PWD_Prefix_24 + SM4Util.encrypt(text, SM4_KEY);
//			} catch (Exception e) {
//				LOG.error(e.getLocalizedMessage(),e);
//				return null;
//			}
//		}
//
//		@Override
//		public String decode(String text) {
//			try {
//				return SM4Util.decrypt(text.substring(PWD_Prefix_24.length()),SM4_KEY);
//			} catch (Exception e) {
//				LOG.error(e.getLocalizedMessage(),e);
//				return null;
//			}
//		}
//	}
//	static class DefaultEncoder implements StringEncoder{
//
//		@Override
//		public String encode(String text) {
//			return text;
//		}
//
//		@Override
//		public String decode(String text) {
//			return text;
//		}
//	}
//	private static String getVersion(String text) {
//		if(text==null) {
//			return "";
//		}
//		if(text.startsWith(PWD_Prefix_10)) {
//			return PWD_Prefix_10;
//		}
//		if(text.startsWith(PWD_Prefix_24)) {
//			return PWD_Prefix_24;
//		}
//		return "";
//	}
//public static void main(String[] args) {
//		String  a = TextEncoder.encode("aaaa1234");
//		System.out.println(TextEncoder.decode(a ));
//}
//	/**
//	 * 对流加密，其解密一定要用 {@link #decodeBytes(byte[])}
//	 * @param bytes
//	 * @return
//	 */
//	public static byte[] encodeBytes(byte[] bytes) {
//		if(bytes == null){
//			return null;
//		}
//
//		byte[] result = new byte[bytes.length];
//		for (int i = 0; i < bytes.length; i++) {
//			result[i] = (byte) (bytes[i] ^ KEY);
//		}
//
//		return result;
//	}
//
//	/**
//	 * 对流解密，其加密一定要用{@link #encodeBytes(byte[])}，否则不能解密
//	 * @param bytes
//	 * @return
//	 */
//	public static byte[] decodeBytes(byte[] bytes){
//		if(bytes == null){
//			return null;
//		}
//
//		byte[] result = new byte[bytes.length];
//		for (int i = 0; i < bytes.length; i++) {
//			result[i] = (byte) (bytes[i] ^ KEY);
//		}
//
//		return result;
//	}
//
//	/**
//	 * 加密，输出值类似：/1.0/kja3a3wklasd<br>
//	 * 解密使用 {@link #decode(String)}
//	 * @param clearPwd
//	 * @return
//	 */
//	public static String encode(String clearPwd){
//		return encode(clearPwd,PWD_Prefix_24);
//	}
//
//	public static String encode(String clearPwd,String version){
//		if(clearPwd==null) {
//			return null;
//		}
//		return encoders.get(version).encode(clearPwd);
//	}
//	/**
//	 * 解密<br>
//	 * 其加密必须使用 {@link #encode(String)}，否则不能解密
//	 * @param text
//	 * @return
//	 */
//	public static String decode(String text){
//		if(text==null) {
//			return null;
//		}
//		return encoders.get(getVersion(text)).decode(text);
//	}
//}
