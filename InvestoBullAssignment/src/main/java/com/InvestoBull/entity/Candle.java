package com.InvestoBull.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Candle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("LastTradeTime")
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime lastTradeTime;

    @JsonProperty("QuotationLot")
    private Integer quotationLot;

    @JsonProperty("TradedQty")
    private Long tradedQty;

    @JsonProperty("OpenInterest")
    private Integer openInterest;

    @JsonProperty("Open")
    private Double open;
    
    @JsonProperty("High")
    private Double high;
    
    @JsonProperty("Low")
    private Double low;
    
    @JsonProperty("Close")
    private Double close;

}
