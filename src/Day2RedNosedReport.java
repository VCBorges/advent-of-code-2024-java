import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2RedNosedReport {
    final static Path inputFile = Paths.get("resources/day-2-input.txt");

    public static void main(String[] args) {
        try {
            var reports = Files.readAllLines(inputFile);
            var safeReportsCount = 0;
            for (String report : reports) {
                List<Integer> levels = Arrays.stream(report.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                System.err.println("Levels: " + levels);
                var results = getReportResults(levels);
                System.err.println("Results: " + results);
                var isSafe = isReportSafe(results);
                System.err.println("Is safe: " + isSafe);
                if (isSafe){
                    safeReportsCount += 1;
                } else {
                    for (int i = 0; i < levels.size(); i++){
                        // var newResults = new ArrayList<>(results);
                        var levelsCopy = new ArrayList<>(levels);
                        levelsCopy.remove(i);
                        var newResults = getReportResults(levelsCopy);
                        System.err.println("I: " + i);
                        System.err.println("New results: " + newResults);
                        var isNewResultSafe = isReportSafe(newResults);
                        System.err.println("Is new safe: " + isNewResultSafe);
                        if (isNewResultSafe){
                            safeReportsCount += 1;
                            break;
                        }
                    }                    
                }
                
            }
            System.out.println(safeReportsCount);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static Boolean isReportSafe(List<Integer> reportResults){
        if (reportResults.stream().allMatch(
            level -> level >= 1 & level <= 3 
        ))
            return true;

        if (reportResults.stream().allMatch(
            level -> level <= -1 & level >= -3 
        ))
            return true;

        return false;
    }


    public static List<Integer> getReportResults(List<Integer> report){
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < report.size() - 1; i ++){
            var diff = report.get(i) - report.get(i + 1);
            results.add(diff);       
        }
        return results;        
    }
}
