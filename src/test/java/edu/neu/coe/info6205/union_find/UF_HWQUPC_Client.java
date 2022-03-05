package edu.neu.coe.info6205.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class UF_HWQUPC_Client {

	public static void main(String[] args) {

		int numberOfSites;

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Enter initial number of sites");
			System.out.println("The number you enter will be doubled in each iteration.");
			numberOfSites = Integer.parseInt(bufferedReader.readLine());
			for(int i = 0; i < 20; i++) {
				UF_HWQUPC uf = new UF_HWQUPC(numberOfSites);
				int connections = count(uf, numberOfSites);
				System.out.println("Number of sites: " + numberOfSites + ",     Number of connections: " + connections);
				numberOfSites = numberOfSites * 2;
			}
		} catch (NumberFormatException e) {
			System.out.println("The input is invalid. Enter a valid integer");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static int count(UF_HWQUPC uf , int numberOfSites) {
		int connections = 0;
		int p,q;
		Random random = new Random();
		while (uf.components() > 1) {
			p= random.nextInt(numberOfSites);
			q= random.nextInt(numberOfSites);
			uf.connect(p,q);
			connections++;
		}
		return connections;
	}



}
