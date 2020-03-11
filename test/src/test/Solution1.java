package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution1 {

    public static int findSum(List<List<Integer>> trainers) {

        int total = 0;
        int min = 0;

        List<Integer> finalCourse = new ArrayList<>();
        List<Integer> tmpCourse = new ArrayList<>();

        List<String> coursesNames = Arrays.asList("java", "angular", "database", "python");
        List<String> trainerNames = Arrays.asList("joe", "andy", "ricky", "sachin");

        for (int i = 0; i < trainers.get(0).size(); i++) {
            tmpCourse.add(i);
            total += trainers.get(0).get(i);
            //Traverse next trainers
            for (int j = 1; j < trainers.size(); j++) {
                List<Integer> sortedPrice = trainers.get(j).stream().sorted().collect(Collectors.toList());

                //get the minimum prices of course that's not present in tempCourses
                int minPrice = 0;
                for (Integer integer : sortedPrice) {
                    int courseIndex = trainers.get(j).indexOf(integer);
                    if (!tmpCourse.contains(courseIndex)) {
                        minPrice = integer;
                        tmpCourse.add(courseIndex);
                        break;
                    }
                }
                total += minPrice;
            }

            if (i == 0) {
                min = total;
                finalCourse.addAll(tmpCourse);
            }

            if (total < min) {
                min = total;
                finalCourse.removeAll(tmpCourse);
                finalCourse.addAll(tmpCourse);
            }
            tmpCourse.clear();
        }

        for (int i = 0; i < finalCourse.size(); i++) {
            System.out.println("Trainer " + trainerNames.get(i) + ", course : " + coursesNames.get(finalCourse.get(i)));
        }
        return min;
    }
}
