package com.InvestoBull.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.InvestoBull.entity.Candle;
import com.InvestoBull.exception.CandleException;
import com.InvestoBull.exception.IntervalException;
import com.InvestoBull.service.CandleService;

@RestController
public class CandleController {

	
	@Autowired
    private CandleService candleService;
	/**
	 * 
	 * @param key Use to verify
	 * @return Success message when Data Successfully stored
	 * @throws IOException 
	 */
	
	@PostMapping("/store-candles")
    public ResponseEntity<String> storeCandlesInDatabase(@RequestParam("api-key") String key) throws IOException{
    	String resp = candleService.storeJsonData();
    	return new ResponseEntity<>(resp,HttpStatus.CREATED);
    }

	/**
	 * 
	 * @param key Use to verify
	 * @return List<Candle> from data base
	 */
    @GetMapping("/get-candles")
    public List<Candle> getCandlesFromDatabase(@RequestParam("api-key") String key) {
        return candleService.getCandlesData();
    }
    
    /**
     * 
     * @param time the time interval at which we have to find the first ORB candle
     * @param key Use to verify
     * @return Success message with Last traded time of first ORB candle
     */
    @GetMapping("/first-Orb-candle/{time}")
    public ResponseEntity<String> getFirstOrbCandleGenerated(@PathVariable("time") Integer time,@RequestParam("api-key") String key){
    	String resp = candleService.getFirstOrbCandle(time);
    	return new ResponseEntity<String>(resp,HttpStatus.OK);
    }
    
    /**
     * 
     * @param time interval at which we have to combine all the data
     * @param key key Use to verify
     * @return New List of candle with given time interval
     */
    
    @GetMapping("/new-interval-candles/{time}")
    public ResponseEntity<List<Candle>> getNewIntervalCandles(@PathVariable("time") Integer time,@RequestParam("api-key") String key) {
    	List<Candle> candles = candleService.getCandlesWithNewInterval(time);
    	return new ResponseEntity<List<Candle>>(candles,HttpStatus.OK);
    }
}
