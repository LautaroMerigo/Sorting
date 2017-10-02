package com.lmerigo.arraymanagement;

public class SortingFactory {
	public Sorting createNativeSorting() {
		return new NativeSort();
	}

	public Sorting createQuickSorting() {
		return new Quicksort();
	}
}
