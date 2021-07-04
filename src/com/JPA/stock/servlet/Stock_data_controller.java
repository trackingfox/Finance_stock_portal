package com.JPA.stock.servlet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

import com.JPA.stock.entity.Data;

public class Stock_data_controller {

	private final Queue<Double> window = new LinkedList<>();
	private double sum;
	private int period;

	public void Stock_controller(int period) {

		this.period = period;
	}

	public void newNum(double num) {
		sum += num;
		window.add(num);
		if (window.size() > period) {
			sum -= window.remove();
		}
	}

	public double getAvg() {
		if (window.isEmpty()) {
			return 0.0;
		}
		return sum / window.size();
	}

	@Test
	public void MovingAverage() {
		List<Data> high_list = new ArrayList<Data>();
		List<Double> high = new ArrayList<Double>();

		for (Data data1 : high_list) {
			high.add((double) data1.getHigh_price());
		}
		// double[] testData = { 1, 2, 3, 4, 5, 5, 4, 3, 2, 1 };
		int[] windowSizes = { 3, 9, 15, 7, 4 };

		for (int windSize : windowSizes) {
			Stock_data_controller ma = new Stock_data_controller();
			ma.Stock_controller(windSize);
			for (double x : high) {
				ma.newNum(x);
				System.out.println("Next number = " + x + ", SimpleMovingAvg = " + ma.getAvg());
			}
			System.out.println();
		}
	}

}
