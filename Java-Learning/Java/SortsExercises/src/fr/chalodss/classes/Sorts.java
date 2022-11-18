package fr.chalodss.classes;

import java.util.List;

/**
 * @author Charles T.
 * 
 */
public final class Sorts {

  private Sorts() {

  }

  public static <E extends Comparable<E>> void bubbleSort(List<E> elements) {
    int i   = elements.size() - 1;
    int j   = 0;
    E   tmp = null;

    while (i > 0) {
      while (j < i) {
        if (elements.get(j).compareTo(elements.get(j + 1)) > 0) {
          tmp = elements.get(j);
          elements.set(j, elements.get(j + 1));
          elements.set(j + 1, tmp);
        }
        ++j;
      }
      j = 0;
      --i;
    }
  }

  public static <E extends Comparable<E>> void insertionSort(List<E> elements) {
    int i   = 0;
    int j;
    E   tmp = null;

    while (i < elements.size()) {
      tmp = elements.get(i);
      j   = i;
      while (j > 0 && tmp.compareTo(elements.get(j - 1)) < 0) {
        elements.set(j, elements.get(j - 1));
        --j;
      }
      elements.set(j, tmp);
      ++i;
    }
  }

  public static <E extends Comparable<E>> void merge(List<E> elements, List<E> tmp, int deb, int mid, int end) {
    int i = deb;
    int j = mid + 1;
    int k = deb;

    while (k <= end) {
      if ((j > end) || ((i <= mid) && (elements.get(i).compareTo(elements.get(j)) < 0))) {
        tmp.set(k, elements.get(i));
        i++;
      } else {
        tmp.set(k, elements.get(j));
        j++;
      }
      k++;
    }
    k = deb;
    while (k <= end) {
      elements.set(k, tmp.get(k));
      ++k;
    }
  }

  public static <E extends Comparable<E>> void mergeSort(List<E> elements, List<E> tmp, int deb, int end) {
    int mid = 0;

    if (deb < end) {
      mid = (deb + end) / 2;
      mergeSort(elements, tmp, deb, mid);
      mergeSort(elements, tmp, mid + 1, end);
      merge(elements, tmp, deb, mid, end);
    }
  }

  public static <E extends Comparable<E>> int swap(List<E> elements, int left, int right) {
    int i   = left - 1;
    int j   = right;
    E   k   = elements.get(right);
    E   tmp = null;

    while (i < j) {
      while (elements.get(++i).compareTo(k) < 0);
      while (j > 0 && elements.get(--j).compareTo(k) > 0);
      if (i < j) {
        tmp = elements.get(i);
        elements.set(i, elements.get(j));
        elements.set(j, tmp);
      }
    }
    tmp = elements.get(i);
    elements.set(i, elements.get(right));
    elements.set(right, tmp);
    return i;
  }

  public static <E extends Comparable<E>> void quickSort(List<E> elements, int left, int right) {
    int pivot = 0;

    if (left < right) {
      pivot = swap(elements, left, right);
      quickSort(elements, left, pivot - 1);
      quickSort(elements, pivot + 1, right);
    }
  }

  public static <E extends Comparable<E>> void selectionSort(List<E> elements) {
    int i   = 0;
    int j;
    int k   = 0;
    E   tmp = null;

    while (i < elements.size() - 1) {
      j   = i + 1;
      tmp = elements.get(i);
      while (j < elements.size()) {
        if (elements.get(j).compareTo(tmp) < 0) {
          tmp = elements.get(j);
          k   = j;
        }
        ++j;
      }
      if (tmp.compareTo(elements.get(i)) < 0) {
        elements.set(k, elements.get(i));
        elements.set(i, tmp);
      }
      ++i;
    }
  }

}
