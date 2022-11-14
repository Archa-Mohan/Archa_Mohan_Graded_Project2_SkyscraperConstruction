package com.greatlearning.skyscraperconstruction.service;

import java.util.Comparator;
import java.util.Stack;

public class Service {

	public int noOfFloors;
	public static int nextCheck;

	public int getNoOfFloors() {
		return noOfFloors;
	}

	public void setNoOfFloors(int floors) {
		this.noOfFloors = floors;
		nextCheck = getNoOfFloors();
	}

	// Sort in decreasing order.
	public Stack<Integer> sortStack(Stack<Integer> stack) {
		stack.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});

		return stack;
	}

	// Count no of floors that can be assembled
	public int assemble(Stack<Integer> temp_stack, int currentVal) {
		int count = 0;

		while (nextCheck >= currentVal) {
			if (temp_stack.contains(nextCheck)) {
				count++;
				nextCheck--;
			} else {
				break;
			}
		}
		return count;

	}

}
