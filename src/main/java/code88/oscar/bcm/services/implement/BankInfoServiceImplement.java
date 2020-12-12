package code88.oscar.bcm.services.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.model.BankInfoModel;
import code88.oscar.bcm.repository.BankInfoRepository;
import code88.oscar.bcm.services.BankInfoService;
import code88.oscar.bcm.viewObjects.BankInfoVO;

/**
 * @FileName: BankInfoServiceImplement.java
 * @since: 11/12/2020
 */
@Service
public class BankInfoServiceImplement implements BankInfoService {

    @Autowired
    private CommonMethod commonMethod;

    @Autowired
    private BankInfoRepository bankInfoRepository;

    @Override
    public List<BankInfoVO> getAllBanksInfo() {
	List<BankInfoModel> listModel = bankInfoRepository.getListBankInfo();
	List<BankInfoVO> listVO = mappListBankVO(listModel);
	return listVO;
    }

    @Override
    public List<BankInfoVO> getBankInfoActive() {
	List<BankInfoModel> listModel = bankInfoRepository.getListBankActive();
	List<BankInfoVO> listVO = mappListBankVO(listModel);
	return listVO;
    }

    @Override
    public void disableBankInfoByCode(String bankCode) {
	bankInfoRepository.disableBankInfoByCode(bankCode);
    }

    /**
     * @Function: mappListBankVO(...)
     * @param: List<BankInfoModel> - object
     */
    List<BankInfoVO> mappListBankVO(List<BankInfoModel> listModel) {
	List<BankInfoVO> listVO = new ArrayList<>();
	for (BankInfoModel model : listModel) {
	    BankInfoVO vo = new BankInfoVO();
	    vo.setBankCode(model.getBankCode());
	    vo.setBankName(model.getBankName());
	    vo.setStatus(model.getStatus());
	    vo.setCreateBy(model.getCreateBy());
	    vo.setCreateDate(commonMethod.convertDateTimeToString(model.getCreateDate()));
	    listVO.add(vo);
	}
	return listVO;
    }

    @Override
    public void enabledBankInfoByCode(String banCode) {
	bankInfoRepository.enableBankInfoByCode(banCode);
    }

}
