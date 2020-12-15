package code88.oscar.bcm.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import code88.oscar.bcm.model.OrderDetailModel;

/**
 * @FileName: OrderDetailRepository.java
 * @since: 14/12/2020
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailModel, Integer> {

    public static final String saveOrderDetail = ""
    	+ "INSERT INTO order_detail "
    	+ "( "
    	+ "	order_id, "
    	+ "	table_id, "
    	+ "	product_id, "
    	+ "	quantity, "
    	+ "	price, "
    	+ "	create_by, "
    	+ "	create_date "
    	+ ") VALUES "
    	+ "("
    	+ "	:pOrderId, "
    	+ "	:pTableId, "
    	+ "	:pProductId,"
    	+ "	:pQuantity, "
    	+ "	:pPrice, "
    	+ "	:pCreateBy, "
    	+ "	:pCreateDate"
    	+ ")";
    
    @Transactional
    @Modifying
    @Query(value = saveOrderDetail, nativeQuery = true)
    void insertOrderDetail(@Param("pOrderId") String orderId, @Param("pTableId") String tableId,
	    @Param("pProductId") String productId, @Param("pQuantity") int quantity, @Param("pPrice") BigDecimal price,
	    @Param("pCreateBy") String createBy, @Param("pCreateDate") LocalDateTime createDate);
}
