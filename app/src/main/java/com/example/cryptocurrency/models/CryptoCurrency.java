package com.example.cryptocurrency.models;

import java.util.ArrayList;
import java.util.Date;

public class CryptoCurrency implements Comparable<CryptoCurrency>{
    public int id;
    public String name;
    public String symbol;
    public String slug;
    public ArrayList<String> tags;
    public double cmcRank;
    public double marketPairCount;
    public double circulatingSupply;
    public double selfReportedCirculatingSupply;
    public double totalSupply;
    public double maxSupply;
    public double isActive;
    public Date lastUpdated;
    public Date dateAdded;
    Double PercentChange24h;
    public ArrayList<Quote> quotes;
    public boolean isAudited;
    public ArrayList<AuditInfo> auditInfoList;
    public Platform platform;
    public double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public double getCmcRank() {
        return cmcRank;
    }

    public void setCmcRank(double cmcRank) {
        this.cmcRank = cmcRank;
    }

    public double getMarketPairCount() {
        return marketPairCount;
    }

    public void setMarketPairCount(double marketPairCount) {
        this.marketPairCount = marketPairCount;
    }

    public double getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(double circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public double getSelfReportedCirculatingSupply() {
        return selfReportedCirculatingSupply;
    }

    public void setSelfReportedCirculatingSupply(double selfReportedCirculatingSupply) {
        this.selfReportedCirculatingSupply = selfReportedCirculatingSupply;
    }

    public double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public double getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(double maxSupply) {
        this.maxSupply = maxSupply;
    }

    public double getIsActive() {
        return isActive;
    }

    public void setIsActive(double isActive) {
        this.isActive = isActive;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public ArrayList<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(ArrayList<Quote> quotes) {
        this.quotes = quotes;
    }

    public boolean isAudited() {
        return isAudited;
    }

    public void setAudited(boolean audited) {
        isAudited = audited;
    }

    public ArrayList<AuditInfo> getAuditInfoList() {
        return auditInfoList;
    }

    public void setAuditInfoList(ArrayList<AuditInfo> auditInfoList) {
        this.auditInfoList = auditInfoList;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Double getPercentChange24h() {
        return PercentChange24h;
    }

    public void setPercentChange24h(Double percentChange24h) {
        PercentChange24h = percentChange24h;
    }

    @Override
    public String toString() {
        return  name + " ("+getPercentChange24h()+")";
    }

    @Override
    public int compareTo(CryptoCurrency currency) {
        return this.getPercentChange24h().compareTo(currency.getPercentChange24h());
    }
}
