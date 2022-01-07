package mc.sn.echo.ai.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

import org.springframework.stereotype.Service;

@Service("aiService")
public class AiService {
	
	public String translate(String words)  {
		// TODO Auto-generated method stub
		
		 StringBuffer res = null;
		 String clientId = "";// Application Client ID";
	     String clientSecret = "";// Application Client Secret";
	     try {
	         String text = URLEncoder.encode(words, "UTF-8");
	         String apiURL = "https://naveropenapi.apigw.ntruss.com/nmt/v1/translation";
	         URL url = new URL(apiURL);
	         HttpURLConnection con = (HttpURLConnection)url.openConnection();
	         con.setRequestMethod("POST");
	         con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
	         con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
	         // post request
	         String postParams = "source=ko&target=en&text=" + text;
	         con.setDoOutput(true);
	         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	         wr.writeBytes(postParams);
	         wr.flush();
	         wr.close();
	         int responseCode = con.getResponseCode();
	         BufferedReader br;
	         if(responseCode==200) { 
	             br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	         } else {  
	             br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	         }
	         String inputLine;
	         res = new StringBuffer();
	         while ((inputLine = br.readLine()) != null) {
	             res.append(inputLine);
	         }
	         br.close();
	         System.out.println("service "+res.toString());
	     } catch (Exception e) {
	         System.out.println(e);
	     }
		
	
		return res.toString();
	}
	
	public String clovaSpeechToText(String filePathName, String language) {
		// TODO Auto-generated method stub
		String clientId = "";             // Application Client ID";
        String clientSecret = "";     // Application Client Secret";
        String result = null;
        try {
            String imgFile = filePathName;
            File voiceFile = new File(imgFile);

            //String language = "Kor";        //  ( Kor, Jpn, Eng, Chn )
            String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;
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
                System.out.println(response.toString());
                result = response.toString();
            } else {
                System.out.println("error !!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
      
		return result;
	}

	public String clovaTextToSpeech(String filePathName, String language) {
		// TODO Auto-generated method stub
		String result = null;
		String clientId = "";// Application Client ID";
        String clientSecret = ""; // Application Client Secret";
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
        	System.out.println(sb.toString());
            String text = URLEncoder.encode(sb.toString(), "UTF-8"); 
            String apiURL = "https://naveropenapi.apigw.ntruss.com/tts-premium/v1/tts";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            // post request
            String postParams = "speaker="+language+"&volume=0&speed=0&pitch=0&format=mp3&text=" + text;
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
               
                String tempname = Long.valueOf(new Date().getTime()).toString();
                result = "tts_"+tempname+".mp3";
                File f = new File("c:/ai/"+result);
                f.createNewFile();
                OutputStream outputStream = new FileOutputStream(f);
                while ((read =is.read(bytes)) != -1) {///-1�� ������ �� 
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
                System.out.println(response.toString());
                result = response.toString();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
		return result;
	}
	
	public String geocode(String words) {
		// TODO Auto-generated method stub
		 StringBuffer res = null;
		 String clientId = "";// Application Client ID";
	     String clientSecret = "";// Application Client Secret";
	     try {
	         String text = URLEncoder.encode(words, "UTF-8");
	         String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + text;
	         URL url = new URL(apiURL);
	         HttpURLConnection con = (HttpURLConnection)url.openConnection();
	         con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
	         con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
	         con.setDoOutput(true);

	         int responseCode = con.getResponseCode();
	         BufferedReader br;
	         if(responseCode==200) { 
	             br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	         } else {  
	             br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	         }
	         String inputLine;
	         res = new StringBuffer();
	         while ((inputLine = br.readLine()) != null) {
	             res.append(inputLine);
	         }
	         br.close();
	         System.out.println("service "+res.toString());
	     } catch (Exception e) {
	         System.out.println(e);
	     }
		
	
		return res.toString();
	}
	
	
	public String reverseGeocode(String coords) {
		// TODO Auto-generated method stub
		 StringBuffer res = null;
		 String clientId = "";// Application Client ID";
	     String clientSecret = "";// Application Client Secret";
	     try {

	         String apiURL = "https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?coords=" +coords+"&output=json";
	         URL url = new URL(apiURL);
	         HttpURLConnection con = (HttpURLConnection)url.openConnection();
	         con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
	         con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
	         con.setDoOutput(true);
	         int responseCode = con.getResponseCode();
	         BufferedReader br;
	         if(responseCode==200) { 
	             br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
	         } else {  
	             br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	         }
	         String inputLine;
	         res = new StringBuffer();
	         while ((inputLine = br.readLine()) != null) {
	             res.append(inputLine);
	         }
	         br.close();
	         System.out.println("service "+res.toString());
	     } catch (Exception e) {
	         System.out.println(e);
	     }
		
	
		return res.toString();
	}
	
}

		
	


