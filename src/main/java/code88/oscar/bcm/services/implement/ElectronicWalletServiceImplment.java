package code88.oscar.bcm.services.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.model.ElectronicWalletModel;
import code88.oscar.bcm.repository.ElectronicWalletRepository;
import code88.oscar.bcm.services.ElectronicWalletService;
import code88.oscar.bcm.viewObjects.ElectronicWalletVO;

/**
 * @FileName: ElectronicWalletServiceImplment.java
 * @since: 11/12/2020
 */
@Service
public class ElectronicWalletServiceImplment implements ElectronicWalletService {

    @Autowired
    private CommonMethod commonMethod;

    @Autowired
    private ElectronicWalletRepository electronicWalletRepository;

    @Override
    public List<ElectronicWalletVO> getListWallet() {
	List<ElectronicWalletModel> listModel = electronicWalletRepository.getListWallet();
	List<ElectronicWalletVO> listVO = mappListWalletVO(listModel);
	return listVO;
    }

    @Override
    public List<ElectronicWalletVO> getListWalletActive() {
	List<ElectronicWalletModel> listModel = electronicWalletRepository.getListWalletActive();
	List<ElectronicWalletVO> listVO = mappListWalletVO(listModel);
	return listVO;
    }

    @Override
    public void disableElectronicWalletById(int id) {
	electronicWalletRepository.disableWalletById(id);
    }

    /**
     * @Function: mappListWalletVO(...)
     * @param: List<ElectronicWalletModel> - object
     */
    List<ElectronicWalletVO> mappListWalletVO(List<ElectronicWalletModel> listModel) {
	List<ElectronicWalletVO> listVO = new ArrayList<>();
	for (ElectronicWalletModel model : listModel) {
	    ElectronicWalletVO vo = new ElectronicWalletVO();
	    vo.setId(model.getId());
	    vo.setWalletName(model.getWallet_name());
	    vo.setStatus(model.getStatus());
	    vo.setCreateBy(model.getCreate_by());
	    vo.setCreateDate(commonMethod.convertDateTimeToString(model.getCreateDate()));
	    listVO.add(vo);
	}
	return listVO;
    }
}
