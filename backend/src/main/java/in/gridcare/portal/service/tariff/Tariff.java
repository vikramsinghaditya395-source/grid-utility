package in.gridcare.portal.service.tariff;
import java.math.BigDecimal; import java.math.RoundingMode;
public abstract class Tariff { public abstract BigDecimal calculateEnergyCharge(BigDecimal units); public abstract BigDecimal fixedCharge(); public BigDecimal total(BigDecimal units){return calculateEnergyCharge(units).add(fixedCharge()).setScale(2,RoundingMode.HALF_UP);} }