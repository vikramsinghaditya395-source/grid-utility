package in.gridcare.portal.model;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDateTime;
@Entity @Table(name="electricity_bills",uniqueConstraints=@UniqueConstraint(columnNames={"customer_id","billing_month"}))
public class ElectricityBill {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@Column(nullable=false,unique=true) private String billNumber;
@ManyToOne(fetch=FetchType.LAZY,optional=false) private Customer customer;
@Column(nullable=false) private String billingMonth;
@Column(nullable=false,precision=12,scale=2) private BigDecimal previousReading;
@Column(nullable=false,precision=12,scale=2) private BigDecimal currentReading;
@Column(nullable=false,precision=12,scale=2) private BigDecimal unitsConsumed;
@Enumerated(EnumType.STRING) @Column(nullable=false) private TariffType tariffType;
@Column(nullable=false,precision=12,scale=2) private BigDecimal energyCharge;
@Column(nullable=false,precision=12,scale=2) private BigDecimal fixedCharge;
@Column(nullable=false,precision=12,scale=2) private BigDecimal amount;
@Enumerated(EnumType.STRING) @Column(nullable=false) private BillStatus status=BillStatus.UNPAID;
@Column(nullable=false) private LocalDateTime createdAt=LocalDateTime.now();
protected ElectricityBill(){}
public ElectricityBill(String number,Customer c,String month,BigDecimal prev,BigDecimal curr,TariffType type,BigDecimal energy,BigDecimal fixed,BigDecimal amount){this.billNumber=number;this.customer=c;this.billingMonth=month;this.previousReading=prev;this.currentReading=curr;this.unitsConsumed=curr.subtract(prev);this.tariffType=type;this.energyCharge=energy;this.fixedCharge=fixed;this.amount=amount;this.status=BillStatus.UNPAID;this.createdAt=LocalDateTime.now();}
public Long getId(){return id;} public String getBillNumber(){return billNumber;} public Customer getCustomer(){return customer;} public String getBillingMonth(){return billingMonth;} public BigDecimal getPreviousReading(){return previousReading;} public BigDecimal getCurrentReading(){return currentReading;} public BigDecimal getUnitsConsumed(){return unitsConsumed;} public TariffType getTariffType(){return tariffType;} public BigDecimal getEnergyCharge(){return energyCharge;} public BigDecimal getFixedCharge(){return fixedCharge;} public BigDecimal getAmount(){return amount;} public BillStatus getStatus(){return status;} public LocalDateTime getCreatedAt(){return createdAt;}
public void markPaid(){if(status==BillStatus.PAID)throw new IllegalStateException("Bill is already paid.");status=BillStatus.PAID;}
}