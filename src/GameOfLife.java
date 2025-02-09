/* compilar el archivo javac src/GameOfLife.java ...
ejecutar ejemplo java -cp src GameOfLife w=10 h=20 g=100 s=300 p=”101#010#111” n=1

 */

import java.util.Random;

public class GameOfLife {
    public static void main (String [] args) {
        int width = 0, height = 0, generations = 0, speed = 0, neighborhood = 0;
        String poblation = "";

        for (String arg : args) {
            String[] parts = arg.split("="); // separo por clave valor
            if (parts.length == 2) { // Asegurar que tiene la forma clave=valor
                String key = parts[0];
                String value = parts[1];

                switch (key) {
                    case "w":
                        if(isInterger(value)) {
                            int temporalValue = Integer.parseInt(value);
                            if (temporalValue == 10 || temporalValue == 20 || temporalValue == 40 || temporalValue == 80) {
                                width = temporalValue;
                                System.out.println("width : " + width);
                                break;
                            }
                        } else {
                            System.out.println("width : " + "[invalid]");
                            width = 0;
                        }

                    case "h":
                        if(isInterger(value)) {
                            int temporalValue = Integer.parseInt(value);
                            if (temporalValue == 10 || temporalValue == 20 || temporalValue == 40 || temporalValue == 80) {
                                height = temporalValue;
                                System.out.println("height : " + height);
                                break;
                            }
                        } else {
                            System.out.println("height : " + "[invalid]");
                            height = 0;
                        }
                        break;

                    case "g": generations = Integer.parseInt(value);
                        // valor especial infinito 0, tecla ¿?
                        if(generations >=0) {
                            System.out.println("generations :" + generations);
                            break;
                        } else {
                            System.out.println("generations: " + "[not available]");
                            break;
                        }
                    case "s": speed = Integer.parseInt(value);
                        if (speed == 250 || speed == 500 || speed == 1000 || speed == 1500 ) {
                            System.out.println("speed : " + speed);
                            break;
                        } else {
                            System.out.println("speed: " + "[not available]");
                            break;
                        }

                    case "p":
                        poblation = value.replace("\"", "");
                        if (poblation.isEmpty()) {
                            System.out.println("poblation: " + "[not available]");
                            return;
                        } else {
                            System.out.println("poblacion : " + poblation);
                        }
                        break;
                    case "n":
                        if(isInterger(value));
                        int parcialneighborhood = Integer.parseInt(value);
                        if (parcialneighborhood >= 1 || parcialneighborhood <= 5){
                            neighborhood = parcialneighborhood;
                            System.out.println("neighborhood :" + neighborhood);
                        } else {
                            neighborhood = 3;
                        }
                        break;
                    default:
                        System.out.println("Parámetro desconocido: " + key);
                        break;
                }
            } else {
                System.out.println("Formato incorrecto en el argumento: " + arg);
                return;
            }
        }

        int[][] gameBoard = createBoardGame(width, height, poblation);
        if(poblation.isEmpty()) {
            System.out.println("El tablero no se puede generar con los parametros dados");
        } else {
            System.out.println("poblacion inicial : ");
            showBoard(gameBoard);
        }


    }

    public static int[][] createBoardGame(int width, int height, String poblation) {
        if(width == 0 || height == 0 || poblation.isEmpty()) {
            return new int[0][0];
        }
       String endPoblation =  random(poblation, height, width);
       int [][] board = new int[height][width];
       String [] filas = endPoblation.split("#");

       for( int fila = 0; fila < board.length && fila < filas.length; fila++) {
           String filaPoblation = filas[fila];
           for( int columnas = 0;columnas < filaPoblation.length() && columnas < board[fila].length; columnas++ ) {
               char c = filaPoblation.charAt(columnas);
               if (c == '0' || c == '1') {
                   board[fila][columnas] = c - '0'; // Conversión correcta
               } else {
                   System.out.println("Error: Caracter inesperado en poblation -> " + c);
               }

           }

       }
        return board;
    }
    public static String random (String poblation, int height, int width) {
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

    public static void showBoard (int [][] board) {
        for (int[] fila : board) {
            for (int celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }
    public static boolean isInterger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }



}
