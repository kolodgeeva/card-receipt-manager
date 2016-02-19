package cardreceiptmanager.service;

import cardreceiptmanager.domain.entity.Receipt;
import cardreceiptmanager.domain.repository.ReceiptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private ReceiptRepository receiptRepository;

    @Autowired
    public void setProductRepository(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    @Override
    public Boolean isNumberUnique(Receipt receipt) {
        List<Receipt> receipts = receiptRepository.findByNumber(receipt.getNumber());
        for(Receipt r: receipts) {
            return Objects.equals(r.getId(), receipt.getId());
        }
        return true;
    }

    @Override
    public Receipt saveReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    @Override
    public Iterable<Receipt> listReceiptsByFilter(String filter) {
        if(filter == null || filter.isEmpty()) {
            return new ArrayList();
        }
        return receiptRepository.findByNumberContaining(filter);
    }

    @Override
    public Receipt getReceiptById(Integer id) {
        return receiptRepository.findOne(id);
    }

    @Override
    public void deleteReceipt(Integer id) {
        receiptRepository.delete(id);
    }
}
