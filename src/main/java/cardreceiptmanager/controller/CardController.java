package cardreceiptmanager.controller;

import cardreceiptmanager.domain.entity.Card;
import cardreceiptmanager.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

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
     public String addCard(@RequestParam("file1") MultipartFile file, @Valid Card card , BindingResult result) {

        if (!cardService.isNumberUnique(card)) {
            result.rejectValue("number", "error.card", "must be unique");
        }

        if (result.hasErrors()) {
            return "cardForm";
        }
        cardService.saveCard(card, file);
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
        cardService.blockCard(id);
        return "redirect:/card";
    }
}