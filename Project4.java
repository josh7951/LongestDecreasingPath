import java.io.*;
import java.util.*;

public class Project4{
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
  public static void main(String[] args) {
    int[] input = readFile("input4-1.txt");
    int row = input[0];
    int column = input[1];
    int[] arr = Arrays.copyOfRange(input, 2, input.length);
    System.out.println(Arrays.toString(arr));
    int grid[][] = new int[row][column];

    for(int i = 0; i < row; i++)
      for(int j = 0; j < column; j++)
        grid[i][j] = arr[(i*column) + j];

    System.out.println(Arrays.deepToString(grid));
  } 
}