package code88.oscar.bcm.services.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.model.UserLogModel;
import code88.oscar.bcm.repository.UserLogRepository;
import code88.oscar.bcm.services.UserLogService;
import code88.oscar.bcm.viewObjects.UserLogVO;

@Service
public class UserLogServiceImplement implements UserLogService{

    @Autowired
    private UserLogRepository userLogRepository;
    
    @Autowired
    private CommonMethod commonMethod;
    
    @Override
    public List<UserLogVO> getListLogUser() {
	List<UserLogModel> listModel = userLogRepository.findAll();
	List<UserLogVO> listResult = mappingListModel(listModel);
	return listResult;
    }

    public List<UserLogVO> mappingListModel(List<UserLogModel> listModel) {
	List<UserLogVO> listVO = new ArrayList<>();
	for(UserLogModel model : listModel) {
	    UserLogVO vo = new UserLogVO();
	    vo.setId(model.getId());
	    vo.setFullName(model.getFullName());
	    vo.setUserId(model.getUserId());
	    vo.setIdCard(model.getIdCard());
	    vo.setDateOfBirth(commonMethod.autoCreatePassword(model.getDateOfBirth()));
	    vo.setUserAction(model.getUserAction());
	    vo.setAddress(model.getAddress());
	    vo.setEmail(model.getEmail());
	    vo.setPhoneNumber(model.getPhoneNumber());
	    vo.setCreatedBy(model.getCreatedBy());
	    vo.setCreatedDate(commonMethod.convertDateTimeToString(model.getCreatedDate()));
	    listVO.add(vo);
	}
	return listVO;
    }
    
}
