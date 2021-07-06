package com.JPA.stock.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Stock")
public class Stock {

	// @GeneratedValue(strategy = GenerationType.IDENTITY)

	@Id
	private String stockName;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "stockName")
	private List<Data> data;

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	@Override
	public String toString() {
		return "Stock [stockName=" + stockName + ", data=" + data + "]";
	}

}
