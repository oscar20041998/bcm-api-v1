package code88.oscar.bcm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import code88.oscar.bcm.model.ProductModel;

/**
 * @FileName: ProductRepository.java
 * @since: 22/10/2020
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductModel, String> {

    public final static String sql_getALlProducts = "" 
    + " SELECT " 
	    + "	prd.product_id, "
	    + "	ctg.category_name as category_id, " 
	    + "	prd.product_name, "
	    + " prd.image,"
	    + " prd.image_name, " 
	    + "	prd.price, "
	    + " prd.create_by, "
	    + " prd.create_date " 
	    + " FROM "
	    + "	product prd" 
	    + " LEFT JOIN category ctg ON prd.category_id = ctg.category_id "
	    + " ORDER BY category_id";
    
    public final static String sql_getProductByCategoryId = "" 
	    + " SELECT " 
		    + "	prd.product_id, "
		    + "	ctg.category_name as category_id, " 
		    + "	prd.product_name,"
		    + " prd.image, " 
		    + "	prd.price, "
		    + " prd.create_by, "
		    + " prd.create_date " 
		    + " FROM "
		    + "	product prd" 
		    + " LEFT JOIN category ctg ON prd.category_id = ctg.category_id "
		    + " WHERE prd.category_id = :pCategoryId";
    
    public final static String sql_getProductByProductName = "" 
	    + " SELECT " 
		    + "	prd.product_id, "
		    + "	ctg.category_name as category_id, " 
		    + "	prd.product_name, "
		    + " prd.image, " 
		    + "	prd.price, "
		    + " prd.create_by, "
		    + " prd.create_date " 
		    + " FROM "
		    + "	product prd" 
		    + " LEFT JOIN category ctg ON prd.category_id = ctg.category_id "
		    + " WHERE "
		    + "		prd.product_id = :pProductName OR "	
		    + "		prd.product_name LIKE %:pProductName%";
    
    public final static String sql_getProductByProductId = "SELECT * FROM product WHERE product_id = :pProductId";

    @Query(value = sql_getALlProducts, nativeQuery = true)
    List<ProductModel> getListProducts();
    
    @Query(value = sql_getProductByCategoryId, nativeQuery = true)
    List<ProductModel> getProductByCategoryId(@Param("pCategoryId") String categoryId);
    
    @Query(value = sql_getProductByProductName, nativeQuery = true)
    List<ProductModel> getProductByName(@Param("pProductName") String productName);
    
    @Query(value = sql_getProductByProductId, nativeQuery = true)
    ProductModel getProductById(@Param("pProductId") String productId);
}
