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

import ci.smile.system.manager.obf.dao.entity.Menus;
import ci.smile.system.manager.obf.dao.repository.customize._MenusRepository;
import ci.smile.system.manager.obf.utils.CriteriaUtils;
import ci.smile.system.manager.obf.utils.Utilities;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.dto.MenusDto;

/**
 * Repository : Menus.
 */
@Repository
public interface MenusRepository extends JpaRepository<Menus, Integer>, _MenusRepository {
	/**
	 * Finds Menus by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Menus whose id is equals to the given id. If
	 *         no Menus is found, this method returns null.
	 */
	@Query("select e from Menus e where e.id = :id and e.isDeleted = :isDeleted")
	Menus findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Menus by using libelle as a search criteria.
	 * 
	 * @param libelle
	 * @return An Object Menus whose libelle is equals to the given libelle. If
	 *         no Menus is found, this method returns null.
	 */
	@Query("select e from Menus e where e.libelle = :libelle and e.isDeleted = :isDeleted")
	Menus findByLibelle(@Param("libelle")String libelle, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Menus by using isLocked as a search criteria.
	 * 
	 * @param isLocked
	 * @return An Object Menus whose isLocked is equals to the given isLocked. If
	 *         no Menus is found, this method returns null.
	 */
	@Query("select e from Menus e where e.isLocked = :isLocked and e.isDeleted = :isDeleted")
	List<Menus> findByIsLocked(@Param("isLocked")Boolean isLocked, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Menus by using code as a search criteria.
	 * 
	 * @param code
	 * @return An Object Menus whose code is equals to the given code. If
	 *         no Menus is found, this method returns null.
	 */
	@Query("select e from Menus e where e.code = :code and e.isDeleted = :isDeleted")
	Menus findByCode(@Param("code")String code, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Menus by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Menus whose createdAt is equals to the given createdAt. If
	 *         no Menus is found, this method returns null.
	 */
	@Query("select e from Menus e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Menus> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Menus by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Menus whose updatedAt is equals to the given updatedAt. If
	 *         no Menus is found, this method returns null.
	 */
	@Query("select e from Menus e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Menus> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Menus by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Menus whose deletedAt is equals to the given deletedAt. If
	 *         no Menus is found, this method returns null.
	 */
	@Query("select e from Menus e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Menus> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Menus by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Menus whose createdBy is equals to the given createdBy. If
	 *         no Menus is found, this method returns null.
	 */
	@Query("select e from Menus e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Menus> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Menus by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Menus whose updatedBy is equals to the given updatedBy. If
	 *         no Menus is found, this method returns null.
	 */
	@Query("select e from Menus e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Menus> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Menus by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Menus whose deletedBy is equals to the given deletedBy. If
	 *         no Menus is found, this method returns null.
	 */
	@Query("select e from Menus e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Menus> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Menus by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Menus whose isDeleted is equals to the given isDeleted. If
	 *         no Menus is found, this method returns null.
	 */
	@Query("select e from Menus e where e.isDeleted = :isDeleted")
	List<Menus> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Menus by using parentId as a search criteria.
	 * 
	 * @param parentId
	 * @return An Object Menus whose parentId is equals to the given parentId. If
	 *         no Menus is found, this method returns null.
	 */
	@Query("select e from Menus e where e.parent.id = :parentId and e.isDeleted = :isDeleted")
	List<Menus> findByParentId(@Param("parentId")Integer parentId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Menus by using moduleId as a search criteria.
	 * 
	 * @param moduleId
	 * @return An Object Menus whose moduleId is equals to the given moduleId. If
	 *         no Menus is found, this method returns null.
	 */
	@Query("select e from Menus e where e.module.id = :moduleId and e.isDeleted = :isDeleted")
	List<Menus> findByModuleId(@Param("moduleId")Integer moduleId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Menus by using menusDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Menus
	 * @throws DataAccessException,ParseException
	 */
	default List<Menus> getByCriteria(Request<MenusDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Menus e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Menus> query = em.createQuery(req, Menus.class);
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
	 * Finds count of Menus by using menusDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Menus
	 * 
	 */
	default Long count(Request<MenusDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Menus e where e IS NOT NULL";
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
	default String getWhereExpression(Request<MenusDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		MenusDto dto = request.getData() != null ? request.getData() : new MenusDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (MenusDto elt : request.getDatas()) {
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
	default String generateCriteria(MenusDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("libelle", dto.getLibelle(), "e.libelle", "String", dto.getLibelleParam(), param, index, locale));
			}
			if (dto.getIsLocked()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isLocked", dto.getIsLocked(), "e.isLocked", "Boolean", dto.getIsLockedParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("code", dto.getCode(), "e.code", "String", dto.getCodeParam(), param, index, locale));
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
			if (dto.getParentMenusId()!= null && dto.getParentMenusId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("parentMenusId", dto.getParentMenusId(), "e.parent.id", "Integer", dto.getParentMenusIdParam(), param, index, locale));
			}
			if (dto.getModuleId()!= null && dto.getModuleId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("moduleId", dto.getModuleId(), "e.module.id", "Integer", dto.getModuleIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getParentMenusLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("parentMenusLibelle", dto.getParentMenusLibelle(), "e.parent.libelle", "String", dto.getParentMenusLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getParentMenusCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("parentMenusCode", dto.getParentMenusCode(), "e.parent.code", "String", dto.getParentMenusCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getModuleLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("moduleLibelle", dto.getModuleLibelle(), "e.module.libelle", "String", dto.getModuleLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getModuleCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("moduleCode", dto.getModuleCode(), "e.module.code", "String", dto.getModuleCodeParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
