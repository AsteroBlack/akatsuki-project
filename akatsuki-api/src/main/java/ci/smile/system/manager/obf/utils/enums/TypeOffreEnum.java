/*
 * Created on 2018-04-14 ( Time 21:52:32 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.enums;

/**
 * 
 * @author Geo
 *
 */
public enum TypeOffreEnum {
	DEFAULT("DATA"),
	DATA("DATA"),	
	VOIX("Voix");

	private final String value;
	public String getValue() {
		return value;
	}
	private TypeOffreEnum(String value) {
		this.value = value;
	}
}
