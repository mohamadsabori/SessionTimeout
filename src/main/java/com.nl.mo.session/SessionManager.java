package com.nl.mo.session;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

import java.util.Timer;
import java.util.TimerTask;

public class SessionManager {
    private static SessionManager instance;
    private Cache<String, String> sessionCache;
    private final long TIMEOUT = 15 * 60 * 1000; // 15 minutes in milliseconds

    private SessionManager() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        sessionCache = cacheManager.createCache("sessionCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10, MemoryUnit.MB)));

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sessionCache.clear();
            }
        }, TIMEOUT, TIMEOUT);
    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void startSession(String sessionId) {
        System.out.println("Session started: " + sessionId);
        sessionCache.put(sessionId, "active");
    }

    public void endSession(String sessionId) {
        System.out.println("Session ended: " + sessionId);
        sessionCache.remove(sessionId);
    }

    public boolean isSessionActive(String sessionId) {
        return sessionCache.containsKey(sessionId);
    }

    public static void main(String[] args) {
        SessionManager sessionManager = SessionManager.getInstance();
        sessionManager.startSession("12345");

        System.out.println("Is session active? " + sessionManager.isSessionActive("12345"));

        // Simulate session timeout after 30 minutes
        try {
            Thread.sleep(30 * 60 * 1000); // 30 minutes in milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Is session active? " + sessionManager.isSessionActive("12345"));

        sessionManager.endSession("12345");
    }
}
