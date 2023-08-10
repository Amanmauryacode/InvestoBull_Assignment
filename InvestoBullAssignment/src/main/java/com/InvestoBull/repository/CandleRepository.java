package com.InvestoBull.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.InvestoBull.entity.Candle;

public interface CandleRepository extends JpaRepository<Candle, Long> {

	public List<Candle> findAllByOrderByLastTradeTimeAsc();


	
	
}
