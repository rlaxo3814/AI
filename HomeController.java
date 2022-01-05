package mc.sn.echo;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mc.sn.echo.vo.GeoInfoVO;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "Home";
	}
	
	@ResponseBody
	@RequestMapping(value="/reqInfo" ,method = RequestMethod.POST)
	public void removeMember(@RequestParam("id") String id,
									 @RequestParam("pwd") String pwd, 
			           HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		System.out.println(id+", "+pwd);
		
		response.getWriter().print("working");
	}
	
	@RequestMapping(value = "/kthmap4", method = RequestMethod.GET)
	public String map3(Locale locale, Model model) {
		return "/service/web_maps";
		}
	
	@RequestMapping(value = "/kthmap5", method = RequestMethod.GET)
	public String map4(Locale locale, Model model) {
		return "/service/geotest";
		}
	
	@ResponseBody
	@RequestMapping("/geoList")
	  public List<GeoInfoVO> listMembers () {
	    List<GeoInfoVO> list = new ArrayList<GeoInfoVO>();
		GeoInfoVO vo1 = new GeoInfoVO("37.4959854","127.0664091","강남");
		GeoInfoVO vo2 = new GeoInfoVO("37.5492077","127.1464824","강동");
		GeoInfoVO vo3 = new GeoInfoVO("37.6469954","127.0147158","강북");
     	list.add(vo1);
     	list.add(vo2);
     	list.add(vo3);

	    return list; 
	  }	
	
	@RequestMapping(value = "/view_chat", method = RequestMethod.GET)
	public String chat(Locale locale, Model model) {
		return "/service/chatForm2";
		}
	
}
