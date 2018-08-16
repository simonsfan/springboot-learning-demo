package com.simons.cn.springbootdemo.util;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;


/**
 * 
 * 
 * @author xiaotaoma
 */

public class DistributedLock {

	private static String ZK_CONNECTION_STRING ="";

	private static final Logger logger = Logger
			.getLogger(DistributedLock.class);

	private String root = "/dlocks/usergs";// 根

	private String lockName;// 竞争资源的标志
	private InterProcessMutex lock;

	// 用来标识zookeeper是否可用
	private boolean isCanUsed = false;

	private static CuratorFramework client;

	static {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		client = CuratorFrameworkFactory.newClient(ZK_CONNECTION_STRING,
				retryPolicy);
		client.start();
	}

	/**
	 * 
	 * 创建分布式锁,使用前请确认config配置的zookeeper服务可用
	 * 
	 * @param config
	 * 
	 * @param lockName
	 *            竞争资源标志,lockName中不能包含单词lock
	 */

	public DistributedLock(String act, String username) {
		this(act + "_" + username);
	}

	public DistributedLock(String lockName){
		this.lockName = lockName;
		try {
			lock = new InterProcessMutex(client, root + "/" + lockName);
			isCanUsed = true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void trylock() {
		int count = 0;
		try {
			// 2秒内会尝试获取锁，加上重试的2次，可能占据4s的时间
			while (!lock.acquire(2, TimeUnit.SECONDS)) {
				count++;
				if (count > 1) {
					break;
				}
			}
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		if (count > 1) {
			logger.debug("Thread " + Thread.currentThread().getId()
					+ "----------" + this.lockName
					+ " giving up acquiring lock for busy----------");
		} else {
			logger.debug("Thread " + Thread.currentThread().getId()
					+ "----------" + this.lockName
					+ " success acquire lock----------");
		}
	}

	/**
	 * 获取锁
	 * @return
	 */
	public boolean tryLockWithReturn(){
	    int count = 0;
        try {
            // 3秒内会尝试获取锁，加上重试的3次，可能占据9s的时间
            boolean flag = false;
            do{
                count ++ ;
                logger.info("try for "+count+" times");
                flag = lock.acquire(1, TimeUnit.SECONDS);
            }while(!flag && count < 3);
           return flag;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
	}	
	
	
	/****
	 * 释放锁
	 */
	public void unlock() {
		try {
			if (lock != null && lock.isAcquiredInThisProcess()) {
				lock.release();
				logger.debug("Thread " + Thread.currentThread().getId()
						+ "----------" + this.lockName + " release----------");
				String path = root + "/" + lockName;
				client.delete().inBackground().forPath(path);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public boolean isCanUsed() {
		return isCanUsed;
	}

	public void setCanUsed(boolean isCanUsed) {
		this.isCanUsed = isCanUsed;
	}

	public String getLockName() {
		return lockName;
	}

	public void setLockName(String lockName) {
		this.lockName = lockName;
	}
}
