package cardreceiptmanager.domain.repository;

import cardreceiptmanager.domain.entity.Receipt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReceiptRepository extends CrudRepository<Receipt, Integer> {
    List<Receipt> findByNumber(String number);
}
