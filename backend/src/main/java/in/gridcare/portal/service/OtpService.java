package in.gridcare.portal.service;
import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service; import java.security.SecureRandom; import java.time.Instant; import java.util.concurrent.ConcurrentHashMap; import java.util.Map;
@Service public class OtpService{
private final SecureRandom random=new SecureRandom(); private final Map<String,Entry> codes=new ConcurrentHashMap<>();
@Value("${app.otp-expiry-minutes:5}") private long expiry; @Value("${app.otp-max-attempts:5}") private int maxAttempts;
public String issue(String request,String customer){String code=String.format("%06d",random.nextInt(1_000_000));codes.put(request,new Entry(code,customer,Instant.now().plusSeconds(expiry*60),0));return code;}
public void verify(String request,String customer,String code){Entry e=codes.get(request);if(e==null||!e.customer.equals(customer))throw PortalException.unauthorized("OTP request is invalid; request a new OTP.");if(Instant.now().isAfter(e.expires)) {codes.remove(request);throw PortalException.unauthorized("OTP expired; request a new OTP.");}if(e.attempts>=maxAttempts){codes.remove(request);throw PortalException.unauthorized("Too many attempts; request a new OTP.");}if(!e.code.equals(code)){codes.put(request,new Entry(e.code,e.customer,e.expires,e.attempts+1));throw PortalException.unauthorized("Incorrect OTP.");}codes.remove(request);}
private record Entry(String code,String customer,Instant expires,int attempts){}
}