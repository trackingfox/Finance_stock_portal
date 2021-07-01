package com.JPA.stock.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Stock")
public class Stock {

	// CompoundKey compoundKey;
	private int stock_id;
	private String stockName;
	private List<Data> data;

	@OneToMany
	@JoinColumn
	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

//	@Id
//	@GeneratedValue
//	public CompoundKey getCompoundKey() {
//		return compoundKey;
//	}
//
//	public void setCompoundKey(CompoundKey compoundKey) {
//		this.compoundKey = compoundKey;
//	}

//	public Stock(int stock_id, String stockName) {
//		super();
//		this.stock_id = stock_id;
//		this.stockName = stockName;
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getStock_id() {
		return stock_id;
	}

	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	@Override
	public String toString() {
		return "Stock [stock_id=" + stock_id + ", stockName=" + stockName + ", data=" + data + "]";
	}

}
