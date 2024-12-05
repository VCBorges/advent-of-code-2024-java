import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day1HistorianHysteria {

    final static Path inputFile = Paths.get("resources/day-1-input.txt");

    public static void main(String[] args) {
        var leftList = new ArrayList<Integer>();
        var rightList = new ArrayList<Integer>();
        try {
            var lines = Files.readAllLines(inputFile);
            for (String line : lines) {
                var splittedLine = line.split(" ");
                var leftValue = Integer.parseInt(splittedLine[0]);
                var rightValue = Integer.parseInt(splittedLine[splittedLine.length - 1]);
                leftList.add(leftValue);
                rightList.add(rightValue);
            }
            leftList.sort(null);
            rightList.sort(null);

            var totalDistance = 0;

            for (int i = 0; i < rightList.size(); i++) {
                var distance = Math.abs(leftList.get(i) - rightList.get(i));
                totalDistance += distance;
            }

            System.out.println(totalDistance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
