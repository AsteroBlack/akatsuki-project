package ci.smile.system.manager.obf.dao.repository;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ci.smile.system.manager.obf.utils.*;
import ci.smile.system.manager.obf.utils.dto.*;
import ci.smile.system.manager.obf.utils.contract.*;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.dao.entity.*;
import ci.smile.system.manager.obf.dao.repository.customize._CompteRepository;

/**
 * Repository : Compte.
 */
@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer>, _CompteRepository {
	/**
	 * Finds Compte by using idCompte as a search criteria.
	 * 
	 * @param idCompte
	 * @return An Object Compte whose idCompte is equals to the given idCompte. If
	 *         no Compte is found, this method returns null.
	 */
	@Query("select e from Compte e where e.idCompte = :idCompte and e.isDeleted = :isDeleted")
	Compte findOne(@Param("idCompte")Integer idCompte, @Param("isDeleted")Boolean isDeleted);
	
	
	@Query("select e from Compte e where e.securityToken = :securityToken and e.securityPassword = :securityPassword and e.isDeleted = :isDeleted")
	Compte authenticateAccount(@Param("securityToken")String securityToken, @Param("securityPassword")String securityPassword, @Param("isDeleted") Boolean isDeleted);

	/**
	 * Finds Compte by using userCompte as a search criteria.
	 * 
	 * @param userCompte
	 * @return An Object Compte whose userCompte is equals to the given userCompte. If
	 *         no Compte is found, this method returns null.
	 */
	@Query("select e from Compte e where e.userCompte = :userCompte and e.isDeleted = :isDeleted")
	List<Compte> findByUserCompte(@Param("userCompte")String userCompte, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Compte by using securityPassword as a search criteria.
	 * 
	 * @param securityPassword
	 * @return An Object Compte whose securityPassword is equals to the given securityPassword. If
	 *         no Compte is found, this method returns null.
	 */
	@Query("select e from Compte e where e.securityPassword = :securityPassword and e.isDeleted = :isDeleted")
	List<Compte> findBySecurityPassword(@Param("securityPassword")String securityPassword, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Compte by using isConnected as a search criteria.
	 * 
	 * @param isConnected
	 * @return An Object Compte whose isConnected is equals to the given isConnected. If
	 *         no Compte is found, this method returns null.
	 */
	@Query("select e from Compte e where e.isConnected = :isConnected and e.isDeleted = :isDeleted")
	List<Compte> findByIsConnected(@Param("isConnected")Boolean isConnected, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Compte by using isBlocked as a search criteria.
	 * 
	 * @param isBlocked
	 * @return An Object Compte whose isBlocked is equals to the given isBlocked. If
	 *         no Compte is found, this method returns null.
	 */
	@Query("select e from Compte e where e.isBlocked = :isBlocked and e.isDeleted = :isDeleted")
	List<Compte> findByIsBlocked(@Param("isBlocked")Boolean isBlocked, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Compte by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Compte whose createdAt is equals to the given createdAt. If
	 *         no Compte is found, this method returns null.
	 */
	@Query("select e from Compte e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Compte> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Compte by using securityToken as a search criteria.
	 * 
	 * @param securityToken
	 * @return An Object Compte whose securityToken is equals to the given securityToken. If
	 *         no Compte is found, this method returns null.
	 */
	@Query("select e from Compte e where e.securityToken = :securityToken and e.isDeleted = :isDeleted")
	List<Compte> findBySecurityToken(@Param("securityToken")String securityToken, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Compte by using salt as a search criteria.
	 * 
	 * @param salt
	 * @return An Object Compte whose salt is equals to the given salt. If
	 *         no Compte is found, this method returns null.
	 */
	@Query("select e from Compte e where e.salt = :salt and e.isDeleted = :isDeleted")
	List<Compte> findBySalt(@Param("salt")String salt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Compte by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Compte whose updatedAt is equals to the given updatedAt. If
	 *         no Compte is found, this method returns null.
	 */
	@Query("select e from Compte e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Compte> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Compte by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Compte whose createdBy is equals to the given createdBy. If
	 *         no Compte is found, this method returns null.
	 */
	@Query("select e from Compte e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Compte> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Compte by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Compte whose updatedBy is equals to the given updatedBy. If
	 *         no Compte is found, this method returns null.
	 */
	@Query("select e from Compte e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Compte> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Compte by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Compte whose isDeleted is equals to the given isDeleted. If
	 *         no Compte is found, this method returns null.
	 */
	@Query("select e from Compte e where e.isDeleted = :isDeleted")
	List<Compte> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Compte by using secretKey as a search criteria.
	 * 
	 * @param secretKey
	 * @return An Object Compte whose secretKey is equals to the given secretKey. If
	 *         no Compte is found, this method returns null.
	 */
	@Query("select e from Compte e where e.secretKey = :secretKey and e.isDeleted = :isDeleted")
	List<Compte> findBySecretKey(@Param("secretKey")String secretKey, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Compte by using compteDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Compte
	 * @throws DataAccessException,ParseException
	 */
	default List<Compte> getByCriteria(Request<CompteDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Compte e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.idCompte desc";
		TypedQuery<Compte> query = em.createQuery(req, Compte.class);
		for (Map.Entry<String, java.lang.Object> entry : param.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		if (request.getIndex() != null && request.getSize() != null) {
			query.setFirstResult(request.getIndex() * request.getSize());
			query.setMaxResults(request.getSize());
		}
		return query.getResultList();
	}

	/**
	 * Finds count of Compte by using compteDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Compte
	 * 
	 */
	default Long count(Request<CompteDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Compte e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by  e.idCompte desc";
		javax.persistence.Query query = em.createQuery(req);
		for (Map.Entry<String, java.lang.Object> entry : param.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		Long count = (Long) query.getResultList().get(0);
		return count;
	}

	/**
	 * get where expression
	 * @param request
	 * @param param
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	default String getWhereExpression(Request<CompteDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		CompteDto dto = request.getData() != null ? request.getData() : new CompteDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (CompteDto elt : request.getDatas()) {
				elt.setIsDeleted(false);
				String eltReq = generateCriteria(elt, param, index, locale);
				if (request.getIsAnd() != null && request.getIsAnd()) {
					othersReq += "and (" + eltReq + ") ";
				} else {
					othersReq += "or (" + eltReq + ") ";
				}
				index++;
			}
		}
		String req = "";
		if (!mainReq.isEmpty()) {
			req += " and (" + mainReq + ") ";
		}
		req += othersReq;
		return req;
	}

	/**
	 * generate sql query for dto
	 * @param dto
	 * @param param
	 * @param index
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	default String generateCriteria(CompteDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getIdCompte()!= null && dto.getIdCompte() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("idCompte", dto.getIdCompte(), "e.idCompte", "Integer", dto.getIdCompteParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUserCompte())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("userCompte", dto.getUserCompte(), "e.userCompte", "String", dto.getUserCompteParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSecurityPassword())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("securityPassword", dto.getSecurityPassword(), "e.securityPassword", "String", dto.getSecurityPasswordParam(), param, index, locale));
			}
			if (dto.getIsConnected()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isConnected", dto.getIsConnected(), "e.isConnected", "Boolean", dto.getIsConnectedParam(), param, index, locale));
			}
			if (dto.getIsBlocked()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isBlocked", dto.getIsBlocked(), "e.isBlocked", "Boolean", dto.getIsBlockedParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCreatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSecurityToken())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("securityToken", dto.getSecurityToken(), "e.securityToken", "String", dto.getSecurityTokenParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSalt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("salt", dto.getSalt(), "e.salt", "String", dto.getSaltParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUpdatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
			}
			if (dto.getCreatedBy()!= null && dto.getCreatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdBy", dto.getCreatedBy(), "e.createdBy", "Integer", dto.getCreatedByParam(), param, index, locale));
			}
			if (dto.getUpdatedBy()!= null && dto.getUpdatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedBy", dto.getUpdatedBy(), "e.updatedBy", "Integer", dto.getUpdatedByParam(), param, index, locale));
			}
			if (dto.getIsDeleted()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getSecretKey())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("secretKey", dto.getSecretKey(), "e.secretKey", "String", dto.getSecretKeyParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
