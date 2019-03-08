package recursion.backtracking.sudoku;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SudokuSolver {
    //region Variables, Ctor, Helpers

    private int[][] _arr;

    public SudokuSolver(int[][] arr) {
        _arr = arr;
    }

    private static int[][] Copy(int[][] arr) {
        return arr.clone();
    }

    //endregion

    //region Public Interface

    public int[][] Solve() {
        if (Solve(_arr))
            return _arr;
        else
            return null;
    }

    public static void PrettyPrintSudokuField(int[][] arr)
    {
        System.out.println("═════════╦═════════╦═════════");
        for (int x = 0; x < 9; x++)
        {
            if (x != 0 && x % 3 == 0)
                System.out.println("═════════╬═════════╬═════════");

            for (int y = 0; y < 9; y++)
            {
                if (y != 0 && y % 3 == 0)
                    System.out.print("║");

                System.out.print(" " + arr[x][y] + " ");

                if (y == 8)
                    System.out.println();
            }
        }
        System.out.println("═════════╩═════════╩═════════");
    }

    //endregion

    //region Private Interface

    private boolean Solve(int[][] arr) {
        int[][] copy = Copy(arr);
        int[] spot = FindNextSpot(copy); // Nächstes Feld mit einer 0 finden
        int x = spot[0];
        int y = spot[1];

        if (x == -1)
        {
            _arr = copy;
            return true; // Wenn alle Felder durch, dann raus
        }

        // Alle Zahlen von 1 bis 9 durchgehen
        for (int num = 1; num <= 9; num++)
        {
            if (!Check(copy, x, y, num)) continue;

            copy[x][y] = num;
            if (Solve(copy))
                return true;
        }

        return false;
    }

    private static boolean Check(int[][] arr, int x, int y, int num) {
        // Check vertikal
        for (int i = 0; i < 9; i++)
        {
            if (arr[x][i] == num)
            return false; // Wenn Zahl bereits in Spalte enthalten, dann Zahl an der Position fehlerhaft
        }

        // Check horizontal
        for (int i = 0; i < 9; i++)
        {
            if (arr[i][y] == num)
            return false; // Wenn Zahl bereits in Reihe enthalten, dann Zahl an der Position fehlerhaft
        }

        // Check Feld
        for (int k = x - (x % 3); k < x - (x % 3) + 3; k++)
            for (int l = y - (y % 3); l < y - (y % 3) + 3; l++)
            {
                if (arr[k][l] == num)
                return false;
            }

        // Wenn alle Tests erfolgreich, dann true zurückgeben
        return true;
    }

    private int[] FindNextSpot(int[][] arr) {
        for (int x = 0; x < 9; x++)
            for (int y = 0; y < 9; y++)
            {
                if (arr[x][y] == 0)
                return new int[] { x, y };
            }

        return new int[] { -1, -1 };
    }

    //endregion

}