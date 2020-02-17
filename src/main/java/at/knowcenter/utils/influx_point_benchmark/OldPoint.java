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
public class OldPoint {
    private Long time;
    private TimeUnit precision = TimeUnit.NANOSECONDS;

    public OldPoint(Long time) {
        this.time = time;
    }

    public OldPoint(Long time, TimeUnit precision) {
        this.time = time;
        this.precision = precision;
    }

    public void formatedTime(final StringBuilder sb, final TimeUnit precision) {
        if (this.time == null) {
            return;
        }
        if (precision == null) {
            sb.append(" ").append(TimeUnit.NANOSECONDS.convert(this.time, this.precision));
            return;
        }
        sb.append(" ").append(precision.convert(this.time, this.precision));
    }

}
