package code88.oscar.bcm.services.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.model.CardTypeModel;
import code88.oscar.bcm.repository.CardTypeRepository;
import code88.oscar.bcm.services.CardTypeService;
import code88.oscar.bcm.viewObjects.CardTypeVO;

/**
 * @FileName: CardTypeServiceImplement.java
 * @since: 12/12/2020
 */
@Service
public class CardTypeServiceImplement implements CardTypeService {

    @Autowired
    private CommonMethod commonMethod;

    @Autowired
    private CardTypeRepository cardTypeRepository;

    @Override
    public List<CardTypeVO> getAllCards() {
	List<CardTypeModel> listModel = cardTypeRepository.getAllCards();
	List<CardTypeVO> listVO = mappingListCardVO(listModel);
	return listVO;
    }

    @Override
    public List<CardTypeVO> getActiveCards() {
	List<CardTypeModel> listModel = cardTypeRepository.getActiveCards();
	List<CardTypeVO> listVO = mappingListCardVO(listModel);
	return listVO;
    }

    @Override
    public void disableCardById(int id) {
	try {
	    cardTypeRepository.disableCardById(id);
	} catch (Exception ex) {

	}
    }

    @Override
    public void enableCardById(int id) {
	try {
	    cardTypeRepository.enableCardById(id);
	} catch (Exception ex) {

	}
    }

    List<CardTypeVO> mappingListCardVO(List<CardTypeModel> listModel) {
	List<CardTypeVO> listVO = new ArrayList<>();
	for (CardTypeModel model : listModel) {
	    CardTypeVO vo = new CardTypeVO();
	    vo.setId(model.getId());
	    vo.setCardType(model.getCardType());
	    vo.setCardName(model.getCardName());
	    vo.setStatus(model.getStatus());
	    vo.setCreateBy(model.getCreateBy());
	    vo.setCreateDate(commonMethod.convertDateTimeToString(model.getCreateDate()));
	    listVO.add(vo);
	}
	return listVO;
    }

}
