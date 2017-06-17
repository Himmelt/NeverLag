package cn.jiongjionger.neverlag.system;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

import cn.jiongjionger.neverlag.config.ConfigManager;
import cn.jiongjionger.neverlag.NeverLag;

public class RedStoneCounter {

	// 保存红石每触发的次数
	private int syncRestoneCount;
	private AtomicInteger asyncRestoneCount = new AtomicInteger(0);
	// 记录一分钟内的红石触发次数
	private ConcurrentLinkedDeque<Integer> asyncOneMinutesRecord = new ConcurrentLinkedDeque<Integer>();
	private LinkedList<Integer> syncOneMinutesRecord = new LinkedList<Integer>();

	private NeverLag plg = NeverLag.getInstance();
	private ConfigManager cm = ConfigManager.getInstance();

	private final static class RedStoneCounterHolder {
		private final static RedStoneCounter rc = new RedStoneCounter();
	}

	public final static RedStoneCounter getInstance() {
		return RedStoneCounterHolder.rc;
	}

	public RedStoneCounter() {
		plg.getServer().getScheduler().runTaskTimerAsynchronously(plg, new Runnable() {
			public void run() {
				if (cm.isCheckRedstoneOnAsync()) {
					synchronized (asyncOneMinutesRecord) {
						if (asyncOneMinutesRecord.size() >= 60) {
							asyncOneMinutesRecord.removeFirst();
						}
					}
					asyncOneMinutesRecord.add(asyncRestoneCount.get());
					asyncRestoneCount.set(0);
				}
			}
		}, 20L, 20L);
		plg.getServer().getScheduler().runTaskTimer(plg, new Runnable() {
			public void run() {
				if (!cm.isCheckRedstoneOnAsync()) {
					if (syncOneMinutesRecord.size() >= 60) {
						syncOneMinutesRecord.removeFirst();
					}
					syncOneMinutesRecord.add(syncRestoneCount);
					syncRestoneCount = 0;
				}
			}
		}, 20L, 20L);
	}

	public void updateRedstoneCount(boolean forceSync) {
		if (forceSync) {
			syncRestoneCount = syncRestoneCount + 1;
		} else {
			asyncRestoneCount.incrementAndGet();
		}
	}

	public int getRedstoneAvgCount(boolean forceSync) {
		if (forceSync) {
			if (syncOneMinutesRecord.isEmpty()) {
				return 0;
			}
			int total = 0;
			for (int count : syncOneMinutesRecord) {
				total = total + count;
			}
			return total / 60;
		} else {
			synchronized (asyncOneMinutesRecord) {
				if (asyncOneMinutesRecord.isEmpty()) {
					return 0;
				}
				int total = 0;
				for (int count : asyncOneMinutesRecord) {
					total = total + count;
				}
				return total / 60;
			}
		}
	}

	public int getRedstoneRealTimeCount(boolean forceSync) {
		if (forceSync) {
			if (syncOneMinutesRecord.isEmpty()) {
				return 0;
			}
			return syncOneMinutesRecord.getLast();
		} else {
			synchronized (asyncOneMinutesRecord) {
				if (asyncOneMinutesRecord.isEmpty()) {
					return 0;
				}
				return asyncOneMinutesRecord.getLast();
			}
		}
	}

}
