
/*
 * Java dto for entity table radius
 * Created on 2021-03-09 ( Time 11:10:42 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.dto.customize;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ci.smile.system.manager.obf.utils.dto.RadiusDto;
import lombok.Data;

/**
 * DTO customize for table "radius"
 *
 * @author Geo
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class _RadiusDto {

	private String port;
	private String mac;
	private List<RadiusDto> datasGroupeRadius;
	private Integer coeff;
	private String modeleId;
	// private String macAddress;
	private boolean containIllegalChar;
	private String groupename;
	private String username;
	private String idService;
	private String nbreAddr;
	private String nd;
	private String codeZone;
	private String codeTypeClient;
	private String address;
	private String mask;
	private String cidrAddress;
	private String gateway;
	private String beginAddr;
	private String endAddr;
	private Boolean isInterco;
	private Integer cidrMask;

	
	
	

}
