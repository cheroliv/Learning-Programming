package fr.chalodss.classes;

import static fr.chalodss.utils.Constants.*;

import java.util.List;
import java.util.Optional;

public final class SearchFunctions {

  private static int countDicho = 0;
  private static int countInter = 0;

  private SearchFunctions() {

  }

  public static int simpleSearch(List<Integer> list, int elem) {
    var i = 0;

    while (list.get(i++) != elem && i < list.size());
    System.out.println(NBI + i + IND + --i + VTF + elem);
    return list.get(i);
  }

  public static int dichotomousSearch(List<Integer> list, int start, int end, int elem) {
    var middle = (start + end) / 2;
    var res    = -1;

    ++countDicho;
    if (list.get(middle) == elem) {
      System.out.println(NBI + countDicho + IND + middle + VTF + elem);
      return res;
    } else if (start >= end) {
      res = -1;
    } else if (elem < list.get(middle)) {
      return dichotomousSearch(list, start, middle - 1, elem);
    } else {
      return dichotomousSearch(list, middle + 1, end, elem);
    }
    return res;
  }

  public static int interpolationSearch(List<Integer> tab, int start, int end, int elem) {
    var borne  = 0;
    var offset = 1.0;

    while (offset >= 0) {
      countInter++;
      offset = (elem - tab.get(start)) * ((double) (end - start) / (tab.get(end) - tab.get(start)));
      borne  = (int) (start + offset);
      if (elem == tab.get(borne)) {
        System.out.println(NBI + countInter + IND + borne + VTF + elem);
        return borne;
      } else {
        start = borne + 1;
      }
    }
    return -1;
  }

  public static void multiThreadSearch(Thread[] searchAgents, int nbCpu) {
    for (var i = 0; i < nbCpu; i++) {
      searchAgents[i].start();
    }
  }

  public static Optional<Integer> streamSearch(List<Integer> list, int elem) {
    return list.parallelStream().filter(e -> e == elem).findAny();
  }

}
