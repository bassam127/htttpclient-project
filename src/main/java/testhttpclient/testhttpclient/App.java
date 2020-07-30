package testhttpclient.testhttpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.GsonBuilder;

import testhttpclient.request.Requestmessage;
import util.AllAsStringSerializer;

/**
 * Hello world!
 *
 */
public class App {
	private static final String URL = "https://httpbin.org/post";


	public static void main(String[] args) throws UnsupportedEncodingException, ClientProtocolException, IOException {
		 String result = "";
		Requestmessage requestmessage = new Requestmessage();

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost(URL);

		String jsonString = convertObjectToJson(requestmessage);
		System.out.println(jsonString);

		StringEntity entity = new StringEntity(jsonString);
		post.setEntity(entity);
		post.setHeader("Accept", "application/json");
		post.setHeader("Content-type", "application/json");

		CloseableHttpResponse response = httpclient.execute(post);
		result = EntityUtils.toString(response.getEntity());

		System.out.println(result);

		httpclient.close();
	}

	/**
	 * this method to convert object to json
	 * 
	 * @param requestmessage
	 * @return
	 */
	private static String convertObjectToJson(Requestmessage requestmessage) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Requestmessage.class, new AllAsStringSerializer());
		String json = gsonBuilder.create().toJson(requestmessage);
		return json;
	}
}
