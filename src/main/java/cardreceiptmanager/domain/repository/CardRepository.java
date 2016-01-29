package cardreceiptmanager.domain.repository;

import cardreceiptmanager.domain.entity.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Integer> {
    List<Card> findByLastName(String lastName);
}
