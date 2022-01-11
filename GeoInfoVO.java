package mc.sn.echo.vo;

public class GeoInfoVO {
	private String lat;
	private String lang;
	private String loc;
	
	public GeoInfoVO() {}
	public GeoInfoVO(String lat, String lang, String loc) {
		this.lat = lat;
		this.lang = lang;
		this.loc = loc;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return lat+","+lang+","+loc;
	}
	
	
}
