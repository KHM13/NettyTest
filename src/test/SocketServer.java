package test;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class SocketServer {
	public static void main(String[] args) {
        
		long start = System.nanoTime();
		
		int test_num = 1000;
		
		for (int i = 0; i < test_num; i++) {
			sss();
		}
		
		long end = System.nanoTime();
		
		long duration = TimeUnit.NANOSECONDS.toSeconds(end - start);
        int tps = (int) (test_num / duration);

        System.out.println("TPS: " + tps);
		
		
    }
	
	public static void sss() {
		Socket socket = null;

        try {
            socket = new Socket();
             //System.out.println("[연결 요청]");
            socket.connect(new InetSocketAddress("localhost", 8000));
            //System.out.println("[연결 성공]");
            byte[] bytes = null;
            //String message = null;

            OutputStream os = socket.getOutputStream();
            
            String message = "{" +
                    "\"EBNK_MED_DSC\":\"091\"," +
                    "\"RMS_SVC_C\":\"SVC_03\"," +
                    "\"Amount\":1500000," +
                    "\"LS_TRDT\":\"2023-11-30\"," +
                    "\"sm_mobileAPSsid\":\"SKT\"," +
                    "\"cus_birth\":1948," +
                    "\"eventType\":3," +
                    "\"country\":\"CN\"," +
                    "\"key\":\"구형모\"," +
                    "\"securityMediaType\":1" +
                    "}";
            System.out.println(message);
            /**
            String message = "{" +
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
					  "\"thall\":1}";
            */
            bytes = message.getBytes("UTF-8");
            os.write(bytes);
            os.flush();
            //System.out.println("[데이터 보내기 성공]");

            InputStream is = socket.getInputStream();
            bytes = new byte[100];
            int readByteCount = is.read(bytes);
            message = new String(bytes, 0, readByteCount, "UTF-8");
            //System.out.println("[데이터 받기 성공] " + message);

            os.close(); is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
}