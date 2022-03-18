package OOD;

import java.util.HashSet;
import java.util.Set;

public class PlexiglassProject {
    Panel[] panels;
    Piece[] pieces;
    boolean finished;

    public static void main(String[] args) {
        Panel panel1 = new Panel(48, 96);
        Panel panel2 = new Panel(36, 72);
        Piece myPanel1 = new Piece(25, 28, 'a');
        Piece myPanel2 = new Piece(25, 28, 'b');
        Piece myPanel3 = new Piece(14, 25, 'c');
        Piece myPanel4 = new Piece(14, 25, 'd');
        Piece myPanel5 = new Piece(15, 28, 'e');
        Piece myPanel6 = new Piece(15, 28, 'f');
        Piece myPanel7 = new Piece(20, 29, 'A');
        Piece myPanel8 = new Piece(20, 29, 'B');
        Piece myPanel9 = new Piece(13, 20, 'C');
        Piece myPanel10 = new Piece(13, 20, 'D');
        Piece myPanel11 = new Piece(14, 29, 'E');
        Piece myPanel12 = new Piece(14, 29, 'F');
        Panel[] panels = new Panel[]{panel1};
        Piece[] pieces = new Piece[]{myPanel1, myPanel3, myPanel5,
                myPanel7, myPanel9, myPanel11};
        PlexiglassProject project = new PlexiglassProject(panels, pieces);
        project.cuttingStrategy();
        if (!project.finished) {
            System.out.println("Won't fit, consider to buy more panels");
        }
    }

    PlexiglassProject(Panel[] panels, Piece[] pieces) {
        this.panels = panels;
        this.pieces = pieces;
    }

    void cuttingStrategy() {
        Set<Integer> indexSet = new HashSet<>();
        for (int i = 0; i < pieces.length; i++) {
            indexSet.add(i);
        }
        cuttingStrategy(0, indexSet);
    }

    void cuttingStrategy(int round, Set<Integer> indexSet) {
        if (finished == true) {
            return;
        }
        if (round == pieces.length) {
            for (Panel elem : panels) {
                printMatrix(elem.panel);
            }
            System.out.println();
            finished = true;
            return;
        }

        int index;
        for (index = 0; index < pieces.length; index++) {
            if (indexSet.contains(index)) {
                int targetDim1 = pieces[index].panel.length;
                int targetDim2 = pieces[index].panel[0].length;
                char targetSymbol = pieces[index].symbol;
                for (Panel panel : panels) {
                    int width = panel.panel.length;
                    int length = panel.panel[0].length;
                    for (int row = 0; row < width; row++) {
                        for (int col = 0; col < length; col++) {
                            if (panel.panel[row][col] == 'X'
                                    && ((row == 0 && col == 0) || (row == 0 && panel.panel[row][col - 1] != 'X')
                                    || (col == 0 && panel.panel[row - 1][col] != 'X')
                                    || (row != 0 && col != 0 && panel.panel[row - 1][col] != 'X' && panel.panel[row][col - 1] != 'X'))) {
                                boolean valid1 = valid(panel, row, col, targetDim1, targetDim2);
                                boolean valid2 = valid(panel, row, col, targetDim2, targetDim1);
                                if (valid1) {
                                    // eat
                                    swap(panel, row, col, targetDim1, targetDim2, targetSymbol);
                                    indexSet.remove(index);
                                    cuttingStrategy(round + 1, indexSet);
                                    // spit
                                    swap(panel, row, col, targetDim1, targetDim2, 'X');
                                    indexSet.add(index);
                                }
                                if (valid2) {
                                    // eat
                                    swap(panel, row, col, targetDim2, targetDim1, targetSymbol);
                                    indexSet.remove(index);
                                    cuttingStrategy(round + 1, indexSet);
                                    // spit
                                    swap(panel, row, col, targetDim2, targetDim1, 'X');
                                    indexSet.add(index);
                                }
//                                if (!valid1 && !valid2) {
//                                    row = row + Math.max(targetDim1, targetDim2) - 1;
//                                    col = col + Math.max(targetDim1, targetDim2) - 1;
//                                    if (row >= width) {
//                                        break;
//                                    }
//                                }
                            }
                        }
                    }
                }
            }
        }
    }

    boolean valid(Panel panel, int row, int col, int rowAdd, int colAdd) {
        if (row + rowAdd > panel.panel.length || col + colAdd > panel.panel[0].length) {
            return false;
        }
        for (int i = 0; i < rowAdd; i++) {
            for (int j = 0; j < colAdd; j++) {
                if (panel.panel[row + i][col + j] != 'X') {
                    return false;
                }
            }
        }
        return true;
    }

    void swap(Panel panel, int row, int col, int rowAdd, int colAdd, char symbol) {
        for (int i = 0; i < rowAdd; i++) {
            for (int j = 0; j < colAdd; j++) {
                panel.panel[row + i][col + j] = symbol;
            }
        }
    }

    void printMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length + 2; i++) {
            for (int j = 0; j < matrix[0].length + 2; j++) {
                if (i == 0 || i == matrix.length + 1) {
                    System.out.print('-');
                } else if (j == 0 || j == matrix[0].length + 1) {
                    System.out.print('|');
                } else {
                    System.out.print(matrix[i - 1][j - 1]);
                }
            }
            System.out.println();
        }
    }
}

class Panel {
    char[][] panel;

    Panel(int rowSize, int colSize) {
        this.panel = new char[rowSize][colSize];
        for (int col = 0; col < colSize; col++) {
            for (int row = 0; row < rowSize; row++) {
                this.panel[row][col] = 'X';
            }
        }
    }
}

class Piece extends Panel {
    char symbol;

    Piece(int rowSize, int colSize, char symbol) {
        super(rowSize, colSize);
        this.symbol = symbol;
        for (int col = 0; col < colSize; col++) {
            for (int row = 0; row < rowSize; row++) {
                this.panel[row][col] = symbol;
            }
        }
    }
}