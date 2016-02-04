package cardreceiptmanager.service;

import cardreceiptmanager.domain.entity.Card;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CardService {

    Iterable<Card> listAllCards();

    Card getCardById(Integer id);

    Card saveCard(Card card);

    Card saveCard(Card card, MultipartFile file) throws IOException;

    void deleteCard(Integer id);

    void blockCard(Integer id);

}
