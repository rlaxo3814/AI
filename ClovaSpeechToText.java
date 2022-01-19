package mc.sn.lesson2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ClovaSpeechToText {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// lesson1.mp3 파일을 읽어서 콘솔에 출력하는 코드를 작성하시오
		String clientId = "";             // Application Client ID";
        String clientSecret = "";     // Application Client Secret";
        String result = null;
        try {
            String imgFile = "lesson1.mp3";
            File voiceFile = new File(imgFile);

            String language = "Kor";        // ( Kor, Jpn, Eng, Chn )
            String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;
            //System.out.println(apiURL);
            URL url = new URL(apiURL);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/octet-stream");
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            conn.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(voiceFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            BufferedReader br = null;
            int responseCode = conn.getResponseCode();
            if(responseCode == 200) { 
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf8"));
            } else {  
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            String inputLine;
            
            if(br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                
                result = response.toString();
                System.out.println(result);
                JSONParser parser = new JSONParser();
                JSONObject obj = (JSONObject)parser.parse(result);
                String content = (String)obj.get("text");
                System.out.println(content);
                
            } else {
                System.out.println("error !!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
      
	}

}
