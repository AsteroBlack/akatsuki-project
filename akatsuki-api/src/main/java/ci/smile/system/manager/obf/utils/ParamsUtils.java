
/*
 * Created on 2018-11-10 ( Time 15:09:37 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * Params Utils
 * 
 * @author SFL Back-End developper
 *
 */
@Component
@Getter
public class ParamsUtils {

	// les parametres de SMTP
//	@Value("${smtp.mail.port}")
//	private Integer smtpPort;
//	@Value("${smtp.mail.login}")
//	private String smtpLogin;
////	@Value("${smtp.mail.password}")
////	private String smtpPassword;
//	@Value("${smtp.mail.host}")
//	private String smtpHost;

	// REDIS
//	@Value("#{'${redis.nodes}'.split(',')}")
//	private List<String> nodes;
//	@Value("${redis.clusterName}")
//	private String clusterName;
//	@Value("${redis.password}")
	// private String password;

	@Value("${mac.adresse.length}")
	private Integer macAdressLength;

	// API LDAP
	@Value("${api.ip.zone}")
	private String zone;
	
	
	@Value("${url.radius.check}")
	private String urlCheckRadius;
	
	@Value("${url.radius.infos}")
	private String urlCheckRadiusInfos;
	
	@Value("${url.radius.check.nd}")
	private String urlCheckNdRadius;




}