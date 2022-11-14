package com.greatlearning.skyscraperconstruction.service;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Driver {

	public static void main(String[] args) {
		int noOfFloors, counter, dayCount = 1;
		Scanner sc = new Scanner(System.in);
		Stack<Integer> floor_size_stack = new Stack<Integer>();
		Stack<Integer> temp_stack = new Stack<Integer>();
		Service service = new Service();
		boolean flag = false;

		// Get user input for total no of floors where total no of floors is greater
		// than 0.

		System.out.println("Enter the total no of floors in the building: ");

		while (!flag) {
			noOfFloors = sc.nextInt();
			if (noOfFloors > 0) {
				flag = true;
				service.setNoOfFloors(noOfFloors);
			} else {
				System.out.println("Enter value greater than 0.");
			}
		}

		noOfFloors = service.getNoOfFloors();
		ArrayList<Integer> removedEle = new ArrayList<Integer>(noOfFloors);

		/**
		 * Get user input for available floor size on each day given user inputs are
		 * valid.If invalid input, display messages based on invalid input and await new
		 * user input.
		 **/

		for (int i = 1; i <= noOfFloors; i++) {
			flag = false;
			System.out.println("Enter the floor size given on day :" + i);

			while (!flag) {

				int floorNo = sc.nextInt();
				boolean isFloorAvailable = floor_size_stack.contains(floorNo);

				if (floorNo > 0 && floorNo <= noOfFloors && !isFloorAvailable) {
					flag = true;
					floor_size_stack.push(floorNo);
					break;
				} else {
					if (isFloorAvailable) {
						System.out.println("This floor size is available.Enter correct floor size.");
					}

					else if (floorNo == 0) {
						System.out.println("Enter value greater than 0.");
					}

					else if (floorNo > noOfFloors) {
						System.out.println("Enter correct floor size.");
					}
				}
			}

		}

		System.out.println("The order of construction is as follows:");

		for (int i = 0; i < noOfFloors; i++) {

			// Create temporary stack for each day
			for (int j = 0; j <= i; j++) {
				int val = floor_size_stack.elementAt(j);
				if (!removedEle.contains(val)) {
					temp_stack.push(val);
				}

			}
			// Sort in descending order
			service.sortStack(temp_stack);

			// Check how many floors can be assembled on a day based on the floor size.
			counter = service.assemble(temp_stack, noOfFloors - i);

			System.out.println("Day: " + (dayCount));
			if (counter == 0) {
				System.out.println();
			} else {
				while (counter >= 1) {
					int temp = temp_stack.remove(0);
					removedEle.add(temp);
					System.out.print(temp);
					if (counter == 1) {
						System.out.print("\n");
					} else {
						System.out.print(" ");
					}
					counter--;
				}
			}
			temp_stack.clear();
			dayCount++;
		}
		sc.close();
	}

}
