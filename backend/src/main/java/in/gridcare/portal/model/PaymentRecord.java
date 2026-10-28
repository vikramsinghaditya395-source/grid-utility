package in.gridcare.portal.model;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDateTime;
@Entity @Table(name="payment_records",uniqueConstraints=@UniqueConstraint(columnNames="payment_reference"))
public class PaymentRecord {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@Column(nullable=false,unique=true) private String paymentReference;
@ManyToOne(fetch=FetchType.LAZY,optional=false) private ElectricityBill bill;
@Column(nullable=false,precision=12,scale=2) private BigDecimal amount;
@Column(nullable=false) private String method;
@Column(nullable=false) private LocalDateTime paidAt;
protected PaymentRecord(){}
public PaymentRecord(String ref,ElectricityBill b,BigDecimal amount,String method){this.paymentReference=ref;this.bill=b;this.amount=amount;this.method=method;this.paidAt=LocalDateTime.now();}
public Long getId(){return id;} public String getPaymentReference(){return paymentReference;} public ElectricityBill getBill(){return bill;} public BigDecimal getAmount(){return amount;} public String getMethod(){return method;} public LocalDateTime getPaidAt(){return paidAt;}
}