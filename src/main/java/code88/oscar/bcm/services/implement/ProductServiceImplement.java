package code88.oscar.bcm.services.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.model.ProductModel;
import code88.oscar.bcm.repository.ProductRepository;
import code88.oscar.bcm.request.SaveProductRequest;
import code88.oscar.bcm.services.ProductService;
import code88.oscar.bcm.viewObjects.ProductVO;

/**
 * @FileName: ProductServiceImplement.java
 * @since: 25/10/2020
 */
@Service
public class ProductServiceImplement implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CommonMethod commonMethod;

    @Override
    public List<ProductVO> getListProduct() {
	List<ProductModel> listProduct = productRepository.getListProducts();
	List<ProductVO> listVO = mappingListProduct(listProduct);
	return listVO;
    }

    @Override
    public ProductModel saveProduct(SaveProductRequest request) {
	ProductModel model = mappingProductModel(request);
	return productRepository.save(model);
    }

    @Override
    public void delteProductById(String productId) {
	productRepository.deleteById(productId);
    }

    /**
     * @Function: mappingListProduct(...)
     */
    List<ProductVO> mappingListProduct(List<ProductModel> listModels) {
	List<ProductVO> listVO = new ArrayList<>();
	for (ProductModel model : listModels) {
	    ProductVO vo = new ProductVO();
	    vo.setProductId(model.getProductId());
	    vo.setCategoryId(model.getCategoryId());
	    vo.setProductName(model.getProductName());
	    vo.setPrice(model.getPrice());
	    vo.setPriceFormatString(commonMethod.convertCurrencyToString(model.getPrice()));
	    vo.setCreateBy(model.getCreateBy());
	    vo.setCreateDate(commonMethod.convertDateTimeToString(model.getCreateDate()));
	    listVO.add(vo);
	}
	return listVO;
    }

    /**
     * @Function: mappingProductModel(...)
     * @param: SaveProductRequest - object
     * */
    ProductModel mappingProductModel(SaveProductRequest request) {
	ProductModel model = new ProductModel();
	model.setProductId(request.getProductId() == null || request.getProductId().isEmpty()
		? "PRD" + commonMethod.convertDateTimeNowToString()
		: request.getProductId());
	model.setCategoryId(request.getCategoryId());
	model.setProductName(request.getProductName());
	model.setPrice(request.getPrice());
	model.setCreateBy(request.getCreateBy());
	model.setCreateDate(commonMethod.getDateTimeNow());
	return model;
    }

    @Override
    public List<ProductVO> getProductByCategoryId(String categoryId) {
	List<ProductModel> listProduct = productRepository.getProductByCategoryId(categoryId);
	List<ProductVO> listVO = mappingListProduct(listProduct);
	return listVO;
    }

    @Override
    public List<ProductVO> getProductByProductName(String productName) {
	List<ProductModel> listProduct = productRepository.getProductByName(productName);
	List<ProductVO> listVO = mappingListProduct(listProduct);
	return listVO;
    }

    @Override
    public ProductModel getProductById(String productId) {
	return productRepository.getProductById(productId);
    }

}
