package com.JPA.stock.servlet;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Test;

import com.JPA.stock.entity.Data;

public class Stock_delete {
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

			// The entity object is physically deleted in the database when the transaction
			// is committed

			em.remove(obj);

		}
		em.getTransaction().commit();
		em.close();

	}

}