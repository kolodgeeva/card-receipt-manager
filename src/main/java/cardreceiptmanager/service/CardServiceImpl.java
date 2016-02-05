package cardreceiptmanager.service;

import cardreceiptmanager.domain.entity.Card;
import cardreceiptmanager.domain.repository.CardRepository;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@Service
public class CardServiceImpl implements CardService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

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
    public Card saveCard(Card card, MultipartFile file) {
        if (!file.isEmpty()) {
            // Use selected file
            try {
                byte[] bytes = file.getBytes();
                card.setFile(bytes);
            } catch (IOException e) {
                log.warn("File didn't uploaded ", e);
            }
        } else if (card.getId() != null) {
            Card existsCard = getCardById(card.getId());
            if (existsCard != null) {
                byte[] existsFile = existsCard.getFile();
                if (existsFile != null) {
                    // Use existing File for existing Card
                    card.setFile(existsFile);
                } else if (card.getWebLink() != null) {
                    // Use photo from VK for existing Card
                    setPhotoFromVK(card);
                }
            }
        } else if (card.getWebLink() != null) {
            // Use photo from VK for new Card
            setPhotoFromVK(card);
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

    @Override
    public boolean isNumberUnique(Card card) {
        List<Card> cards = cardRepository.findByNumber(card.getNumber());
        for(Card c: cards) {
            return Objects.equals(c.getId(), card.getId());
        }
        return true;
    }

    private void setPhotoFromVK(Card card) {

        String link = card.getWebLink();
        if (link == null || link.isEmpty()) {
            return;
        }

        try {
            Document doc = Jsoup.connect(link).timeout(0)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com").get();
            if (doc != null) {
                Element a = doc.select("#profile_photo_link").first();
                if (a != null) {
                    Element img = a.getElementsByTag("img").get(0);
                    if (img != null) {
                        String src = img.absUrl("src");
                        URL url = new URL(src);
                        InputStream in = url.openStream();
                        byte[] bytes = IOUtils.toByteArray(in);
                        card.setFile(bytes);
                    }
                }
            }
        } catch (IOException e) {
            log.warn("File didn't uploaded ", e);
        }

    }
}
