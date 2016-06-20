package br.com.itwv.cdatasy.base.exceptions.helpers;

import java.lang.reflect.Method;

public class TraceHelper {

	private static Method m;

	static {
		try {
			m = Throwable.class.getDeclaredMethod("getStackTraceElement",
					int.class);
			m.setAccessible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getMethodName(final int depth) {
		try {
			StackTraceElement element = (StackTraceElement) m.invoke(
					new Throwable(), depth + 1);
			return element.getClassName() + "." + element.getMethodName();
		} catch (Exception e) {

			return null;
		}
	}
}