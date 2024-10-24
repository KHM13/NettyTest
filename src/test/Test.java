package test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) throws Exception{
		
		System.out.println("start");
		
		long start = System.nanoTime();
		
		int test_num = 1;
		
		for (int i = 0; i < test_num; i++) {
			
			URL url = new URL("http://192.168.0.121:8000/changeModel/LogisticRegression");
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				
			connection.setRequestMethod("GET");
				
			int responseCode = connection.getResponseCode();
		    if (responseCode == 200) {
		        InputStream inputStream = connection.getInputStream();
		        byte[] bytes = new byte[inputStream.available()];
		        inputStream.read(bytes);
		        String response = new String(bytes);
		        System.out.println(i);
		        System.out.println(response);
		    }
		} 
		
		long end = System.nanoTime();
		System.out.println("종료");
		long duration = TimeUnit.NANOSECONDS.toSeconds(end - start);
        int tps = (int) (test_num / duration);

        System.out.println("TPS: " + tps);
  	}
}
  

/*
 * URL url = new URL("http://192.168.0.121:8000/detection/detectionReal?data=[{" +
		  "\"age\":23," +
		  "\"sex\":1," +
		  "\"cp\":3," +
		  "\"trtbps\":145," +
		  "\"chol\":4," +
		  "\"fbs\":5," +
		  "\"restecg\":6," +
		  "\"thalachh\":3," +
		  "\"exng\":4," +
		  "\"oldpeak\":0.1," +
		  "\"slp\":2," +
		  "\"caa\":4," +
		  "\"thall\":1}" +
		"]");
 * */
/*URL url = new URL("http://127.0.0.1:8000/detect/{" +
		  "\"age\":23," +
		  "\"sex\":1," +
		  "\"cp\":3," +
		  "\"trtbps\":145," +
		  "\"chol\":4," +
		  "\"fbs\":5," +
		  "\"restecg\":6," +
		  "\"thalachh\":3," +
		  "\"exng\":4," +
		  "\"oldpeak\":0.1," +
		  "\"slp\":2," +
		  "\"caa\":4," +
		  "\"thall\":1}");

 * */