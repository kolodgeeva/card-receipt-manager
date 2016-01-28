package cardreceiptmanager.model.dao;

import cardreceiptmanager.model.entity.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardDao extends CrudRepository<Card, Integer> {
    List<Card> findByLastName(String lastName);
}
