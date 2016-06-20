package br.com.itwv.cdatasy.base.encoding.arrays;

public class ArrayHelper {

	public static Object resizeArray(Object oldArray, int newSize) {
		try {
			int oldSize = java.lang.reflect.Array.getLength(oldArray);
			Class<?> elementType = oldArray.getClass().getComponentType();
			Object newArray = java.lang.reflect.Array.newInstance(elementType,
					newSize);
			int preserveLength = Math.min(oldSize, newSize);
			if (preserveLength > 0)
				System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
			return newArray;
		} catch (Exception e) {
			e.printStackTrace();
			return newSize;
		}
	}
}
