package cardreceiptmanager.service;

import cardreceiptmanager.domain.entity.Card;
import org.springframework.web.multipart.MultipartFile;

public interface CardService{

    Iterable<Card> listAllCards();

    Card getCardById(Integer id);

    Card saveCard(Card card);

    Card saveCard(Card card, MultipartFile file);

    void deleteCard(Integer id);

    void blockCard(Integer id);

    boolean isNumberUnique(Card card);

}
