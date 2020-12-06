package code88.oscar.bcm.services.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.model.ProductLogModel;
import code88.oscar.bcm.repository.ProductLogRepository;
import code88.oscar.bcm.services.ProductLogService;
import code88.oscar.bcm.viewObjects.ProductLogVO;

/**
 * @FileName: ProductLogServiceImplement.java
 * @since: 28/10/2020
 * */
@Service
public class ProductLogServiceImplement implements ProductLogService{

    @Autowired
    private ProductLogRepository productRepository;
    
    @Autowired
    private CommonMethod commonMethod;
    
    @Override
    public List<ProductLogVO> getProductLog() {
	List<ProductLogModel> listModel = productRepository.findAll();
	List<ProductLogVO> listVO = mappingListProduct(listModel);
	return listVO;
    }
    
    List<ProductLogVO> mappingListProduct(List<ProductLogModel> listModel){
	List<ProductLogVO> listVO = new ArrayList<>();
	for(ProductLogModel model : listModel) {
	    ProductLogVO vo = new ProductLogVO();
	    vo.setId(model.getId());
	    vo.setProductName(model.getProductName());
	    vo.setPrice(commonMethod.convertCurrencyToString(model.getPrice()));
	    vo.setUserAction(model.getActionUser());
	    vo.setCreateBy(model.getCreateBy());
	    vo.setCreateDate(commonMethod.convertDateTimeToString(model.getCreateDate()));
	    listVO.add(vo);
	}
	return listVO;
	
    }

}
