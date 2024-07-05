
/*
 * Created on 2021-03-09 ( Time 11:10:34 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ci.smile.system.manager.obf.utils.contract.ResponseBase;


@Component
public class ExceptionUtils {

	private static Logger	slf4jLogger;

	/*
	 * @Autowired private FunctionalError functionalError;
	 */

	@Autowired
	private TechnicalError	technicalError;

	public ExceptionUtils() {
		slf4jLogger = LoggerFactory.getLogger(getClass());
	}

	/**
	 * Permission non accordée pour acceder au serveur de BD
	 * 
	 * @param response
	 * @param locale
	 * @param e
	 */
	public void PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(ResponseBase response, Locale locale, Exception e) {
		// Permission non accordée pour acceder au serveur de BD
		e.printStackTrace();
		response.setHasError(Boolean.TRUE);
		response.setStatus(technicalError.DB_PERMISSION_DENIED(e.getMessage(), locale));
		slf4jLogger.warn("Erreur| code: {} -  message: {} - cause: {}  - SysMessage: {}", StatusCode.TECH_DB_PERMISSION_DENIED, StatusMessage.TECH_DB_PERMISSION_DENIED, e.getCause(), e.getMessage());
	}

	/**
	 * Base de données indisponible
	 * 
	 * @param response
	 * @param locale
	 * @param e
	 */
	public void DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(ResponseBase response, Locale locale, Exception e) {
		// base de données indisponible
		e.printStackTrace();
		response.setHasError(Boolean.TRUE);
		response.setStatus(technicalError.DB_FAIL(e.getMessage(), locale));
		slf4jLogger.warn("Erreur| code: {} -  message: {} - cause: {}  - SysMessage: {}", StatusCode.TECH_DB_FAIL, StatusMessage.TECH_DB_FAIL, e.getCause(), e.getMessage());
	}

	/**
	 * Serveur a refusé la requete
	 * 
	 * @param response
	 * @param locale
	 * @param e
	 */
	public void DATA_ACCESS_EXCEPTION(ResponseBase response, Locale locale, Exception e) {
		// Serveur a refusé la requete
		e.printStackTrace();
		response.setHasError(Boolean.TRUE);
		response.setStatus(technicalError.DB_QUERY_REFUSED(e.getMessage(), locale));
		slf4jLogger.warn("Erreur| code: {} -  message: {} - cause: {}  - SysMessage: {}", StatusCode.TECH_DB_QUERY_REFUSED, StatusMessage.TECH_DB_QUERY_REFUSED, e.getCause(), e.getMessage());
	}

	/**
	 * Erreur interne
	 * 
	 * @param response
	 * @param locale
	 * @param e
	 */
	public void RUNTIME_EXCEPTION(ResponseBase response, Locale locale, Exception e) {
		// Erreur interne
		e.printStackTrace();
		response.setHasError(Boolean.TRUE);
		response.setStatus(technicalError.ERROR(e.getMessage(), locale));
		slf4jLogger.warn("Erreur| code: {} -  message: {} - cause: {}  - SysMessage: {}", response.getStatus().getCode(), StatusMessage.FUNC_FAIL, e.getCause(), response.getStatus().getMessage());
	}

	/**
	 * Erreur interne
	 * 
	 * @param response
	 * @param locale
	 * @param e
	 */
	public void EXCEPTION(ResponseBase response, Locale locale, Exception e) {
		// Erreur interne
		e.printStackTrace();
		response.setHasError(Boolean.TRUE);
		response.setStatus(technicalError.INTERN_ERROR(e.getMessage(), locale));
		slf4jLogger.warn("Erreur| code: {} -  message: {} - cause: {}  - SysMessage: {}", StatusCode.TECH_INTERN_ERROR, StatusMessage.TECH_INTERN_ERROR, e.getCause(), e.getMessage());
		e.printStackTrace();
		// e.getStackTrace();
	}

	/**
	 * 
	 * @param response
	 * @param locale
	 * @param e
	 */
	public void CANNOT_CREATE_TRANSACTION_EXCEPTION(ResponseBase response, Locale locale, Exception e) {
		// Impossible de se connecter à  la base de données
		e.printStackTrace();
		response.setHasError(Boolean.TRUE);
		response.setStatus(technicalError.DB_NOT_CONNECT(e.getMessage(), locale));
		slf4jLogger.warn("Erreur| code: {} -  message: {} - cause: {}  - SysMessage: {}", StatusCode.TECH_DB_NOT_CONNECT, StatusMessage.TECH_DB_NOT_CONNECT, e.getCause(), e.getMessage());
	}

	/**
	 * 
	 * @param response
	 * @param locale
	 * @param e
	 */
	public void TRANSACTION_SYSTEM_EXCEPTION(ResponseBase response, Locale locale, Exception e) {
		// base de données indisponible
		e.printStackTrace();
		response.setHasError(Boolean.TRUE);
		response.setStatus(technicalError.DB_FAIL(e.getMessage(), locale));
		slf4jLogger.warn("Erreur| code: {} -  message: {} - cause: {}  - SysMessage: {}", StatusCode.TECH_DB_FAIL, StatusMessage.TECH_DB_FAIL, e.getCause(), e.getMessage());
	}
}