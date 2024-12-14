import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Day2RedNosedReport {
    final static Path inputFile = Paths.get("resources/day-2-input.txt");

    public static void main(String[] args) {
        try {
            var reports = Files.readAllLines(inputFile);
            var safeReportsCount = 0;
            for (String report : reports) {
                var levels = report.split(" ");

                System.out.println("levels: " + Arrays.toString(levels));

                var isSafe = true;

                var isIncreasing = asInt(levels[0]) < asInt(levels[levels.length - 1]);

                System.err.println("Is increasing: " + isIncreasing);

                // check if all elements are from the same sign (positive or negative instead of
                // use isIncreasing var)
                // if a report if unsafe try an iteration remove each element to check if one of
                // then are safe

                for (int i = 0; i < levels.length; i++) {
                    if (i >= levels.length - 1) {
                        break;
                    }

                    if (isIncreasing) {
                        if (asInt(levels[i]) > asInt(levels[i + 1])
                                || (asInt(levels[i + 1]) - asInt(levels[i]) > 3)
                                || asInt(levels[i]) == asInt(levels[i + 1])) {
                            isSafe = false;
                            break;
                        }
                    } else {
                        if (asInt(levels[i]) < asInt(levels[i + 1])
                                || (asInt(levels[i]) - asInt(levels[i + 1]) > 3)
                                || asInt(levels[i]) == asInt(levels[i + 1])) {
                            isSafe = false;
                            break;
                        }
                    }
                }
                System.err.println("Is safe:" + isSafe);
                if (isSafe) {
                    safeReportsCount += 1;
                }
            }
            System.out.println(safeReportsCount);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static Integer asInt(String str) {
        return Integer.parseInt(str);
    }
}
