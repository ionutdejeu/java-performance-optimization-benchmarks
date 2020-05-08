package com.ionutdejeu.benchmarks;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                //.include(ArrayListBenchMark.class.getSimpleName())
                //.include(ListOperationsBenchmark.class.getSimpleName())
                .include(StringInputValidationBenchmark.class.getSimpleName())
                .forks(1)
                .jvmArgsAppend("-Djava.io.temdir=C:\\temp")
                .build();
        new Runner(opt).run();
    }
}
