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

import com.JPA.stock.entity.Data;
import com.JPA.stock.entity.DataTemp;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class Data_csv2db {

	public List<DataTemp> DatacsvToclass() {
		String filename = "D:\\datafolder\\AES_data.csv";
		return DatacsvToclass(filename);

	}

	public List<DataTemp> DatacsvToclass(String filename) {

		// create a hashmap of column header to class attribute
		Map<String, String> mapper = new HashMap<String, String>();

		mapper.put("Date", "date");
		mapper.put("High", "high_price");
		mapper.put("Low", "low_price");
		mapper.put("Open", "open_price");
		mapper.put("Close", "close_price");

		// HeaderColumnNameTranslateMappingStrategy

		HeaderColumnNameTranslateMappingStrategy<DataTemp> strategy = new HeaderColumnNameTranslateMappingStrategy<DataTemp>();
		strategy.setType(DataTemp.class);
		strategy.setColumnMapping(mapper);

		// csvReader
		CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(new FileReader(filename));

		} catch (Exception e) {
			e.printStackTrace();
		}

		// csv to bean
		CsvToBean csvBean = new CsvToBean();

		// call the parse method
		List<DataTemp> datalist = csvBean.parse(strategy, csvReader);

		return datalist;
	}

	public List<Data> DataDetails() {
		String filename = "D:\\datafolder\\AES_data.csv";
		return DataDetails(filename);

	}

	// @Test
	public List<Data> DataDetails(String filename) {
		List<DataTemp> datalist = DatacsvToclass(filename);

		// 2 convert customertemp list to customer list using stream

		List<Data> datalist1 = new ArrayList<Data>();
		datalist1 = datalist.stream().map(cust -> {

			Data cust2 = new Data();

			cust2.setDate(cust.getDate());

			Float c = Float.valueOf(cust.getHigh_price());
			cust2.setHigh_price(c);

			Float d = Float.valueOf(cust.getLow_price());
			cust2.setLow_price(d);

			Float e = Float.valueOf(cust.getOpen_price());
			cust2.setOpen_price(e);

			Float f = Float.valueOf(cust.getClose_price());
			cust2.setClose_price(f);

			return cust2;
		}).collect(Collectors.toList());

		return datalist1;
	}

	@Test
	public void importTodb() {

		// use persistence.xml configuration

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_Stock_Data");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<Data> datalist1 = this.DataDetails();
		datalist1.forEach(x -> em.merge(x));
		em.getTransaction().commit();
		em.close();
		emf.close();

	}

}
