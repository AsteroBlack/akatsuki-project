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

import ci.smile.system.manager.obf.dao.entity.TypeOffre;
import ci.smile.system.manager.obf.dao.repository.customize._TypeOffreRepository;
import ci.smile.system.manager.obf.utils.CriteriaUtils;
import ci.smile.system.manager.obf.utils.Utilities;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.dto.TypeOffreDto;

/**
 * Repository : TypeOffre.
 */
@Repository
public interface TypeOffreRepository extends JpaRepository<TypeOffre, Integer>, _TypeOffreRepository {
	/**
	 * Finds TypeOffre by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object TypeOffre whose id is equals to the given id. If
	 *         no TypeOffre is found, this method returns null.
	 */
	@Query("select e from TypeOffre e where e.id = :id and e.isDeleted = :isDeleted")
	TypeOffre findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds TypeOffre by using code as a search criteria.
	 * 
	 * @param code
	 * @return An Object TypeOffre whose code is equals to the given code. If
	 *         no TypeOffre is found, this method returns null.
	 */
	@Query("select e from TypeOffre e where e.code = :code and e.isDeleted = :isDeleted")
	TypeOffre findByCode(@Param("code")String code, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TypeOffre by using libelle as a search criteria.
	 * 
	 * @param libelle
	 * @return An Object TypeOffre whose libelle is equals to the given libelle. If
	 *         no TypeOffre is found, this method returns null.
	 */
	@Query("select e from TypeOffre e where e.libelle = :libelle and e.isDeleted = :isDeleted")
	TypeOffre findByLibelle(@Param("libelle")String libelle, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TypeOffre by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object TypeOffre whose createdAt is equals to the given createdAt. If
	 *         no TypeOffre is found, this method returns null.
	 */
	@Query("select e from TypeOffre e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<TypeOffre> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TypeOffre by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object TypeOffre whose updatedAt is equals to the given updatedAt. If
	 *         no TypeOffre is found, this method returns null.
	 */
	@Query("select e from TypeOffre e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<TypeOffre> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TypeOffre by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object TypeOffre whose deletedAt is equals to the given deletedAt. If
	 *         no TypeOffre is found, this method returns null.
	 */
	@Query("select e from TypeOffre e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<TypeOffre> findByDeletedAt(@Param("deletedAt")String deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TypeOffre by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object TypeOffre whose createdBy is equals to the given createdBy. If
	 *         no TypeOffre is found, this method returns null.
	 */
	@Query("select e from TypeOffre e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<TypeOffre> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TypeOffre by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object TypeOffre whose updatedBy is equals to the given updatedBy. If
	 *         no TypeOffre is found, this method returns null.
	 */
	@Query("select e from TypeOffre e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<TypeOffre> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TypeOffre by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object TypeOffre whose deletedBy is equals to the given deletedBy. If
	 *         no TypeOffre is found, this method returns null.
	 */
	@Query("select e from TypeOffre e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<TypeOffre> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds TypeOffre by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object TypeOffre whose isDeleted is equals to the given isDeleted. If
	 *         no TypeOffre is found, this method returns null.
	 */
	@Query("select e from TypeOffre e where e.isDeleted = :isDeleted")
	List<TypeOffre> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of TypeOffre by using typeOffreDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of TypeOffre
	 * @throws DataAccessException,ParseException
	 */
	default List<TypeOffre> getByCriteria(Request<TypeOffreDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from TypeOffre e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<TypeOffre> query = em.createQuery(req, TypeOffre.class);
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
	 * Finds count of TypeOffre by using typeOffreDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of TypeOffre
	 * 
	 */
	default Long count(Request<TypeOffreDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from TypeOffre e where e IS NOT NULL";
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
	default String getWhereExpression(Request<TypeOffreDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		TypeOffreDto dto = request.getData() != null ? request.getData() : new TypeOffreDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (TypeOffreDto elt : request.getDatas()) {
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
	default String generateCriteria(TypeOffreDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("code", dto.getCode(), "e.code", "String", dto.getCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("libelle", dto.getLibelle(), "e.libelle", "String", dto.getLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCreatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUpdatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDeletedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("deletedAt", dto.getDeletedAt(), "e.deletedAt", "String", dto.getDeletedAtParam(), param, index, locale));
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
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
