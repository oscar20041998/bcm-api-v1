package code88.oscar.bcm.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import code88.oscar.bcm.common.StatusCommon;
import code88.oscar.bcm.model.OrderProductModel;

/**
 * @FileName: OrderProductRepository.java
 * @since: 22/10/2020
 * */
@Repository
public interface OrderProductRepository extends JpaRepository<OrderProductModel, Integer>{

    public static final String sql_getOrderProductByTable = ""
    	+ " SELECT "
    	+ "	op.id, "
    	+ "	op.position_id, "
    	+ "	op.product_id,"
    	+ "	prd.product_name AS product_name, "
    	+ "	op.quantity, "
    	+ "	op.price, "
    	+ "	op.status_product, "
    	+ "	op.create_by, "
    	+ "	op.create_date "
    	+ " FROM order_product op "
    	+ " LEFT JOIN product prd ON op.product_id = prd.product_id "
    	+ " LEFT JOIN position p ON op.position_id = p.position_id "
    	+ " WHERE op.position_id = :pTableId"
    	+ "";
    
//    public static final String sql_countOrderProductExist = ""
//    	+ " SELECT quantity"
//    	+ " FROM order_product WHERE position_id = :pPositionId AND product_id = :pProductId";
    
    public static final String sql_insertNewOrderProduct = ""
    	+ " INSERT INTO "
    	+ "	order_product "
    	+ " 	(position_id, product_id, quantity, price, status_product, create_by, create_date) "
    	+ " VALUES "
    	+ " 	(:pPositionId, :pProductId, :pQuantity, :pPrice, :pStatusProduct, :pCreateBy, :pCreateDate)";
    
    public static final String sql_increaseOrderProductExist = ""
    	+ " UPDATE "
    	+ "	order_product op"
    	+ " LEFT JOIN product prd ON prd.product_id = op.product_id "
    	+ " SET "
    	+ "	op.quantity = op.quantity + 1, "
    	+ "	op.price = prd.price * op.quantity "
    	+ " WHERE "
    	+ "	op.position_id = :pPositionId AND "
    	+ "	op.product_id = :pProductId ";
    
    public static final String sql_decreaseOrderProductExist = ""
	    	+ " UPDATE "
	    	+ "	order_product op"
	    	+ " LEFT JOIN product prd ON prd.product_id = op.product_id "
	    	+ " SET "
	    	+ "	op.quantity = op.quantity - 1, "
	    	+ "	op.price = prd.price*op.quantity "
	    	+ " WHERE "
	    	+ "	op.position_id = :pPositionId AND "
	    	+ "	op.product_id = :pProductId ";
    public static final String sql_deleteOrderProduct = ""
    	+ " DELETE FROM "
    	+ "	order_product "
    	+ " WHERE position_id = :pPositionId AND product_id = :pProductId";
    
    public static final String sql_getListOrderPeding = ""
    	+ " SELECT "
    	+ "	op.id, "
    	+ "	op.position_id, "
    	+ "	op.product_id,"
    	+ "	prd.product_name AS product_name, "
    	+ "	op.quantity, "
    	+ "	op.price, "
    	+ "	op.status_product, "
    	+ "	op.create_by, "
    	+ "	op.create_date "
    	+ " FROM order_product op "
    	+ " LEFT JOIN product prd ON op.product_id = prd.product_id "
    	+ " WHERE "
    	+ "	status_product ='"+StatusCommon.PENDING +"'"
    	+ " ORDER BY op.create_date ASC";
    
    public static final String sql_deleteOrderProductByTableId = ""
	    	+ " DELETE FROM "
	    	+ "	order_product "
	    	+ " WHERE position_id = :pPositionId";
    
    public final static String sql_updateStatusOrderPending = ""
    	+ " UPDATE "
    	+ "	order_product "
    	+ " SET status_product ='"+StatusCommon.DONE+"'"
    	+ " WHERE "
    	+ "	position_id = :pPositionId AND "
    	+ "	product_id = :pProductId";
    
    @Query(value = sql_getOrderProductByTable, nativeQuery = true)
    List<OrderProductModel> getListOrderByTable (@Param("pTableId") String tableId);
    
//    @Transactional
//    @Modifying
//    @Query(value = sql_countOrderProductExist, nativeQuery=true)
//    int countProduct(@Param("pPositionId") String positionId, @Param("pProductId") String productId);
    
    @Transactional
    @Modifying
    @Query(value = sql_increaseOrderProductExist, nativeQuery = true)
    void increaseOrderProductExist(@Param("pPositionId") String positionId, @Param("pProductId") String productId);
    
    @Transactional
    @Modifying
    @Query(value = sql_decreaseOrderProductExist, nativeQuery = true)
    void decreaseOrderProductExist(@Param("pPositionId") String positionId, @Param("pProductId") String productId);
    
    @Transactional
    @Modifying
    @Query(value = sql_deleteOrderProduct, nativeQuery = true)
    void deleteOrderProduct(@Param("pPositionId") String positionId, @Param("pProductId") String productId);
    
    @Transactional
    @Modifying
    @Query(value = sql_deleteOrderProductByTableId, nativeQuery = true)
    void deleteOrderProductByTableId(@Param("pPositionId") String positionId);
    
    @Transactional
    @Modifying
    @Query(value = sql_insertNewOrderProduct, nativeQuery = true)
    void insertNewOrderProduct(@Param("pPositionId") String positionId, @Param("pProductId") String productId,
	    @Param("pQuantity") int quantity, @Param("pPrice") BigDecimal price,
	    @Param("pStatusProduct") String statusProduct, @Param("pCreateBy") String createBy,
	    @Param("pCreateDate") LocalDateTime createDate);
    
    @Query(value = sql_getListOrderPeding, nativeQuery = true)
    List<OrderProductModel> getListOrderPending();
    
    @Transactional
    @Modifying
    @Query(value = sql_updateStatusOrderPending, nativeQuery = true)
    void updateStatusProductPending(@Param("pPositionId") String positionId, @Param("pProductId") String productId);
}
