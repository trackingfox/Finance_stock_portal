package com.JPA.stock.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Test;

import com.JPA.stock.entity.Data;

public class Stock_fetch_db {

	@Test
	public void fetchFromdb() {

		// use persistence.xml configuration

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_Stock_Data");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

//		Stock stock1 = em.find(Stock.class, "AES");
//	    Data data1 = em.find(Data.class, 1);
//		System.out.println("stock name :: " + stock1.getStockName());
//		System.out.println("stock data :: " + stock1.getData().toString());

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

		Query query = em.createQuery(
				"FROM Data where stockName='A' AND open_price>69 AND  open_price<72 AND Date BETWEEN '2019-01-11' AND '2019-01-17' ");
		List<Data> results = query.getResultList();

		for (Data obj : results) {
			System.out.println(obj.getDate() + "   " + obj.getHigh_price() + "   " + obj.getLow_price() + "    "
					+ obj.getOpen_price() + "    " + obj.getClose_price());

		}
		// highPriceList() function
		List<Double> highList = new ArrayList<Double>();
		for (Data obj : results) {
			highList.add((double) obj.getHigh_price());
		}

		em.close();
		emf.close();

	}

}
