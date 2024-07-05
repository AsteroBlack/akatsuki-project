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
import ci.smile.system.manager.obf.dao.repository.customize._OfferRepository;

/**
 * Repository : Offer.
 */
@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer>, _OfferRepository {
	/**
	 * Finds Offer by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Offer whose id is equals to the given id. If
	 *         no Offer is found, this method returns null.
	 */
	@Query("select e from Offer e where e.id = :id and e.isDeleted = :isDeleted")
	Offer findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Offer by using codeOffer as a search criteria.
	 * 
	 * @param codeOffer
	 * @return An Object Offer whose codeOffer is equals to the given codeOffer. If
	 *         no Offer is found, this method returns null.
	 */
	@Query("select e from Offer e where e.codeOffer = :codeOffer and e.isDeleted = :isDeleted")
	List<Offer> findByCodeOffer(@Param("codeOffer")String codeOffer, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Offer by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Offer whose createdAt is equals to the given createdAt. If
	 *         no Offer is found, this method returns null.
	 */
	@Query("select e from Offer e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Offer> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Offer by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Offer whose createdBy is equals to the given createdBy. If
	 *         no Offer is found, this method returns null.
	 */
	@Query("select e from Offer e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Offer> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Offer by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Offer whose deletedAt is equals to the given deletedAt. If
	 *         no Offer is found, this method returns null.
	 */
	@Query("select e from Offer e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Offer> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Offer by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Offer whose deletedBy is equals to the given deletedBy. If
	 *         no Offer is found, this method returns null.
	 */
	@Query("select e from Offer e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Offer> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Offer by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Offer whose isDeleted is equals to the given isDeleted. If
	 *         no Offer is found, this method returns null.
	 */
	@Query("select e from Offer e where e.isDeleted = :isDeleted")
	List<Offer> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Offer by using isSuspend as a search criteria.
	 * 
	 * @param isSuspend
	 * @return An Object Offer whose isSuspend is equals to the given isSuspend. If
	 *         no Offer is found, this method returns null.
	 */
	@Query("select e from Offer e where e.isSuspend = :isSuspend and e.isDeleted = :isDeleted")
	List<Offer> findByIsSuspend(@Param("isSuspend")Boolean isSuspend, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Offer by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Offer whose updatedAt is equals to the given updatedAt. If
	 *         no Offer is found, this method returns null.
	 */
	@Query("select e from Offer e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Offer> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Offer by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Offer whose updatedBy is equals to the given updatedBy. If
	 *         no Offer is found, this method returns null.
	 */
	@Query("select e from Offer e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Offer> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Offer by using libelle as a search criteria.
	 * 
	 * @param libelle
	 * @return An Object Offer whose libelle is equals to the given libelle. If
	 *         no Offer is found, this method returns null.
	 */
	@Query("select e from Offer e where e.libelle = :libelle and e.isDeleted = :isDeleted")
	Offer findByLibelle(@Param("libelle")String libelle, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Offer by using codeTypeClient as a search criteria.
	 * 
	 * @param codeTypeClient
	 * @return An Object Offer whose codeTypeClient is equals to the given codeTypeClient. If
	 *         no Offer is found, this method returns null.
	 */
	@Query("select e from Offer e where e.codeTypeClient = :codeTypeClient and e.isDeleted = :isDeleted")
	List<Offer> findByCodeTypeClient(@Param("codeTypeClient")String codeTypeClient, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Offer by using idTypeOffre as a search criteria.
	 * 
	 * @param idTypeOffre
	 * @return An Object Offer whose idTypeOffre is equals to the given idTypeOffre. If
	 *         no Offer is found, this method returns null.
	 */

	/**
	 * Finds List of Offer by using offerDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Offer
	 * @throws DataAccessException,ParseException
	 */
	default List<Offer> getByCriteria(Request<OfferDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Offer e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Offer> query = em.createQuery(req, Offer.class);
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
	 * Finds count of Offer by using offerDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Offer
	 * 
	 */
	default Long count(Request<OfferDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Offer e where e IS NOT NULL";
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
	default String getWhereExpression(Request<OfferDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		OfferDto dto = request.getData() != null ? request.getData() : new OfferDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (OfferDto elt : request.getDatas()) {
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
	default String generateCriteria(OfferDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCodeOffer())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("codeOffer", dto.getCodeOffer(), "e.codeOffer", "String", dto.getCodeOfferParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDescription())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("description", dto.getDescription(), "e.description", "String", dto.getDescriptionParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCreatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
			}
			if (dto.getCreatedBy()!= null && dto.getCreatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdBy", dto.getCreatedBy(), "e.createdBy", "Integer", dto.getCreatedByParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDeletedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("deletedAt", dto.getDeletedAt(), "e.deletedAt", "Date", dto.getDeletedAtParam(), param, index, locale));
			}
			if (dto.getDeletedBy()!= null && dto.getDeletedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("deletedBy", dto.getDeletedBy(), "e.deletedBy", "Integer", dto.getDeletedByParam(), param, index, locale));
			}
			if (dto.getIsDeleted()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
			}
			if (dto.getIsSuspend()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isSuspend", dto.getIsSuspend(), "e.isSuspend", "Boolean", dto.getIsSuspendParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUpdatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
			}
			if (dto.getUpdatedBy()!= null && dto.getUpdatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedBy", dto.getUpdatedBy(), "e.updatedBy", "Integer", dto.getUpdatedByParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("libelle", dto.getLibelle(), "e.libelle", "String", dto.getLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCodeTypeClient())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("codeTypeClient", dto.getCodeTypeClient(), "e.codeTypeClient", "String", dto.getCodeTypeClientParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
