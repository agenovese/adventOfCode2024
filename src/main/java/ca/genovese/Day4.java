package ca.genovese;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day4 {
    public static long wordsearch(Stream<String> lineStream) {
        List<List<String>> wordSearchGrid = new ArrayList<>();
        lineStream.forEach(l -> wordSearchGrid.add(l.chars().mapToObj(i -> String.valueOf((char) i)).toList()));


        int count = 0;
        for (int row = 0; row < wordSearchGrid.size(); row++) {
            for (int col = 0; col < wordSearchGrid.get(row).size(); col++) {
                if (wordSearchGrid.get(row).get(col).equals("A")
                        && row >= 1
                        && wordSearchGrid.size() - row > 1
                        && col >= 1
                        && wordSearchGrid.get(row).size() - col > 1) {
                    if (
                            ((wordSearchGrid.get(row - 1).get(col - 1).equals("M")
                                    && wordSearchGrid.get(row + 1).get(col + 1).equals("S"))
                                    || (wordSearchGrid.get(row - 1).get(col - 1).equals("S")
                                    && wordSearchGrid.get(row + 1).get(col + 1).equals("M")))
                                    &&
                                    ((wordSearchGrid.get(row - 1).get(col + 1).equals("M")
                                    && wordSearchGrid.get(row + 1).get(col - 1).equals("S"))
                                    || (wordSearchGrid.get(row - 1).get(col + 1).equals("S")
                                    && wordSearchGrid.get(row + 1).get(col - 1).equals("M")))
                    ) {
                        //UP
                        count++;
                    }

                }
            }
        }


        return count;
    }

    public static long wordsearch2(Stream<String> lineStream) {
        List<List<String>> wordSearchGrid = new ArrayList<>();
        lineStream.forEach(l -> wordSearchGrid.add(l.chars().mapToObj(i -> String.valueOf((char) i)).toList()));


        int count = 0;
        for (int row = 0; row < wordSearchGrid.size(); row++) {
            for (int col = 0; col < wordSearchGrid.get(row).size(); col++) {
                if (wordSearchGrid.get(row).get(col).equals("X")) {
                    if (row >= 3
                            && wordSearchGrid.get(row - 1).get(col).equals("M")
                            && wordSearchGrid.get(row - 2).get(col).equals("A")
                            && wordSearchGrid.get(row - 3).get(col).equals("S")) {
                        //UP
                        count++;
                    }
                    if (row >= 3
                            && col >= 3
                            && wordSearchGrid.get(row - 1).get(col - 1).equals("M")
                            && wordSearchGrid.get(row - 2).get(col - 2).equals("A")
                            && wordSearchGrid.get(row - 3).get(col - 3).equals("S")) {
                        //UP LEFT
                        count++;
                    }
                    if (col >= 3
                            && wordSearchGrid.get(row).get(col - 1).equals("M")
                            && wordSearchGrid.get(row).get(col - 2).equals("A")
                            && wordSearchGrid.get(row).get(col - 3).equals("S")) {
                        //LEFT
                        count++;
                    }
                    if (wordSearchGrid.size() - row > 3
                            && col >= 3
                            && wordSearchGrid.get(row + 1).get(col - 1).equals("M")
                            && wordSearchGrid.get(row + 2).get(col - 2).equals("A")
                            && wordSearchGrid.get(row + 3).get(col - 3).equals("S")) {
                        //DOWN LEFT
                        count++;
                    }

                    if (wordSearchGrid.size() - row > 3
                            && wordSearchGrid.get(row + 1).get(col).equals("M")
                            && wordSearchGrid.get(row + 2).get(col).equals("A")
                            && wordSearchGrid.get(row + 3).get(col).equals("S")) {
                        //DOWN
                        count++;
                    }
                    if (wordSearchGrid.size() - row > 3
                            && wordSearchGrid.get(row).size() - col > 3
                            && wordSearchGrid.get(row + 1).get(col + 1).equals("M")
                            && wordSearchGrid.get(row + 2).get(col + 2).equals("A")
                            && wordSearchGrid.get(row + 3).get(col + 3).equals("S")) {
                        //DOWN RIGHT
                        count++;
                    }
                    if (wordSearchGrid.get(row).size() - col > 3
                            && wordSearchGrid.get(row).get(col + 1).equals("M")
                            && wordSearchGrid.get(row).get(col + 2).equals("A")
                            && wordSearchGrid.get(row).get(col + 3).equals("S")) {
                        //RIGHT
                        count++;
                    }
                    if (row >= 3
                            && wordSearchGrid.get(row).size() - col > 3
                            && wordSearchGrid.get(row - 1).get(col + 1).equals("M")
                            && wordSearchGrid.get(row - 2).get(col + 2).equals("A")
                            && wordSearchGrid.get(row - 3).get(col + 3).equals("S")) {
                        //UP RIGHT
                        count++;
                    }

                }
            }
        }


        return count;
    }


}
