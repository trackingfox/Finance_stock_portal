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

		// create a hashmap of column header to class attribute
		Map<String, String> mapper = new HashMap<String, String>();

		mapper.put("Name", "StockName");

		// HeaderColumnNameTranslateMappingStrategy

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
//		for (StockTemp stock : stocklist) {
//			System.out.println(stock);
//		}
//		

		return stocklist;
	}

	// @Test
	public List<Stock> StockDetails() {
		List<StockTemp> stocklist = StockcsvToclass();
		for (StockTemp stock : stocklist) {
			System.out.println(stock);
		}

		// 2 convert stocktemp list to stocklist using stream

		List<Stock> stocklist1 = new ArrayList<Stock>();
		stocklist1 = stocklist.stream().map(st -> {

			Stock stock2 = new Stock();

			stock2.setStockName(st.getStockName());

			return stock2;
		}).collect(Collectors.toList());

		for (Stock st : stocklist1) {
			System.out.println(st + "new stocklist");

		}
		return stocklist1;
	}

	@Test
	public void importTodb() {

		// use persistence.xml configuration

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_Stock_Data");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<Stock> stocklist1 = this.StockDetails();
		stocklist1.forEach(x -> em.merge(x));
		em.getTransaction().commit();
		em.close();
		emf.close();

	}

}
