
/*
 * Created on 2021-03-09 ( Time 11:10:34 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Operator Enums
 * 
 * @author Geo
 *
 */
public class OperatorEnum {
	public static final String	EQUAL				= "=";
	public static final String	NOT_EQUAL_1			= "<>";
	public static final String	NOT_EQUAL_2			= "!=";
	public static final String	LESS_OR_EQUAL		= "<=";
	public static final String	LESS				= "<";
	public static final String	MORE_OR_EQUAL		= ">=";
	public static final String	MORE				= ">";
	public static final String	BETWEEN				= "[]";
	public static final String	BETWEEN_OUT			= "][";
	public static final String	BETWEEN_LEFT_OUT	= "]]";
	public static final String	BETWEEN_RIGHT_OUT	= "[[";
	public static final String	CONTAINS			= "%%";
	public static final String	START_WTIH			= "_%";
	public static final String	END_WTIH			= "%_";
	
	private static final List<String> LIST_OF_BETWEEN = Arrays.asList(BETWEEN, BETWEEN_OUT, BETWEEN_LEFT_OUT, BETWEEN_RIGHT_OUT);
	
	public static final boolean	IS_BETWEEN_OPERATOR	(String operator){
		return LIST_OF_BETWEEN.stream().anyMatch(s -> operator.equals(s));
	}
}