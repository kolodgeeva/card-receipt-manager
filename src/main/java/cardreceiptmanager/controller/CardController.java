package cardreceiptmanager.controller;

import cardreceiptmanager.model.dao.CardDao;
import cardreceiptmanager.model.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class CardController {

    @Autowired
    private CardDao cardDao;

    @RequestMapping(value = "/cards", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("title", "Card Manager Test");
        model.addAttribute("msg", "Hello, It is Card Manager");

        return "cards";
    }

    @RequestMapping("/create")
    @ResponseBody
    public String create(String firstName, String lastName) {
        String cardId = "";
        try {
            Card card = new Card(firstName, lastName);
            cardDao.save(card);
            cardId = String.valueOf(card.getId());
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created with id = " + cardId;
    }



}