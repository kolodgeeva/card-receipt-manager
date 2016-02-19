package cardreceiptmanager.controller;

import cardreceiptmanager.domain.entity.Card;
import cardreceiptmanager.domain.entity.Receipt;
import cardreceiptmanager.service.CardService;
import cardreceiptmanager.service.ReceiptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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

    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    @RequestMapping("/card/{id}/receipt/new")
    public String add(@PathVariable Integer id, Model model){
        Card card = cardService.getCardById(id);
        model.addAttribute("receipt", new Receipt(card));
        return "receiptForm";
    }

    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    @RequestMapping(value = "/receipt", method = RequestMethod.POST)
    public String addCard(@Valid Receipt receipt, BindingResult result) {
        if (!receiptService.isNumberUnique(receipt)) {
            result.rejectValue("number", "error.receipt", "must be unique");
        }

        if (result.hasErrors()) {
            return "receiptForm";
        }

        receiptService.saveReceipt(receipt);
        if (receipt.getCard() != null) {
            return "redirect:/card/" + receipt.getCard().getId();
        }
        else return "error";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/receipt/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("receipt", receiptService.getReceiptById(id));
        return "receiptForm";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/receipt/delete/{id}")
    public String deleteCard(@PathVariable Integer id) {
        Receipt receipt = receiptService.getReceiptById(id);
        if (receipt != null && receipt.getCard() != null) {
            Integer cardId = receipt.getCard().getId();
            receiptService.deleteReceipt(id);
            return "redirect:/card/" + cardId;
        }
        else return "error";
    }

    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    @RequestMapping(value = {"/receipts"}, method = RequestMethod.GET)
    public String getReceipts(@RequestParam("filter") String filter ,Model model) {
        model.addAttribute("filter", filter);
        model.addAttribute("receipts", receiptService.listReceiptsByFilter(filter));
        return "receipts";
    }
}