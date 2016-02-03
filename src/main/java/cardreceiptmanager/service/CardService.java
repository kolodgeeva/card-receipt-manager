package cardreceiptmanager.service;

import cardreceiptmanager.domain.entity.Card;

public interface CardService {

    Iterable<Card> listAllCards();

    Card getCardById(Integer id);

    Card saveCard(Card card);

    void deleteCard(Integer id);

    void blockCard(Integer id);

}
