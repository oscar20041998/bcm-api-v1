package code88.oscar.bcm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import code88.oscar.bcm.common.StatusCommon;
import code88.oscar.bcm.model.PositionModel;

/**
 * @FileName: PositionRepository.java
 * @since: 22/10/2020
 * */
@Repository
public interface PositionRepository extends JpaRepository<PositionModel, String>{

    public final static String sql_openTableById = ""
    	+ "UPDATE position "
    	+ "SET position_status ='" + StatusCommon.OPENING +"', "
    	+ "     last_update_by = :pCreateBy,"
    	+ "    last_update_time = NOW()"
    	+ "WHERE position_id = :tableId";

    public final static String sql_closeTableById = ""
    	+ "UPDATE position "
    	+ "SET position_status ='" + StatusCommon.CLOSED +",' "
    	+ "    last_update_by = :pCreateBy,"
    	+ "    last_update_time = NOW()"
    	+ "WHERE position_id = :tableId";
    
    public final static String sql_moveCurrentTable = 
	    "UPDATE order_product "
	    + " SET position_id = :pNewTable "
	    + " WHERE position_id = :pCurrentTable";
    
    public static final String sql_getListPositionOpening = ""
    	+ " SELECT"
    	+ "	position_id, "
    	+ "	name,"
    	+ "	position_status,"
    	+ "	last_update_by, "
    	+ "	last_update_time"
    	+ " FROM position "
    	+ " WHERE position_status = 'OPENING' ";
    
    public static final String sql_getListPositionClosed = ""
	    	+ " SELECT"
	    	+ "	position_id, "
	    	+ "	name,"
	    	+ "	position_status,"
	    	+ "	last_update_by, "
	    	+ "	last_update_time"
	    	+ " FROM position "
	    	+ " WHERE position_status = 'CLOSED' ";

    @Transactional
    @Modifying
    @Query(value = sql_openTableById, nativeQuery = true)
    void openTableById(@Param("tableId") String tableId,@Param("pCreateBy") String createBy);
    
    @Transactional
    @Modifying
    @Query(value = sql_closeTableById, nativeQuery = true)
    void closeTableById(@Param("tableId") String tableId, @Param("pCreateBy") String createBy);
    
    @Transactional
    @Modifying
    @Query(value = sql_moveCurrentTable, nativeQuery = true)
    void moveCurrentTable(@Param("pCurrentTable") String currentTable, @Param("pNewTable") String newTable);
    
    @Query(value = sql_getListPositionOpening, nativeQuery = true)
    List<PositionModel> getListPositionOpening();
    
    @Query(value = sql_getListPositionClosed, nativeQuery = true)
    List<PositionModel> getListPositionClosed();

}
