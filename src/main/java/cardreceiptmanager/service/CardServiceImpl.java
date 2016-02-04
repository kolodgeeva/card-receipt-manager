package cardreceiptmanager.service;

import cardreceiptmanager.domain.entity.Card;
import cardreceiptmanager.domain.repository.CardRepository;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
    public Card saveCard(Card card, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            card.setFile(bytes);
        } else if (card.getId() != null) {
            Card existsCard = getCardById(card.getId());
            card.setFile(existsCard.getFile());
        } else if (card.getWebLink() != null) {
            Document doc  = Jsoup.connect(card.getWebLink()).timeout(0)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com").get();
            Element a = doc.select("#profile_photo_link").first();
            Element img = a.getElementsByTag("img").get(0);
            String src = img.absUrl("src");
            URL url = new URL(src);
            InputStream in = url.openStream();
            byte[] bytes = IOUtils.toByteArray(in);
            card.setFile(bytes);
        }
        return saveCard(card);
    }

    @Override
    public void deleteCard(Integer id) {
        cardRepository.delete(id);
    }

    @Override
    public void blockCard(Integer id) {
        Card card = getCardById(id);
        if (card != null) {
            card.setState(Card.State.BLOCKED);
            saveCard(card);
        }
    }
}
