package com.huan.es.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description TODO 线程池配置  定时任务，异步任务
 * @Author ahuan
 * @Date 2020/08/20 15:19
 * @Version V1.0
 **/
@Configuration
@EnableAsync
public class ExecutorConfig implements SchedulingConfigurer, AsyncConfigurer {
    private static final Logger log= LoggerFactory.getLogger("ExecutorConfig");
    /**
     * 定时任务使用的线程池
     * @return
     */
    @Bean(destroyMethod = "shutdown", name = "taskScheduler")
    public ThreadPoolTaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(100);
        scheduler.setThreadNamePrefix("disaster-task-");
        scheduler.setAwaitTerminationSeconds(600);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        return scheduler;
    }

    /**
     * 异步任务执行线程池
     * @return
     */
    @Bean(name = "asyncExecutor")
    public ThreadPoolTaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setQueueCapacity(1000);
        executor.setKeepAliveSeconds(600);
        executor.setMaxPoolSize(20);
        executor.setThreadNamePrefix("disaster-async-taskExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler taskScheduler = taskScheduler();
        scheduledTaskRegistrar.setTaskScheduler(taskScheduler);
    }

    @Override
    public Executor getAsyncExecutor() {
        return asyncExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            log.error("异步任务执行出现异常, message {}, emthod {}, params {}", throwable, method, objects);
        };
    }

}