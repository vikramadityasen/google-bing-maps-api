package com.practice.geomap.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.geomap.service.LocationService;

@RequestMapping("/api")
@RestController
public class LocationController {

	@Autowired
	private LocationService locationService;

	@GetMapping(value = "/gLocation/{placeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> getLocationByGoogle(@PathVariable String placeId) {
		return locationService.getLocationByGoogle(placeId);
	}

	@GetMapping(value = "/bLocation/{adminDistrict}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> getLocationByBing(@PathVariable String adminDistrict) {
		return locationService.getLocationByBing(adminDistrict);
	}
}
