package com.zqw.springsession.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionExpiredEvent;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * //TODO
 *
 * @author zhuquanwen
 * @vesion 1.0
 * @date 2018/9/17 13:59
 * @since jdk1.8
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 120)
@Slf4j
public class SpringSessionConfiguration {
    /**
     * Spring-session-redis执行线程池
     */
    @Bean
    public ThreadPoolTaskScheduler springSessionRedisTaskExecutor() {
        ThreadPoolTaskScheduler taskSchedule = new ThreadPoolTaskScheduler();
        taskSchedule.setPoolSize(3);
        return taskSchedule;
    }

    @Bean
    public DefaultCookieSerializer defaultCookieSerializer() {
        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
        defaultCookieSerializer.setCookiePath("/");
        return defaultCookieSerializer;
    }

    /**
     * Redis内session过期事件监听
     *
     * @param expiredEvent
     */
    @EventListener
    public void onSessionExpired(SessionExpiredEvent expiredEvent) {
        String sessionId = expiredEvent.getSessionId();
//        log.info(expiredEvent.getSession().getAttribute("user"));
        log.info("[{}]session过期", sessionId);
    }

    /**
     * Redis内session删除事件监听
     *
     * @param deletedEvent
     */
    @EventListener
    public void onSessionDeleted(SessionDeletedEvent deletedEvent) {
        String sessionId = deletedEvent.getSessionId();
//        log.info(deletedEvent.getSession().getAttribute("user"));
        log.info("删除session[{}]", sessionId);
    }

    /**
     * Redis内session保存事件监听
     *
     * @param createdEvent
     */
    @EventListener
    public void onSessionCreated(SessionCreatedEvent createdEvent) {
        String sessionId = createdEvent.getSessionId();
//        log.info(createdEvent.getSession().getAttribute("user"));
        log.info("保存session[{}]", sessionId);
    }

}
