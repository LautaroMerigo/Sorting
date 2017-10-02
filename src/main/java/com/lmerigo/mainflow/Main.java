package com.lmerigo.mainflow;

import java.util.Random;

import com.lmerigo.arraymanagement.Sorting;
import com.lmerigo.arraymanagement.SortingFactory;

public class Main {
	private final static int FIGHTS_COUNTER = 100;
	private final static int HOW_MUCH_ELEMENTS = 50000;

	public static void main(String[] args) {
		SortingFactory sortingFactory = new SortingFactory();
		Sorting nativeSort = sortingFactory.createNativeSorting();
		Sorting quickSort = sortingFactory.createQuickSorting();

		Random random = new Random();
		int randomNumbers[] = new int[HOW_MUCH_ELEMENTS];
		// Init arrays
		for (int i = 0; i < HOW_MUCH_ELEMENTS; i++) {
			randomNumbers[i] = random.nextInt();
		}
		int workbase[] = new int[HOW_MUCH_ELEMENTS];

		int nativelyOrdered[] = new int[HOW_MUCH_ELEMENTS];
		int quickSorterlyOrdered[] = new int[HOW_MUCH_ELEMENTS];
		int nativeTimeVictoriesCounter = 0;
		int quickSortTimeVictoriesCounter = 0;
		int tiesCounter = 0;

		for (int k = 1; k <= FIGHTS_COUNTER; k++) {
			System.arraycopy(randomNumbers, 0, workbase, 0, randomNumbers.length);
			final long nativeT1 = System.currentTimeMillis();
			nativeSort.sort(workbase);
			final long nativeT2 = System.currentTimeMillis();
			final long nativeTimeConsumption = nativeT2 - nativeT1;
			System.arraycopy(workbase, 0, nativelyOrdered, 0, workbase.length);

			System.arraycopy(randomNumbers, 0, workbase, 0, randomNumbers.length);
			final long quickSortT1 = System.currentTimeMillis();
			quickSort.sort(workbase);
			final long quickSortT2 = System.currentTimeMillis();
			final long quickSortTimeConsumption = quickSortT2 - quickSortT1;
			System.arraycopy(workbase, 0, quickSorterlyOrdered, 0, workbase.length);

			for (int i = 0; i < nativelyOrdered.length; i++) {
				assert (nativelyOrdered[i] == quickSorterlyOrdered[i]);
			}

			if (nativeTimeConsumption < quickSortTimeConsumption) {
				nativeTimeVictoriesCounter++;
			} else if (quickSortTimeConsumption < nativeTimeConsumption) {
				quickSortTimeVictoriesCounter++;
			} else {
				tiesCounter++;
			}
		}

		final double nativeVictoriesPercentage = ((double) nativeTimeVictoriesCounter / FIGHTS_COUNTER) * 100;
		final double quickSortVictoriesPercentage = ((double) quickSortTimeVictoriesCounter / FIGHTS_COUNTER) * 100;
		final double tiesPercentage = ((double) tiesCounter / FIGHTS_COUNTER) * 100;

		System.out.println();
		System.out.println("PERCENTAGES:");
		System.out.println("NATIVE: " + Math.round(nativeVictoriesPercentage) + "%");
		System.out.println("QUICKSORT: " + Math.round(quickSortVictoriesPercentage) + "%");
		System.out.println("TIES: " + Math.round(tiesPercentage) + "%");

	}

}
