package com.nl.mo.session;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SessionManagerTest {
    private SessionManager sessionManager;

    @Before
    public void setUp() {
        sessionManager = SessionManager.getInstance();
    }

    @After
    public void tearDown() {
        sessionManager.endSession("12345");
    }

    @Test
    public void testSessionStartAndEnd() {
        sessionManager.startSession("12345");
        assertTrue(sessionManager.isSessionActive("12345"));

        sessionManager.endSession("12345");
        assertFalse(sessionManager.isSessionActive("12345"));
    }
}
