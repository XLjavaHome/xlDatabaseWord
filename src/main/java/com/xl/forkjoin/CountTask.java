package com.xl.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-23
 * @time 22:44
 * To change this template use File | Settings | File Templates.
 */
public class CountTask extends RecursiveTask<Integer> {
    private int threshold = 2;
    private int start;
    private int end;
    
    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            //任务分割
            leftTask.fork();
            rightTask.fork();
            //等待子任务结束得到结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
        //合并
            sum = leftResult + rightResult;
        }
        return sum;
    }
    
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(1, 100);
        ForkJoinTask<Integer> result = forkJoinPool.submit(task);
        System.out.println(result.get());
    }
}
