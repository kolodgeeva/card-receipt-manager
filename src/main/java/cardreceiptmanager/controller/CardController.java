package cardreceiptmanager.controller;

import cardreceiptmanager.model.repository.CardRepository;
import cardreceiptmanager.model.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    // TODO: add permission for USER
    @RequestMapping(value = "/card", method = RequestMethod.GET)
     public String getAllCards(Model model) {
        model.addAttribute("cards", cardRepository.findAll());
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
    public String addCard(Card card) {
        cardRepository.save(card);
        return "redirect:/card/" + card.getId();
    }

    // TODO: add permission for USER
    @RequestMapping(value = "/card/{id}", method = RequestMethod.GET)
    public String getCard(@PathVariable Integer id, Model model) {
        model.addAttribute("card", cardRepository.findOne(id));
        return "card";
    }

    // TODO: add permission for ADMIN
    @RequestMapping("/card/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("card", cardRepository.findOne(id));
        return "cardForm";
    }

    // TODO: add permission for ADMIN
    @RequestMapping(value = "/card", method = RequestMethod.PUT)
    public String updateCard(Card card) {
        cardRepository.save(card);
        return "redirect:/card/" + card.getId();
    }

    // TODO: add permission for ADMIN
    @RequestMapping(value = "/card/$id", method = RequestMethod.DELETE)
    public String deleteCard(@PathVariable Integer id) {
        cardRepository.delete(id);
        return "redirect:/card";
    }

    // TODO: delete it, only for test
    @RequestMapping("/create")
    @ResponseBody
    public String create(String firstName, String lastName) {
        String cardId = "";
        try {
            Card card = new Card(firstName, lastName);
            cardRepository.save(card);
            cardId = String.valueOf(card.getId());
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User successfully created with id = " + cardId;
    }



}