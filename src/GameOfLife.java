/* compilar el archivo javac src/GameOfLife.java ...
javac src/*.java
ejecutar ejemplo java -cp src GameOfLife w=10 h=20 g=100 s=300 p=”101#010#111” n=1

 */

public class GameOfLife {
    public static void main (String [] args) {

        // Creo las variables a utilizar y separo en clave-valor, leo args []
        int width = 0, height = 0, generations = 0, speed = 0, neighborhood = 0;
        String poblation = "";

        for (String arg : args) {
            String[] parts = arg.split("="); // separo por clave valor
            if (parts.length == 2) { // Asegurar que tiene la forma clave=valor
                String key = parts[0];
                String value = parts[1];


        // Asigno los parametros recibidos a las variables creadas
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

                    case "g":
                        if(isInterger(value)) {
                            generations = Integer.parseInt(value);
                            // valor especial infinito 0, tecla ¿?
                            if (generations >= 0) {
                                System.out.println("generations :" + generations);
                            } else {
                                System.out.println("generations: " + "[not available]");
                            }
                            break;
                        }
                            case "s":
                        if (isInterger(value)) {
                            int tempSpeed = Integer.parseInt(value);
                            if (tempSpeed == 0) {
                                System.out.println("speed: " + "[not available], se usará el valor por defecto (1000ms)");
                                speed = 1000; // Valor por defecto si no es válido
                            }
                        } else {
                            System.out.println("speed: " + "[invalid input], se usará el valor por defecto (1000ms)");
                            speed = 1000; // Valor por defecto si no es un número
                        }
                        break;

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

        // Empiezo a ejecutar el juego, creo el tablero
        System.out.println("Iniciando el Juego de la Vida...");
        BoardGame board = new BoardGame(width, height, poblation,speed, neighborhood, generations);
        board.getBoardGame();

        if(poblation.isEmpty()) {
            System.out.println("El tablero no se puede generar con los parametros dados");
        } else {
            System.out.println("poblacion inicial : ");
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
