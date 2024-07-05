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
import ci.smile.system.manager.obf.dao.repository.customize._OfferOltRepository;

/**
 * Repository : OfferOlt.
 */
@Repository
public interface OfferOltRepository extends JpaRepository<OfferOlt, Integer>, _OfferOltRepository {
	/**
	 * Finds OfferOlt by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object OfferOlt whose id is equals to the given id. If
	 *         no OfferOlt is found, this method returns null.
	 */
	@Query("select e from OfferOlt e where e.id = :id and e.isDeleted = :isDeleted")
	OfferOlt findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds OfferOlt by using rx as a search criteria.
	 * 
	 * @param rx
	 * @return An Object OfferOlt whose rx is equals to the given rx. If
	 *         no OfferOlt is found, this method returns null.
	 */
	@Query("select e from OfferOlt e where e.rx = :rx and e.isDeleted = :isDeleted")
	List<OfferOlt> findByRx(@Param("rx")String rx, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds OfferOlt by using tx as a search criteria.
	 * 
	 * @param tx
	 * @return An Object OfferOlt whose tx is equals to the given tx. If
	 *         no OfferOlt is found, this method returns null.
	 */
	@Query("select e from OfferOlt e where e.tx = :tx and e.isDeleted = :isDeleted")
	List<OfferOlt> findByTx(@Param("tx")String tx, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds OfferOlt by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object OfferOlt whose createdAt is equals to the given createdAt. If
	 *         no OfferOlt is found, this method returns null.
	 */
	@Query("select e from OfferOlt e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<OfferOlt> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds OfferOlt by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object OfferOlt whose deletedAt is equals to the given deletedAt. If
	 *         no OfferOlt is found, this method returns null.
	 */
	@Query("select e from OfferOlt e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<OfferOlt> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds OfferOlt by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object OfferOlt whose isDeleted is equals to the given isDeleted. If
	 *         no OfferOlt is found, this method returns null.
	 */
	@Query("select e from OfferOlt e where e.isDeleted = :isDeleted")
	List<OfferOlt> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds OfferOlt by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object OfferOlt whose deletedBy is equals to the given deletedBy. If
	 *         no OfferOlt is found, this method returns null.
	 */
	@Query("select e from OfferOlt e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<OfferOlt> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds OfferOlt by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object OfferOlt whose updatedAt is equals to the given updatedAt. If
	 *         no OfferOlt is found, this method returns null.
	 */
	@Query("select e from OfferOlt e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<OfferOlt> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds OfferOlt by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object OfferOlt whose updatedBy is equals to the given updatedBy. If
	 *         no OfferOlt is found, this method returns null.
	 */
	@Query("select e from OfferOlt e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<OfferOlt> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds OfferOlt by using template as a search criteria.
	 * 
	 * @param template
	 * @return An Object OfferOlt whose template is equals to the given template. If
	 *         no OfferOlt is found, this method returns null.
	 */
	@Query("select e from OfferOlt e where e.template = :template and e.isDeleted = :isDeleted")
	List<OfferOlt> findByTemplate(@Param("template")String template, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of OfferOlt by using offerOltDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of OfferOlt
	 * @throws DataAccessException,ParseException
	 */
	default List<OfferOlt> getByCriteria(Request<OfferOltDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from OfferOlt e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<OfferOlt> query = em.createQuery(req, OfferOlt.class);
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
	 * Finds count of OfferOlt by using offerOltDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of OfferOlt
	 * 
	 */
	default Long count(Request<OfferOltDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from OfferOlt e where e IS NOT NULL";
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
	default String getWhereExpression(Request<OfferOltDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		OfferOltDto dto = request.getData() != null ? request.getData() : new OfferOltDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (OfferOltDto elt : request.getDatas()) {
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
	default String generateCriteria(OfferOltDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getRx())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("rx", dto.getRx(), "e.rx", "String", dto.getRxParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTx())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("tx", dto.getTx(), "e.tx", "String", dto.getTxParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCreatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDeletedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("deletedAt", dto.getDeletedAt(), "e.deletedAt", "Date", dto.getDeletedAtParam(), param, index, locale));
			}
			if (dto.getIsDeleted()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
			}
			if (dto.getDeletedBy()!= null && dto.getDeletedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("deletedBy", dto.getDeletedBy(), "e.deletedBy", "Integer", dto.getDeletedByParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUpdatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
			}
			if (dto.getUpdatedBy()!= null && dto.getUpdatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedBy", dto.getUpdatedBy(), "e.updatedBy", "Integer", dto.getUpdatedByParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTemplate())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("template", dto.getTemplate(), "e.template", "String", dto.getTemplateParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
