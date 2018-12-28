//package com.xiaohulu.tools;
//
//import java.util.Comparator;
//import java.util.Map;
////Comparator该接口代表一个比较器，比较器具有可比性！我总结的两个场景：
//// 1. 排序，需要比较两个对象谁排在前谁排在后（排序也可以让类实现Comparable接口，实现后该类的实例也具有排序能力）。
//// 2. 分组，需要比较两个对象是否是属于同一组。
//public class ByValueComparator implements Comparator<String>  {
//
//	Map<String, Double> base_map;
//
//	public ByValueComparator(Map<String, Double> base_map) {
//		this.base_map = base_map;
//	}
//
//	public int compare(String arg0, String arg1) {
//		if (!base_map.containsKey(arg0) || !base_map.containsKey(arg1)) {
//			return 0;
//		}
//
//		if (base_map.get(arg0) < base_map.get(arg1)) {
//			return 1;
//		} else if (base_map.get(arg0) > base_map.get(arg1)) {
//			return -1;
//		} else {
//			return 0;
//		}
//	}
//}
