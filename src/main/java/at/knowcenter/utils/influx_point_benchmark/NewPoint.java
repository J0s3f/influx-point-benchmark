package at.knowcenter.utils.influx_point_benchmark;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

/**
 * Created by jschneider on 17.02.20.
 *
 * @author Josef H.B. Schneider  {@literal <jschneider@know-center.at>}
 */
public class NewPoint {
    private Number time;
    private TimeUnit precision = TimeUnit.NANOSECONDS;

    public NewPoint(Number time) {
        this.time = time;
    }

    public NewPoint(Number time, TimeUnit precision) {
        this.time = time;
        this.precision = precision;
    }

    public void formatedTime(final StringBuilder sb, final TimeUnit precision) {
        if (this.time == null) {
            return;
        }
        TimeUnit converterPrecision = precision;

        if (converterPrecision == null) {
            converterPrecision = TimeUnit.NANOSECONDS;
        }
        if (this.time instanceof BigInteger) {
            BigInteger time = (BigInteger) this.time;
            long conversionFactor = converterPrecision.convert(1, this.precision);
            if (conversionFactor >= 1) {
                time = time.multiply(BigInteger.valueOf(conversionFactor));
            } else {
                conversionFactor = this.precision.convert(1, converterPrecision);
                time = time.divide(BigInteger.valueOf(conversionFactor));
            }
            sb.append(" ").append(time);
        } else if (this.time instanceof BigDecimal) {
            BigDecimal time = (BigDecimal) this.time;
            long conversionFactor = converterPrecision.convert(1, this.precision);
            if (conversionFactor >= 1) {
                time = time.multiply(BigDecimal.valueOf(conversionFactor));
            } else {
                conversionFactor = this.precision.convert(1, converterPrecision);
                time = time.divide(BigDecimal.valueOf(conversionFactor), RoundingMode.HALF_UP);
            }
            sb.append(" ").append(time.toBigInteger());
        } else {
            sb.append(" ").append(converterPrecision.convert(this.time.longValue(), this.precision));
        }
    }
}
