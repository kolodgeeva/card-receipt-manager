package cardreceiptmanager.controller;

import cardreceiptmanager.model.repository.CardRepository;
import cardreceiptmanager.model.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @RequestMapping(value = "/cards", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("title", "Card Manager Test");
        model.addAttribute("msg", "Hello, It is Card Manager");

        return "cards";
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