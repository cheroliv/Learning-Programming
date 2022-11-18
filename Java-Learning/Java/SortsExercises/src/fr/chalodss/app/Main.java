package fr.chalodss.app;

import static fr.chalodss.classes.Sorts.*;

import java.util.Arrays;

/**
 * @author Charles T.
 * 
 */
public class Main {

  public static void main(String[] args) {
    var l1 = Arrays.asList(7, 3, 5, 1, -8, 17, 2);
    var l2 = Arrays.asList(7, 3, 5, 1, -8, 17, 2);
    var l3 = Arrays.asList(7, 3, 5, 1, -8, 17, 2);
    var l4 = Arrays.asList(7, 3, 5, 1, -8, 17, 2);
    var l5 = Arrays.asList(7, 3, 5, 1, -8, 17, 2);

    System.out.println("l1 before:\t\t" + l1);
    bubbleSort(l1);
    System.out.println("l1 after bubble sort:\t" + l1 + "\n");
    System.out.println("l2 before:\t\t" + l2);
    insertionSort(l2);
    System.out.println("l2 after insertion sort:" + l2 + "\n");
    System.out.println("l3 before:\t\t" + l3);
    selectionSort(l3);
    System.out.println("l3 after selection sort:" + l3 + "\n");
    System.out.println("l4 before:\t\t" + l4);
    var l4Tmp = Arrays.asList(new Integer[l4.size()]);
    mergeSort(l4, l4Tmp, 0, l4.size() - 1);
    System.out.println("l4 after merge sort:\t" + l4 + "\n");
    System.out.println("l5 before:\t\t" + l5);
    quickSort(l5, 0, l5.size() - 1);
    System.out.println("l5 after quick sort:\t" + l5);
  }

}
