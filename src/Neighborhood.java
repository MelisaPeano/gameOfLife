import java.util.ArrayList;

public class Neighborhood {
    int neighbor;
    Cell [][] startBoard;


    public Neighborhood(int neighbor, Cell [][] startBoard ) {
        this.neighbor = neighbor;
        this.startBoard = startBoard;
    }



    public Cell [][] getNeighborhood() {
        int height = startBoard.length;
        int width = startBoard[0].length;
        int[] moveInX;
        int[] moveInY;

        switch (neighbor) {
            case 1 -> {
                moveInX = new int[]{-1, 1, 0, 0};
                moveInY = new int[]{0, 0, -1, 1};
            }
            case 2, 4 -> {
                moveInX = new int[]{-1, -1, 1, 1};
                moveInY = new int[]{-1, 1, -1, 1};
            }
            case 5 -> {
                moveInX = new int[]{-2, -1, 1, 2, -2, -1, 1, 2};
                moveInY = new int[]{-2, -2, -2, -2, 2, 2, 2, 2};
            }
            default -> {
                moveInX= new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
                moveInY = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
            }
        }

        // Nueva generación sin modificar startBoard directamente
        Cell[][] nextGeneration = new Cell[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int aliveNeighbors = 0;

                for (int k = 0; k < moveInX.length; k++) {
                    int newRow = i + moveInX[k];
                    int newCol = j + moveInY[k];

                    // Verificar si la nueva posición está dentro de los límites
                    if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width) {
                        if (startBoard[newRow][newCol].getState() == 1) {
                            aliveNeighbors++;
                        }
                    }
                }
                nextGeneration[i][j] = new Cell(startBoard[i][j].keepAlive(aliveNeighbors));
            }
        }

        return nextGeneration;

    }
}
