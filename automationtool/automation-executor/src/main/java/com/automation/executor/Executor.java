package com.automation.executor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class Executor {

	@SuppressWarnings("unchecked")
	public void execute() {
		System.out.println("this is execute method");
		Reflections reflections = new Reflections("com.automation.action", new SubTypesScanner(false));
		Set<String> subTypes = reflections.getAllTypes();
		subTypes.forEach(calssName -> {
			try {
				Set<Method> allMethod = ReflectionUtils.getAllMethods(Class.forName(calssName),
						ReflectionUtils.withName("action1"), ReflectionUtils.withParametersCount(0));
				allMethod.forEach(method -> {
					try {
						Object result = method.invoke(Class.forName(calssName).newInstance());
						System.out.println(result);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				});

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});

	}
}
