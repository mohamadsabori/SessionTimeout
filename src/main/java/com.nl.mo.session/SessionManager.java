package com.nl.mo.session;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class SessionManager {
    private static SessionManager instance;
    private Cache<String, String> sessionCache;
    private final long TIMEOUT = 15 * 60 * 1000; // 15 minutes in milliseconds

    private static final Logger logger = Logger.getLogger(SessionManager.class.getName());

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
        String sessionIds = getSessionIdsFromParameter(args);
        SessionManager sessionManager = SessionManager.getInstance();
        sessionManager.startSession(sessionIds);

        logger.info("Is session active? " + sessionManager.isSessionActive(sessionIds));

        try {
            Thread.sleep(30 * 60 * 1000);
        } catch (InterruptedException e) {
            logger.severe("An error occurred: " + e.getMessage());
            logger.severe("Stack trace: ");
            for (StackTraceElement element : e.getStackTrace()) {
                logger.severe(element.toString());
            }
        }
    }

    public static String getSessionIdsFromParameter(String[] args) {
        if (args.length > 0) {
            logger.info("Command-line arguments:");
            for (int i = 0; i < args.length; i++) {
                logger.info("Argument " + (i + 1) + ": " + args[i]);
            }
            return args[0];
        } else {
            logger.warning("No command-line arguments provided.");
            return "12345";
        }
    }
}
