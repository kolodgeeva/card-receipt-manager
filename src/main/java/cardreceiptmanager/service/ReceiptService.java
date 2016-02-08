package cardreceiptmanager.service;

import cardreceiptmanager.domain.entity.Receipt;

public interface ReceiptService {

    Receipt saveReceipt(Receipt receipt);

    Receipt getReceiptById(Integer id);

}
