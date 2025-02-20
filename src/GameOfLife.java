/* compilar el archivo javac src/GameOfLife.java ...
javac src/*.java
ejecutar ejemplo java -cp src GameOfLife w=10 h=20 g=100 s=300 p=”101#010#111” n=1

 */

public class GameOfLife {
    public static void main(String[] args) {

        // Creo las variables a utilizar y separo en clave-valor, leo args []
        int width = 0, height = 0, generations = 0, speed = 0, neighborhood = 0;
        String poblation = "";

        for (String arg : args) {
            String[] parts = arg.split("=");
            if (parts.length == 2) {
                String key = parts[0];
                String value = parts[1];

                // valido args
                switch (key) {
                    case "w":
                        if (isInteger(value)) {
                            int temporalValue = Integer.parseInt(value);
                            if (temporalValue == 10 || temporalValue == 20 || temporalValue == 40 || temporalValue == 80) {
                                width = temporalValue;
                                System.out.println("width : " + width);
                            } else {
                                System.out.println("width : " + "[invalid]");
                                System.out.println("Se aplicará el ancho por defecto: 40");
                                width = 40;
                            }
                        } else {
                            System.out.println("width : " + "[invalid]");
                            System.out.println("Se aplicará el ancho por defecto: 40");
                            width = 40;
                        }
                        break;

                    case "h":
                        if (isInteger(value)) {
                            int temporalValue = Integer.parseInt(value);
                            if (temporalValue == 10 || temporalValue == 20 || temporalValue == 40 || temporalValue == 80) {
                                height = temporalValue;
                                System.out.println("height : " + height);
                            } else {
                                System.out.println("height : " + "[invalid]");
                                System.out.println("Se aplicará el alto por defecto: 40");
                                height = 40;
                            }
                        } else {
                            System.out.println("height : " + "[invalid]");
                            System.out.println("Se aplicará el alto por defecto: 40");
                            height = 40;
                        }
                        break;

                    case "g":
                        if (isInteger(value)) {
                            generations = Integer.parseInt(value);
                            if (generations >= 0) {
                                System.out.println("generations: " + generations);
                            } else {
                                System.out.println("generations: " + "[not available]");
                                generations = 0;
                            }
                        } else {
                            System.out.println("generations: " + "[invalid]");
                            generations = 0;
                        }
                        break;

                    case "s":
                        if (isInteger(value)) {
                            int tempSpeed = Integer.parseInt(value);
                            if (tempSpeed > 0) {
                                speed = tempSpeed;
                                System.out.println("speed: " + speed);
                            } else {
                                System.out.println("speed: " + "[not available], se usará el valor por defecto (1000ms)");
                                speed = 1000;
                            }
                        } else {
                            System.out.println("speed: " + "[invalid input], se usará el valor por defecto (1000ms)");
                            speed = 1000;
                        }
                        break;

                    case "p":
                        poblation = value.replace("\"", "");
                        if (poblation.isEmpty()) {
                            System.out.println("poblation: " + "[not available]");
                        } else {
                            System.out.println("poblacion: " + poblation);
                        }
                        break;

                    case "n":
                        if (isInteger(value)) {
                            int parcialNeighborhood = Integer.parseInt(value);
                            if (parcialNeighborhood >= 1 && parcialNeighborhood <= 5) {
                                neighborhood = parcialNeighborhood;
                                System.out.println("neighborhood: " + neighborhood);
                            } else {
                                System.out.println("neighborhood: " + "[invalid], se usará el valor por defecto (3)");
                                neighborhood = 3;
                            }
                        } else {
                            System.out.println("neighborhood: " + "[invalid input], se usará el valor por defecto (3)");
                            neighborhood = 3;
                        }
                        break;

                    default:
                        System.out.println("Parámetro desconocido: " + key);
                        break;
                }
            } else {
                System.out.println("Parámetro inválido: " + arg);
            }

        }


        // Empiezo a ejecutar el juego
        System.out.println("Iniciando el Juego de la Vida...");
        //creo el tableero base
        BoardGame board = new BoardGame(width, height, poblation,speed, neighborhood, generations);
        // tablero con la pob pasada por parámetro
        Cell [][] boardEnd =  board.startBoardGame();
        board.getBoardGame(boardEnd);

    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }




}
