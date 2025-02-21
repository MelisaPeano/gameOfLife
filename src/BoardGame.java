import java.io.IOException;
import java.util.Random;

public class BoardGame {
    private int neighbor;
    private int width;
    private int height;
    private String poblation;
    private int generations;
    private int speed;
    private boolean stopRequested;


    public BoardGame(int width, int height, String poblation, int speed, int neighbor, int generations) {
        this.width = width;
        this.height = height;
        this.poblation = poblation;
        this.neighbor = neighbor;
        this.generations = generations;
        this.speed = speed;
        this.stopRequested = false;
    }

    // Metodo para crear la poblacion random
    public static String getrandomPoblation(String poblation, int height, int width) {
        if (poblation.equals("rnd")) {
            Random randomPopulation = new Random();
            StringBuilder poblationRandom = new StringBuilder();

            for (int i = 0; i < height; i++) {
                StringBuilder row = new StringBuilder();
                for (int j = 0; j < width; j++) {
                    row.append(randomPopulation.nextInt(2));
                }
                poblationRandom.append(row.toString());
                // Si no es la última fila, agregamos el separador #
                if (i < height - 1) {
                    poblationRandom.append("#");
                }

            }

            poblation = poblationRandom.toString();
        }
        return poblation;
    }

    // creo el tablero inicial con la poblacion del args
    public Cell[][] startBoardGame() {
        if (width <= 0 || height <= 0 || poblation == null || poblation.isEmpty()) {
            throw new IllegalStateException("Dimensiones inválidas o población vacía.");
        } else if (poblation.equals("rnd")) {
            poblation = getrandomPoblation(poblation, height, width);
        }
        String[] rows = poblation.split("#");
        Cell[][] board = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i < rows.length && j < rows[i].length()) {
                    board[i][j] = new Cell(rows[i].charAt(j) == '1' ? 1 : 0);
                } else {
                    board[i][j] = new Cell(0);
                }
            }
        }
        return board;
    }


    public Cell[][] getBoardGame(Cell[][] board) {
        int count = 0;

        // Copiar el tablero inicial
        Cell[][] boarForGenerations = copyGame(board);

        // Creo el vecindario en el tablero que copie
        Neighborhood neighborhood = new Neighborhood(neighbor, boarForGenerations);

        // estoy atenta a si el usuario detiene el juego
        stopGame();


        while ((generations == 0 || count < generations) && !stopRequested) {
            System.out.println("Tablero en generación: " + count);
            renderBoard(neighborhood.getStartBoard());

            // Actualizar el tablero con la nueva generación
            neighborhood.updateBoard();

            count++;
        }
        return neighborhood.getStartBoard();
    }


    // renderizo el talbero con la velocidad asignada
    public void renderBoard(Cell[][] board) {
        for (Cell[] fila : board) {
            for (Cell celda : fila) {
                if (celda == null) {
                    System.out.print(". ");
                } else if (celda.getState() == 1) {
                    System.out.print("■ ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        try {
            Thread.sleep(speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private Cell [][] copyGame (Cell [][] board) {
        Cell [][] boarForGenerations = new Cell [height][width];

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++) {
               Cell newCell = new Cell(board[i][j].getState());
                boarForGenerations[i][j] = newCell;
            }
        }

        return boarForGenerations;
    }


    // Hilo de ejecucion que espera a que el usuario presione una tecla para detener el juego
    public void stopGame () {
       Thread stopThread = new Thread(() -> {
           try {
               System.in.read();
               stopRequested = true;
               System.out.println("\nDeteniendo el juego...");
           } catch (IOException e) {
               e.printStackTrace();
           }
       });
       stopThread.setDaemon(true);
       stopThread.start();
   }
}
