package com.example.cryptocurrency.models;

import java.sql.Date;

public class Quote{
    public String name;
    public double price;
    public double volume24h;
    public double marketCap;
    public double percentChange1h;
    public double percentChange24h;
    public double percentChange7d;
    public Date lastUpdated;
    public double percentChange30d;
    public double percentChange60d;
    public double percentChange90d;
    public double fullyDilluttedMarketCap;
    public double marketCapByTotalSupply;
    public double dominance;
    public double turnover;
    public double ytdPriceChangePercentage;
    public double tvl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVolume24h() {
        return volume24h;
    }

    public void setVolume24h(double volume24h) {
        this.volume24h = volume24h;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public double getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(double percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public double getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(double percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public double getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(double percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public double getPercentChange30d() {
        return percentChange30d;
    }

    public void setPercentChange30d(double percentChange30d) {
        this.percentChange30d = percentChange30d;
    }

    public double getPercentChange60d() {
        return percentChange60d;
    }

    public void setPercentChange60d(double percentChange60d) {
        this.percentChange60d = percentChange60d;
    }

    public double getPercentChange90d() {
        return percentChange90d;
    }

    public void setPercentChange90d(double percentChange90d) {
        this.percentChange90d = percentChange90d;
    }

    public double getFullyDilluttedMarketCap() {
        return fullyDilluttedMarketCap;
    }

    public void setFullyDilluttedMarketCap(double fullyDilluttedMarketCap) {
        this.fullyDilluttedMarketCap = fullyDilluttedMarketCap;
    }

    public double getMarketCapByTotalSupply() {
        return marketCapByTotalSupply;
    }

    public void setMarketCapByTotalSupply(double marketCapByTotalSupply) {
        this.marketCapByTotalSupply = marketCapByTotalSupply;
    }

    public double getDominance() {
        return dominance;
    }

    public void setDominance(double dominance) {
        this.dominance = dominance;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public double getYtdPriceChangePercentage() {
        return ytdPriceChangePercentage;
    }

    public void setYtdPriceChangePercentage(double ytdPriceChangePercentage) {
        this.ytdPriceChangePercentage = ytdPriceChangePercentage;
    }

    public double getTvl() {
        return tvl;
    }

    public void setTvl(double tvl) {
        this.tvl = tvl;
    }
}
