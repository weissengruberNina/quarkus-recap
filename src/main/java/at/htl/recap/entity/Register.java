package at.htl.recap.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Entity
@Table(name = "V_REGISTER")
@SequenceGenerator(
        name = "registerSeq"
        , sequenceName = "V_REGISTER_SEQ"
        , initialValue = 5000
)
public class Register extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "registerSeq")
    @Column(name = "R_ID")
    public Long id;

    @Column(name = "R_OWNER")
    public String owner;

    @Column(name = "R_LICENSE_NO")
    public String licenseNo;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "R_V_ID")
    public Vehicle vehicle;

    @Column(name = "R_REGISTER_DATE")
    public LocalDate registerDate;

    @Column(name = "R_SIGN_OFF_DATE")
    public LocalDate signOffDate;

    public Register() {
    }

    public Register(String owner, String licenseNo, Vehicle vehicle) {
        this.owner = owner;
        this.licenseNo = licenseNo;
        this.vehicle = vehicle;
        this.registerDate = LocalDate.now();
    }
}
