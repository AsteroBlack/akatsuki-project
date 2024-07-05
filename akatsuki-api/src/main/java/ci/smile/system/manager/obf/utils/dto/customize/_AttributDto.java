
/*
 * Java dto for entity table attribut 
 * Created on 2021-04-22 ( Time 11:55:13 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.dto.customize;

import java.util.Date;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ci.smile.system.manager.obf.utils.contract.*;
import ci.smile.system.manager.obf.utils.dto.AttributDto;
import lombok.Data;
import lombok.ToString;

/**
 * DTO customize for table "attribut"
 * 
 * @author Geo
 *
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _AttributDto {
	
	protected String key;
	protected String value;
	protected String type;
	protected String attribut;
	protected String code;


}
