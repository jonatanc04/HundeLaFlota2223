package HundeLaFlota;

public class Game {

    public static final int UNKNOWN = 0;
    public static final int NO = 1;

    public static int UP = UNKNOWN;
    public static int DOWN = UNKNOWN;
    public static int LEFT = UNKNOWN;
    public static int RIGHT = UNKNOWN;

    public static boolean touched = false;

    public static String originalTouchedCoordinate = "";
    public static String currentTouchedCoordinate = "";

    /**
     * Esta función inicia los tableros del jugador y de la máquina, además de un tablero de soporte que utilizaremos
     * a la hora de colocar los barcos. Además, es la función principal que llama a las funciones encargadas de hacer
     * funcional el juego.
     */

    public static void initiateGame() {

        char[][] shipBoard = new char[12][12];
        char[][] shootBoard = new char[12][12];
        char[][] machineBoard = new char[12][12];

        char[][] supportBoard = new char[12][12];

        int lenguage = selectLenguage();
        String name = introName(lenguage);

        startBoard(shipBoard, shootBoard, machineBoard, lenguage);
        Tool.initSupport(supportBoard);
        placeShips(shipBoard, shootBoard, supportBoard, lenguage);
        Tool.initSupport(supportBoard);
        placeMachineShips(machineBoard, supportBoard);
        startShoots(shipBoard, shootBoard, machineBoard, lenguage);
        winnerGame(shipBoard, machineBoard, lenguage, name);

    }

    /**
     * Esta función sirve para seleccionar el idioma del juego.
     * @return Devuelve el idioma seleccionado si es correcto.
     */

    public static int selectLenguage() {

        Show.lenguage();

        return Tool.correctOption(1,2);

    }

    /**
     * Esta función sirve para que el jugador introduzca su nombre o sobrenombre.
     * @param lenguage Le pasamos el lenguaje seleccionado para que muestre por pantalla la información en ese idioma.
     * @return Devuelve el nombre introducido si es correcto.
     */

    public static String introName(int lenguage) {

        Show.soldierName(lenguage);

        return Tool.correctName();

    }

    /**
     * Esta función es la encargada de iniciar todos los tableros.
     * @param shipBoard Tablero donde se colocarán los barcos del jugador y los disparos de la máquina.
     * @param shootBoard Tablero donde se mostrarán los disparos realizados por el jugador.
     * @param machineBoard Tablero invisible donde se colocarán los barcos de la máquina.
     * @param lenguage Le pasamos el lenguaje seleccionado para que muestre por pantalla la información en ese idioma.
     */

    public static void startBoard(char[][] shipBoard, char[][] shootBoard, char[][] machineBoard, int lenguage) {

        char nums = '0';
        char letters = 'A';

        for (int j = 0; j < shipBoard.length; j++) {

            for (int l = 0; l < shipBoard.length; l++) {

                if ((j == 0 && l == 0) || (j == 0 && l == shipBoard.length - 1) || (j == shipBoard.length - 1 && l == 0) || (j == shipBoard.length - 1 && l == shipBoard.length - 1)) {

                    shipBoard[j][l] = ' ';
                    shootBoard[j][l] = ' ';
                    machineBoard[j][l] = ' ';


                }

            }

        }

        for (int i = 1; i < shipBoard.length - 1; i++) {

            shipBoard[0][i] = nums;
            shipBoard[shipBoard.length - 1][i] = nums;
            shipBoard[i][0] = letters;
            shipBoard[i][shipBoard.length - 1] = letters;

            shootBoard[0][i] = nums;
            shootBoard[shootBoard.length - 1][i] = nums;
            shootBoard[i][0] = letters;
            shootBoard[i][shootBoard.length - 1] = letters;

            machineBoard[0][i] = nums;
            machineBoard[shootBoard.length - 1][i] = nums;
            machineBoard[i][0] = letters;
            machineBoard[i][shootBoard.length - 1] = letters;

            nums++;
            letters++;

        }

        for (int j = 1; j < shipBoard.length - 1; j++) {

            for (int l = 1; l < shipBoard.length - 1; l++) {

                shipBoard[j][l] = '~';
                shootBoard[j][l] = '~';
                machineBoard[j][l] = '~';

            }

        }

        Show.showBoard(shipBoard, shootBoard, lenguage);

    }

    /**
     * Esta función sirve para colocar los barcos del jugador.
     * @param shipBoard Tablero donde se colocarán los barcos del jugador y los disparos de la máquina.
     * @param shootBoard Tablero donde se mostrarán los disparos realizados por el jugador.
     * @param supportBoard Tablero invisible que utilizamos de soporte para colocar los barcos.
     * @param lenguage Le pasamos el lenguaje seleccionado para que muestre por pantalla la información en ese idioma.
     */

    public static void placeShips(char[][] shipBoard, char[][] shootBoard, char[][] supportBoard, int lenguage) {

        int[] ships = {1, 1, 1, 2, 2, 2, 3, 3, 4};
        int countShips = 0;
        String coordinate;
        char orientation;

        while (countShips < ships.length) {

            Show.introCoordinate(lenguage);
            coordinate = Tool.readCoordinate();

            if (ships[countShips] == 1) {
                if (Tool.correctPosition(shipBoard, coordinate, 'X', ships[countShips], true) && Tool.isSurrounded(coordinate,'X', ships[countShips], true, supportBoard)) {
                    shipBoard[(coordinate.charAt(0) - 'A') + 1][(coordinate.charAt(1) - '0') + 1] = 'S';
                    supportBoard[(coordinate.charAt(0) - 'A') + 1][(coordinate.charAt(1) - '0') + 1] = 'S';
                    countShips++;

                }

            } else if (ships[countShips] > 1) {

                Show.introOrientation(lenguage);
                orientation = Tool.readOrientation();

                if (Tool.correctPosition(shipBoard, coordinate, orientation, ships[countShips], true) && Tool.isSurrounded(coordinate, orientation, ships[countShips], true, supportBoard)) {

                    if (orientation == 'H') {

                        for (int i = 0; i < ships[countShips]; i++) {
                            shipBoard[(coordinate.charAt(0) - 'A') + 1][((coordinate.charAt(1) - '0') + 1) + i] = 'S';
                            supportBoard[(coordinate.charAt(0) - 'A') + 1][((coordinate.charAt(1) - '0') + 1) + i] = 'S';

                        }

                    } else if (orientation == 'V'){

                        for (int i = 0; i < ships[countShips]; i++) {
                            shipBoard[((coordinate.charAt(0) - 'A') + i) + 1][(coordinate.charAt(1) - '0') + 1] = 'S';
                            supportBoard[((coordinate.charAt(0) - 'A') + i) + 1][(coordinate.charAt(1) - '0') + 1] = 'S';

                        }

                    }

                    countShips++;

                }

            }

            Show.showBoard(shipBoard, shootBoard, lenguage);

        }

    }

    /**
     * Esta función sirve para colocar los barcos de la máquina.
     * @param machineBoard Tablero invisible donde se colocarán los barcos de la máquina.
     * @param supportBoard Tablero invisible que utilizamos de soporte para colocar los barcos.
     */

    public static void placeMachineShips(char[][] machineBoard, char[][] supportBoard) {

        int[] ships = {1, 1, 1, 2, 2, 2, 3, 3, 4};
        int countShips = 0;
        String coordinate;
        char orientation;

        while (countShips < ships.length) {

            coordinate = Tool.randomCoordinate();

            if (ships[countShips] == 1) {
                if (Tool.correctPosition(machineBoard, coordinate, 'X', ships[countShips], false) && Tool.isSurrounded(coordinate,'X', ships[countShips], false, supportBoard)) {
                    machineBoard[(coordinate.charAt(0) - 'A') + 1][(coordinate.charAt(1) - '0') + 1] = 'S';
                    supportBoard[(coordinate.charAt(0) - 'A') + 1][(coordinate.charAt(1) - '0') + 1] = 'S';
                    countShips++;

                }

            } else if (ships[countShips] > 1) {

                orientation = Tool.randomOrientation();

                if (Tool.correctPosition(machineBoard, coordinate, orientation, ships[countShips], false) && Tool.isSurrounded(coordinate,orientation, ships[countShips], false, supportBoard)) {

                    if (orientation == 'H') {

                        for (int i = 0; i < ships[countShips]; i++) {
                            machineBoard[(coordinate.charAt(0) - 'A') + 1][((coordinate.charAt(1) - '0') + 1) + i] = 'S';
                            supportBoard[(coordinate.charAt(0) - 'A') + 1][((coordinate.charAt(1) - '0') + 1) + i] = 'S';

                        }

                    } else if (orientation == 'V'){

                        for (int i = 0; i < ships[countShips]; i++) {
                            machineBoard[((coordinate.charAt(0) - 'A') + i) + 1][(coordinate.charAt(1) - '0') + 1] = 'S';
                            supportBoard[((coordinate.charAt(0) - 'A') + i) + 1][(coordinate.charAt(1) - '0') + 1] = 'S';

                        }

                    }

                    countShips++;

                }

            }

        }

    }

    /**
     * En esta función se inician los disparos del jugador y posteriormente llama a la función siguiente que son los
     * disparos de la máquina.
     * @param shipBoard Tablero donde veremos los barcos del jugador y los disparos de la máquina.
     * @param shootBoard Tablero donde se mostrarán los disparos que hemos realizado.
     * @param machineBoard Tablero invisible donde se encuentran los barcos de la máquina.
     * @param lenguage Le pasamos el lenguaje seleccionado para que muestre por pantalla la información en ese idioma.
     */

    public static void startShoots(char[][] shipBoard, char[][] shootBoard, char[][] machineBoard, int lenguage) {

        Show.openGame(lenguage);
        Show.showBoard(shipBoard, shootBoard, lenguage);

        String coordinate;

        do {

            if (Tool.areBoats(machineBoard)) {

                Show.introShoots(lenguage);

                do {

                    coordinate = Tool.readCoordinate();

                } while (Tool.repeatCoordinate(coordinate, shootBoard, true));

                if (machineBoard[coordinate.charAt(0) - 'A' + 1][coordinate.charAt(1) - '0' + 1] == 'S') {

                    shootBoard[coordinate.charAt(0) - 'A' + 1][coordinate.charAt(1) - '0' + 1] = 'X';
                    Show.touched(lenguage);

                } else if (machineBoard[coordinate.charAt(0) - 'A' + 1][coordinate.charAt(1) - '0' + 1] == '~') {

                    shootBoard[coordinate.charAt(0) - 'A' + 1][coordinate.charAt(1) - '0' + 1] = 'O';
                    Show.water(lenguage);

                }

                if (Tool.areBoats(shipBoard)) {

                    machineShoots(shipBoard, lenguage);

                }

                Show.showBoard(shipBoard, shootBoard, lenguage);

            }

        } while (Tool.areBoats(shipBoard) && Tool.areBoats(machineBoard));

    }

    /**
     * Esta función realiza los disparos de la máquina.
     * @param shipBoard Tablero donde la máquina intentará disparar a los barcos del jugador.
     * @param lenguage Le pasamos el lenguaje seleccionado para que muestre por pantalla la información en ese idioma.
     */

    public static void machineShoots(char[][] shipBoard, int lenguage) {

        String coordinate;

        do {

            if (!touched) {

                coordinate = Tool.randomCoordinate();

            } else {

                iaPlays(shipBoard);
                coordinate = currentTouchedCoordinate;

            }

        } while (Tool.repeatCoordinate(coordinate, shipBoard, false));

        if (shipBoard[coordinate.charAt(0) - 'A' + 1][coordinate.charAt(1) - '0'  + 1] == 'S') {

            shipBoard[coordinate.charAt(0) - 'A' + 1][coordinate.charAt(1) - '0' + 1] = 'X';
            Show.touchedMachine(lenguage);

            if (!touched) {
                originalTouchedCoordinate = coordinate;
            }

            touched = true;
            currentTouchedCoordinate = coordinate;

        } else if (shipBoard[coordinate.charAt(0) - 'A' + 1][coordinate.charAt(1) - '0' + 1] == '~') {

            shipBoard[coordinate.charAt(0) - 'A' + 1][coordinate.charAt(1) - '0' + 1] = 'O';
            Show.waterMachine(lenguage);

        }

    }

    /**
     * Esta función es una pequeña IA que detecta la posición de los barcos del jugador, por defecto, inicia detectando
     * la coordenada superior a la anteriormente tocada, después hacia abajo, luego izquierda y luego derecha.
     * @param board Este es el tablero donde la IA buscará los barcos del jugador para tratar de hundirlos.
     */

    public static void iaPlays(char[][] board) {

        if (UP == UNKNOWN && DOWN == UNKNOWN && LEFT == UNKNOWN && RIGHT == UNKNOWN) {

            if (!Tool.isBorder(Tool.upCoordinate(currentTouchedCoordinate)) && board[Tool.upCoordinate(currentTouchedCoordinate).charAt(0) - 'A' + 1][Tool.upCoordinate(currentTouchedCoordinate).charAt(1) - '0' + 1] == 'S') {

                currentTouchedCoordinate = Tool.upCoordinate(currentTouchedCoordinate);

            } else {

               UP = NO;
               currentTouchedCoordinate = originalTouchedCoordinate;

            }

        }

        if (UP == NO) {

            if (!Tool.isBorder(Tool.downCoordinate(currentTouchedCoordinate)) && board[Tool.downCoordinate(currentTouchedCoordinate).charAt(0) - 'A' + 1][Tool.downCoordinate(currentTouchedCoordinate).charAt(1) - '0' + 1] == 'S') {

                currentTouchedCoordinate = Tool.downCoordinate(currentTouchedCoordinate);

            } else {

                DOWN = NO;
                currentTouchedCoordinate = originalTouchedCoordinate;

            }

        }

        if (DOWN == NO) {

            if (!Tool.isBorder(Tool.leftCoordinate(currentTouchedCoordinate)) && board[Tool.leftCoordinate(currentTouchedCoordinate).charAt(0) - 'A' + 1][Tool.leftCoordinate(currentTouchedCoordinate).charAt(1) - '0' + 1] == 'S') {

                currentTouchedCoordinate = Tool.leftCoordinate(currentTouchedCoordinate);

            } else {

                LEFT = NO;
                currentTouchedCoordinate = originalTouchedCoordinate;

            }

        }

        if (LEFT == NO) {

            if (!Tool.isBorder(Tool.rightCoordinate(currentTouchedCoordinate)) && board[Tool.rightCoordinate(currentTouchedCoordinate).charAt(0) - 'A' + 1][Tool.rightCoordinate(currentTouchedCoordinate).charAt(1) - '0' + 1] == 'S') {

                currentTouchedCoordinate = Tool.rightCoordinate(currentTouchedCoordinate);

            } else {

                RIGHT = NO;
                currentTouchedCoordinate = originalTouchedCoordinate;

            }

        }

        if (UP == NO && DOWN == NO && LEFT == NO && RIGHT == NO) {

            UP = UNKNOWN;
            DOWN = UNKNOWN;
            LEFT = UNKNOWN;
            RIGHT = UNKNOWN;

            originalTouchedCoordinate = Tool.randomCoordinate();
            currentTouchedCoordinate = Tool.randomCoordinate();

            touched = false;

        }

    }

    /**
     * Esta función indica al final del juego, cuando uno de los dos tableros se queda sin barcos, quien ha sido el ganador,
     * si el jugador o mas bien la máquina.
     * @param shipBoard Tablero final del jugador con o sin barcos.
     * @param machineBoard Tablero final de la máquina, con o sin barcos.
     * @param lenguage Le pasamos el lenguaje seleccionado para que muestre por pantalla la información en ese idioma.
     * @param player Este es el nombre que ha introducido anteriormente el jugador.
     */
    public static void winnerGame(char[][] shipBoard, char[][] machineBoard, int lenguage, String player) {

        if (!Tool.areBoats(machineBoard)) {

            Show.playerWins(lenguage, player);

        } else if (!Tool.areBoats(shipBoard)) {

            Show.machineWins(lenguage);

        }

        Show.endGame();

    }

}