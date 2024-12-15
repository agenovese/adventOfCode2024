package ca.genovese;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day13 {
    record Machine(BigInteger ax, BigInteger ay, BigInteger bx, BigInteger by, BigInteger x, BigInteger y) {
    }

    record Move(BigInteger a, BigInteger b) {
        BigInteger cost() {
            return a.multiply(BigInteger.valueOf(3)).add(b);
        }
    }

    private static final Pattern p = Pattern.compile("[^:]*: X[=+](?<x>[0-9]+), Y[=+](?<y>[0-9]+)");


    public static BigInteger question1(Stream<String> lineStream) {
        List<Machine> machines = parseMachines(lineStream);

        return machines.stream()
                .map(Day13::maximizeMatch)
                .flatMap(Optional::stream)
                .map(Move::cost)
                .reduce(BigInteger.ZERO, (a, b) -> a.add(b));
    }

    private static Optional<Move> maximizeMatch(Machine machine) {
//        A = (p_x*b_y - prize_y*b_x) / (a_x*b_y - a_y*b_x)
//        B = (a_x*p_y - a_y*p_x) / (a_x*b_y - a_y*b_x)

        BigInteger a = machine.x.multiply(machine.by).subtract(machine.y.multiply(machine.bx))
                .divide(machine.ax.multiply(machine.by).subtract(machine.ay.multiply(machine.bx)));

        BigInteger b = machine.ax.multiply(machine.y).subtract(machine.ay.multiply(machine.x))
                .divide(machine.ax.multiply(machine.by).subtract(machine.ay.multiply(machine.bx)));

        return Optional.of(new Move(a, b)).filter(move -> machine.x.equals(machine.ax.multiply(move.a).add(machine.bx.multiply(move.b)))
                && machine.y.equals(machine.ay.multiply(move.a).add(machine.by.multiply(move.b))));
    }
//    private static Optional<Move> maximizeMatch(Machine machine) {
//        int gcdXA = gcd(machine.ax.intValue(), machine.bx.intValue()); // GCD for x increments
//        int gcdYA = gcd(machine.ay.intValue(), machine.by.intValue()); // GCD for y increments
//
//        // Ensure the targets are multiples of the GCD
//        if (!machine.x.mod(BigInteger.valueOf(gcdXA)).equals(BigInteger.ZERO) ||
//                !machine.y.mod(BigInteger.valueOf(gcdYA)).equals(BigInteger.ZERO)) {
//            return Optional.empty(); // No solution exists
//        }
//
//        BigInteger bestCost = BigInteger.valueOf(Long.MAX_VALUE);
//        BigInteger bestA = null, bestB = null;
//
//        // Reduce modulo xB to find valid values of a
//        for (BigInteger a = BigInteger.ZERO; a.compareTo(machine.bx) < 0 ; a = a.add(BigInteger.ONE)) {
//            BigInteger xRemaining = machine.x.subtract(machine.ax.multiply(a));
//            BigInteger yRemaining = machine.y.subtract(machine.ay.multiply(a));
//
//            if (xRemaining.mod(machine.bx).equals(BigInteger.ZERO) &&
//                    yRemaining.mod(machine.by).equals(BigInteger.ZERO)) {
//                BigInteger b = xRemaining.divide(machine.bx);
//
//                if (yRemaining.divide(machine.by).equals(b) && b.compareTo(BigInteger.ZERO) >= 0) {
//                    BigInteger cost = BigInteger.valueOf(3).multiply(a)
//                            .add(BigInteger.valueOf(1).multiply(b));
//                    if (cost.compareTo(bestCost) < 0) {
//                        bestCost = cost;
//                        bestA = a;
//                        bestB = b;
//                    }
//                }
//            }
//        }
//
//        // Return the best result if found
//        if (bestA != null && bestB != null) {
//            Move result = new Move(bestA, bestB);
//            assert result.cost().equals(bestCost);
//            return Optional.of(result);
//        }
//        return Optional.empty(); // No solution found
//    }


    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static List<Machine> parseMachines(Stream<String> lineStream) {
        List<String> inputLines = lineStream.toList();
        List<Machine> Machines = new ArrayList<>();
        for (int i = 0; i < inputLines.size(); ) {
            while (inputLines.get(i).isBlank()) {
                i++;
            }

            String buttonAString = inputLines.get(i++);
            Matcher buttonA = p.matcher(buttonAString);
            buttonA.matches();
            BigInteger buttonAX = BigInteger.valueOf(Long.parseLong(buttonA.group("x")));
            BigInteger buttonAY = BigInteger.valueOf(Long.parseLong(buttonA.group("y")));

            String buttonBString = inputLines.get(i++);
            Matcher buttonB = p.matcher(buttonBString);
            buttonB.matches();
            BigInteger buttonBX = BigInteger.valueOf(Long.parseLong(buttonB.group("x")));
            BigInteger buttonBY = BigInteger.valueOf(Long.parseLong(buttonB.group("y")));

            String prizeString = inputLines.get(i++);
            Matcher prize = p.matcher(prizeString);
            prize.matches();
            BigInteger prizeX = BigInteger.valueOf(Long.parseLong(prize.group("x")));
            BigInteger prizeY = BigInteger.valueOf(Long.parseLong(prize.group("y")));

            Machines.add(new Machine(buttonAX, buttonAY,
                    buttonBX, buttonBY,
                    prizeX, prizeY));
        }
        return Machines;
    }

    public static BigInteger question2(Stream<String> lineStream) {
        BigInteger inc = BigInteger.valueOf(10000000000000L);
        List<Machine> machines = parseMachines(lineStream).stream().map(machine -> new Machine(machine.ax, machine.ay, machine.bx, machine.by, machine.x.add(inc), machine.y.add(inc))).toList();

        return machines.stream()
                .map(Day13::maximizeMatch)
                .flatMap(Optional::stream)
                .map(Move::cost)
                .reduce(BigInteger.ZERO, BigInteger::add);


    }

}