/* Programmer: Joshua Mejia
 * COMP482 - Project 4
 * Noga
 * 14 December 2020
 */

import java.io.*;
import java.util.*;

public class Project4{
  // initialize some variables
  public static int dp[][];
  public static int longestPath = -1;
  public static int lpi, lpj;
  // simple function to input data from text file
  public static int[] readFile(String file){
    try{
        File f = new File(file);
        Scanner s = new Scanner(f);
        int itr = 0;
        while(s.hasNextInt()) {
            itr++;
            s.nextInt();
        }
        s.close();
        int[] arr = new int[itr];
        Scanner s1 = new Scanner(f);

        for(int i = 0; i < arr.length; i++)    
            arr[i] = s1.nextInt();
            s1.close();
        return arr;
    }
    catch(Exception e) { return null; }
  } 

  // This method will create the paths possible from start to finish
  public static void init(int[][] arr, int row, int col){

    dp[row-1][col-1] = 1;
    for(int i = row - 2; i >= 0; i--){
      if( arr[i][col - 1] > arr[i + 1][col - 1]){
        dp[i][col - 1] = 1 + dp[i + 1][col - 1];
      }
      else{
        dp[i][col - 1] = 1;
      }

      if(dp[i][col - 1] > longestPath){
        longestPath = dp[i][col - 1];
        lpi = i;
        lpj = col - 1; 
      }
    }

    for(int j = col - 2; j >= 0; j--){
      if(arr[row - 1][j] > arr[row - 1][j + 1]){
        dp[row - 1][j] = 1 + dp[row - 1][j + 1];
      }
      else{
        dp[row - 1][j] = 1;
      }

      if(dp[row - 1][j] > longestPath){
        longestPath = dp[row - 1][j];
        lpi = row - 1;
        lpj = j;
      }
    }
  }

  // This method will determine which path is the longest segement from start to finish.
  public static void findLPS(int[][] arr, int row, int col){

    for(int i = row - 2; i >= 0; i--){
      for(int j = col - 2; j >= 0; j--){
        dp[i][j] = 1;

        if(arr[i][j + 1] < arr[i][j])
          dp[i][j] += dp[i][j + 1];

        if(arr[i + 1][j] < arr[i][j])
          dp[i][j] = Math.max(dp[i][j], 1 + dp[i + 1][j]);

        if(dp[i][j] > longestPath){
          longestPath = dp[i][j];
          lpi = i;
          lpj = j;
        }
      }
    }
  }
  public static void main(String[] args) {
    int[] input = readFile("input4.txt");
    int row = input[0];
    int column = input[1];
    int[] arr = Arrays.copyOfRange(input, 2, input.length);
    //System.out.println(Arrays.toString(arr));
    // Create 2D array from input starting after the first two inputs that determine size row x column
    int grid[][] = new int[row][column];
    dp = new int[row][column];
    for(int i = 0; i < row; i++)
      for(int j = 0; j < column; j++)
        grid[i][j] = arr[(i*column) + j];
    //System.out.println(Arrays.deepToString(grid));
    init(grid, row, column);
    findLPS(grid, row, column);
    System.out.println(longestPath);
  } 
}