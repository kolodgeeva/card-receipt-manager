package cardreceiptmanager.controller;

import cardreceiptmanager.domain.entity.Card;
import cardreceiptmanager.domain.entity.Receipt;
import cardreceiptmanager.service.CardService;
import cardreceiptmanager.service.ReceiptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReceiptController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private ReceiptService receiptService;

    private CardService cardService;

    @Autowired
    public void setReceiptService(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Autowired
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    // TODO: add permission for USER
    @RequestMapping("/card/{id}/receipt/new")
    public String add(@PathVariable Integer id, Model model){
        Card card = cardService.getCardById(id);
        model.addAttribute("receipt", new Receipt(card));
        return "receiptForm";
    }

    // TODO: add permission for USER
    @RequestMapping(value = "/receipt", method = RequestMethod.POST)
    public String addCard(Receipt receipt) {
        receiptService.saveReceipt(receipt);
        if (receipt.getCard() != null) {
            return "redirect:/card/" + receipt.getCard().getId();
        }
        else return "error";
    }

}