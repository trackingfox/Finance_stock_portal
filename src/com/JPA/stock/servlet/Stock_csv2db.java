package com.JPA.stock.servlet;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.JPA.stock.entity.Data;
import com.JPA.stock.entity.Stock;

public class Stock_csv2db {

//	public List<StockTemp> StockcsvToclass() {
//
//		// create a hashmap of column header to class attribute
//		Map<String, String> mapper = new HashMap<String, String>();
//
//		mapper.put("Name", "StockName");
//
//		// HeaderColumnNameTranslateMappingStrategy
//
//		HeaderColumnNameTranslateMappingStrategy<StockTemp> strategy = new HeaderColumnNameTranslateMappingStrategy<StockTemp>();
//		strategy.setType(StockTemp.class);
//		strategy.setColumnMapping(mapper);
//
//		// csvReader
//		CSVReader csvReader = null;
//		try {
//			csvReader = new CSVReader(new FileReader("D:\\datafolder\\AES_data.csv"));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		// csv to bean
//		CsvToBean csvBean = new CsvToBean();
//
//		// call the parse method
//		List<StockTemp> stocklist = csvBean.parse(strategy, csvReader);
////		for (StockTemp stock : stocklist) {
////			System.out.println(stock);
////		}
////		
//
//		return stocklist;
//	}

//	// @Test
//	public List<Stock> StockDetails() {
//		List<StockTemp> stocklist = StockcsvToclass();
////		for (StockTemp stock : stocklist) {
////			System.out.println(stock);
////		}
//
//		// 2 convert stocktemp list to stocklist using stream
//		Data_csv2db datacsv = new Data_csv2db();
//
//		List<Stock> stocklist1 = new ArrayList<Stock>();
//		stocklist1 = stocklist.stream().map(st -> {
//
//			Stock stock2 = new Stock();
//
//			stock2.setStockName(st.getStockName());
//			stock2.setData(datacsv.DataDetails());
//
//			return stock2;
//		}).collect(Collectors.toList());
//
//		for (Stock st : stocklist1) {
//			System.out.println(st + "new stocklist");
//
//		}
//		return stocklist1;
//	}

	public Stock stock_name_detail() {

		Stock st = new Stock();
		Data_csv2db datacsv = new Data_csv2db();

		st.setStockName("AES");
		st.setData(datacsv.DataDetails());
		// st.setData(null);
		return st;

	}

	// D:\datafolder
	// @Test
	public void getfileList() {

		File folderName = new File("D:\\datafolder");

		for (File f : folderName.listFiles()) {
			if (f.isDirectory())
				;
			else
				System.out.println(f.getName() + f.getPath());
		}

	}

	@Test
	public void importTodb() {

		// use persistence.xml configuration

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_Stock_Data");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		// List<Stock> stocklist1 = this.StockDetails();
		// stocklist1.forEach(x -> em.merge(x));
		Stock s = this.stock_name_detail();
		em.merge(s);
		em.getTransaction().commit();

		// FETCHING DATA FROM DATABASE
		// retrieval by class and primary key
		Stock stock1 = em.find(Stock.class, "AES");
		Data data1 = em.find(Data.class, 1);
		System.out.println("stock name :: " + stock1.getStockName());
		System.out.println("stock data :: " + stock1.getData().toString());

		System.out.println("StockName :: " + data1.getStock().toString());
		System.out.println("Date id :: " + data1.getDataId());
		System.out.println("open price :: " + data1.getOpen_price());
		System.out.println("close price :: " + data1.getClose_price());
		System.out.println("high price :: " + data1.getHigh_price());
		System.out.println("low price :: " + data1.getLow_price());
		System.out.println("Date :: " + data1.getDate());

		// retrieval by query(JPQL)

//				Query query = em.createQuery("SELECT * FROM Stock, Data where Stock.stockName=Data.stockName");
//				List results = query.getResultList();

		em.close();
		emf.close();

	}

}
