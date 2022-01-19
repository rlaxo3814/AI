package mc.sn.lesson1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Date;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ClovaTextToSpeech {
	//text 파일(lesson1.txt)을 읽어서 mp3파일(lession1.mp3)로 생성한다.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String result = null;
		String clientId = "";
        String clientSecret = ""; 
        String filePathName = "lesson1.txt";
        try {
        	File file = new File(filePathName);
        	FileReader fr = new FileReader(file,Charset.forName("utf-8"));
        	BufferedReader br1 = new BufferedReader(fr);
        	StringBuffer sb = new StringBuffer();
        	String temp = null;
        	while((temp=br1.readLine())!=null) {
        		sb.append(temp);
        	}
        	br1.close();
        	fr.close();
        	//System.out.println("service " + sb.toString());
            String text = URLEncoder.encode(sb.toString(), "UTF-8"); 
            //System.out.println(text);
            String apiURL = "https://naveropenapi.apigw.ntruss.com/tts-premium/v1/tts";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            // post request
            String postParams = "speaker="+"nara"+"&volume=0&speed=0&pitch=0&format=mp3&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br= null;
            if(responseCode==200) { 
                InputStream is = con.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
               
                //String tempname = Long.valueOf(new Date().getTime()).toString();
                result = "lesson1.mp3";
                File f = new File(result);
                f.createNewFile();
                OutputStream outputStream = new FileOutputStream(f);
                while ((read =is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                outputStream.flush();
                outputStream.close();
                is.close();
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                //System.out.println(response.toString());
                result = response.toString();
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }

	}

}
