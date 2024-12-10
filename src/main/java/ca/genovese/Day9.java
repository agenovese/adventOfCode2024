package ca.genovese;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


public class Day9 {

    public static long question1(Stream<String> lineStream) {
        List<Integer> inputValues = new ArrayList<>(Arrays.asList(lineStream.toList().getFirst().split("")))
                .stream().map(Integer::parseInt).toList();

        List<Integer> diskLayout = new ArrayList<>();
        int currentID = 0;
        for (int i = 0; i < inputValues.size(); i++) {
            Integer writeValue;
            if (i % 2 == 0) {
                writeValue = currentID++;
            } else {
                writeValue = null;
            }
            for (int j = 0; j < inputValues.get(i); j++) {
                diskLayout.add(writeValue);
            }
        }

        long checksum = 0;
        for (int blank = 0; blank < diskLayout.size(); blank++) {
            if (diskLayout.get(blank) == null) {
                for (int read = diskLayout.size() - 1; read >= blank; read--) {
                    if (diskLayout.get(read) != null) {
                        diskLayout.set(blank, diskLayout.get(read));
                        diskLayout.set(read, null);
                        break;
                    }
                }
            }
            if (diskLayout.get(blank) != null) {
                checksum += diskLayout.get(blank) * blank;
            }
        }

        return checksum;
    }

    record File(Integer id, int size, boolean free) {
    }

    public static long question2(Stream<String> lineStream) {
        List<Integer> inputValues = new ArrayList<>(Arrays.asList(lineStream.toList().getFirst().split(""))
                .stream().map(Integer::parseInt).toList());



        List<File> original = new ArrayList<>();
        List<File> defraged = new ArrayList<>();
        int currentId = 0;

        for (int i = 0; i < inputValues.size(); i++) {
            if (i % 2 == 0 && inputValues.get(i) != 0) {
                original.add(new File(currentId, inputValues.get(i), false));
                defraged.add(new File(currentId++, inputValues.get(i), false));
            } else {
                original.add(new File(null, inputValues.get(i), true));
                defraged.add(new File(null, inputValues.get(i), true));
            }
        }

//        List<Integer> currentDiskLayout = toDiskLayout(defraged);
//        currentDiskLayout.forEach(l -> System.out.print(l == null ? "." : l));
//        System.out.println();

        for (int read = 1; read <= defraged.size(); read++) {
            int readPosition = defraged.size() - read;
            if(Objects.equals(defraged.get(readPosition).id, 9998)) {
                System.out.println("?");
            }
            if (defraged.get(readPosition).id != null && defraged.get(readPosition).size != 0) {
                for (int write = 0; write < readPosition; write++) {
                    if (defraged.get(write).free && defraged.get(write).size >= defraged.get(readPosition).size) {
                        File file = defraged.remove( readPosition);

                        defraged.add(readPosition, new File(null, file.size, false));

                        File space = defraged.remove(write);
                        defraged.add(write, new File(null, space.size - file.size, true));
                        defraged.add(write, file);
                        defraged.add(write, new File(null, 0, true));

//                        currentDiskLayout = toDiskLayout(defraged);
//                        currentDiskLayout.forEach(l -> System.out.print(l == null ? "." : l));
//                        System.out.println();

                        break;
                    }
                }
            }
        }
        System.out.println(original.stream().toList());
        System.out.println(defraged.stream().toList());


        List<Integer> diskLayout = toDiskLayout(defraged);
//        diskLayout.forEach(l -> System.out.print(l == null ? "." : l));
//        System.out.println();

        long checksum = 0;
        for (int blank = 0; blank < diskLayout.size(); blank++) {
            if (diskLayout.get(blank) != null) {
                checksum += diskLayout.get(blank) * blank;
            }
        }

        return checksum;
    }

    private static List<Integer> toDiskLayout(List<File> defraged) {
        List<Integer> diskLayout = new ArrayList<>();

        for (int i = 0; i < defraged.size(); i++) {
            Integer writeValue;
            if (i % 2 == 0) {
                writeValue = defraged.get(i).id;
            } else {
                writeValue = null;
            }
            for (int j = 0; j < defraged.get(i).size; j++) {
                diskLayout.add(writeValue);
            }
        }
        return diskLayout;
    }


}