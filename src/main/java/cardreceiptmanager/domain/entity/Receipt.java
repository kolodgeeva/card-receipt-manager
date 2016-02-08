package cardreceiptmanager.domain.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

public class Receipt {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "NUMBER", unique = true)
    private String number;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "AMOUNT")
    private String amount;

    @Column(name = "COMMENT")
    private String comment;

    @JoinColumn(name = "card_id", referencedColumnName = "id")
    @ManyToOne
    private Card person;

    public Receipt() {
    }

    public Receipt(String number, Date createDate, String amount, String comment, Card person) {
        this.number = number;
        this.createDate = createDate;
        this.amount = amount;
        this.comment = comment;
        this.person = person;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Card getPerson() {
        return person;
    }

    public void setPerson(Card person) {
        this.person = person;
    }
}
