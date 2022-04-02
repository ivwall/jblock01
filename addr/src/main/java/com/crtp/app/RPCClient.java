package com.crtp.app;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class RPCClient {

	private static final String COMMAND_GET_BALANCE = "getbalance";
	private static final String COMMAND_GET_INFO = "getinfo";
	private static final String COMMAND_GET_NEW_ADDRESS = "getnewaddress";
	private static final String COMMAND_GET_BLOCK_COUNT = "getblockcount";

	private JSONObject invokeRPC(String id, String method, List<String> params) {
		DefaultHttpClient httpclient = new DefaultHttpClient();

		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("method", method);
		if (null != params) {
			JSONArray array = new JSONArray();
			array.addAll(params);
			json.put("params", params);
		}
		JSONObject responseJsonObj = null;
		try {
			//httpclient.getCredentialsProvider().setCredentials(new AuthScope("localhost", 8332),
			//		new UsernamePasswordCredentials("btc", "123"));
            httpclient.getCredentialsProvider().setCredentials(new AuthScope("10.10.89.92", 8332),
					new UsernamePasswordCredentials("bitcoin", "abc1234"));
			StringEntity myEntity = new StringEntity(json.toJSONString());
			System.out.println(json.toString());
			//HttpPost httppost = new HttpPost("http://localhost:8332");
			HttpPost httppost = new HttpPost("http://10.10.89.92:8332");
			httppost.setEntity(myEntity);

			System.out.println("executing request" + httppost.getRequestLine());
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());
			if (entity != null) {
				System.out.println("Response content length: " + entity.getContentLength());
				// System.out.println(EntityUtils.toString(entity));
			}
			JSONParser parser = new JSONParser();
			responseJsonObj = (JSONObject) parser.parse(EntityUtils.toString(entity));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
		return responseJsonObj;
	}

	public Double getBalance(String account) {
		String[] params = { account };
		JSONObject json = invokeRPC(UUID.randomUUID().toString(), COMMAND_GET_BALANCE, Arrays.asList(params));
		return (Double)json.get("result");
	}

	public String getNewAddress(String account) {
		String[] params = { account };
		JSONObject json = invokeRPC(UUID.randomUUID().toString(), COMMAND_GET_NEW_ADDRESS, Arrays.asList(params));
		return (String)json.get("result");
	}

	public JSONObject getInfo() {
		JSONObject json = invokeRPC(UUID.randomUUID().toString(), COMMAND_GET_INFO, null);
		return (JSONObject)json.get("result");
	}

    public JSONObject getBlockCount() {
		JSONObject json = invokeRPC(UUID.randomUUID().toString(), COMMAND_GET_BLOCK_COUNT, null);
        Class cls = json.getClass();
        System.out.println("json class "+cls.getName());
        System.out.println(json);
		//return (JSONObject)json.get("result");
		return null;
	}

}