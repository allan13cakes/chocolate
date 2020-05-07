package com.allan.jgit;

import org.eclipse.jgit.lib.ProgressMonitor;

public class MyProgressMonitor implements ProgressMonitor {
	private int totalCompleted = 0;
	private int myTotalWork = 0;

	@Override
	public void start(int totalTasks) {
		System.out.println("total tasks:" + totalTasks);
	}

	@Override
	public void beginTask(String title, int totalWork) {
		System.out.println("title:" + title + ",totalWork : " + totalWork);
		totalCompleted = 0;
		myTotalWork = totalWork;
	}

	@Override
	public void update(int completed) {
		totalCompleted += completed;
		try {
			double progressPercentage = totalCompleted / myTotalWork * 100 / 100d;
			ProgressDemo.updateProgress(progressPercentage);
		} catch (Exception e) {
		}
	}

	@Override
	public void endTask() {
		System.out.println("done");
	}

	@Override
	public boolean isCancelled() {
		return false;
	}

}
