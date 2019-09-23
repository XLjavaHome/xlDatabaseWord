package com.xl.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-23
 * @time 23:25
 * To change this template use File | Settings | File Templates.
 */
public class SumTask extends RecursiveTask<Long>{
    //默认任务计算限制
    private int taskSize=100;
    //局部变量
    private List<Integer> sumList;
    
    public SumTask(int taskSize, List<Integer> sumList) {
        this.taskSize = taskSize;
        this.sumList = sumList;
    }
    
    public SumTask(List<Integer> sumList) {
        this.sumList = sumList;
    }
    
    @Override
    protected Long compute() {
        if(this.sumList.size()<=this.taskSize){
            //若集合数量小于限制值则直接计算
            long sum = 0;
            for(Integer item :this.sumList){
                sum += item;
            }
            System.out.println(String.format("Sum List[%d] = %d", this.sumList.size(), sum));
            return sum;
        }
        // 任务大于限制值,则一分为二:
        int middle = (this.sumList.size()) / 2;
        System.out.println(String.format("Split Task List[%d] ==> List[%d], List[%d]", this.sumList.size(), this.sumList.size()-middle,middle));
        SumTask subTask1 = new SumTask(this.taskSize,this.sumList.subList(0,middle));
        SumTask subTask2 = new SumTask(this.taskSize,this.sumList.subList(middle,this.sumList.size()));
        invokeAll(subTask1, subTask2);
        Long subResult1 = subTask1.join();
        Long subResult2 = subTask2.join();
        Long result = subResult1 + subResult2;
        System.out.println("Sum Split Task Result = " + subResult1 + " + " + subResult2 + " ==> " + result);
        return result;
    }
    
    public static void main(String[] args) {
        //获取当前系统CPU核数
        int coreNumber = Runtime.getRuntime().availableProcessors();
        List<Integer> originalList = new ArrayList<>();
        for(int i=0;i<100;i++){
            originalList.add(i);
        }
        ForkJoinPool forkJoinPool = new ForkJoinPool(coreNumber);
        ForkJoinTask<Long> task = new SumTask(10, originalList);
        long startTime = System.currentTimeMillis();
        Long result = 0L;
        //等待结果返回
        result=forkJoinPool.invoke(task);
        //使用Future 获取结果
        //        Future<Long> future = forkJoinPool.submit(task);
        //        try {
        //            result= future.get();
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        } catch (ExecutionException e) {
        //            e.printStackTrace();
        //        }
        long endTime = System.currentTimeMillis();
        System.out.println("Sum Task Result : " + result + " Cost Time : " + (endTime - startTime) + " ms.");
        forkJoinPool.shutdown();
    }
}
