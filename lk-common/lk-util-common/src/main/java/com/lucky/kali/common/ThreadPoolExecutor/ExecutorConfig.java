package com.lucky.kali.common.ThreadPoolExecutor;

import com.lucky.kali.common.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步线程池配置类
 *
 * @author Elliot
 */
@Slf4j
@EnableAsync
@Configuration
public class ExecutorConfig {

    @Bean(name = "asyncServiceExecutor")
    public Executor asyncServiceExecutor() {
        log.info("start asyncServiceExecutor");
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        /*配置核心线程数*/
        executor.setCorePoolSize(CommonConstants.ASYNC_EXECUTOR_THREAD_CORE_POOL_SIZE);
        /*配置最大线程数*/
        executor.setMaxPoolSize(CommonConstants.ASYNC_EXECUTOR_THREAD_MAX_POOL_SIZE);
        /*配置队列大小*/
        executor.setQueueCapacity(CommonConstants.ASYNC_EXECUTOR_THREAD_QUEUE_CAPACITY);
        /*配置线程池中的线程的名称前缀*/
        executor.setThreadNamePrefix(CommonConstants.ASYNC_EXECUTOR_THREAD_NAME_PREFIX);

        /*rejection-policy：当pool已经达到max size的时候，如何处理新任务*/
        /*CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行*/
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        /*执行初始化*/
        executor.initialize();
        return executor;
    }
}