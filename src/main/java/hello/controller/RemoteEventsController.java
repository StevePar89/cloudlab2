package hello.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import hello.InvalidCountryCodeException;
import hello.model.Event;


@RestController
public class RemoteEventsController {
	private  static final Logger Log = LoggerFactory.getLogger(RemoteEventsController.class);
	private Map<String,String> urls= new HashMap <String,String>();

	
	@GetMapping("/remote/events/{country}")
	public List<Event> fetchCountryEvents (@PathVariable String country){
		RestTemplate restTemplate =new RestTemplate ();
		
		initUrls();
		
		String url = urls.get(country);
		Log.info("using url"+url);
		
		if (url != null) {
			ResponseEntity<List<Event>> response = restTemplate.exchange(
					url, 
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<Event>>() {
					});
			List<Event> events = response.getBody();
			return events;
		} else {
			throw new InvalidCountryCodeException();
		}		
	}

	private void initUrls() {
		urls.put("us", "https://alfo.cloud-lab.it/hello/event");
		urls.put("fr", "https://paquale.cloud-lab.it/hello/event");
		urls.put("it", "https://danilo.cloud-lab.it/hello/event");
		urls.put("nl", "https://francesco.cloud-lab.it/hello/event");
		urls.put("nz", "https://stefano.cloud-lab.it/hello/event");
		urls.put("no", "https://luca.cloud-lab.it/hello/event");
		urls.put("ph", "https://rose.cloud-lab.it/hello/event");
		urls.put("pe", "https://ana.cloud-lab.it/hello/event");
	}
	
}
