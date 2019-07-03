package dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TotalDto {

    private BigDecimal gross;
    private BigDecimal vat;

    public TotalDto(){

    }

    public BigDecimal getGross() {
        if(gross == null){
            gross = BigDecimal.ZERO;
        }
        return gross.setScale(2, RoundingMode.HALF_UP);
    }

    public void setGross(BigDecimal gross) {
        this.gross = gross;
    }

    public BigDecimal getVat() {
        if(vat == null){
            vat = BigDecimal.ZERO;
        }
        return vat.setScale(2, RoundingMode.HALF_UP);
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }
}
