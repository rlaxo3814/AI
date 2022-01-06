package mc.sn.echo.ai.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import mc.sn.echo.ai.service.AiService;

@Controller("aiController")
public class AiController {
	@Autowired
	private AiService aiService;
	

	@ResponseBody
	@RequestMapping(value="/translate" ,method = RequestMethod.GET)
	public void service1(@RequestParam("text") String text, 
			           HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		System.out.println(text);
	
		String result = aiService.translate(text);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);
	}
	
	@RequestMapping(value = "/view_translate", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String url = "/service/translate";
		mav.setViewName(url);
		return mav;
	}
	
	@RequestMapping(value = "/view_stt", method = RequestMethod.GET)
	public ModelAndView stt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String url = "/service/sttResult";
		mav.setViewName(url);
		return mav;
	}
	
	@RequestMapping(value = "/view_tts", method = RequestMethod.GET)
	public ModelAndView tts(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String url = "/service/ttsResult";
		mav.setViewName(url);
		return mav;
	}
	//http://localhost:9090/stt/kthmap1
	@RequestMapping(value = "/kthmap1", method = RequestMethod.GET)
	public ModelAndView map(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String url = "/service/test";
		mav.setViewName(url);
		return mav;
	}
	
	@RequestMapping(value = "/kthmap2", method = RequestMethod.GET)
	public ModelAndView map1(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String url = "/service/map";
		mav.setViewName(url);
		return mav;
	}
	
	@RequestMapping(value = "/kthmap3", method = RequestMethod.GET)
	public ModelAndView map2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String url = "/service/static";
		mav.setViewName(url);
		return mav;
	}
	

	
	
	
	@RequestMapping(value="/clovaSTT", produces = "application/text; charset=UTF-8", method =RequestMethod.POST)
	@ResponseBody
	public String stt(@RequestParam("uploadFile") MultipartFile file,
								@RequestParam("language") String language) {
		String result = "";
		System.out.println(language);
		try {
			
			  String uploadPath =  "c:/ai/";
			  
			 
			  String originalFileName = file.getOriginalFilename();  
			  
			  
			  String filePathName = uploadPath + originalFileName;
			  File file1 = new File(filePathName);
			  System.out.println(filePathName);
			  
			  file.transferTo(file1);
			  
			  result = aiService.clovaSpeechToText(filePathName, language);
			  System.out.println("ai "+result);
			  
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value="/tts", method=RequestMethod.POST)
	@ResponseBody
	public String tts(@RequestParam("uploadFile") MultipartFile file,
								@RequestParam("language") String language) {
		String result = "";
		
		try {
			
			  String uploadPath =  "c:/ai/";
			  
			  
			  String originalFileName = file.getOriginalFilename();  
			  
			  
			  String filePathName = uploadPath + originalFileName;
			  File file1 = new File(filePathName);
			  System.out.println("3");
			  
			  
			  file.transferTo(file1);
			  
			  result = aiService.clovaTextToSpeech(filePathName, language);
			  System.out.println(result);
			  
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;  
	}

	@ResponseBody
	@RequestMapping(value="/kthmap2", method=RequestMethod.GET, produces = "application/text; charset=UTF-8")
	public void getGeo(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		String addr = req.getParameter("addr");
		res.setContentType("text/text; charset=utf-8");
		String result = aiService.geocode(addr);
		//String result = mapService.reverseGeocode("127.1054065,37.3595669");
		System.out.println("controller "+result);
		res.getWriter().print(result);
	}
	
	@ResponseBody
	@RequestMapping(value="/kthmap3", method=RequestMethod.GET, produces = "application/text; charset=UTF-8")
	public void reverseGetgeo(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		String coords = req.getParameter("coords");
		res.setContentType("text/text; charset=utf-8");
		String result = aiService.reverseGeocode(coords);
		System.out.println("controller "+result);
		res.getWriter().print(result);
	}
	
}