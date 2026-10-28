package in.gridcare.portal.repo;
import in.gridcare.portal.model.Customer; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface CustomerRepository extends JpaRepository<Customer,Long>{Optional<Customer> findByCustomerIdAndMobileAndActiveTrue(String customerId,String mobile); Optional<Customer> findByCustomerId(String customerId);}