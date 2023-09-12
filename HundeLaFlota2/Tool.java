package HundeLaFlota;

import java.util.Scanner;
import java.lang.Math;

public class Tool {

    /**
     * Esta función lee un número y comprueba que sea un número.
     * @return Devuelve el número introducido.
     */

    public static int readNum() {

        Scanner sc = new Scanner(System.in);

        while (!sc.hasNextInt()) {
            System.out.print("Introduce a number, please - ");
            sc.next();

        }

        return sc.nextInt();

    }

    /**
     * Esta función lee una palabra introducida por pantalla.
     * @return Devuelve la palabra introducida.
     */

    public static String readWord() {

        Scanner sc = new Scanner(System.in);

        String word;

        do {

            word = sc.nextLine();

            if (word.isEmpty()) {
                System.out.print("You cannot leave this data empty... - ");

            }

        } while (word.isEmpty());

        return word;

    }

    /**
     * Esta función comprueba que un número se encuentre entre un máximo y un mínimo.
     * @param inf Número mínimo
     * @param sup Número máximo
     * @return Devuelve el número en caso de ser correcto.
     */
    public static int correctOption(int inf, int sup) {

        int num;

        do {

            num = Tool.readNum();

            if (num < inf || num > sup) {
                System.out.print("Introduce a correct option! - ");

            }

        } while (num < inf || num > sup);

        return num;

    }

    /**
     * Esta función comprueba que el nombre de jugador introducido sea correcto.
     * @return Devuelve el nombre introducido.
     */

    public static String correctName() {

        String name;

        do {

            name = Tool.readWord();

        } while (!isCorrect(name));

        return name;

    }

    /**
     * Esta función indica si la palabra introducida es correcta o no, leyéndola letra por letra, pudiendo tener solamente
     * letras y números.
     * @param name Le pasamos el nombre introducido por pantalla.
     * @return Devuelve true o false en función de si el nombre está bien o mal.
     */

    public static Boolean isCorrect(String name) {

        int i = 0;
        boolean noCorrect = true;
        name = name.toUpperCase();

        while (noCorrect && i < name.length()) {

            if (!((name.charAt(i) >= 'A' && name.charAt(i) <= 'Z') || (name.charAt(i) >= '0' && name.charAt(i) <= '9') || name.charAt(i) == ' ')) {
                System.out.print("Introduce a correct name! (Without character please...) - ");
                noCorrect = false;
            }

            i++;
        }

        return noCorrect;
    }

    /**
     * Esta función se encarga de leer una coordenada.
     * @return Devuelve la coordenada introducida.
     */

    public static String readCoordinate() {

        String coordinate;

        do {

            coordinate = readWord().toUpperCase();

            while (coordinate.length() != 2) {
                System.out.print("Introduce a correct coordinate (A-J and 0-9) - ");
                coordinate = readWord().toUpperCase();

            }

        } while (!correctCoordinate(coordinate));

        return coordinate;

    }

    /**
     * Esta función comprueba que la coordenada introducida sea correcta, o mas bien, esté dentro del tablero y cumpla
     * la sintaxis (letra y número).
     * @param coordinate Le pasamos la coordenada introducida.
     * @return Devuelve true o false en función de si la coordenada es correcta o no.
     */

    public static boolean correctCoordinate(String coordinate) {

        int i = 0;
        boolean noCorrect = true;
        coordinate = coordinate.toUpperCase();

        char letter = coordinate.charAt(0);
        int number = coordinate.charAt(1);

        while (noCorrect && i < coordinate.length()) {

            if (letter < 'A' || letter > 'J' || number < '0' || number > '9') {
                System.out.print("Introduce a correct coordinate (A-J and 0-9) - ");
                noCorrect = false;

            }

            i++;

        }

        return noCorrect;

    }

    /**
     * Esta función lee una orientación y la devuelve si es correcta.
     * @return Devuelve la orientación seleccionada.
     */

    public static char readOrientation() {

        String orientation;

        do {

            orientation = readWord().toUpperCase();

            if (orientation.length() > 1 || !(orientation.charAt(0) == 'V' || orientation.charAt(0) == 'H')) {

                System.out.print("Introduce a correct orientation (V or H) - ");

            }


        } while (orientation.length() > 1 || !(orientation.charAt(0) == 'V' || orientation.charAt(0) == 'H'));

        return orientation.toUpperCase().charAt(0);

    }

    /**
     * Esta función genera una coordenada aleatoria dentro del tablero.
     * @return Devuelve una coordenada generada aleatoriamente.
     */

    public static String randomCoordinate() {

        String coordinate = "";

        char letter = (char) (Math.random() * ('J' - 'A' + 1) + 'A');
        coordinate += letter;

        char number = (char) (Math.random() * ('9' - '0' + 1) + '0');
        coordinate += number;

        return coordinate;

    }

    /**
     * Esta función genera una orientación aleatoria.
     * @return Devuelve una orientación generada aleatoriamente.
     */

    public static char randomOrientation() {

        char orientation = ' ';

        int random = (int) (Math.random() * 2);

        if (random == 0) {
            orientation = 'H';

        } else if (random == 1) {
            orientation = 'V';
        }

        return orientation;

    }

    /**
     * Esta función comprueba que los barcos no se salgan del tablero o se superpongan.
     * @param shipBoard Tablero con los barcos del jugador donde comprobaremos que se coloca correctamente.
     * @param coordinate Coordenada del barco introducido.
     * @param orientation Orientación del barco, para comprobar que el barco no se salga o se coloque encima de otro.
     * @param lenght Longitud del barco que estamos introduciendo.
     * @param player Este parámetro es para comprobar si está comprobando un barco del jugador o de la máquina.
     * @return Devuelve true o false dependiendo si el barco se puede colocar o no.
     */
    public static boolean correctPosition(char[][] shipBoard, String coordinate, char orientation, int lenght, boolean player) {

        boolean isCorrect = true;

        String red = "\033[31m";
        int x = 0;

        int letter = (coordinate.charAt(0) - 'A') + 1;
        int number = (coordinate.charAt(1) - '0') + 1;

        if (lenght == 1) {

            if (shipBoard[letter][number] == 'S') {

                if (player) {
                    System.out.println("\n" + red + "Cannot put a ship on top of another ship!" + "\033[0;0m");

                }
                isCorrect = false;

            }


        } else {

            if (fit(orientation, lenght, letter, number)) {
                if (player) {
                    System.out.println("\n" + red + "The ship goes off the board!" + "\033[0;0m");

                }
                isCorrect = false;

            } else {

                if (orientation == 'H') {

                    for (int i = 0; i < lenght; i++) {

                        if (shipBoard[letter][number + i] == 'S') {
                            x++;
                            if (player && x == 1) {
                                System.out.println("\n" + red + "Cannot put a ship on top of another ship!" + "\033[0;0m");

                            }
                            isCorrect = false;

                        }

                    }

                } else if (orientation == 'V') {

                    for (int i = 0; i < lenght; i++) {

                        if (shipBoard[letter + i][number] == 'S') {
                            x++;
                            if (player && x == 1) {
                                System.out.println("\n" + red + "Cannot put a ship on top of another ship!" + "\033[0;0m");

                            }
                            isCorrect = false;

                        }

                    }

                }

            }

        }

        return isCorrect;

    }

    /**
     * Esta función comprueba que el barco que hemos introducido no pueda colocarse al lado de otro barco.
     * @param coordinate Coordenada del barco que queremos introducir.
     * @param orientation Orientación del barco que queremos introducir.
     * @param lenght Longitud del barco que queremos introducir.
     * @param player Este parámetro es para comprobar si está comprobando un barco del jugador o de la máquina.
     * @param supportBoard Tablero de soporte que utilizamos para que el barco pueda colocarse en los bordes del tablero.
     * @return Devuelve true o false dependiendo si el barco está rodeado por agua o no.
     */

    public static boolean isSurrounded(String coordinate, char orientation, int lenght, boolean player, char[][] supportBoard) {

        boolean isCorrect = true;

        String red = "\033[31m";
        int x = 0;

        int letter = (coordinate.charAt(0) - 'A') + 1;
        int number = (coordinate.charAt(1) - '0') + 1;

        if (lenght == 1) {

            if (supportBoard[letter + 1][number] != '~' || supportBoard[letter - 1][number] != '~' || supportBoard[letter][number + 1] != '~' || supportBoard[letter][number - 1] != '~') {

                if (player) {
                    System.out.println("\n" + red + "Cannot place one ship next to another ship!" + "\033[0;0m");

                }
                isCorrect = false;

            }

        } else {

            if (fit(orientation, lenght, letter, number)) {
                if (player) {
                    System.out.println("\n" + red + "The ship goes off the board!" + "\033[0;0m");

                }
                isCorrect = false;

            } else {

                if (orientation == 'H') {

                    for (int i = 0; i < lenght; i++) {

                        if (supportBoard[letter + 1][number + i] != '~' || supportBoard[letter - 1][number + i] != '~' || supportBoard[letter][(number + 1) + i] != '~' || supportBoard[letter][(number - 1) + i] != '~') {
                            x++;
                            if (player && x == 1) {
                                System.out.println("\n" + red + "Cannot place one ship next to another ship!" + "\033[0;0m");

                            }
                            isCorrect = false;

                        }

                    }

                } else if (orientation == 'V') {

                    for (int i = 0; i < lenght; i++) {

                        if (supportBoard[(letter + 1) + i][number] != '~' || supportBoard[(letter - 1) + i][number] != '~' || supportBoard[letter + i][number + 1] != '~' || supportBoard[letter + i][number - 1] != '~') {
                            x++;
                            if (player && x == 1) {
                                System.out.println("\n" + red + "Cannot place one ship next to another ship!" + "\033[0;0m");

                            }
                            isCorrect = false;

                        }

                    }

                }

            }

        }

        return isCorrect;

    }

    /**
     * Esta función combrueba que el barco quepa en el tablero.
     * @param orientation Orientación del barco que estamos introduciendo.
     * @param lenght Longitud del barco que estamos introduciendo.
     * @param letter Coordenada vertical del barco.
     * @param number Coordenada horizontal del barco.
     * @return Devuelve true o false dependiendo si el barco cabe o no cabe.
     */

    public static boolean fit(char orientation, int lenght, int letter, int number) {

        boolean fits = true;

        if (orientation == 'V') {

            if (letter + lenght >= 12) {
                fits = false;

            }

        } else if (orientation == 'H') {

            if (number + lenght >= 12) {
                fits = false;

            }

        }

        return !fits;

    }

    /**
     * Función que inicia el tablero de soporte.
     * @param supportBoard Le pasamos el tablero de soporte que vamos a inicializar.
     */

    public static void initSupport(char[][] supportBoard) {

        for (int i = 0; i < supportBoard.length; i++) {

            for (int j = 0; j < supportBoard.length; j++) {

                supportBoard[i][j] = '~';

            }

        }

    }

    /**
     * Esta función nos detecta si en el tablero hay barcos o no hay barcos.
     * @param board Le pasamos el tablero que queremos comprobar.
     * @return Devuelve true or false dependiendo de si quedan o no barcos.
     */

    public static boolean areBoats(char[][] board) {

        boolean areBoats = false;
        int i = 1;


        do {

            int j = 1;

            do {

                if (board[i][j] == 'S') {
                    areBoats = true;

                }

                j++;

            } while (!areBoats && j < board.length);

            i++;

        } while (!areBoats && i < board.length);

        return areBoats;

    }

    /**
     * Esta función indica si la coordenada que hemos introducido ahora ha sido introducida anteriormente.
     * @param coordinate Le pasamos la coordenada introducida.
     * @param board Pasamos el tablero donde comprobaremos si ya hemos introducido esa coordenada o no.
     * @param player Este parámetro es para comprobar si está comprobando un barco del jugador o de la máquina.
     * @return Devuelve true or false dependiendo de si hemos repetido la coordenada o no.
     */

    public static boolean repeatCoordinate(String coordinate, char[][] board, boolean player) {

        boolean repeated = false;

        if (board[coordinate.charAt(0) - 'A' + 1][coordinate.charAt(1) - '0' + 1] == 'X' || board[coordinate.charAt(0) - 'A' + 1][coordinate.charAt(1) - '0' + 1] == 'O') {

            if (player) {
                System.out.print("\033[31m" + "Cannot select the same box twice! - " + "\033[0;0m");
            }

            repeated = true;

        }

        return repeated;

    }

    /**
     * Esta función sirve para que la IA compruebe si la currentcoordinate se sale del tablero o no.
     * @param coordinate Le pasamos la coordenada actual.
     * @return Devuelve true o false en función de si la coordenada se sale o no del tablero.
     */

    public static boolean isBorder(String coordinate) {

        boolean isBorder = false;

        if (coordinate.charAt(0) - 'A' + 1 < 1 || coordinate.charAt(0) - 'A' + 1 > 10 || coordinate.charAt(1) - '0' + 1 < 1 || coordinate.charAt(1) - '0' + 1 > 10) {

            isBorder = true;

        }

        return isBorder;

    }

    /**
     * Estas funciones devuelven la cordenada de arriba, abajo, izquierda o derecha, respectivamente, de la coordenada
     * actual.
     * @param coordinate Le pasamos la coordenada actual.
     * @return Devuelve la coordenada superior, inferior, izquierda o derecha de la coordenada actual.
     */

    public static String upCoordinate (String coordinate) {

        String result = "";

        char letter = coordinate.charAt(0);
        char number = coordinate.charAt(1);

        letter--;

        result += letter;
        result += number;

        return result;

    }

    public static String downCoordinate (String coordinate) {

        String result = "";

        char letter = coordinate.charAt(0);
        char number = coordinate.charAt(1);

        letter++;

        result += letter;
        result += number;

        return result;

    }

    public static String leftCoordinate (String coordinate) {

        String result = "";

        char letter = coordinate.charAt(0);
        char number = coordinate.charAt(1);

        number--;

        result += letter;
        result += number;

        return result;

    }

    public static String rightCoordinate (String coordinate) {

        String result = "";

        char letter = coordinate.charAt(0);
        char number = coordinate.charAt(1);

        number++;

        result += letter;
        result += number;

        return result;

    }

}