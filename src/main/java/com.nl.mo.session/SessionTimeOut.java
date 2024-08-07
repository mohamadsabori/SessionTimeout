package com.nl.mo.session;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract sealed interface SessionTimeOut {
    void timeOut(String sessionId);
    void addSession(String sessionId);

    final class A implements SessionTimeOut {
        private static final Logger log = LoggerFactory.getLogger(SessionTimeOut.class);
        private Cache<String, String> sessionCache;
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();

        void A(){
            this.cacheManager.init();
            sessionCache = cacheManager.createCache("sessionCache",
                    CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
                            ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10, MemoryUnit.MB)));
        }
        @Override
        public void timeOut(String sessionId) {
            normalTimeOut();
            log.info("Session ended: {}", sessionId);
        }

        @Override
        public void addSession(String sessionId) {
            //TODO Need to find good value
            this.sessionCache.put(sessionId, "NOT-IDEAL");
            log.info("Session added: {}", sessionId);
        }

        void normalTimeOut(){

        }
    }
}
