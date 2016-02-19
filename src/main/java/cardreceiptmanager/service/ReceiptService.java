package cardreceiptmanager.service;

import cardreceiptmanager.domain.entity.Card;
import cardreceiptmanager.domain.entity.Receipt;

public interface ReceiptService {

    Boolean isNumberUnique(Receipt receipt);

    Receipt saveReceipt(Receipt receipt);

    Receipt getReceiptById(Integer id);

    void deleteReceipt(Integer id);

    Iterable<Receipt> listReceiptsByFilter(String filter);

}
