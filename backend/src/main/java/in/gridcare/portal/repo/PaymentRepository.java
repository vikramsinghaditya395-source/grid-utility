package in.gridcare.portal.repo;
import in.gridcare.portal.model.PaymentRecord; import org.springframework.data.jpa.repository.JpaRepository;
public interface PaymentRepository extends JpaRepository<PaymentRecord,Long>{}