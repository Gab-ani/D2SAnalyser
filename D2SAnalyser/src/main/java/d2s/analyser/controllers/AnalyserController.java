package d2s.analyser.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import d2s.analyser.D2SAnalyser;
import d2s.analyser.Predictor;
import d2s.analyser.data.MatchListDTO;
import d2s.analyser.domain.Match;
import d2s.analyser.domain.PredictRequest;

@RestController  
public class AnalyserController {
	
	@Autowired
	D2SAnalyser analyser;
	@Autowired
	RestTemplate rest;
	@Autowired
	Predictor predictor;
	
	@GetMapping("/")
	public String defaultAnswer() {
		return "hi, I am the microservice dedicated to process large amounts of dota2 games date into useful representations";
	}
	
	@GetMapping("/newSelection/{from}/{to}/{name}")
	public String createNewSelection(@PathVariable(value="from") long from, @PathVariable(value="to") long to, @PathVariable(value="name") String name) {
		System.out.println(from + "/" + to);
		var matchesArray = rest.getForObject("http://d2shistory/parsed/" + from + "/" + to, Match[].class);
		System.out.println("got " + matchesArray.length + " matches to create a selection from " + from + " to " + to);
		var matches = Arrays.asList(matchesArray);
		analyser.createSelection(matches, name);
		return "successfully created new selection";
	}
	
	@PostMapping(value = "/predict")
	public String predict(@RequestBody PredictRequest request) {
		return predictor.predict(request);
	}
	
}
