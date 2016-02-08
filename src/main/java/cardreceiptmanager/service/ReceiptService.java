package cardreceiptmanager.service;

import cardreceiptmanager.domain.entity.Card;
import cardreceiptmanager.domain.entity.Receipt;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ReceiptService {

    Receipt saveReceipt(Receipt receipt);

    Receipt getCardById(Integer id);

}
