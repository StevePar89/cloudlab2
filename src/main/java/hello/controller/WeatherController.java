package hello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import hello.value.weather.*;
import hello.value.weatherResponse.WeatherResponse;

@RestController
public class WeatherController {
	
	private static final Logger Log = LoggerFactory.getLogger(WeatherController.class); 
	
	@GetMapping ("/weather")
	public OpenWeather getOpenWeather () {
		RestTemplate restTemplate = new RestTemplate ();
		String apiKey = "a532f2378e969c1e540f500d74e0153c";
		String loc = "Domaso,IT";
		
	 return	restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?q="+loc+"&"+"appid="+apiKey, OpenWeather.class) ;
	}
	
	@GetMapping ("/weather/city")
	public OpenWeather getOpenWeather1 (@RequestParam String loc, @RequestParam String country ) {
		RestTemplate restTemplate = new RestTemplate ();
		String apiKey = "a532f2378e969c1e540f500d74e0153c";
		
	 return	restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?q="+loc+","+country+"&"+"appid="+apiKey, OpenWeather.class) ;
	}
	

	
	@GetMapping ("/weather/all")
	public WeatherResponse getOpenWeatherall (@RequestParam ("loc") String loc, @RequestParam ("loc2") String loc2) {
		RestTemplate restTemplate = new RestTemplate ();
		String apiKey = "a532f2378e969c1e540f500d74e0153c";
		String url1="https://api.openweathermap.org/data/2.5/weather?q="+loc+"&"+"appid="+apiKey;
		String url2="https://api.openweathermap.org/data/2.5/weather?q="+loc2+"&"+"appid="+apiKey;
		
		Log.debug("fetch url1: "+url1);
		Log.debug("fetch url1: "+url2);
		
		OpenWeather weather1 = restTemplate.getForObject(url1,OpenWeather.class);
		OpenWeather weather2 = restTemplate.getForObject(url2,OpenWeather.class);
		
		WeatherResponse response = new WeatherResponse();
		response.setFirstCity(weather1.getName());
		response.setSecondCity(weather2.getName());
		response.setFirstTemp(weather1.getMain().getTemp() - 273.15);
		response.setSecondTemp(weather2.getMain().getTemp()- 273.15);
		
		return response;
	}
}




/* 1.estrarre la localita e api key
 * 2.ottenere localita con parametro di request
 * 3.doppia interogazione
 */
