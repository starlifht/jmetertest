package star.test.openproxy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.sohu.open.SceOpenApiConstant;

import sun.misc.BASE64Encoder;

/**
 * @author kaizhao
 * 
 */
public class SignatureUtils {

//	private final static Logger logger = LoggerFactory.getLogger(SignatureUtils.class);

	public final static String ALGORITHM_TYPE_HMAC_SHA1 = "HmacSHA1";

	public final static String CHARSET_UTF8 = "UTF-8";

	/**
	 * open api 的签名算法
	 * 
	 * @param parameterMap
	 * @param secret
	 * @return
	 */
	public static String signatureMultiValue(Map<String, String[]> parameterMap, String secret) {
		List<String> list = new ArrayList<String>();

		// (1)connecting all parameters
		for (Entry<String, String[]> entry : parameterMap.entrySet()) {
			StringBuilder values = new StringBuilder();
			for (String v : entry.getValue()) {
				values.append(v);
				values.append(",");
			}
			values.replace(values.lastIndexOf(","), values.length(), "");
			list.add(entry.getKey() + "=" + values);
		}

		// (2)sort all strings
		Collections.sort(list);

		StringBuilder buf = new StringBuilder();
		for (String s : list) {
			buf.append(s);
		}
		// (3)append secret key
		buf.append(secret);

		// (4)md5 your code with utf8 encoding
		return getMD5MessageDigest(buf.toString());
	}

	/**
	 * open api 的签名算法
	 * 
	 * @param parameterMap
	 * @param secret
	 * @return
	 */
	public static String signature(Map<String, String> parameterMap, String secret) {
		List<String> list = new ArrayList<String>();

		// (1)connecting all parameters
		for (Entry<String, String> entry : parameterMap.entrySet()) {
			list.add(entry.getKey() + "=" + entry.getValue());
		}

		// (2)sort all strings
		Collections.sort(list);

		StringBuilder buf = new StringBuilder();
		for (String s : list) {
			buf.append(s);
		}
		// (3)append secret key
		buf.append(secret);

		// (4)md5 your code with utf8 encoding
		return getMD5MessageDigest(buf.toString());
	}

	/**
	 * 按照UTF-8生成md5摘要
	 * 
	 * @param sourceStr
	 * @return
	 */
	public static String getMD5MessageDigest(String sourceStr) {

		String result = null;
		try {
			result = DigestUtils.md5Hex(sourceStr.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
		//	logger.error("", e);
		}

		return result;
	}

	/**
	 * 按照指定的字符编码集生成md5摘要
	 * 
	 * @param sourceStr
	 * @param charset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getMD5MessageDigest(String sourceStr, String charset) throws UnsupportedEncodingException {

		return DigestUtils.md5Hex(sourceStr.getBytes(charset));
	}

	/**
	 * sce 的签名算法
	 * 
	 * @param parameterMap
	 * @param secret
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String signatureForSce(Map<String, String[]> parameterMap, String secret, String method, String uri,
			String algorithm) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
		System.out.println(secret+"|"+method+"|"+uri+"|"+algorithm);
		// (1) 按参数名进行升序排列，并对key 和value做url编码
		List<String> list = new ArrayList<String>();
		for (Entry<String, String[]> entry : parameterMap.entrySet()) {
			for(String value:entry.getValue()){
				list.add(URLEncoder.encode(entry.getKey(), CHARSET_UTF8) + "=" + URLEncoder.encode(value, CHARSET_UTF8));	
			}
		}

		Collections.sort(list);
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			buf.append(list.get(i));
			if (i < list.size() - 1) {
				buf.append("&");
			}
		}

		// (2) 做url编码
		String rtn = URLEncoder.encode(buf.toString(), CHARSET_UTF8);
		System.out.println(rtn);
		// (3) 构造签名串
		rtn = method.toUpperCase() + "\n" + uri + "\n" + rtn;
		// (4) 签名
		byte[] rawHmac = hmac(rtn.getBytes(CHARSET_UTF8), secret.getBytes(CHARSET_UTF8), algorithm);
		// (5) base64
		BASE64Encoder base64Encoder = new BASE64Encoder();
		rtn= base64Encoder.encode(rawHmac);
		rtn.replace(" ", "+");
		String sigature = URLEncoder.encode(rtn,CHARSET_UTF8);
		System.out.println("sigature:>>"+sigature);
		return sigature;
//		return DigestUtils.md5Hex(rawHmac);
	}
 

	public static byte[] hmac(byte[] data, byte[] key, String algorithm) throws NoSuchAlgorithmException,
			InvalidKeyException, UnsupportedEncodingException {

		SecretKeySpec signingKey = new SecretKeySpec(key, algorithm);
		Mac mac = Mac.getInstance(algorithm);
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(data);
		return rawHmac;
	}
}
