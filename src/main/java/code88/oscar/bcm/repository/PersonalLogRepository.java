package code88.oscar.bcm.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import code88.oscar.bcm.model.PersonalLogModel;

/**
 * @FileName: PersonalLogRepository.java
 * @since: 22/10/2020
 * */
@Repository
public interface PersonalLogRepository extends JpaRepository<PersonalLogModel, String>{

    public static final String sql_getListSystemLogByAccount = ""
    	+ " SELECT "
    	+ "	id,"
    	+ "	user_name, "
    	+ "	action, "
    	+ "	action_status,"
    	+ "	action_date "
    	+ " FROM personal_log"
    	+ " WHERE "
    	+ "	user_name = :pUserName "
    	+ " ORDER BY action_date DESC"
    	+ " LIMIT 100";
    
    public static final String sql_getListSystemLogByAccountAndDate = ""
	    	+ " SELECT "
	    	+ "	id,"
	    	+ "	user_name, "
	    	+ "	action, "
	    	+ "	action_status,"
	    	+ "	action_date "
	    	+ " FROM personal_log"
	    	+ " WHERE "
	    	+ "	user_name = :pUserName AND "
	    	+ "	DATE(action_date) = DATE(:pActionDate)"
	    	+ " ORDER BY action_date DESC"
	    	+ " LIMIT 100";
    
    
    @Query(value = sql_getListSystemLogByAccount, nativeQuery = true)
    List<PersonalLogModel> getListSystemLogByAccount(@Param("pUserName") String userName);
    
    @Query(value = sql_getListSystemLogByAccountAndDate, nativeQuery = true)
    List<PersonalLogModel> getListSystemLogByAccounAndDate(@Param("pUserName") String userName, @Param("pActionDate") Date date);
}
