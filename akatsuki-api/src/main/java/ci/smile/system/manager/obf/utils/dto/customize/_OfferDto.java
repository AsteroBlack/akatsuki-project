
/*
 * Java dto for entity table offer 
 * Created on 2022-11-07 ( Time 18:22:19 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.dto.customize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.reflect.Parameter;

import ci.smile.system.manager.obf.dao.entity.Groupe;
import ci.smile.system.manager.obf.dao.entity.OfferOlt;
import ci.smile.system.manager.obf.utils.contract.*;
import ci.smile.system.manager.obf.utils.dto.GroupeDto;
import ci.smile.system.manager.obf.utils.dto.OfferGroupeDto;
import ci.smile.system.manager.obf.utils.dto.OfferOltDto;
import ci.smile.system.manager.obf.utils.dto.TypeOffreDto;
import lombok.Data;
import lombok.ToString;

/**
 * DTO customize for table "offer"
 * 
 * @author Geo
 *
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _OfferDto {
	private OfferOltDto template;
	private String ontMacLabel;
	private String port;
	private String expirationDate;
	private String sn;
	private String tx;
	private String rx;
	private String nd;
	private String groupename;
	private String libellegroupe;
	private String username ;
	private List<_Parameter> datasParameter;
	private List<Groupe> dataGroupe;
	private List<GroupeDto> datasGroupe;
	private List<OfferGroupeDto> datasOffer;
	private String modeleBoxe;
	private List<OfferOltDto> datasOfferOlt;
}
