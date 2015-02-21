package com.codechef.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * Problem statement here: http://www.codechef.com/problems/STRQ
 * 
 * @author rohitjain
 *
 */
public class ChefAndStrings {

  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  private static String specialString = "";
  private static int numberOfQueries = 0;

  public static void main(String[] args) {
    PrintStream out = System.out;
    try {
      specialString = br.readLine();
      numberOfQueries = Integer.parseInt(br.readLine());

      for (int i = 0; i < numberOfQueries; i++) {
        String nextQuery = nextQuery();
        out.println(getGoodStringCounts(nextQuery));
      }
    } catch (IOException ex) {
      out.println("Exception occurred while reading input");
    }
  }

  private static String nextQuery() throws IOException {
    return br.readLine();
  }

  private static int getGoodStringCounts(String query) {
    tokenizer = new StringTokenizer(query, " ");
    char startLetter = tokenizer.nextToken().charAt(0);
    char endLetter = tokenizer.nextToken().charAt(0);
    int startIndex = Integer.parseInt(tokenizer.nextToken()) - 1;
    int endIndex = Integer.parseInt(tokenizer.nextToken()) - 1;

    int[] startLetterIndices = allIndicesGreaterThanMin(startLetter, startIndex);
    int[] endLetterIndices = allIndicesLessThanMax(endLetter, endIndex);

    int total = 0;
    int beginEndIndexFrom = 0;

    for (int i = 0; i < startLetterIndices.length; i++) {
      int index = startLetterIndices[i];
      for (int j = beginEndIndexFrom; j < endLetterIndices.length; j++) {
        if (endLetterIndices[j] > index) {
          beginEndIndexFrom = j;
          total += (endLetterIndices.length - beginEndIndexFrom);
          break;
        }
      }
    }

    return total;
  }

  private static int[] allIndicesGreaterThanMin(char letter, int startIndex) {
    int[] indices = new int[specialString.length() / 2];
    int currentIndex = 0;
    int index = specialString.indexOf(letter, startIndex);
    while (index != -1) {
      indices[currentIndex++] = index;
      startIndex = index + 1;
      index = specialString.indexOf(letter, startIndex);
    }
    int[] result = new int[currentIndex];
    System.arraycopy(indices, 0, result, 0, result.length);
    return result;
  }

  private static int[] allIndicesLessThanMax(char letter, int endIndex) {
    int[] indices = new int[specialString.length() / 2];
    int currentIndex = 0;
    int index = specialString.indexOf(letter);
    while (index != -1 && index <= endIndex) {
      indices[currentIndex++] = index;
      index = specialString.indexOf(letter, index + 1);
    }
    int[] result = new int[currentIndex];
    System.arraycopy(indices, 0, result, 0, result.length);
    return result;
  }
}
