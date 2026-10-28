package in.gridcare.portal.service;
import org.springframework.http.HttpStatus;
public class PortalException extends RuntimeException{
private final HttpStatus status; public PortalException(HttpStatus s,String m){super(m);status=s;} public HttpStatus getStatus(){return status;}
public static PortalException bad(String m){return new PortalException(HttpStatus.BAD_REQUEST,m);} public static PortalException unauthorized(String m){return new PortalException(HttpStatus.UNAUTHORIZED,m);} public static PortalException missing(String m){return new PortalException(HttpStatus.NOT_FOUND,m);} public static PortalException conflict(String m){return new PortalException(HttpStatus.CONFLICT,m);}
}