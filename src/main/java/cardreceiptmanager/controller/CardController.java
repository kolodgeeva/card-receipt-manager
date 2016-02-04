package cardreceiptmanager.controller;

import cardreceiptmanager.domain.repository.CardRepository;
import cardreceiptmanager.domain.entity.Card;
import cardreceiptmanager.service.CardService;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;

@Controller
public class CardController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private CardService cardService;

    @Autowired
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    // TODO: add permission for USER
    @RequestMapping(value = {"/card", "/"}, method = RequestMethod.GET)
     public String getAllCards(Model model) {
        model.addAttribute("cards", cardService.listAllCards());
        return "cards";
    }

    // TODO: add permission for USER
    @RequestMapping("card/new")
    public String add(Model model){
        model.addAttribute("card", new Card());
        return "cardForm";
    }

    // TODO: add permission for USER
    @RequestMapping(value = "/card", method = RequestMethod.POST)
    public String addCard(@RequestParam("file1") MultipartFile file, Card card) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                card.setFile(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (card.getId() != null) {
            Card existsCard = cardService.getCardById(card.getId());
            card.setFile(existsCard.getFile());
        }

        cardService.saveCard(card);
        return "redirect:/card/" + card.getId();
    }

    // TODO: add permission for USER
    @RequestMapping(value = "/card/{id}", method = RequestMethod.GET)
    public String getCard(@PathVariable Integer id, Model model) {
        model.addAttribute("card", cardService.getCardById(id));
        return "card";
    }

    // TODO: add permission for ADMIN
    @RequestMapping("/card/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("card", cardService.getCardById(id));
        return "cardForm";
    }

    // TODO: add permission for ADMIN
    @RequestMapping(value = "/card/delete/{id}")
    public String deleteCard(@PathVariable Integer id) {
        cardService.deleteCard(id);
        return "redirect:/card";
    }

    // TODO: add permission for ADMIN
    @RequestMapping(value = "/card/ban/{id}")
    public String banCard(@PathVariable Integer id) {
        Card card = cardService.getCardById(id);
        if (card != null) {
            card.setState(Card.State.BLOCKED);
            cardService.saveCard(card);
        }
        return "redirect:/card";
    }
}