package ca.genovese;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day2 {
    public static long getSafeReports(Stream<String> lineStream) {
        List<String> lines = lineStream.toList();
        List<Boolean> safe = new ArrayList<>();


        lines.forEach(line -> {
            String[] split = line.split("\s+");
            ArrayList<Integer> report = new ArrayList<>(Arrays.stream(split).map(Integer::parseInt).toList());
            boolean reportSafe = isReportSafe(report);

            for(int i = 0; i < report.size(); i++) {
                if(!reportSafe) {
                    Integer removed = report.remove(i);
                    reportSafe = isReportSafe(report);
                    report.add(i, removed);
                }
            }
            safe.add(reportSafe);
        });

        return safe.stream().filter(b -> b).count();
    }

    private static boolean isReportSafe(List<Integer> report) {
        Boolean inc = null;
        boolean reportSafe = true;
        for(int i = 0; i < report.size() - 1; i++) {
            Integer current = report.get(i + 1);
            Integer prev = report.get(i);
            if (inc == null) {
                inc = current > prev;
            }
            if (Math.abs(current - prev) == 0 || Math.abs(current - prev) > 3 ||  inc != (current > prev)) {
                reportSafe = false;
            }
        }
        return reportSafe;
    }

}
