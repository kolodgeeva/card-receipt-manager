package cardreceiptmanager.domain.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Entity
public class Card {

    private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @NotEmpty
    @Basic(optional = false)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotEmpty
    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MID_NAME")
    private String midName;

    @NotEmpty
    @Column(name = "NUMBER", unique = true)
    private String number;

    @NotNull
    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Past(message="only the past is valid")
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @NotNull
    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Pattern(regexp="(^$|[0-9]{10})", message = "must be numeric")
    @Column(name = "PHONE")
    private String phone;

    @URL
    @Column(name = "WEB_LINK")
    private String webLink;

    @Column(name = "COMMENT")
    private String comment;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "STATE")
    private State state;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private Type type;

    @Lob
    @Column(name = "FILE")
    private byte[] file;

    @OneToMany(mappedBy = "card" , cascade = CascadeType.ALL)
    @OrderBy("id asc")
    private List<Receipt> receipts;

    @OneToOne(mappedBy = "card")
    private Discount discount;

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

    public String getBirthDateString() {
        return format.format(birthDate);
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getCreateDateString() {
        return format.format(createDate);
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
        if (file != null) {
            return Base64.getEncoder().encodeToString(file);
        }
        return null;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public boolean isBlocked() {
        return state == State.BLOCKED;
    }

    public boolean isActive() {
        return state == State.ACTIVE;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
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
