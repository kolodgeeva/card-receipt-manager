package cardreceiptmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class CardController {

    @RequestMapping(value = "/cards", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("title", "Card Manager Test");
        model.addAttribute("msg", "Hello, It is Card Manager");

        return "cards";
    }
}