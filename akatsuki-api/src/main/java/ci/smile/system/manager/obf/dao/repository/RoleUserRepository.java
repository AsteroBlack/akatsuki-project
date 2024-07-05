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

import ci.smile.system.manager.obf.dao.entity.RoleUser;
import ci.smile.system.manager.obf.dao.repository.customize._RoleUserRepository;
import ci.smile.system.manager.obf.utils.CriteriaUtils;
import ci.smile.system.manager.obf.utils.Utilities;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.dto.RoleUserDto;

/**
 * Repository : RoleUser.
 */
@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser, Integer>, _RoleUserRepository {
	/**
	 * Finds RoleUser by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object RoleUser whose id is equals to the given id. If
	 *         no RoleUser is found, this method returns null.
	 */
	@Query("select e from RoleUser e where e.id = :id and e.isDeleted = :isDeleted")
	RoleUser findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds RoleUser by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object RoleUser whose createdAt is equals to the given createdAt. If
	 *         no RoleUser is found, this method returns null.
	 */
	@Query("select e from RoleUser e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<RoleUser> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleUser by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object RoleUser whose updatedAt is equals to the given updatedAt. If
	 *         no RoleUser is found, this method returns null.
	 */
	@Query("select e from RoleUser e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<RoleUser> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleUser by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object RoleUser whose deletedAt is equals to the given deletedAt. If
	 *         no RoleUser is found, this method returns null.
	 */
	@Query("select e from RoleUser e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<RoleUser> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleUser by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object RoleUser whose createdBy is equals to the given createdBy. If
	 *         no RoleUser is found, this method returns null.
	 */
	@Query("select e from RoleUser e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<RoleUser> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleUser by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object RoleUser whose updatedBy is equals to the given updatedBy. If
	 *         no RoleUser is found, this method returns null.
	 */
	@Query("select e from RoleUser e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<RoleUser> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleUser by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object RoleUser whose deletedBy is equals to the given deletedBy. If
	 *         no RoleUser is found, this method returns null.
	 */
	@Query("select e from RoleUser e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<RoleUser> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleUser by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object RoleUser whose isDeleted is equals to the given isDeleted. If
	 *         no RoleUser is found, this method returns null.
	 */
	@Query("select e from RoleUser e where e.isDeleted = :isDeleted")
	List<RoleUser> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds RoleUser by using roleId as a search criteria.
	 * 
	 * @param roleId
	 * @return An Object RoleUser whose roleId is equals to the given roleId. If
	 *         no RoleUser is found, this method returns null.
	 */
	@Query("select e from RoleUser e where e.role.id = :roleId and e.isDeleted = :isDeleted")
	List<RoleUser> findByRoleId(@Param("roleId")Integer roleId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds RoleUser by using userId as a search criteria.
	 * 
	 * @param userId
	 * @return An Object RoleUser whose userId is equals to the given userId. If
	 *         no RoleUser is found, this method returns null.
	 */
	@Query("select e from RoleUser e where e.user.id = :userId and e.isDeleted = :isDeleted")
	List<RoleUser> findByUserId(@Param("userId")Integer userId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of RoleUser by using roleUserDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of RoleUser
	 * @throws DataAccessException,ParseException
	 */
	default List<RoleUser> getByCriteria(Request<RoleUserDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from RoleUser e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<RoleUser> query = em.createQuery(req, RoleUser.class);
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
	 * Finds count of RoleUser by using roleUserDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of RoleUser
	 * 
	 */
	default Long count(Request<RoleUserDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from RoleUser e where e IS NOT NULL";
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
	default String getWhereExpression(Request<RoleUserDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		RoleUserDto dto = request.getData() != null ? request.getData() : new RoleUserDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (RoleUserDto elt : request.getDatas()) {
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
	default String generateCriteria(RoleUserDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
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
			if (dto.getRoleId()!= null && dto.getRoleId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("roleId", dto.getRoleId(), "e.role.id", "Integer", dto.getRoleIdParam(), param, index, locale));
			}
			if (dto.getUserId()!= null && dto.getUserId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("userId", dto.getUserId(), "e.user.id", "Integer", dto.getUserIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getRoleLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("roleLibelle", dto.getRoleLibelle(), "e.role.libelle", "String", dto.getRoleLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getRoleCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("roleCode", dto.getRoleCode(), "e.role.code", "String", dto.getRoleCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUserNom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("userNom", dto.getUserNom(), "e.user.nom", "String", dto.getUserNomParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUserPrenom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("userPrenom", dto.getUserPrenom(), "e.user.prenom", "String", dto.getUserPrenomParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUserLogin())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("userLogin", dto.getUserLogin(), "e.user.login", "String", dto.getUserLoginParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
