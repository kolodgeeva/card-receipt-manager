package cardreceiptmanager.domain.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "V_DISCOUNT")
public class Discount implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "TOTAL_AMOUNT")
    private Double totalAmount;

    @Column(name = "DISCOUNT")
    private Integer discount;

    public Discount() {
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
