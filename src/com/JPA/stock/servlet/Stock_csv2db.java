package com.JPA.stock.servlet;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.JPA.stock.entity.Stock;
import com.JPA.stock.entity.StockTemp;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class Stock_csv2db {

	public List<StockTemp> StockcsvToclass() {

		// CustomerNumber, CustomerName, ContactLastName, ContactFirstName,Phone,
		// AddressLine1,
		// AddressLine2, City, State, PostalCode,Country
		// SalesRepoEmployeeNumber, CreditLimit

		// "customerNumber","customerName","contactLastName","contactFirstName","phone","addressLine1",
		// "addressLine2","city","state","postalCode","country","salesRepEmployeeNumber","creditLimit"

		// create a hashmap of column header to class attribute
		Map<String, String> mapper = new HashMap<String, String>();

		mapper.put("Name", "StockName");

		// HeaderColumnNameTranslateMappingStrategy
		// for Country class
		HeaderColumnNameTranslateMappingStrategy<StockTemp> strategy = new HeaderColumnNameTranslateMappingStrategy<StockTemp>();
		strategy.setType(StockTemp.class);
		strategy.setColumnMapping(mapper);

		// csvReader
		CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(new FileReader("D:\\datafolder\\AES_data.csv"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		// csv to bean
		CsvToBean csvBean = new CsvToBean();

		// call the parse method
		List<StockTemp> stocklist = csvBean.parse(strategy, csvReader);
//		for (CustomerTemp customerDb : customerlist) {
//			System.out.println(customerDb);
//
//		}

		return stocklist;
	}

	// @Test
	public List<Stock> StockDetails() {
		List<StockTemp> stocklist = StockcsvToclass();
		// Convert customertemp objects, customertemp list to customer list
//		List<Customer> custlist1 = custlist.stream().map(x -> x.getSalesRepoEmployeeNumber().equals("") ? null
//				: Integer.parseInt(x.getSalesRepoEmployeeNumber())).collect(Collectors.toList());

		List<Stock> stocklist2 = new ArrayList<Stock>();
		for (StockTemp cust : stocklist) {
			// 3.convert customertemp list to customer list
			Stock cust2 = new Stock();

			cust2.setStockName(cust.getStockName());

			stocklist2.add(cust2);
		}

//		for (Customer customer : custlist2) {
//			System.out.println(customer);
//
//		}

		// 1.convert customertemp list to customer list using interface

//		CustomerInterface obj1 = new CustomerTemp();
//		CustomerInterface obj2 = new Customer();
//		obj2 = obj1;
//
//		Integer temp = obj2.getSalesRepoEmployeeNumber().equals("") ? null
//				: Integer.valueOf(obj2.getSalesRepoEmployeeNumber());
//		obj2.setSalesRepoEmployeeNumber(temp);
//
//		int temp2 = Integer.valueOf(obj2.getCustomerNumber());
//		obj2.setCustomerNumber(temp2);
//
//		Float temp3 = Float.valueOf(obj2.getCreditLimit());
//		obj2.setCreditLimit(temp3);

		// 2 convert customertemp list to customer list using stream

		List<Stock> stocklist1 = new ArrayList<Stock>();
		stocklist1 = stocklist.stream().map(cust -> {

			Stock cust2 = new Stock();

			cust2.setStockName(cust.getStockName());

			return cust2;
		}).collect(Collectors.toList());

		for (Stock st : stocklist1) {
			System.out.println(st);

		}
		return stocklist1;
	}

	@Test
	public void importTodb() {

//		SessionFactory sessionFactory = null;
//		// configures settings from hibernate.cfg.xml
//		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
//
//		try {
//			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//		} catch (Exception ex) {
//			System.out.println(ex.toString()); // If error display in console
//			StandardServiceRegistryBuilder.destroy(registry);
//		}
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		List<Student> studentlist = this.csvToclass();
//		studentlist.forEach(x -> session.save(x));
//		session.getTransaction().commit();
//		session.close();

		// use persistence.xml configuration

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_Stock_Data");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<Stock> stocklist1 = this.StockDetails();
//		custlist1.forEach(x -> em.persist(x));
		stocklist1.forEach(x -> em.merge(x));
		em.getTransaction().commit();
		em.close();
		emf.close();

	}

}
