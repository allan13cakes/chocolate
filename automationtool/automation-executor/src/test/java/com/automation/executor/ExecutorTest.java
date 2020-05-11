package com.automation.executor;

import org.junit.Before;
import org.junit.Test;

public class ExecutorTest {
	private Executor executor;

	@Before
	public void before() {
		executor = new Executor();
	}

	@Test
	public void executeTest() {
		executor.execute();
	}
}
