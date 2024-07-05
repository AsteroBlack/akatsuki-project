
/*
 * Created on 2021-03-09 ( Time 11:10:34 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils;


/**
 * Math Utils
 * 
 * @author Geo
 *
 */
public class MathUtils {
	/**
	 * 
	 * @param percentage
	 * @param totalPrice
	 * @return
	 */
	public static Double getValueByPercentage(Integer percentage, Double totalPrice) {
		return getValueByPercentage(Double.parseDouble(percentage.toString()), totalPrice);
	}

	/**
	 * 
	 * @param percentage
	 * @param totalPrice
	 * @return
	 */
	public static Double getValueByPercentage(Double percentage, Double totalPrice) {
		Double value = 0d;
		Double pe = ((double) percentage / 100);
		value = totalPrice * pe;
		return value;
	}

	/**
	 * 
	 * @param value
	 * @param totalPrice
	 * @return
	 */
	public static Double getPercentageByValue(Double value, Double totalPrice) {
		Double percentage = 0d;
		percentage = ((value * 100) / totalPrice);
		return percentage;
	}
}