

public class Neighborhood {
    int neighbor;
    Cell [][] startBoard;


    public Neighborhood(int neighbor, Cell [][] startBoard ) {
        this.neighbor = neighbor;
        this.startBoard = startBoard;
    }



    public Cell [][] getNeighborhood() {
        if (startBoard == null || startBoard.length == 0 || startBoard[0].length == 0) {
            throw new IllegalStateException("El tablero no puede estar vacío o nulo.");
        }
        int height = startBoard.length;
        int width = startBoard[0].length;
        int[] moveInX;
        int[] moveInY;

        switch (neighbor) {
            case 1 -> {
                //Jala University
                moveInX = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
                moveInY = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
            }
            case 2 -> {
                // programming 1
                moveInX = new int[]{1, 0, -1, 0};
                moveInY = new int[]{0, 1, 0, -1};
            }
            case 4 -> {
                moveInX = new int[]{1, 1, 1, 0, 0, -1, -1, -1};
                moveInY = new int[]{1, 0, -1, 1, -1, 1, 0, -1};
            }
            case 5 -> {
                moveInX = new int[]{1, -1, 1, -1};
                moveInY = new int[]{1, 1, -1, -1};
            }
            default -> {
                // moore
                moveInX = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
                moveInY = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
            }
        }

        Cell[][] nextGeneration = new Cell[height][width];

        // Inicializar todas las celdas para evitar valores nulos
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                nextGeneration[i][j] = new Cell(startBoard[i][j].getState());
            }
        }

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

                // Aplicar las reglas del Juego de la Vida
                int newState = startBoard[i][j].keepAlive(aliveNeighbors);
                nextGeneration[i][j].setState(newState);
            }
        }

        return nextGeneration;
    }
    public void updateBoard() {
        startBoard = getNeighborhood(); // Guardamos la nueva generación en el tablero
    }

    public Cell[][] getStartBoard() {
        return startBoard;
    }

    }
