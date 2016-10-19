package com.xxfeii.baseside.modules;

public class Lookup {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * 二分法查找
		 */
		int a[] = { 23, 45, 98, 100, 110, 120, 140 };
		int search = 120;// 记录要查找的元素
		int lower = 0;// 记录第一个元素
		int temp = a.length - 1;
		int index = -1;
		while (lower <= temp) {
			index = (lower + temp) / 2;// 记录中间元素，用两边之和除2.
			int currentValue = a[index];
			if (currentValue == search) {// 如果得到的数与要查找的数相等则break退出;
				break;
			} else if (currentValue < search) {// 如果得到的数要小于查找的数、就用下标加1;否则减一
				lower = index + 1;
			} else {
				temp = index - 1;
			}
		}
		if (lower <= temp) {
			System.out.println(search + "在数组中第：" + (index + 1) + "位");
		} else {
			System.out.println("里面没有这个元素");
		}
	}
}
