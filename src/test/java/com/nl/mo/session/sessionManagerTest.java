package com.nl.mo.session;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class sessionManagerTest {

    private SessionManager sessionManager;

    @Before
    public void setUp() {
        sessionManager = SessionManager.getInstance();
    }

    @After
    public void tearDown() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();
        Cache<String, String> sessionCache = cacheManager.createCache("sessionCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10, MemoryUnit.MB)));
        sessionCache.clear();
    }

    @Test
    public void testStartSession() {
        String sessionId = "testSession";
        sessionManager.startSession(sessionId);
        assertTrue(sessionManager.isSessionActive(sessionId));
    }

    @Test
    public void testEndSession() {
        String sessionId = "testSession";
        sessionManager.startSession(sessionId);
        sessionManager.endSession(sessionId);
        assertFalse(sessionManager.isSessionActive(sessionId));
    }

    @Test
    public void testIsSessionActive() {
        String sessionId = "testSession";
        sessionManager.startSession(sessionId);
        assertTrue(sessionManager.isSessionActive(sessionId));
        sessionManager.endSession(sessionId);
        assertFalse(sessionManager.isSessionActive(sessionId));
    }

    @Test
    public void testSingletonInstance() {
        SessionManager anotherInstance = SessionManager.getInstance();
        assertSame(sessionManager, anotherInstance);
    }

    @Test
    public void testSessionTimeout() throws InterruptedException {
        String sessionId = "testSession";
        sessionManager.startSession(sessionId);
        assertTrue(sessionManager.isSessionActive(sessionId));
        Thread.sleep(16 * 60 * 1000); // Sleep for 16 minutes to ensure timeout occurs
        assertFalse(sessionManager.isSessionActive(sessionId));
    }

    @Test
    public void testGetSessionIdsFromParameter() {
        String[] args = {"testSessionId"};
        String sessionId = SessionManager.getSessionIdsFromParameter(args);
        assertEquals("testSessionId", sessionId);
    }

    @Test
    public void testGetSessionIdsFromParameterWithNoArgs() {
        String[] args = {};
        String sessionId = SessionManager.getSessionIdsFromParameter(args);
        assertEquals("12345", sessionId);
    }
}
