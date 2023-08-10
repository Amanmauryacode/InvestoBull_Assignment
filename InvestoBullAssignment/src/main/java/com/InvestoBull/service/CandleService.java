package com.InvestoBull.service;

import java.io.IOException;
import java.util.List;

import com.InvestoBull.entity.Candle;
import com.InvestoBull.exception.CandleException;
import com.InvestoBull.exception.IntervalException;

public interface CandleService {

	/**
	 * 
	 * @return Success message of data store
	 * @throws CandleException throw when JSON data not found 
	 * @throws IOException
	 */
	public String storeJsonData() throws CandleException, IOException;
	
	/**
	 * 
	 * @return List of candle form data base which we save earlier 
	 */
	public List<Candle> getCandlesData();
	
	/**
	 * 
	 * @param time interval given at which we have to find the first ORB candle
	 * @return Success message with Last traded time of candle if first ORB found 
	 * @throws CandleException if data not found in database or if no ORB found for given interval 
	 * @throws IntervalException if given time was not multiple of 5
	 */
	public String getFirstOrbCandle(Integer time)throws CandleException,IntervalException;
	
	/**
	 * 
	 * @param time was new Interval at which we have to combine all the data and create new candle
	 * @return List of candle with new Interval
	 * @throws CandleException throw if data not found in database or if no ORB formed for given interval 
	 * @throws IntervalException if given time was not multiple of 5
	 */
	public List<Candle> getCandlesWithNewInterval(Integer time)throws CandleException,IntervalException;
}
