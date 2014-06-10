package com.ecarinfo.traffic.filter;

/**
 * @Description:系统分页参数处理类
 * @Author 邓支晓
 * @Date 2012-11-6
 * @Version V1.0
 */

public class SystemContext {
	/**
	 * 分页的记录数
	 */
	private static ThreadLocal<Integer> pageOffset = new ThreadLocal<Integer>();

	/**
	 * 升序还是降序
	 */
	private static ThreadLocal<String> order = new ThreadLocal<String>();

	/**
	 * 根据那个字段排序
	 */
	private static ThreadLocal<String> sort = new ThreadLocal<String>();

	public static String getOrder() {
		return order.get();
	}

	public static void setOrder(String _order) {
		order.set(_order);
	}

	public static void removeOrder() {
		order.remove();
	}

	public static String getSort() {
		return sort.get();
	}

	public static void setSort(String _sort) {
		sort.set(_sort);
	}

	public static void removeSort() {
		sort.remove();
	}

	public static int getPageOffset() {
		return pageOffset.get();
	}

	public static void setPageOffset(int _pageOffset) {
		pageOffset.set(_pageOffset);
	}

	public static void removePageOffset() {
		pageOffset.remove();
	}
}
