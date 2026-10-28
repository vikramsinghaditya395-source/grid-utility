package in.gridcare.portal.model;
import jakarta.persistence.*;
@Entity @Table(name="customers",uniqueConstraints={@UniqueConstraint(columnNames="customer_id"),@UniqueConstraint(columnNames="mobile")})
public class Customer {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@Column(name="customer_id",nullable=false,unique=true) private String customerId;
@Column(nullable=false) private String name;
@Column(nullable=false,unique=true) private String mobile;
@Enumerated(EnumType.STRING) @Column(nullable=false) private TariffType tariffType;
@Column(nullable=false) private boolean active=true;
protected Customer(){}
public Customer(String id,String name,String mobile,TariffType type){this.customerId=id;this.name=name;this.mobile=mobile;this.tariffType=type;this.active=true;}
public Long getId(){return id;} public String getCustomerId(){return customerId;} public String getName(){return name;} public String getMobile(){return mobile;} public TariffType getTariffType(){return tariffType;} public boolean isActive(){return active;}
}