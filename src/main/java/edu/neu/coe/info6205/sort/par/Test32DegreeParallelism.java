package edu.neu.coe.info6205.sort.par;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Test32DegreeParallelism {

	
	public static void main(String[] args) {
		
		Random random = new Random();
		for (int arraySize = 1000000; arraySize <= 10000000;  arraySize = 2 * arraySize) {
			int[] array = new int[arraySize];
			ArrayList<Long> timeList = new ArrayList<>();
			ParSort.pool = new ForkJoinPool(32);
			System.out.println("Degree of parallelism: " + ParSort.pool.getParallelism());
			System.out.println("Size of the array: " + arraySize);
			for (int j = 50; j < 100; j++) {
				ParSort.cutoff = 10000 * (j + 1);
				long time;
				long startTime = System.currentTimeMillis();
				for (int t = 0; t < 10; t++) {
					for (int i = 0; i < array.length; i++)
						array[i] = random.nextInt(10000000);
					ParSort.sort(array, 0, array.length);
				}
				long endTime = System.currentTimeMillis();
				time = (endTime - startTime);
				timeList.add(time);

				System.out.println("cutoff：" + (ParSort.cutoff) + "\t\t10 times Time :" + time + "ms");
			}
			try {
				FileOutputStream fis = new FileOutputStream(
						"./src/results/result-32-ThreadCount-Varying-Array-Sizes" + arraySize + ".csv");
				OutputStreamWriter isr = new OutputStreamWriter(fis);
				BufferedWriter bw = new BufferedWriter(isr);
				int j = 50;
				for (long i : timeList) {
					String content = (double) 10000 * (j + 1) / 2000000 + "," + (double) i / 10 + "\n";
					j++;
					bw.write(content);
					bw.flush();
				}
				bw.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	


}
