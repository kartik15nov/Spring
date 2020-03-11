package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class Solution1Test {

    static List<List<Integer>> trainers = new ArrayList<>();

    @BeforeAll
    static void init() {

        List<Integer> joe = new ArrayList<>();
        List<Integer> andy = new ArrayList<>();
        List<Integer> ricky = new ArrayList<>();
        List<Integer> sachin = new ArrayList<>();

        joe.add(2000);
        joe.add(6000);
        joe.add(8000);
        joe.add(1000);

        andy.add(7000);
        andy.add(3000);
        andy.add(9000);
        andy.add(4000);

        ricky.add(8000);
        ricky.add(6000);
        ricky.add(8000);
        ricky.add(1000);

        sachin.add(9000);
        sachin.add(2000);
        sachin.add(1000);
        sachin.add(4000);

        trainers.add(joe);
        trainers.add(andy);
        trainers.add(ricky);
        trainers.add(sachin);
    }

    @Test
    void findSum() {
        System.out.println(Solution1.findSum(trainers));
    }
}