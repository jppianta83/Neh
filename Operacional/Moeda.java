package operacional;
import java.math.BigDecimal;

public enum Moeda {
	UM, VINTECINCO, CINQUENTA, DEZ, CINCO;

	public BigDecimal valorMoeda() {
		switch(this) {
			case UM: return new BigDecimal(1.00);
			case VINTECINCO: return new BigDecimal(0.25);
			case CINQUENTA: return new BigDecimal(0.50);
			case DEZ: return new BigDecimal(0.10);
			case CINCO: return BigDecimal.valueOf(0.05);
			
			default: return null;
		}
	}
}
