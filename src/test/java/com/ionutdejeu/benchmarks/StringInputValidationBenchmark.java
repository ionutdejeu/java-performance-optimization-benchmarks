package com.ionutdejeu.benchmarks;

import com.google.common.primitives.Chars;
import joptsimple.internal.Strings;
import org.apache.commons.collections4.CollectionUtils;
import org.openjdk.jmh.annotations.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StringInputValidationBenchmark {

    private static final List<String> INVALID_CHARACTERS_STRING_ARRAY = Arrays.asList("A", "B", "C", "D", "E", ";", "=", "?", "@", "#", "~");
    private static final List<Character> INVALID_CHARACTERS_ARRAY = Arrays.asList('A', 'B', 'C', 'D', 'E', ';', '=', '?', '@', '#', '~');
    private static final char[] INVALID_PRIMITIVE_CHARACTERS_ARRAY = new char[]{'A', 'B', 'C', 'D', 'E', ';', '=', '?', '@', '#', '~'};


    private static final List<String> INVALID_CHARACTERS_STRING_ARRAY_ADDITIONAL = Arrays.asList("<",">");

    private static final String STRING_TO_VALIDATE = "POILKMNIOIOIWOOKPIOPKKKKKKLLMMNJOPMLLLLLLLLLLLLLLLLMMMMMMNNNNNNNNNNNNNNNNNNN=";

    @Warmup(iterations = 4)
    @Measurement(iterations = 4)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public boolean validateStringSplitToList() {
        List<String> labelCharacters = Arrays.asList(STRING_TO_VALIDATE.split(""));
        if(CollectionUtils.containsAny(labelCharacters,INVALID_CHARACTERS_STRING_ARRAY)) {
            return false;
        }
        return true;
    }

    @Warmup(iterations = 4)
    @Measurement(iterations = 4)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public boolean validateStringCharactersArray() {
        List<Character> labelCharacters = Chars.asList(STRING_TO_VALIDATE.toCharArray());
        if(CollectionUtils.containsAny(labelCharacters,INVALID_CHARACTERS_STRING_ARRAY)) {
            return false;
        }
        return true;
    }

    @Warmup(iterations = 4)
    @Measurement(iterations = 4)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public String findFirstInvalidCharacter() {
        List<String> invalidStringArray = new ArrayList<>();
        invalidStringArray.addAll(INVALID_CHARACTERS_STRING_ARRAY);
        for (String invalidChar : invalidStringArray) {
            if(STRING_TO_VALIDATE.contains(invalidChar)){
                return invalidChar;
            }
        }
        return Strings.EMPTY;
    }

    @Warmup(iterations = 4)
    @Measurement(iterations = 4)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public Character findFirstInvalidCharacterWithCharArray() {
        List<Character> invalidStringArray = Chars.asList(STRING_TO_VALIDATE.toCharArray());
        for (Character invalidChar : invalidStringArray) {
            if(INVALID_CHARACTERS_ARRAY.indexOf(invalidChar)!=-1){
                return invalidChar;
            }
        }
        return null;
    }

    @Warmup(iterations = 4)
    @Measurement(iterations = 4)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public Character findFirstInvalidCharacterWithCharArrayForI_WithList() {
        char[] invalidCharsArray = STRING_TO_VALIDATE.toCharArray();
        for (int i = 0; i < invalidCharsArray.length; i++) {
            if(INVALID_CHARACTERS_ARRAY.indexOf(invalidCharsArray[i])!=-1){
                return invalidCharsArray[i];
            }
        }
        return null;
    }
    @Warmup(iterations = 4)
    @Measurement(iterations = 4)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public Character findFirstInvalidCharacterWithCharArrayForI_WithPrimitiveArray() {
        char[] invalidCharsArray = STRING_TO_VALIDATE.toCharArray();
        for (int i = 0; i < invalidCharsArray.length; i++) {
            for (int j = 0; j < INVALID_PRIMITIVE_CHARACTERS_ARRAY.length; j++) {
                if (INVALID_PRIMITIVE_CHARACTERS_ARRAY[j] == invalidCharsArray[i]) {
                    return invalidCharsArray[i];
                }
            }
        }
        return null;
    }
}
