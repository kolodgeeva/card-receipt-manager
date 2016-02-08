package cardreceiptmanager.domain.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.util.Date;

@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "NUMBER", unique = true)
    @NotEmpty
    private String number;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "AMOUNT")
    @NotEmpty
    @Digits(integer = 10, fraction = 5)
    private Integer amount;

    @Column(name = "COMMENT")
    private String comment;

    @JoinColumn(name = "card_id")
    @ManyToOne
    private Card card;

    public Receipt() {
    }

    public Receipt(Card card) {
        this.card = card;
    }

    public Receipt(String number, Date createDate, Integer amount, String comment, Card card) {
        this.number = number;
        this.createDate = createDate;
        this.amount = amount;
        this.comment = comment;
        this.card = card;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
