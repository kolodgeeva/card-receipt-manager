package cardreceiptmanager.controller;

import cardreceiptmanager.domain.entity.Receipt;
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

    @Autowired
    public void setReceiptService(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    // TODO: add permission for USER
    @RequestMapping("receipt/new")
    public String add(Model model){
        model.addAttribute("receipt", new Receipt());
        return "receiptForm";
    }

    // TODO: add permission for USER
    @RequestMapping(value = "receipt", method = RequestMethod.POST)
    public String addCard(Receipt receipt) {
        receiptService.saveReceipt(receipt);
        return "redirect:/receipt/" + receipt.getId();
    }

    // TODO: add permission for USER
    @RequestMapping(value = "/card/{id}", method = RequestMethod.GET)
    public String getCard(@PathVariable Integer id, Model model) {
        model.addAttribute("receipt", receiptService.getCardById(id));
        return "receipt";
    }
}