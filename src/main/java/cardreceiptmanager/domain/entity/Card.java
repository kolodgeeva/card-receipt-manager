package cardreceiptmanager.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MID_NAME")
    private String midName;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @Column(name = "CREATE_DATE")
    private String createDate;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "WEB_LINK")
    private String webLink;

    public Card() {
    }

    public Card(String firstName, String lastName, String midName, String number, Date birthDate, String createDate, String phone, String webLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.midName = midName;
        this.number = number;
        this.birthDate = birthDate;
        this.createDate = createDate;
        this.phone = phone;
        this.webLink = webLink;
    }

    // TODO Remove it
    public Card(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }
}
