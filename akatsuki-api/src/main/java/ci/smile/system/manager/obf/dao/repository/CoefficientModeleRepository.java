package ci.smile.system.manager.obf.dao.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ci.smile.system.manager.obf.dao.entity.CoefficientModele;
import ci.smile.system.manager.obf.dao.repository.customize._CoefficientModeleRepository;
import ci.smile.system.manager.obf.utils.CriteriaUtils;
import ci.smile.system.manager.obf.utils.Utilities;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.dto.CoefficientModeleDto;

/**
 * Repository : CoefficientModele.
 */
@Repository
public interface CoefficientModeleRepository extends JpaRepository<CoefficientModele, Integer>, _CoefficientModeleRepository {
	/**
	 * Finds CoefficientModele by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object CoefficientModele whose id is equals to the given id. If
	 *         no CoefficientModele is found, this method returns null.
	 */
	@Query("select e from CoefficientModele e where e.id = :id and e.isDeleted = :isDeleted")
	CoefficientModele findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds CoefficientModele by using coefMac as a search criteria.
	 * 
	 * @param coefMac
	 * @return An Object CoefficientModele whose coefMac is equals to the given coefMac. If
	 *         no CoefficientModele is found, this method returns null.
	 */
	@Query("select e from CoefficientModele e where e.coefMac = :coefMac and e.isDeleted = :isDeleted")
	List<CoefficientModele> findByCoefMac(@Param("coefMac")Integer coefMac, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds CoefficientModele by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object CoefficientModele whose createdAt is equals to the given createdAt. If
	 *         no CoefficientModele is found, this method returns null.
	 */
	@Query("select e from CoefficientModele e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<CoefficientModele> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds CoefficientModele by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object CoefficientModele whose updatedAt is equals to the given updatedAt. If
	 *         no CoefficientModele is found, this method returns null.
	 */
	@Query("select e from CoefficientModele e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<CoefficientModele> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds CoefficientModele by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object CoefficientModele whose deletedAt is equals to the given deletedAt. If
	 *         no CoefficientModele is found, this method returns null.
	 */
	@Query("select e from CoefficientModele e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<CoefficientModele> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds CoefficientModele by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object CoefficientModele whose createdBy is equals to the given createdBy. If
	 *         no CoefficientModele is found, this method returns null.
	 */
	@Query("select e from CoefficientModele e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<CoefficientModele> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds CoefficientModele by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object CoefficientModele whose updatedBy is equals to the given updatedBy. If
	 *         no CoefficientModele is found, this method returns null.
	 */
	@Query("select e from CoefficientModele e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<CoefficientModele> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds CoefficientModele by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object CoefficientModele whose deletedBy is equals to the given deletedBy. If
	 *         no CoefficientModele is found, this method returns null.
	 */
	@Query("select e from CoefficientModele e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<CoefficientModele> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds CoefficientModele by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object CoefficientModele whose isDeleted is equals to the given isDeleted. If
	 *         no CoefficientModele is found, this method returns null.
	 */
	@Query("select e from CoefficientModele e where e.isDeleted = :isDeleted")
	List<CoefficientModele> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds CoefficientModele by using modeleId as a search criteria.
	 * 
	 * @param modeleId
	 * @return An Object CoefficientModele whose modeleId is equals to the given modeleId. If
	 *         no CoefficientModele is found, this method returns null.
	 */
	@Query("select e from CoefficientModele e where e.modele.id = :modeleId and e.isDeleted = :isDeleted")
	List<CoefficientModele> findByModeleId(@Param("modeleId")Integer modeleId, @Param("isDeleted")Boolean isDeleted);
	
	
	/**
	 * Finds CoefficientModele by using modeleId as a search criteria.
	 * 
	 * @param modeleId
	 * @return An Object CoefficientModele whose modeleId is equals to the given modeleId. If
	 *         no CoefficientModele is found, this method returns null.
	 */
	@Query("select e from CoefficientModele e where e.modele.id = :modeleId and e.isDeleted = :isDeleted")
	CoefficientModele getByModeleId(@Param("modeleId")Integer modeleId, @Param("isDeleted")Boolean isDeleted);


	/**
	 * Finds CoefficientModele by using typeOffreId as a search criteria.
	 * 
	 * @param typeOffreId
	 * @return An Object CoefficientModele whose typeOffreId is equals to the given typeOffreId. If
	 *         no CoefficientModele is found, this method returns null.
	 */
	@Query("select e from CoefficientModele e where e.typeOffre.id = :typeOffreId and e.isDeleted = :isDeleted")
	List<CoefficientModele> findByTypeOffreId(@Param("typeOffreId")Integer typeOffreId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of CoefficientModele by using coefficientModeleDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of CoefficientModele
	 * @throws DataAccessException,ParseException
	 */
	default List<CoefficientModele> getByCriteria(Request<CoefficientModeleDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from CoefficientModele e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<CoefficientModele> query = em.createQuery(req, CoefficientModele.class);
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
	 * Finds count of CoefficientModele by using coefficientModeleDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of CoefficientModele
	 * 
	 */
	default Long count(Request<CoefficientModeleDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from CoefficientModele e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by  e.id desc";
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
	default String getWhereExpression(Request<CoefficientModeleDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		CoefficientModeleDto dto = request.getData() != null ? request.getData() : new CoefficientModeleDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (CoefficientModeleDto elt : request.getDatas()) {
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
	default String generateCriteria(CoefficientModeleDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (dto.getCoefMac()!= null && dto.getCoefMac() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("coefMac", dto.getCoefMac(), "e.coefMac", "Integer", dto.getCoefMacParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCreatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUpdatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDeletedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("deletedAt", dto.getDeletedAt(), "e.deletedAt", "Date", dto.getDeletedAtParam(), param, index, locale));
			}
			if (dto.getCreatedBy()!= null && dto.getCreatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdBy", dto.getCreatedBy(), "e.createdBy", "Integer", dto.getCreatedByParam(), param, index, locale));
			}
			if (dto.getUpdatedBy()!= null && dto.getUpdatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedBy", dto.getUpdatedBy(), "e.updatedBy", "Integer", dto.getUpdatedByParam(), param, index, locale));
			}
			if (dto.getDeletedBy()!= null && dto.getDeletedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("deletedBy", dto.getDeletedBy(), "e.deletedBy", "Integer", dto.getDeletedByParam(), param, index, locale));
			}
			if (dto.getIsDeleted()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
			}
			if (dto.getModeleId()!= null && dto.getModeleId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("modeleId", dto.getModeleId(), "e.modele.id", "Integer", dto.getModeleIdParam(), param, index, locale));
			}
			if (dto.getTypeOffreId()!= null && dto.getTypeOffreId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("typeOffreId", dto.getTypeOffreId(), "e.typeOffre.id", "Integer", dto.getTypeOffreIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getModeleCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("modeleCode", dto.getModeleCode(), "e.modele.code", "String", dto.getModeleCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getModeleLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("modeleLibelle", dto.getModeleLibelle(), "e.modele.libelle", "String", dto.getModeleLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTypeOffreCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("typeOffreCode", dto.getTypeOffreCode(), "e.typeOffre.code", "String", dto.getTypeOffreCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTypeOffreLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("typeOffreLibelle", dto.getTypeOffreLibelle(), "e.typeOffre.libelle", "String", dto.getTypeOffreLibelleParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
