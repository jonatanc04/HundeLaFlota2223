package HundeLaFlota;

public class Show {

    public static void showMenu() {

        System.out.println("\n" + """
                ◉-------------------------------------◉
                |                                     |
                |             WELCOME TO              |
                |           BATTLESHIP GAME           |
                |                                     |
                ◉-------------------------------------◉
                
                ◉ - 1 - ◉ Play Battleship Game (Java Edition)
                ◉ - 2 - ◉ See the credits
                ◉ - 0 - ◉ Exit Game
                """);

        System.out.print("◉ - Select an option: ");

    }

    public static void lenguage() {

        System.out.println("\n" + """
                ◉-----------------------------------◉
                |        INTRODUCE A LENGUAGE       |
                ◉-----------------------------------◉
                
                ◉ - 1 - ◉ English
                ◉ - 2 - ◉ Spanish
                """);

        System.out.print("◉ - Select an option: ");

    }

    public static void credits() {

        System.out.println("\n" + """
                ◉------------------------------------◉
                |                                    |
                |  ◉  Game created for:              |
                |       Jonatan Córcoles García      |
                |  ◉  Project of:                    |
                |       1ª DAW - IES La Vereda       |
                |  ◉  Professor:                     |
                |       Joaquin Vicente Alonso Saiz  |
                |                                    |
                ◉------------------------------------◉
                """);

        System.out.print("◉ - Enter 1 to return to the menu: ");
        int num = Tool.correctOption(1,1);
        if (num == 1) {
            Lobby.mainLobby();
        }

    }

    public static void endGame() {

        System.out.println("\n" + """
                ◉------------------------------------◉
                |                                    |
                |          THANKS FOR PLAY           |
                |     @Sirinazurey on Twitter ;)     |
                |                                    |
                ◉------------------------------------◉
                """);

    }

    public static void soldierName(int num) {

        if (num == 1) {

            System.out.println("\n" + """
                ◉-----------------------------------◉
                |   INTRODUCE YOUR NAME, SOLDIER!   |
                ◉-----------------------------------◉
                """);

            System.out.print("◉ - Name or nickname: ");

        } else if (num == 2) {

            System.out.println("\n" + """
                ◉-----------------------------------◉
                |  ¡INTRODUZCA SU NOMBRE, SOLDADO!  |
                ◉-----------------------------------◉
                """);

            System.out.print("◉ - Nombre o sobrenombre: ");

        }

    }

    public static void showBoard(char[][] shipBoard, char[][] shootBoard, int num) {

        if (num == 1) {

            System.out.println("""

                    ◉------------◉ SHIPS ◉-------------◉          ◉------------◉ SHOOTS ◉------------◉
                    """);

        } else if (num == 2) {

            System.out.println("""

                    ◉------------◉ BARCOS ◉------------◉          ◉-----------◉ DISPAROS ◉-----------◉
                    """);

        }

        for (int x=0; x < shipBoard.length; x++) {

            for (int y=0; y < shipBoard[x].length; y++) {
                System.out.print(" " + shipBoard[x][y] + " ");

            }

            System.out.print("          ");

            for (int y=0; y < shootBoard[x].length; y++) {
                System.out.print(" " + shootBoard[x][y] + " ");

            }

            System.out.println();

        }

        System.out.println("""
                
                ◉----------------------------------◉          ◉----------------------------------◉
                """);

    }

    public static void introCoordinate(int num) {

        if (num == 1) {

            System.out.println("""
                    ◉-----------------------------------◉
                    |     PLACE THE BOATS, SOLDIER.     |
                    |      INTRODUCE A COORDINATE!      |
                    ◉-----------------------------------◉
                    """);

            System.out.print("◉ - Introduce a coordinate: ");

        } else if (num == 2) {

            System.out.println("""
                    ◉-----------------------------------◉
                    |    COLOCA LOS BARCOS, SOLDADO.    |
                    |     ¡INTRODUCE UNA COORDENADA!    |
                    ◉-----------------------------------◉
                    """);

            System.out.print("◉ - Introduce una coordenada: ");

        }

    }

    public static void introOrientation(int num) {

        if (num == 1) {

            System.out.println("\n" + """
                    ◉-----------------------------------◉
                    |   WHAT ARE YOU DOING, SOLDIER?!   |
                    |    INTRODUCE THE ORIENTATION!     |
                    ◉-----------------------------------◉
                    """);

            System.out.print("◉ - Introduce the orientation: ");

        } else if (num == 2) {

            System.out.println("\n" + """
                    ◉-----------------------------------◉
                    |  ¿¡QUE ESTÁS HACIENDO, SOLDADO!   |
                    |     INTRODUCE LA ORIENTACIÓN!     |
                    ◉-----------------------------------◉
                    """);

            System.out.print("◉ - Introduce la orientación: ");

        }

    }

    public static void introShoots(int num) {

        if (num == 1) {

            System.out.println("""
                    ◉-----------------------------------◉
                    |    THIS IS NOT A GAME, SOLDIER.   |
                    |      INTRODUCE A COORDINATE!      |
                    ◉-----------------------------------◉
                    """);

            System.out.print("◉ - Introduce a coordinate: ");

        } else if (num == 2) {

            System.out.println("""
                    ◉-----------------------------------◉
                    |    ESTO NO ES UN JUEGO, SOLDADO   |
                    |     ¡INTRODUCE UNA COORDENADA!    |
                    ◉-----------------------------------◉
                    """);

            System.out.print("◉ - Introduce una coordenada: ");

        }



    }

    public static void openGame(int num) {

        if (num == 1) {

            System.out.println("\u001B[32m" + """
                    ◉-------------------------------------------------◉
                    ◉---------------- STARTS THE GAME ----------------◉
                    ◉-------------------------------------------------◉
                    """ + "\033[0;0m");

        } else if (num == 2) {

            System.out.println("\u001B[32m" + """
                    ◉-------------------------------------------------◉
                    ◉--------------- COMIENZA EL JUEGO ---------------◉
                    ◉-------------------------------------------------◉
                    """ + "\033[0;0m");

        }

    }

    public static void touched(int num) {

        if (num == 1) {

            System.out.println("\n" + "\u001B[33m" + "TOUCHED! GOOD JOB, SOLDIER." + "\033[0;0m");

        } else if (num == 2) {

            System.out.println("\n" + "\u001B[33m" + "¡TOCADO! BUEN TRABAJO, SOLDADO." + "\033[0;0m");

        }

    }

    public static void water(int num) {

        if (num == 1) {

            System.out.println("\n" + "\u001B[34m" + "Water soldier... aim better." + "\033[0;0m");

        } else if (num == 2) {

            System.out.println("\n" + "\u001B[34m" + "Agua soldado... apunte mejor." + "\033[0;0m");

        }

    }

    public static void touchedMachine(int num) {

        if (num == 1) {

            System.out.println("\u001B[33m" + "THEY HAVE GIVEN US, SOLDIER! THEY HAVE GIVEN US!!!" + "\033[0;0m");

        } else if (num == 2) {

            System.out.println("\u001B[33m" + "¡NOS HAN DADO, SOLDADO! ¡¡¡NOS HAN DADO!!!" + "\033[0;0m");

        }

    }

    public static void waterMachine(int num) {

        if (num == 1) {

            System.out.println("\u001B[34m" + "That was very close..." + "\033[0;0m");

        } else if (num == 2) {

            System.out.println("\u001B[34m" + "Eso ha estado muy cerca..." + "\033[0;0m");

        }

    }

    public static void playerWins(int num, String name) {

        if (num == 1) {

            System.out.println("\u001B[33m" + "CONGRATULATIONS SOLDIER " + name + ", WE WIN!!!! " + "\033[0;0m");

        } else if (num == 2) {

            System.out.println("\u001B[33m" + "ENHORABUENA SOLDADO " + name + ", HEMOS GANADO!!!! " + "\033[0;0m");

        }

    }

    public static void machineWins(int num) {

        if (num == 1) {

            System.out.println("\u001B[34m" + "Soldier, we have to say goodbye to our fleet..." + "\033[0;0m");

        } else if (num == 2) {

            System.out.println("\u001B[34m" + "Soldado, hemos de decir adiós a nuestra flota..." + "\033[0;0m");

        }

    }

}
