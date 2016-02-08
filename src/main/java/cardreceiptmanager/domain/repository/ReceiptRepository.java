package cardreceiptmanager.domain.repository;

import cardreceiptmanager.domain.entity.Receipt;
import org.springframework.data.repository.CrudRepository;

public interface ReceiptRepository extends CrudRepository<Receipt, Integer> {
}
