package com.practice.geomap.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class LocationService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${google.map.api.key}")
	private String googleMapApiKey;

	@Value("${place.api.url}")
	private String placeApiUrl;

	@Value("${bing.map.url}")
	private String bingMapUrl;

	private String CountryRegion = "CA";

	private boolean sensor = false;

	public ResponseEntity<String> getLocationByGoogle(String placeId) {

		ResponseEntity<String> response = null;

		try {

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(placeApiUrl)
					.queryParam("reference", placeId).queryParam("sensor", sensor).queryParam("key", googleMapApiKey);

			RestTemplate restTemplate = new RestTemplate();

			response = restTemplate.getForEntity(builder.toUriString(), String.class);

		} catch (Exception e) {
			String message = String.format("Invalid response: %s", e.getMessage());
			logger.error(message);
		}

		return response;
	}

	public ResponseEntity<String> getLocationByBing(String adminDistrict) {
		ResponseEntity<String> response = null;

		try {

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(bingMapUrl)
					.queryParam("CountryRegion", CountryRegion)
					.queryParam("adminDistrict", adminDistrict);

			RestTemplate restTemplate = new RestTemplate();

			response = restTemplate.getForEntity(builder.toUriString(), String.class);
			
		} catch (Exception e) {
			String message = String.format("Invalid response: %s", e.getMessage());
			logger.error(message);
		}

		return response;
	}

}
