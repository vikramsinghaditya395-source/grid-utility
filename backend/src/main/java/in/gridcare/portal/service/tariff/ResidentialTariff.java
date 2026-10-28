package in.gridcare.portal.service.tariff;
import java.math.BigDecimal; import java.math.RoundingMode;
public class ResidentialTariff extends Tariff{
public BigDecimal calculateEnergyCharge(BigDecimal units){BigDecimal u=units.max(BigDecimal.ZERO);return u.min(new BigDecimal("100")).multiply(new BigDecimal("3")).add(u.subtract(new BigDecimal("100")).max(BigDecimal.ZERO).min(new BigDecimal("200")).multiply(new BigDecimal("5"))).add(u.subtract(new BigDecimal("300")).max(BigDecimal.ZERO).multiply(new BigDecimal("7"))).setScale(2,RoundingMode.HALF_UP);}
public BigDecimal fixedCharge(){return new BigDecimal("75.00");}
}