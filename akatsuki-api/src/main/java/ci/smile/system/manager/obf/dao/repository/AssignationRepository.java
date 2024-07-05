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
import ci.smile.system.manager.obf.dao.repository.customize._AssignationRepository;

/**
 * Repository : Assignation.
 */
@Repository
public interface AssignationRepository extends JpaRepository<Assignation, Integer>, _AssignationRepository {
	/**
	 * Finds Assignation by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Assignation whose id is equals to the given id. If
	 *         no Assignation is found, this method returns null.
	 */
	@Query("select e from Assignation e where e.id = :id and e.isDeleted = :isDeleted")
	Assignation findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Assignation by using libelle as a search criteria.
	 * 
	 * @param libelle
	 * @return An Object Assignation whose libelle is equals to the given libelle. If
	 *         no Assignation is found, this method returns null.
	 */
	@Query("select e from Assignation e where e.libelle = :libelle and e.isDeleted = :isDeleted")
	Assignation findByLibelle(@Param("libelle")String libelle, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Assignation by using code as a search criteria.
	 * 
	 * @param code
	 * @return An Object Assignation whose code is equals to the given code. If
	 *         no Assignation is found, this method returns null.
	 */
	@Query("select e from Assignation e where e.code = :code and e.isDeleted = :isDeleted")
	Assignation findByCode(@Param("code")String code, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Assignation by using qualificatif2 as a search criteria.
	 * 
	 * @param qualificatif2
	 * @return An Object Assignation whose qualificatif2 is equals to the given qualificatif2. If
	 *         no Assignation is found, this method returns null.
	 */
	@Query("select e from Assignation e where e.qualificatif2 = :qualificatif2 and e.isDeleted = :isDeleted")
	List<Assignation> findByQualificatif2(@Param("qualificatif2")String qualificatif2, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Assignation by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Assignation whose createdAt is equals to the given createdAt. If
	 *         no Assignation is found, this method returns null.
	 */
	@Query("select e from Assignation e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Assignation> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Assignation by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Assignation whose updatedAt is equals to the given updatedAt. If
	 *         no Assignation is found, this method returns null.
	 */
	@Query("select e from Assignation e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Assignation> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Assignation by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Assignation whose deletedAt is equals to the given deletedAt. If
	 *         no Assignation is found, this method returns null.
	 */
	@Query("select e from Assignation e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Assignation> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Assignation by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Assignation whose createdBy is equals to the given createdBy. If
	 *         no Assignation is found, this method returns null.
	 */
	@Query("select e from Assignation e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Assignation> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Assignation by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Assignation whose updatedBy is equals to the given updatedBy. If
	 *         no Assignation is found, this method returns null.
	 */
	@Query("select e from Assignation e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Assignation> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Assignation by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Assignation whose deletedBy is equals to the given deletedBy. If
	 *         no Assignation is found, this method returns null.
	 */
	@Query("select e from Assignation e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Assignation> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Assignation by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Assignation whose isDeleted is equals to the given isDeleted. If
	 *         no Assignation is found, this method returns null.
	 */
	@Query("select e from Assignation e where e.isDeleted = :isDeleted")
	List<Assignation> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Assignation by using assignation as a search criteria.
	 * 
	 * @param assignation
	 * @return An Object Assignation whose assignation is equals to the given assignation. If
	 *         no Assignation is found, this method returns null.
	 */
	@Query("select e from Assignation e where e.assignation = :assignation and e.isDeleted = :isDeleted")
	List<Assignation> findByAssignation(@Param("assignation")String assignation, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Assignation by using technologie as a search criteria.
	 * 
	 * @param technologie
	 * @return An Object Assignation whose technologie is equals to the given technologie. If
	 *         no Assignation is found, this method returns null.
	 */
	@Query("select e from Assignation e where e.technologie = :technologie and e.isDeleted = :isDeleted")
	List<Assignation> findByTechnologie(@Param("technologie")String technologie, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Assignation by using qualificatifId as a search criteria.
	 * 
	 * @param qualificatifId
	 * @return An Object Assignation whose qualificatifId is equals to the given qualificatifId. If
	 *         no Assignation is found, this method returns null.
	 */
	@Query("select e from Assignation e where e.qualificatif.id = :qualificatifId and e.isDeleted = :isDeleted")
	List<Assignation> findByQualificatifId(@Param("qualificatifId")Integer qualificatifId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Assignation by using assignationDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Assignation
	 * @throws DataAccessException,ParseException
	 */
	default List<Assignation> getByCriteria(Request<AssignationDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Assignation e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Assignation> query = em.createQuery(req, Assignation.class);
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
	 * Finds count of Assignation by using assignationDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Assignation
	 * 
	 */
	default Long count(Request<AssignationDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Assignation e where e IS NOT NULL";
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
	default String getWhereExpression(Request<AssignationDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		AssignationDto dto = request.getData() != null ? request.getData() : new AssignationDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (AssignationDto elt : request.getDatas()) {
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
	default String generateCriteria(AssignationDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("libelle", dto.getLibelle(), "e.libelle", "String", dto.getLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("code", dto.getCode(), "e.code", "String", dto.getCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getQualificatif2())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("qualificatif2", dto.getQualificatif2(), "e.qualificatif2", "String", dto.getQualificatif2Param(), param, index, locale));
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
			if (Utilities.notBlank(dto.getAssignation())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("assignation", dto.getAssignation(), "e.assignation", "String", dto.getAssignationParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTechnologie())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("technologie", dto.getTechnologie(), "e.technologie", "String", dto.getTechnologieParam(), param, index, locale));
			}
			if (dto.getQualificatifId()!= null && dto.getQualificatifId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("qualificatifId", dto.getQualificatifId(), "e.qualificatif.id", "Integer", dto.getQualificatifIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getQualificatifLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("qualificatifLibelle", dto.getQualificatifLibelle(), "e.qualificatif.libelle", "String", dto.getQualificatifLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getQualificatifCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("qualificatifCode", dto.getQualificatifCode(), "e.qualificatif.code", "String", dto.getQualificatifCodeParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
