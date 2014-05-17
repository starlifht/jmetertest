package star.test.openproxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import org.junit.Before;
import org.junit.Test;



public class ApiTest {

	@Test
	public void testAuth() {
		String client_id = "d142ec65623a4a0081c9560cb522f1e6";
		String redirect_uri = "http://www.test.com";
		String response_type = "code";
		String url = "https://api.sohu.com/oauth2/authorize?client_id=" + client_id + "&redirect_uri=" + redirect_uri
				+ "&response_type=" + response_type;

		try {
			String result = HTTPUtils.doGet(url);
			System.out.println(url);
			System.out.println(result);

		} catch (SocketTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void test() {
		try {
			URLEncoder.encode("", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	 String domain = "http://api.sce.sohu.com";

	String signatureMethod = "HmacSHA1";
	String charset = "UTF-8";
	String appid = "129062244";
	String accessKey = "1qaz";
	String secret = "2wsx";
	Map<String, String[]> data = new HashMap<String, String[]>();

	@Before
	public void setUp() {
		data.put(SceOpenApiConstant.ACCESS_KEY, new String[] { accessKey });
		long timestamp = System.currentTimeMillis();
		data.put(SceOpenApiConstant.TIMESTAMP, new String[] { String.valueOf(timestamp) });
		data.put(SceOpenApiConstant.SIGNATURE_METHOD, new String[] { signatureMethod });
		data.put(SceOpenApiConstant.SIGNATURE_VERSION, new String[] { "1.0" });
		data.put(SceOpenApiConstant.VERSION, new String[] { "1.0" });
	}

	@Test
	public void testGetDomain() {

		String uri = "/cs/apps/" + appid + "/domains";
		try {
			String sigature = SignatureUtils.signatureForSce(data, secret, "GET", uri, signatureMethod);
			data.put(SceOpenApiConstant.SIGNATURE, new String[] { sigature });

			String response = HTTPUtils.doGet(domain + uri, data, charset, null);
			System.out.println("=========================");
			System.out.println(response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testAddDomain() {

		String uri = "/cs/apps/" + appid + "/domains";

		data.put("domain", new String[] { "hly1.csapps.sohuno.com" });
		try {

			String sigature = SignatureUtils.signatureForSce(data, secret, "POST", uri, signatureMethod);
			data.put(SceOpenApiConstant.SIGNATURE, new String[] { sigature });

			System.out.println(data);
			String response = HTTPUtils.doPost(domain + uri, data, charset, null);
			System.out.println("=========================");
			System.out.println(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteDomain() {
		String uri = "/cs/apps/" + appid + "/domains";
		data.put("domain", new String[] { "sceopenapi2.csapps.sohuno.com" });
		try {

			String sigature = SignatureUtils.signatureForSce(data, secret, "DELETE", uri, signatureMethod);
			data.put(SceOpenApiConstant.SIGNATURE, new String[] { sigature });

			System.out.println(data);
			String response = HTTPUtils.doDelete(domain + uri, data, charset, null);
			System.out.println("=========================");
			System.out.println(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCleanDomain() {
		String uri = "/cs/apps/" + appid + "/domains/clean";
		data.put("domain", new String[] { "hly*.csapps.sohuno.com" });
		try {

			String sigature = SignatureUtils.signatureForSce(data, secret, "DELETE", uri, signatureMethod);
			data.put(SceOpenApiConstant.SIGNATURE, new String[] { sigature });

			System.out.println(data);
			String response = HTTPUtils.doDelete(domain + uri, data, charset, null);
			System.out.println("=========================");
			System.out.println(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void getAppList() {
		String uri = "/cs/apps";
		//data.put("domain", new String[] { "hly*.csapps.sohuno.com" });
		try {

			String sigature = SignatureUtils.signatureForSce(data, secret, "GET", uri, signatureMethod);
			data.put(SceOpenApiConstant.SIGNATURE, new String[] { sigature });

			System.out.println(data);
			String response = HTTPUtils.doGet(domain + uri, data, charset, null);
			System.out.println("=========================");
			System.out.println(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetConfig() {
		String uri = "/cs/apps/" + appid + "/config";
		try {

			String sigature = SignatureUtils.signatureForSce(data, secret, "GET", uri, signatureMethod);
			data.put(SceOpenApiConstant.SIGNATURE, new String[] { sigature });

			System.out.println(data);
			String response = HTTPUtils.doGet(domain + uri, data, charset, null);
			System.out.println("=========================");
			System.out.println(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPutConfig() {

		String uri = "/cs/apps/" + appid + "/config";

		data.put("is_schedule", new String[] { "true" });
		data.put("max_ins", new String[] { "3" });
		data.put("is_session", new String[] { "true" });
		data.put("container_type", new String[] { "text/xml; charset=UTF-8" });
		data.put("requests", new String[] { "100000" });
		try {

			String sigature = SignatureUtils.signatureForSce(data, secret, "PUT", uri, signatureMethod);
			data.put(SceOpenApiConstant.SIGNATURE, new String[] { sigature });

			System.out.println(data);
			String response = HTTPUtils.doPut(domain + uri, data, charset, null);
			System.out.println("=========================");
			System.out.println(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// https://api.sohu.com/oauth2/authorize?client_id=d142ec65623a4a0081c9560cb522f1e6&redirect_uri=http://www.test.com&response_type=code
	// http://www.test.com/?code=4d0fc88f9a32cfaa222d4c9f89911587

	// https://api.sohu.com/oauth2/token?client_id=d142ec65623a4a0081c9560cb522f1e6&code=4d0fc88f9a32cfaa222d4c9f89911587&client_secret=9d6662a7f8d8ca600d456fbfdfdf721d&redirect_uri=http://www.test.com&grant_type=authorization_code
	// {"access_token":"1.1f6aa2cc3886470s-465672bc1f2b497b61747ad17bde94d3.2592000000.1397376780005|1398","expires_in":"2592000","refresh_token":"2.1f6aa2cc3886470s-f60a30b365df5ad8e905f5702d8649e1|1398","uuid":"1f6aa2cc3886470s","open_id":"1f6aa2cc3886470s"}

}
