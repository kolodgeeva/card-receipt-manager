package cardreceiptmanager.domain.entity;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Base64;
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
    private Date createDate;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "WEB_LINK")
    private String webLink;

    @Column(name = "COMMENT")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATE")
    private State state;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private Type type;

    @Column(name = "FILE")
    @Lob
    private byte[] file;

    @PrePersist
    protected void onCreate() {
        createDate = new Date();
    }

    public Card() {
    }

    public Card(String firstName, String lastName, String midName, String number, Date birthDate, Date createDate,
                String phone, String webLink, String comment, State state, Type type, byte[] file) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.midName = midName;
        this.number = number;
        this.birthDate = birthDate;
        this.createDate = createDate;
        this.phone = phone;
        this.webLink = webLink;
        this.comment = comment;
        this.state = state;
        this.type = type;
        this.file = file;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public byte[] getFile() {
        return file;
    }

    public String base64File() {
        return Base64.getEncoder().encodeToString(file);
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public enum State {
        ACTIVE,
        BLOCKED
    }

    public enum Type {
        DEPOSIT,
        REPOST
    }
}
