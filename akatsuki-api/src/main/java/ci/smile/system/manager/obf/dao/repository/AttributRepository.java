package ci.smile.system.manager.obf.dao.repository;

import java.util.Date;
import java.util.List;
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
import ci.smile.system.manager.obf.dao.repository.customize._AttributRepository;

/**
 * Repository : Attribut.
 */
@Repository
public interface AttributRepository extends JpaRepository<Attribut, Integer>, _AttributRepository {
	/**
	 * Finds Attribut by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Attribut whose id is equals to the given id. If
	 *         no Attribut is found, this method returns null.
	 */
	@Query("select e from Attribut e where e.id = :id and e.isDeleted = :isDeleted")
	Attribut findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Attribut by using attribut as a search criteria.
	 * 
	 * @param attribut
	 * @return An Object Attribut whose attribut is equals to the given attribut. If
	 *         no Attribut is found, this method returns null.
	 */
	@Query("select e from Attribut e where e.attribut = :attribut and e.isDeleted = :isDeleted")
	List<Attribut> findByAttribut(@Param("attribut")String attribut, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Attribut by using op as a search criteria.
	 * 
	 * @param op
	 * @return An Object Attribut whose op is equals to the given op. If
	 *         no Attribut is found, this method returns null.
	 */
	@Query("select e from Attribut e where e.op = :op and e.isDeleted = :isDeleted")
	List<Attribut> findByOp(@Param("op")String op, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Attribut by using value as a search criteria.
	 * 
	 * @param value
	 * @return An Object Attribut whose value is equals to the given value. If
	 *         no Attribut is found, this method returns null.
	 */
	@Query("select e from Attribut e where e.value = :value and e.isDeleted = :isDeleted")
	List<Attribut> findByValue(@Param("value")String value, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Attribut by using type as a search criteria.
	 * 
	 * @param type
	 * @return An Object Attribut whose type is equals to the given type. If
	 *         no Attribut is found, this method returns null.
	 */
	@Query("select e from Attribut e where e.type = :type and e.isDeleted = :isDeleted")
	List<Attribut> findByType(@Param("type")String type, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Attribut by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Attribut whose createdAt is equals to the given createdAt. If
	 *         no Attribut is found, this method returns null.
	 */
	@Query("select e from Attribut e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Attribut> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Attribut by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Attribut whose updatedAt is equals to the given updatedAt. If
	 *         no Attribut is found, this method returns null.
	 */
	@Query("select e from Attribut e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Attribut> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Attribut by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Attribut whose deletedAt is equals to the given deletedAt. If
	 *         no Attribut is found, this method returns null.
	 */
	@Query("select e from Attribut e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Attribut> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Attribut by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Attribut whose createdBy is equals to the given createdBy. If
	 *         no Attribut is found, this method returns null.
	 */
	@Query("select e from Attribut e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Attribut> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Attribut by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Attribut whose updatedBy is equals to the given updatedBy. If
	 *         no Attribut is found, this method returns null.
	 */
	@Query("select e from Attribut e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Attribut> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Attribut by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Attribut whose deletedBy is equals to the given deletedBy. If
	 *         no Attribut is found, this method returns null.
	 */
	@Query("select e from Attribut e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Attribut> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Attribut by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Attribut whose isDeleted is equals to the given isDeleted. If
	 *         no Attribut is found, this method returns null.
	 */
	@Query("select e from Attribut e where e.isDeleted = :isDeleted")
	List<Attribut> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Attribut by using attributDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Attribut
	 * @throws DataAccessException,ParseException
	 */
	default List<Attribut> getByCriteria(Request<AttributDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Attribut e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Attribut> query = em.createQuery(req, Attribut.class);
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
	 * Finds count of Attribut by using attributDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Attribut
	 * 
	 */
	default Long count(Request<AttributDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Attribut e where e IS NOT NULL";
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
	default String getWhereExpression(Request<AttributDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		AttributDto dto = request.getData() != null ? request.getData() : new AttributDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (AttributDto elt : request.getDatas()) {
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
	default String generateCriteria(AttributDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getAttribut())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("attribut", dto.getAttribut(), "e.attribut", "String", dto.getAttributParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getOp())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("op", dto.getOp(), "e.op", "String", dto.getOpParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getValue())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("value", dto.getValue(), "e.value", "String", dto.getValueParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getType())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("type", dto.getType(), "e.type", "String", dto.getTypeParam(), param, index, locale));
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
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
