package com.JPA.stock.servlet;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Test;

import com.JPA.stock.entity.Data;

public class Stock_update_db {
	@Test
	public void updateEntity() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_Stock_Data");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery(
				"FROM Data where stockName='A' AND open_price>69 AND  open_price<72 AND Date BETWEEN '2019-01-11' AND '2019-01-17' ");
		List<Data> results = query.getResultList();

		for (Data obj : results) {
			System.out.println(obj.getDate() + "   " + obj.getHigh_price() + "   " + obj.getLow_price() + "    "
					+ obj.getOpen_price() + "    " + obj.getClose_price());
		}

		for (Data obj : results) {

			// The entity object is physically updated in the database when the transaction
			// is committed

			obj.setDate("1999-01-14");
			obj.setHigh_price((float) 89.34);
			obj.setClose_price((float) 89.34);
			obj.setOpen_price((float) 89.34);
			obj.setLow_price((float) 89.34);

			System.out.println(obj.getDate() + "   " + obj.getHigh_price() + "   " + obj.getLow_price() + "    "
					+ obj.getOpen_price() + "    " + obj.getClose_price());

		}
		em.getTransaction().commit();
		em.close();

	}
}
