package com.InvestoBull.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.InvestoBull.entity.Candle;
import com.InvestoBull.exception.CandleException;
import com.InvestoBull.exception.IntervalException;
import com.InvestoBull.repository.CandleRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class CandleServiceImpl implements CandleService {

	@Autowired
	private CandleRepository candleRepository;

	@Override
	public String storeJsonData() throws CandleException, IOException {
		ClassPathResource resource = new ClassPathResource("candles.json");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		JsonNode rootNode = objectMapper.readTree(resource.getInputStream());

		JsonNode candlesNode = rootNode.get("candles"); // Get the "candles" array

		if (candlesNode.isArray()) {
			List<Candle> candles = new ArrayList<>();
			for (JsonNode candleNode : candlesNode) {
				// Convert JSON object to Candle using configured ObjectMapper
				Candle candle = objectMapper.convertValue(candleNode, Candle.class);
				candles.add(candle);
			}
			Collections.sort(candles,Comparator.comparing(Candle::getLastTradeTime));
			candleRepository.saveAll(candles);
		}

		return "Candles stored in the database.";
	}

	@Override
	public List<Candle> getCandlesData() {
		return candleRepository.findAllByOrderByLastTradeTimeAsc();
	}

	@Override
	public String getFirstOrbCandle(Integer time) throws CandleException, IntervalException {

		List<Candle> candles = getCandlesData();

		if (candles.isEmpty())
			throw new CandleException("Candle data not found");

		if (time % 5 != 0)
			throw new IntervalException("Given time should be in multiple of 5");

		int interval = time / 5;
		if (interval > candles.size())
			throw new CandleException("No ORB found for this interval.");
		double high = Double.MIN_VALUE;
		double low = Double.MAX_VALUE;
		for (int i = 0; i < interval; i++) {
			high = Math.max(high, candles.get(i).getHigh());
			low = Math.min(low, candles.get(i).getLow());
		}
		System.out.println(high + " " + low);
		LocalDateTime lastTradeTime = null;

		for (int i = interval; i < candles.size(); i++) {
			double close = candles.get(i).getClose();
			if (close > high || close < low) {
				System.out.println(close + " " + i);
				lastTradeTime = candles.get(i).getLastTradeTime();
				break;
			}
		}
		if (lastTradeTime == null)
			throw new CandleException("No ORB found for this interval.");

		return "ORB Candle generated at " + lastTradeTime;
	}

	@Override
	public List<Candle> getCandlesWithNewInterval(Integer time) throws CandleException, IntervalException {

		List<Candle> candles = getCandlesData();

		if (candles.isEmpty())
			throw new CandleException("Candle data not found");

		if (time % 5 != 0)
			throw new IntervalException("Given time should be in multiple of 5");

		int interval = time / 5;
		
		List<Candle> newCandles = new ArrayList<>();
		
		for(int i=0;i<candles.size();i+=interval) {
			if(i+interval <=candles.size() ) {
				Candle candle = createNewCandleForGivenInterval(i,i+interval,candles);
				candle.setId(Long.valueOf(newCandles.size()));
				newCandles.add(candle);
			}
		}

		return newCandles;
	}
	
	
	public Candle createNewCandleForGivenInterval(int startIdx,int endIdx,List<Candle> candles) {
		
		Candle candle = new Candle();
		candle.setLastTradeTime(candles.get(startIdx).getLastTradeTime());
		candle.setQuotationLot(candles.get(startIdx).getQuotationLot());
		candle.setOpenInterest(candles.get(startIdx).getOpenInterest());
		candle.setOpen(candles.get(startIdx).getOpen());
		candle.setClose(candles.get(endIdx-1).getClose());
		
		Long tradedQty = Long.valueOf(0);
		Double high = Double.MIN_VALUE;
		Double low = Double.MAX_VALUE;
		
		for(int i= startIdx;i<endIdx;i++) {
			tradedQty+=candles.get(i).getTradedQty();
			high = Math.max(high, candles.get(i).getHigh());
			low = Math.min(low, candles.get(i).getLow());
		}
		
		candle.setHigh(high);
		candle.setLow(low);
		candle.setTradedQty(tradedQty);
		return candle;
	}

}
