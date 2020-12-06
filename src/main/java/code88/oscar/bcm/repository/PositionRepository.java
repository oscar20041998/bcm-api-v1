package code88.oscar.bcm.repository;

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

    public final static String sql_openTableById = "UPDATE position SET position_status ='" + StatusCommon.OPENING +"' WHERE position_id = :tableId";

    public final static String sql_closeTableById = "UPDATE position SET position_status ='" + StatusCommon.CLOSED +"' WHERE position_id = :tableId";

    @Transactional
    @Modifying
    @Query(value = sql_openTableById, nativeQuery = true)
    void openTableById(@Param("tableId") String tableId);
    
    @Transactional
    @Modifying
    @Query(value = sql_closeTableById, nativeQuery = true)
    void closeTableById(@Param("tableId") String tableId);

}
