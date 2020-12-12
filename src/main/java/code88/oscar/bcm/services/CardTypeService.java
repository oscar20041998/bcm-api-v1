package code88.oscar.bcm.services;

import java.util.List;

import code88.oscar.bcm.viewObjects.CardTypeVO;

/**
 * @FileName: CardTypeService.java
 * @since: 12/12/2020
 * */
public interface CardTypeService {

    List<CardTypeVO> getAllCards();
    
    List<CardTypeVO> getActiveCards();
    
    void disableCardById(int id);
    
    void enableCardById(int id);
}
