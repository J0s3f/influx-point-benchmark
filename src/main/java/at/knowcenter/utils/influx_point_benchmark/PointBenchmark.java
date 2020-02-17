package at.knowcenter.utils.influx_point_benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by jschneider on 17.02.20.
 *
 * @author Josef H.B. Schneider  {@literal <jschneider@know-center.at>}
 */

@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.All)
public class PointBenchmark {


    @State(Scope.Benchmark)
    public static class BenchmarkState {

        @Param({"2318793235476"})
        long value;

        NewPoint newPoint;
        OldPoint oldPoint;

        @Setup(Level.Trial)
        public void setup() {
            newPoint = new NewPoint(value);
            oldPoint = new OldPoint(value);
        }
    }


    @State(Scope.Thread)
    public static class ThreadState {
        StringBuilder sb = new StringBuilder();
    }


    @Benchmark
    public String benchmarkOldPoint(BenchmarkState state, ThreadState threadState) {
        threadState.sb.setLength(0);
        state.oldPoint.formatedTime(threadState.sb, TimeUnit.NANOSECONDS);
        return threadState.sb.toString();
    }

    @Benchmark
    public String benchmarkNewPoint(BenchmarkState state, ThreadState threadState) {
        threadState.sb.setLength(0);
        state.newPoint.formatedTime(threadState.sb, TimeUnit.NANOSECONDS);
        return threadState.sb.toString();
    }

}
