package com.gao.demo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ipo_detail")
public class IPODetailEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "exchange_id")
    private Long exchangeId;
    
    @Column(name="price_per_share")
    private double pricePerShare;
    
    @Column(name="total_number_shares")
    private int totalNumberOfShares;
    
    @Column(name="open_datetime")
    private Date openDateTime;

    public IPODetailEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Long exchangeId) {
        this.exchangeId = exchangeId;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

    public void setPricePerShare(double pricePerShare) {
        this.pricePerShare = pricePerShare;
    }

    public int getTotalNumberOfShares() {
        return totalNumberOfShares;
    }

    public void setTotalNumberOfShares(int totalNumberOfShares) {
        this.totalNumberOfShares = totalNumberOfShares;
    }

    public Date getOpenDateTime() {
        return openDateTime;
    }

    public void setOpenDateTime(Date openDateTime) {
        this.openDateTime = openDateTime;
    }

    
}
