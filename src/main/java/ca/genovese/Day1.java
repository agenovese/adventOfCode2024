package ca.genovese;

import java.util.ArrayList;
import java.util.List;

public class Day1 {
    public static int listDistance(List<Integer> list1, List<Integer> list2) {
        List<Integer> sortedList1 = new ArrayList<>(list1);
        sortedList1.sort(Integer::compareTo);
        List<Integer> sortedList2 = new ArrayList<>(list2);
        sortedList2.sort(Integer::compareTo);

        assert sortedList1.size() == sortedList2.size();
        int distance = 0;
        for(int i = 0; i < sortedList1.size(); i++) {
            distance += Math.abs(sortedList1.get(i) - sortedList2.get(i));
        }

        return distance;
    }

    public static int listSimilarity(List<Integer> list1, List<Integer> list2) {
        List<Integer> sortedList1 = new ArrayList<>(list1);
        sortedList1.sort(Integer::compareTo);
        List<Integer> sortedList2 = new ArrayList<>(list2);
        sortedList2.sort(Integer::compareTo);

        int similarity = 0;

        for(int i : sortedList1) {
            similarity += (int) (sortedList2.stream().filter(j -> i == j).count() * i);
        }

        return similarity;
    }
}
