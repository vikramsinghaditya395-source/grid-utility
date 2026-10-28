package in.gridcare.portal.web;
import in.gridcare.portal.service.PortalException;import org.springframework.http.*;import org.springframework.web.bind.MethodArgumentNotValidException;import org.springframework.web.bind.annotation.*;import java.time.Instant;import java.util.Map;
@RestControllerAdvice public class ApiExceptionHandler{
@ExceptionHandler(PortalException.class) ResponseEntity<Map<String,Object>> portal(PortalException e){return ResponseEntity.status(e.getStatus()).body(Map.of("timestamp",Instant.now().toString(),"status",e.getStatus().value(),"message",e.getMessage()));}
@ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<Map<String,Object>> invalid(MethodArgumentNotValidException e){String m=e.getBindingResult().getFieldErrors().stream().findFirst().map(x->x.getField()+": "+x.getDefaultMessage()).orElse("Invalid request.");return ResponseEntity.badRequest().body(Map.of("status",400,"message",m));}
@ExceptionHandler(IllegalStateException.class) ResponseEntity<Map<String,Object>> state(IllegalStateException e){return ResponseEntity.status(409).body(Map.of("status",409,"message",e.getMessage()));}
@ExceptionHandler(Exception.class) ResponseEntity<Map<String,Object>> other(Exception e){return ResponseEntity.status(500).body(Map.of("status",500,"message","Unexpected server error."));}
}