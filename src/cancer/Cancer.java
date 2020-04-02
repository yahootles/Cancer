/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cancer;

/**
 *
 * @author tiein
 */
import java.io.*;

public class Cancer {

    /**
     * @param args the command line arguments
     */
    public static char[][] grid;

    public static void main(String[] args) {
        try {
            int numSpots = 0;//number of cancer spots
            File inFile = new File("inputFile.dat");//get the input file

            BufferedReader read = new BufferedReader(new FileReader(inFile));//set up reader for the file

            grid = new char[15][15];//instantiate grid as a grid of 15x15 characters

            for (int rows = 0; rows < 15; rows++) {//go through the file, and read the characters into grid
                for (int cols = 0; cols < 15; cols++) {
                    char inChar = (char) read.read();
                    if (inChar == '\n') {
                        inChar = (char) read.read();//read the next character if the read character was a newline
                    }
                    grid[rows][cols] = inChar;
                    System.out.print(grid[rows][cols]);//output the characters to show the user the grid
                }
                System.out.println("");
            }
            
            
            for (int rows = 0; rows < 15; rows++) {//floodfill every '-' in grid
                for (int cols = 0; cols < 15; cols++) {
                    if(grid[rows][cols] == '-'){
                        floodFill(rows, cols);
                        numSpots++;//if a '-' is found, increase the number of cancer spots by 1
                    }
                }
            }
            
            System.out.println("\n\nThe file had " + numSpots + " cancer spots in it.\nThe new grid is:");//output number of cancer spots and new grid to user
            for (int rows = 0; rows < 15; rows++) {
                for (int cols = 0; cols < 15; cols++) {
                    System.out.print(grid[rows][cols]);
                }
                System.out.println("");
            }
            

        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

    /**
     * Tries to remove a cancer cell at an input location, and recursively uses /0000/0/
     * @param row - the row that the cell is in
     * @param col - the column that the cell is in
     */
    public static void floodFill(int row, int col) {
        if (grid[row][col] == '-') {
            grid[row][col] = ' ';//if it is cancerous, remove it by setting to be blank
            floodFill(row - 1, col - 1);//recursively try to remove cancer from all adjacent cells
            floodFill(row - 1, col);
            floodFill(row - 1, col + 1);
            floodFill(row, col - 1);
            floodFill(row, col + 1);
            floodFill(row + 1, col - 1);
            floodFill(row + 1, col);
            floodFill(row + 1, col + 1);
        }
    }

}
