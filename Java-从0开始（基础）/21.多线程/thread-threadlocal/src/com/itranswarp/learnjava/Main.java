package com.itranswarp.learnjava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Learn Java from https://www.liaoxuefeng.com/
 * 
 * @author liaoxuefeng
 */
public class Main {
	public static void main(String[] args) throws Exception {
		ExecutorService es = Executors.newFixedThreadPool(3); // 创建包含3个线程的线程池
		String[] users = new String[] { "Bob", "Alice", "Tim", "Mike", "Lily", "Jack", "Bush" };
		for (String user : users) {
			es.submit(new Task(user)); // 启动任务
		}
		es.awaitTermination(3, TimeUnit.SECONDS);
		es.shutdown();
	}
}

class UserContext implements AutoCloseable { // 实现AutoCloseable接口后再使用 `try (resource) {...}` 时编译器可以自动关闭ThreadLocal
	private static final ThreadLocal<String> userThreadLocal = new ThreadLocal<>(); // ThreadLocal静态字段

	public UserContext(String name) {
		userThreadLocal.set(name); //
		System.out.printf("[%s] init user %s...\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
	}

	public static String getCurrentUser() {
		return userThreadLocal.get(); //
	}

	@Override
	public void close() {
		System.out.printf("[%s] cleanup for user %s...\n", Thread.currentThread().getName(),
				UserContext.getCurrentUser());
		userThreadLocal.remove(); // 必须关闭 因为当前线程执行完相关代码后，很可能会被重新放入线程池中，如果ThreadLocal没有被清除，该线程执行其他代码时，会把上一次的状态带进去。
	}
}

class Task implements Runnable { // 任务，主要这几件事：1.创建用户上下文对象（接受用户名为参数） 2.调用三个子任务的处理方法

	final String username;

	public Task(String username) {
		this.username = username;
	}

	@Override
	public void run() {
		// 核心部分！！！
		try (var ctx = new UserContext(this.username)) { // 打开上下文对象
			new Task1().process(); // 一些方法，会用到上下文对象
			new Task2().process();
			new Task3().process();
		} // 编译器自动关闭上下文对象
	}
}

class Task1 {
	public void process() { // 检查用户名
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		System.out.printf("[%s] check user %s...\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
	}
}

class Task2 {
	public void process() { // 注册用户
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		System.out.printf("[%s] %s registered ok.\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
	}
}

class Task3 {
	public void process() { // 提示与当前用户有关的任务完成
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		System.out.printf("[%s] work of %s has done.\n", Thread.currentThread().getName(),
				UserContext.getCurrentUser());
	}
}
