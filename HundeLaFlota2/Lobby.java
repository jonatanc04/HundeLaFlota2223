package HundeLaFlota;

public class Lobby {

    /**
     * Esta función muestra un menú con varias opciones, una para iniciar el juego, otra para ver los créditos del juego
     * y por último, una para finalizar el juego.
     */

    public static void mainLobby() {

        int option;

        Show.showMenu();
        option = Tool.correctOption(0,2);

        switch (option) {
            case 1 -> Game.initiateGame();
            case 2 -> Show.credits();
            case 0 -> Show.endGame();
        }

    }

}