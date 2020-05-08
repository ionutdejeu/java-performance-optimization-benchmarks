package com.ionutdejeu.benchmarks;

import com.google.common.primitives.Chars;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListOperationsBenchmark {
    static final String superset = "random string random string random string random string random string";
    static final String subset = "random string random string";
    static final char[] superSetArray = superset.toCharArray();
    static final char[] subSetArray = subset.toCharArray();

    @Warmup(iterations = 4)
    @Measurement(iterations = 4)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public boolean checkSubsetListFromSplitString() {
        List<String> superSetList = Arrays.asList(superset.split(""));
        List<String> subSetList = Arrays.asList(subset.split(""));
        if (superSetList.containsAll(subSetList)) {
            return true;
        }
        return false;
    }

    @Warmup(iterations = 4)
    @Measurement(iterations = 4)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public boolean checkSubsetListFromCharArray() {
        List<Character> superSetList = Chars.asList(superSetArray);
        List<Character> subSetList = Chars.asList(subSetArray);
        return superSetList.containsAll(subSetList);
    }




}
