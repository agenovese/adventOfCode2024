package ca.genovese;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day3 {
    public static long parseInstructions(Stream<String> lineStream) {
        StringBuilder stringBuilder = new StringBuilder();
        lineStream.forEach(l -> stringBuilder.append(l).append("\n"));
        String input = stringBuilder.toString();
        Pattern pattern = Pattern.compile("(mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\))");

        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        AtomicLong total = new AtomicLong(0);
        pattern.matcher(input).results().forEach(r -> {
            if(r.group(0).equals("do()")) {
                atomicBoolean.set(true);
            } else if(r.group(0).equals("don't()")) {
                atomicBoolean.set(false);
            } else if(atomicBoolean.get()) {
                total.getAndAdd(Long.parseLong(r.group(2)) * Long.parseLong(r.group(3)));
            }
        });

        return total.get();
    }


}
