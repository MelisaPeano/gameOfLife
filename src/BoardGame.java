
import java.util.Random;

public class BoardGame {
    private int neighbor;
    private int width;
    private int height;
    private String poblation;
    private int generations;
    private int speed;


    public BoardGame(int width, int height, String poblation, int speed,int neighbor, int generations) {
        this.width =  width;
        this.height = height;
        this.poblation = poblation;
        this.neighbor = neighbor;
        this.generations = generations;
        this.speed = speed;
    }


    public static String getrandomPoblation (String poblation, int height, int width) {
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

    // creo el tablero y lo renderizo
    public Cell[][] getBoardGame() {
        if (width == 0 || height == 0 || poblation.isEmpty()) {
            return new Cell[0][0];
        } else if (poblation.equals("rnd")) {
            poblation = getrandomPoblation(poblation, height, width);
        }

        Cell[][] board = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = new Cell(0);
            }
        }
        String[] filas = poblation.split("#");
        int count = 0;
        Cell[][] currentlyNeighborhood = new Neighborhood(neighbor, board).getNeighborhood();

        while (count < generations ) {
            for (int fila = 0; fila < height; fila++) {
                    if (fila < filas.length) {
                        String filaPoblation = filas[fila];
                        for (int col = 0; col < width; col++) {
                            if (col < filaPoblation.length()) {
                                currentlyNeighborhood[fila][col] = new Cell(filaPoblation.charAt(col) == '1' ? 1 : 0);
                            } else {
                                currentlyNeighborhood[fila][col] = new Cell(0); // Celdas vacías
                            }
                        }
                    } else {
                        for (int col = 0; col < width; col++) {
                            currentlyNeighborhood[fila][col] = new Cell(0); // Celdas vacías
                        }
                    }
            }
            System.out.println("Tablero generacion :" + count);
            renderBoard(currentlyNeighborhood);
            currentlyNeighborhood = new Neighborhood(neighbor, currentlyNeighborhood).getNeighborhood();
            count++;
        }


        return currentlyNeighborhood;
    }


    public void renderBoard(Cell[][] board) {
        for (Cell[] fila : board) {
            for (Cell celda : fila) {
                if (celda == null) {
                    System.out.print(". ");
                } else if (celda.getState() == 1) {
                    System.out.print("X ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
