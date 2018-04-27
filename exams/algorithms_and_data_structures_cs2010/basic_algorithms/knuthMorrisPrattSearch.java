import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/* MIT License
 *
 * Copyright (c) 2017 Brandon Dooley - dooleyb1@tcd.ie
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

 /* Adapted from Sedgeick & Wayne - https://algs4.cs.princeton.edu/home/ */

public class KMPSearch {

  // ----------------------------------------------------------
  /**
   * Knuth-Morris-Pratt substring search method allows for substrings
   * to be found using a Deterministic Finite State Automoton which
   * allows us to keep track of which state we are currently in and
   * where to transition to upon next letter.
   *
   * @param txt: String to search.
   * @param pat: Pattern to search for.
   * @return True/False depending on whether pat was found in txt.
   *
   * ----------------------------------------------------------
   *
   * Approximate Mathematical Performance:
   * -------------------------------------
   *  Using an appropriate cost model, give the performance of your algorithm.
   *  Explain your answer.
   *
   *  Performance: Worst case - M + N
   *
   *
   */
  public static int[][] dfa;       // the KMP automoton

  public static boolean contains(String txt, String pat) {
    //If pattern is found in string, return true
    if(searchFirst(txt, pat) != -1)
      return true;

    else
    return false;
  }

  /*
   * This method creates a dfa which is then used to process the pattern string.
   * Standard radix of 256
   */
  public static void createDFA(String pat){

        int R = 256;

        // build DFA from pattern
        int m = pat.length();
        dfa = new int[R][m];
        dfa[pat.charAt(0)][0] = 1;

        for (int x = 0, j = 1; j < m; j++) {
          
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][x];     // Copy mismatch cases.
            dfa[pat.charAt(j)][j] = j+1;   // Set match case.
            x = dfa[pat.charAt(j)][x];     // Update restart state.
        }
  }

  /*
   * The method returns the index of the first ocurrence of a pattern pat in String txt.
   * It should return -1 if the pat is not present
   */
  public static int searchFirst(String txt, String pat) {
    //First create dfa
    if(txt.length() == 0 || pat.length() == 0)
    	return -1;

    createDFA(pat);

    // simulate operation of DFA on text
        int m = pat.length();
        int n = txt.length();
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == m) return i - m;    // found
        return -1;                    // not found
  }

  /*
   * The method returns the number of non-overlapping occurences of a pattern pat in String txt.
   */
  public static int searchAll(String txt, String pat) {
  	if(txt.length() == 0 || pat.length() == 0)
    	return 0;

    //First create dfa
    createDFA(pat);
    String tmp = txt;
    int count = 0;
    int result;
    boolean carryOn = true;

    //Recursively search for string, slicing
    while(carryOn){
    	result = searchFirst(tmp, pat);

    	//If pattern found in text continue
    	if(result != -1){
    		count++;
    		//**
    		tmp = tmp.substring(result+pat.length());
    	}
    	else
    		carryOn = false;
    }

    if(count!=0)
    	return count;

    else
    	return count;
  }
}