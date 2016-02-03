package cardreceiptmanager.service;

import cardreceiptmanager.domain.entity.Card;
import cardreceiptmanager.domain.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    @Autowired
    public void setProductRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    @Override
    public Iterable<Card> listAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card getCardById(Integer id) {
        return cardRepository.findOne(id);
    }

    @Override
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public void deleteCard(Integer id) {
        cardRepository.delete(id);
    }

    @Override
    public void blockCard(Integer id) {
        // TODO update Card state
    }
}
