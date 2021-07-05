package com.JPA.stock.servlet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.JPA.stock.entity.Stock;

public class Stock_fetch_db {

	@Test
	public void fetchFromdb() {

		// use persistence.xml configuration

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_Stock_Data");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Stock stock1 = em.find(Stock.class, x);
		// Data data1 = em.find(Data.class, 1);
		System.out.println("stock name :: " + stock1.getStockName());
		System.out.println("stock data :: " + stock1.getData().toString());

		// FETCHING DATA FROM DATABASE
		// retrieval by class and primary key
		// Stock stock1 = em.find(Stock.class, "AES");
		// Data data1 = em.find(Data.class, 1);
//		System.out.println("stock name :: " + stock1.getStockName());
//		System.out.println("stock data :: " + stock1.getData().toString());

//		System.out.println("StockName :: " + data1.getStock().toString());
//		System.out.println("Date id :: " + data1.getDataId());
//		System.out.println("open price :: " + data1.getOpen_price());
//		System.out.println("close price :: " + data1.getClose_price());
//		System.out.println("high price :: " + data1.getHigh_price());
//		System.out.println("low price :: " + data1.getLow_price());
//		System.out.println("Date :: " + data1.getDate());

		// retrieval by query(JPQL)

//				Query query = em.createQuery("SELECT * FROM Stock, Data where Stock.stockName=Data.stockName");
//				List results = query.getResultList();

		em.close();
		emf.close();

	}

}
