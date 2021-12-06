package com.company;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        int[] mas = {5, 7, 8, 9, 0, 11, 55};
        FindMaxTask findMaxTask = new FindMaxTask(mas, 1, 7);
        Integer res = forkJoinPool.invoke(findMaxTask);
        System.out.println(res);


    }


    static class FindMaxTask extends RecursiveTask<Integer> {
        private int[] array;
        private int start, end;

        public FindMaxTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= 4) {
                List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
                Optional<Integer> optional = list.stream().max(Comparator.naturalOrder());
                return optional.orElse(-100);
            } else {
                int mid = (end - start) / 2 + start;
                FindMaxTask left = new FindMaxTask(array, start, mid);
                FindMaxTask right = new FindMaxTask(array, mid + 1, end);
                ForkJoinTask.invokeAll(right, left);
                int leftRes = left.getRawResult();
                int rightRes = right.getRawResult();
                return Integer.max(leftRes, rightRes);
            }
        }

    }
}
