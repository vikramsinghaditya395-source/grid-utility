package in.gridcare.portal.service;
import in.gridcare.portal.model.*; import in.gridcare.portal.repo.*; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.math.*; import java.util.*;
@Service public class PortalService{
private final CustomerRepository customers;private final BillRepository bills;private final PaymentRepository payments;private final TariffService tariffs;
public PortalService(CustomerRepository c,BillRepository b,PaymentRepository p,TariffService t){customers=c;bills=b;payments=p;tariffs=t;}
public Customer verifyCustomer(String id,String mobile){return customers.findByCustomerIdAndMobileAndActiveTrue(id,mobile).orElseThrow(()->PortalException.unauthorized("Customer ID and registered mobile do not match."));}
public List<Customer> customers(){return customers.findAll();} public List<ElectricityBill> allBills(){return bills.findAllByOrderByCreatedAtDesc();} public List<ElectricityBill> customerBills(String id){return bills.findByCustomerCustomerIdOrderByCreatedAtDesc(id);}
public ElectricityBill customerBill(String customer,Long id){ElectricityBill b=bills.findById(id).orElseThrow(()->PortalException.missing("Bill not found."));if(!b.getCustomer().getCustomerId().equals(customer))throw PortalException.missing("Bill not found.");return b;}
public TariffService.Quote calculate(TariffType type,BigDecimal units){return tariffs.quote(type,units);}
@Transactional public ElectricityBill createBill(String id,String month,BigDecimal prev,BigDecimal curr,TariffType type){
if(prev==null||curr==null||prev.signum()<0||curr.compareTo(prev)<0)throw PortalException.bad("Current reading must be >= previous reading; readings cannot be negative.");
if(month==null||!month.matches("\\d{4}-(0[1-9]|1[0-2])"))throw PortalException.bad("Billing month must use YYYY-MM.");
Customer c=customers.findByCustomerId(id).orElseThrow(()->PortalException.missing("Customer not found."));if(bills.existsByCustomerCustomerIdAndBillingMonth(id,month))throw PortalException.conflict("A bill already exists for this customer and month.");
TariffType plan=type==null?c.getTariffType():type;TariffService.Quote q=tariffs.quote(plan,curr.subtract(prev));String no="GC-"+month.replace("-","")+"-"+id+"-"+UUID.randomUUID().toString().substring(0,6).toUpperCase();
return bills.save(new ElectricityBill(no,c,month,prev,curr,plan,q.energyCharge(),q.fixedCharge(),q.totalAmount()));}
@Transactional public PaymentRecord pay(String customer,Long id,String method){ElectricityBill b=customerBill(customer,id);if(b.getStatus()==BillStatus.PAID)throw PortalException.conflict("This bill has already been paid.");b.markPaid();return payments.save(new PaymentRecord("DEMO-"+UUID.randomUUID().toString().substring(0,12).toUpperCase(),b,b.getAmount(),method==null?"DEMO":method));}
}