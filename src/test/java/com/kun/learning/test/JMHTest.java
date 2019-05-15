package com.kun.learning.test;

import com.kun.learning.java.io.FileIODemo;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by jrjiakun on 2018/12/13
 *    基于方法层面的基准测试，精度可以达到微秒级
 *
 *
 *    不能Debug
 *
 *    Throughput("thrpt", "Throughput, ops/time"),   // 整体吞吐量
 *     AverageTime("avgt", "Average time, time/op"),   // 调用平均时间
 *    SampleTime("sample", "Sampling time"),          // 随机取样，最后输出取样结果的分布,类似于 TP99
 *    SingleShotTime("ss", "Single shot invocation time"),  //以上模式都是默认一次 iteration 是 1s，唯有 SingleShotTime 是只运行一次。往往同时把 warmup 次数设为0，用于测试冷启动时的性能
 *     All("all", "All benchmark modes");



 *   Scope.Thread：默认的State，每个测试线程分配一个实例；

 *    Scope.Benchmark：所有测试线程共享一个实例，用于测试有状态实例在多线程共享下的性能；

 *    Scope.Group：每个线程组共享一个实例；
 *
 *
 *    Iteration 是 JMH 进行测试的最小单位。在大部分模式下，一次 iteration 代表的是一秒，JMH 会在这一秒内不断调用需要 benchmark 的方法，
 *    然后根据模式对其采样，计算吞吐量，计算平均执行时间等。
 *
 *
 *   `@Threads `    每个进程中的测试线程，可用于类或者方法上。一般选择为cpu乘以2。如果配置了 Threads.MAX ，代表使用 Runtime.getRuntime().availableProcessors() 个线程
 *
 *  `@Fork`  进行 fork 的次数。可用于类或者方法上  Fork出java虚拟机个数。
 *  `@Benchmark`   方法级注解，表示该方法是需要进行 benchmark 的对象，用法和 JUnit 的 @Test 类似
 *
 *  `@State`   State 用于声明某个类是一个“状态”，然后接受一个 Scope 参数用来表示该状态的共享范围。
 *
 *
 *  @link   http://tutorials.jenkov.com/java-performance/jmh.html
 *  @link https://www.jianshu.com/p/ad34c4c8a2a3
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)

public class JMHTest {

    @Benchmark
    public void bioFileCopy(){
        FileIODemo.bioFileCopy();
    }

    @Benchmark
    public void zeroCopyCopy(){
        FileIODemo.zeroCopy();
    }
    //warmup 预热行为， JIT机制的存在，如果某个函数被多次调用后，JVM会将其编译成机器码以提高执行速度
    public static void main(String[]args) throws Exception{
        //预热的迭代次数，默认1秒  实际测量的迭代次数，默认1秒。  可以用注解
        Options opt = new OptionsBuilder().include(JMHTest.class.getSimpleName()).forks(1).warmupIterations(5)
                .measurementIterations(5).build();
        new Runner(opt).run();
    }
}
