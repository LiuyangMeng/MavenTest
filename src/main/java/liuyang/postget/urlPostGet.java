package liuyang.postget;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class urlPostGet {
	protected static void testPostInStream() {
		try {
			//URL url = new URL("http://localhost/upload/upLoadfile.jsp?username=12345&version=3.12&plat=huawei_p7&packageid=3000752&filetype=xlsx");
			URL url = new URL("http://localhost/MongoDB/curd/showUsers.do");
			// 发送POST请求必须设置如下两行
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "text/html");
			conn.setRequestProperty("Cache-Control", "no-cache");
			conn.setRequestProperty("Charsert", "UTF-8");
			
			//头信息
			conn.setRequestProperty("version","v3");
			conn.setRequestProperty("pg","200188003002640");
			conn.setRequestProperty("mac","0210088142A8");
			conn.setRequestProperty("conf","0");
			conn.setRequestProperty("root","2014070300");
			
			
			conn.connect();
			conn.setConnectTimeout(10000);
			
			/*	OutputStream out = conn.getOutputStream();
			//DataOutputStream out = new DataOutputStream(new BufferedOutputStream(conn.getOutputStream()));
			 
			//测试，使用本地磁盘文件测试上传到服务器端
			File file = new File("C:/Users/DLHT/Desktop/ceshi.zip");
			FileInputStream in = new FileInputStream(file);
			int bytes = 0;
			byte[] buffer = new byte[100];
			while ((bytes = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytes);
			}
			in.close();
			out.flush();
			out.close();
*/
			
			String sCurrentLine = "";
			String sTotalString = "";
			InputStream l_urlStream = conn.getInputStream();
			// 传说中的三层包装阿！
			BufferedReader l_reader = new BufferedReader(new InputStreamReader(
					l_urlStream));
			while ((sCurrentLine = l_reader.readLine()) != null) {
				sTotalString += sCurrentLine;

			}
			System.out.println(sTotalString);
			conn.disconnect();
		} catch (Exception e) {
			System.out.println("发送文件出现异常！" + e);
			e.printStackTrace();
		}

	}
	
	

	public static void main(String[] args) {
		testPostInStream();
		
		
		thread t=new thread();
		
		Thread t1=new Thread(t,"t1");
		Thread t2=new Thread(t,"t2");
		Thread t3=new Thread(t,"t3");
		t1.start();
		t2.start();
		t3.start();
		
		
		
		
	}
}

class thread implements Runnable{
	
	public void run() {
		for (int i = 0; i < 50; i++) {
			System.out.println(Thread.currentThread().getName()+"    ");
			urlPostGet.testPostInStream();
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
} 
