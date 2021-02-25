package com.finance.demo.model;

import java.math.BigDecimal;

public class StockModel {

    private String name;
    private BigDecimal price;

    private String currency;

    private BigDecimal change;

    private BigDecimal bid;

    private BigDecimal priceHint;

    private String stockName;

    public StockModel(String name, BigDecimal price, String currency, BigDecimal change, BigDecimal bid, BigDecimal priceHint, String stockName) {
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.change = change;
        this.bid = bid;
        this.priceHint = priceHint;
        this.stockName = stockName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getPriceHint() {
        return priceHint;
    }

    public void setPriceHint(BigDecimal priceHint) {
        this.priceHint = priceHint;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    @Override
    public String toString() {
        return "StockModel{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", change=" + change +
                ", bid=" + bid +
                ", priceHint=" + priceHint +
                ", stockName='" + stockName + '\'' +
                '}';
    }
}
