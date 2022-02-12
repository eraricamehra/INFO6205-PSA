package edu.neu.coe.info6205.sort.elementary;

import java.io.IOException;
import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.ConfigTest;

public class InsertionSortBenchmark {

	public static void main(String[] args) throws IOException {

		int N = 200;

		for (int i = 0; i <= 5; i++) {
			while (N <= 1600) {
				Helper<Integer> helper = new BaseHelper<>("Insertion sort", N,
						ConfigTest.setupConfig("true", "0", "1", "", ""));
				SortWithHelper<Integer> arraySorter = new InsertionSort<>(helper);

				Benchmark<Integer[]> benchmarkTimer = new Benchmark_Timer<Integer[]>("Insertion Sort",
						b -> arraySorter.sort(b));
				Integer[] arr1 = helper.random(Integer.class, r -> r.nextInt(1000));
				System.out.println("---------------------------------------------------------------");
				System.out.println("Random elements Unsorted");
				double runningTimeRandom = benchmarkTimer.run(arr1, N);
				System.out.println("N : " + arr1.length);
				System.out.println("Running Time : " + runningTimeRandom);

				System.out.println("---------------------------------------------------------------");
				System.out.println("Asending sorted");
				System.out.println("N : " + arr1.length);
				Integer[] arr2 = arraySorter.sort(arr1);
				double runningTimeSorted = benchmarkTimer.run(arr2, N);
				System.out.println("Running Time : " + runningTimeSorted);

				System.out.println("---------------------------------------------------------------");
				System.out.println("Desc sorted");
				arraySorter.sort(arr2, arr2.length - 1, 0);
				System.out.println("N :" + arr2.length);
				double runningTimeReverseSorted = benchmarkTimer.run(arr2, N);
				System.out.println("Running Time : " + runningTimeReverseSorted);

				System.out.println("---------------------------------------------------------------");
				System.out.println("Partially sorted");
				arraySorter.sort(arr2, 0, (arr2.length - 1) / 2);
				System.out.println("N :" + arr2.length);
				double runningTimePartiallySorted = benchmarkTimer.run(arr2, N);
				System.out.println("Running Time : " + runningTimePartiallySorted);
				N = N + 200;
			}
		}

	}

}
