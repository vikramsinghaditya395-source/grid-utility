package in.gridcare.portal.service;
import in.gridcare.portal.model.TariffType; import in.gridcare.portal.service.tariff.*; import org.springframework.stereotype.Service; import java.math.*;
@Service public class TariffService{
public Tariff get(TariffType t){return t==TariffType.COMMERCIAL?new CommercialTariff():new ResidentialTariff();}
public Quote quote(TariffType t,BigDecimal units){if(units==null||units.signum()<0)throw PortalException.bad("Units must be zero or greater.");Tariff x=get(t);return new Quote(units.setScale(2,RoundingMode.HALF_UP),x.calculateEnergyCharge(units),x.fixedCharge(),x.total(units));}
public record Quote(BigDecimal unitsConsumed,BigDecimal energyCharge,BigDecimal fixedCharge,BigDecimal totalAmount){}
}