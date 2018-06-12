package tool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.http.HttpClientConnection;

public class jsonutil {

	public jsonutil() {
		// TODO Auto-generated constructor stub
	}
public static String getjsoncontent(String urlpath)
{
	try {
		URL url=new URL(urlpath);
		HttpURLConnection connection=(HttpURLConnection)url.openConnection();
		connection.setConnectTimeout(3000);
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
		if(200==connection.getResponseCode())
		{
			return changerinpustring(connection.getInputStream());
			
		}
		
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
return "";
}
private static String changerinpustring(InputStream inputStream) {
	// TODO Auto-generated method stub
	String jsonstring="";
	ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();
	int len=0;
	byte[] data=new byte[1024];
	try {
		while((len=inputStream.read(data))!=-1)
		{
			arrayOutputStream.write(data, 0, len);
			
		}
		jsonstring=new String(arrayOutputStream.toByteArray());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return jsonstring;
}
}
