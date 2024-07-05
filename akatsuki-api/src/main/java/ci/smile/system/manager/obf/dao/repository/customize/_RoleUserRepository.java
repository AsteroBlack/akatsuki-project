package ci.smile.system.manager.obf.dao.repository.customize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Repository;

import ci.smile.system.manager.obf.utils.dto.RoleUserDto;

/**
 * Repository customize : RoleUser.
 */
@Repository
public interface _RoleUserRepository {
	default List<String> _generateCriteria(RoleUserDto dto, HashMap<String, java.lang.Object> param, Integer index, Locale locale) throws Exception {
		List<String> listOfQuery = new ArrayList<String>();

		// PUT YOUR RIGHT CUSTOM CRITERIA HERE

		return listOfQuery;
	}
}
