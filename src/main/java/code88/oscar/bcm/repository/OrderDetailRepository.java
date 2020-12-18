package code88.oscar.bcm.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

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
    
    public static final String getListOrderDetail = ""
    	+ " SELECT "
    	+ "	ord.id, "
    	+ "	ord.order_id, "
    	+ "	ord.table_id,"
    	+ "	prd.product_name AS product_id, "
    	+ "	ord.quantity,"
    	+ "	ord.price, "
    	+ "	ord.create_by, "
    	+ "	ord.create_date "
    	+ " FROM "
    	+ "	order_detail ord "
    	+ " LEFT JOIN product prd ON prd.product_id = ord.product_id "
    	+ " LEFT JOIN position p ON p.position_id = ord.table_id "
    	+ " LEFT JOIN transaction txn ON txn.order_id = ord.order_id AND txn.table_id = ord.table_id "
    	+ " WHERE"
    	+ "	txn.transaction_id = :pTransactionId AND "
    	+ "	ord.order_id = :pOrderId AND "
    	+ "	ord.table_id = :pTableId ";
    
    @Modifying
    @Transactional
    @Query(value = saveOrderDetail, nativeQuery = true)
    void insertOrderDetail(@Param("pOrderId") String orderId, @Param("pTableId") String tableId,
	    @Param("pProductId") String productId, @Param("pQuantity") int quantity, @Param("pPrice") BigDecimal price,
	    @Param("pCreateBy") String createBy, @Param("pCreateDate") LocalDateTime createDate);
    
    @Query(value = getListOrderDetail, nativeQuery = true)
    List<OrderDetailModel> getListOrderDetail(@Param("pTransactionId") String transactionId,
	    @Param("pOrderId") String orderId, @Param("pTableId") String tableId);
}
