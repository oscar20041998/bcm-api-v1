package code88.oscar.bcm.services.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.model.AccountUserLogModel;
import code88.oscar.bcm.repository.AccountUserLogRepository;
import code88.oscar.bcm.services.AccountUserLogService;
import code88.oscar.bcm.viewObjects.AccountUserLogVO;

@Service
public class AccountUserLogServiceImplement implements AccountUserLogService {

    @Autowired
    private AccountUserLogRepository accountUserLogRepository;
    
    @Autowired
    private CommonMethod commonMethod;

    @Override
    public List<AccountUserLogVO> getListAccountUserLog() {
	List<AccountUserLogModel> listModel = accountUserLogRepository.findAll();
	List<AccountUserLogVO> listResult = mappingListModel(listModel);
	return listResult;
    }
    List<AccountUserLogVO> mappingListModel (List<AccountUserLogModel> listModel){
	List<AccountUserLogVO> listVO = new ArrayList<>();
	for(AccountUserLogModel model : listModel) {
	    AccountUserLogVO vo = new AccountUserLogVO();
	    vo.setId(model.getId());
	    vo.setAccountId(model.getAccountId());
	    vo.setUserName(model.getUserName());
	    vo.setUserAction(model.getUserAction());
	    vo.setCreatedBy(model.getCreatedBy());
	    vo.setCreatedDate(commonMethod.convertDateTimeToString(model.getCreatedDate()));
	    listVO.add(vo);
	}
	return listVO;
    }

}
