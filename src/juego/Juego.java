package juego;

import java.util.Scanner;
import static juego.Menu.reporte;

public class Juego {

    private final int tableroValores[][] = new int[8][8];
    private final int tablaPenalizacion[][] = new int[8][2];
    private int posicionJugador[][] = new int[8][8];
    public Penalizaciones penalizacion = new Penalizaciones();

    public Juego() {

    }

    public void inicializar() {
        Scanner pausa = new Scanner(System.in);
        String opcion = "";
        do {
            if (tablaPenalizacion[0][0] != 0) {
                if (posicionJugador[7][0] == 1) {
                    impresionFinal(tableroValores, tablaPenalizacion, posicionJugador);
                    System.out.println("**** Juego Terminado ****");

                    break;
                } else {
                    impresionFinal(tableroValores, tablaPenalizacion, posicionJugador);
                    System.out.println("\nTirar dado \nS.Si\nP.Pausa: ");
                    opcion = pausa.nextLine().toLowerCase();
                    if (opcion.equals("s")) {
                        int dado = (int) (Math.random() * 6 + 1);
                        System.out.println("\nDado:" + dado);
                        desplazamientoJugador(dado, posicionJugador);
                        if (posicionJugador[7][0] == 1) {
                            impresionFinal(tableroValores, tablaPenalizacion, posicionJugador);
                            System.out.println("*** JUEGO TERMINADO ***");
                            break;
                        }
                    }
                }
            } else {
                System.out.println("No hay partida en Curso");
                break;
            }
        } while (opcion.equals("p") == false);
    }

    //metodo para mover al jugador dentro del tablero 
    public void desplazamientoJugador(int dado, int[][] matrizJugador) {
        int indicador = 0, contador = 0;
        boolean bandera = true, finalizar = false;
        for (int i = 0; i < matrizJugador.length; i++) {
            if (finalizar == false) {
                if (bandera) {
                    for (int j = 0; j < matrizJugador[i].length; j++) {
                        if (indicador == 1) {
                            if (contador == dado - 1) {
                                finalizar = true;
                                matrizJugador[i][j] = 1;
                                break;
                            } else {
                                if (i == 7 && j == 0) {
                                    matrizJugador[7][0] = 1;
                                    break;
                                } else {
                                    contador++;
                                }
                            }
                        } else {
                            if (matrizJugador[i][j] == 1) {
                                indicador = 1;
                                matrizJugador[i][j] = 0;
                            }
                        }
                        bandera = false;
                    }
                } else {
                    for (int j = matrizJugador[i].length - 1; j >= 0; j--) {
                        if (indicador == 1) {
                            if (contador == dado - 1) {
                                finalizar = true;
                                matrizJugador[i][j] = 1;
                                break;
                            } else {
                                if (i == 7 && j == 0) {
                                    matrizJugador[7][0] = 1;
                                    break;
                                } else {
                                    contador++;
                                }
                            }
                        } else {
                            if (matrizJugador[i][j] == 1) {
                                indicador = 1;
                                matrizJugador[i][j] = 0;
                            }
                        }
                        bandera = true;
                    }
                }
            } else {
                break;
            }
        }
    }

    //metodo para ubicar la posicion actual del jugador
    public void posicionJugadorInicio() {
        for (int i = 0; i < posicionJugador.length; i++) {
            for (int j = 0; j < posicionJugador[i].length; j++) {
                if (i == 0 & j == 0) {
                    posicionJugador[i][j] = 1;
                } else {
                    posicionJugador[i][j] = 0;
                }
            }
        }
    }

    //metodo para llenado de matriz de penalizacion
    public void llenadoMPenalizacion() {
        int maximo = 8, minimo = 1;
        for (int i = 0; i < tablaPenalizacion.length; i++) {
            for (int j = 0; j < tablaPenalizacion[i].length; j++) {
                condicionRandom(j, i, minimo, maximo, tablaPenalizacion);
            }
            minimo += 8;
            maximo += 8;
        }
    }

    //metodo para llenar las penalizaciones 
    public void condicionRandom(int j, int i, int minimo, int maximo, int[][] matriz) {
        int valor = (int) (Math.random() * (maximo - minimo) + minimo);
        boolean bandera = false;
        if (j == 1) {
            while (bandera == false) {
                if (valor == matriz[i][j - 1]) {
                    valor = (int) (Math.random() * (maximo - minimo) + minimo);
                } else {
                    bandera = true;
                }
            }
        }
        matriz[i][j] = valor;
    }

    //metodo para llenar la table con los valores del 1 al 64 de forma normal
    public void llenadotablero() {
        int inicio = 1;
        boolean bandera = true;
        for (int i = 0; i < tableroValores.length; i++) {
            if (bandera) {
                for (int j = 0; j < tableroValores[i].length; j++) {
                    tableroValores[i][j] = inicio;
                    inicio++;
                    bandera = false;
                }
            } else {
                for (int j = tableroValores[i].length - 1; j >= 0; j--) {
                    tableroValores[i][j] = inicio;
                    inicio++;
                    bandera = true;
                }
            }
        }
    }

    //metodo para hacer las filas divisorias del tablero 
    public void horizontal() {
        for (int i = 0; i < 80; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }

    //metodo para imprimir la matriz como realmente esta
    public void impresionTablero(int matriz[][]) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
    }

    //metodo para imprimir la matriz como se esta pidiendo que se muestre al usuario
    public void impresionFinal(int matriz[][], int[][] matrizPenalizacion, int[][] posicionJugador) {
        boolean bandera = false;
        int indicador = 0;
        for (int i = matriz.length - 1; i >= 0; i--) {
            horizontal();
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] > 9) {
                    System.out.print("|      " + matriz[i][j] + "|");
                } else {
                    System.out.print("|       " + matriz[i][j] + "|");
                }
            }
            System.out.println("");
            for (int j = 0; j < matriz[i].length; j++) {
                if (buscar(matrizPenalizacion, matriz[i][j])) {
                    if (posicionJugador[i][j] == 1) {
                        System.out.print("|@      #|");
                        indicador = i;
                        bandera = true;
                    } else {
                        System.out.print("|       #|");
                    }
                } else {
                    if (posicionJugador[i][j] == 1) {
                        System.out.print("|@       |");
                    } else {
                        System.out.print("|        |");
                    }

                }
            }
            System.out.println("");
        }
        horizontal();

        if (bandera == true) {
            if (indicador >= 0 && indicador <= 1) {
                System.out.println("Haz caido en Penalizacion: ");
                penalizacionesFacil();
                indicador = 0;
                bandera = false;
            } else if (indicador >= 2 && indicador <= 4) {
                System.out.println("Haz caido en Penalizacion: ");
                penalizacionIntermedia();
                indicador = 0;
                bandera = false;
            } else if (indicador >= 5 && indicador <= 7) {
                System.out.println("Haz caido en Penalizacion: ");
                penalizacionDificil();
                indicador = 0;
                bandera = false;
            }
        }
    }

    //funcion para buscar un valor en una matriz
    public boolean buscar(int[][] matriz, int buscar) {
        boolean bandera = false;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (buscar == matriz[i][j]) {
                    bandera = true;
                    break;
                }
            }

        }
        return bandera;
    }

    //metodo apra realizar el proceso de la penalizacion facil
    public void penalizacionesFacil() {
        //penalizacion facil
        int p1lado_A = 15, p1lado_C = 20, p1angulo_a = 25;  //opcion1
        int p2lado_B = 10, p2lado_C = 25, p2angulo_b = 30; //opcion 2 
        int p3lado_A = 18, p3lado_B = 25, p3angulo_y = 30; //opcion 3

        int seleccion = (int) (Math.random() * 3 + 1);
        switch (seleccion) {
            case 1:
                penalizacion.penalizacionFacilparaB(p1lado_A, p1lado_C, p1angulo_a);
                break;
            case 2:
                penalizacion.penalizacionFacilparaA(p2lado_B, p2lado_C, p2angulo_b);
                break;
            case 3:
                penalizacion.penalizacionFacilparaC(p3lado_A, p3lado_B, p3angulo_y);
                break;
        }
    }

    //metodo para realizar el proceso de la penalizacion intermedia
    public void penalizacionIntermedia() {
        int[][] p1MatrizA = {{7, 48, 5, 0, 1}, {57, 8, 4, 6, 14}, {0, 5, 6, 78, 15}, {21, 14, 8, 19, 54}, {32, 20, 26, 47, 12}}; //opcion 1 
        int[][] p1MatrizB = {{9, 5, 2, 1, 8}, {4, 2, 3, 47, 8}, {48, 55, 32, 19, 6}, {7, 56, 32, 14, 8}, {32, 87, 0, 1, 7}}; //opncion 1

        int[][] p2MatrizA = {{4, 9, 7, 45, 18}, {7, 51, 26, 8, 38}, {48, 26, 37, 21, 19}, {1, 0, 6, 8, 1}, {2, 19, 55, 25, 15}}; //opcion 2
        int[][] p2MatrizB = {{0, 2, 15, 1, 66}, {21, 48, 62, 7, 33}, {4, 88, 0, 68, 4}, {25, 18, 24, 7, 55}, {24, 15, 36, 5, 98}}; //opcion 2

        int[][] p3MatrizA = {{0, 1, 15, 5, 36}, {1, 78, 65, 32, 4}, {48, 66, 39, 0, 55}, {14, 98, 63, 20, 15}, {11, 39, 84, 7, 1}}; //opcion 3
        int[][] p3MatrizB = {{78, 25, 66, 48, 98}, {0, 45, 2, 3, 1}, {2, 9, 14, 10, 20}, {35, 87, 65, 2, 32}, {25, 8, 4, 9, 39}}; //opcion 3

        int seleccion = (int) (Math.random() * 3 + 1);
        switch (seleccion) {
            case 1:
                penalizacion.penalizacionMedio(p1MatrizA, p1MatrizB);
                break;
            case 2:
                penalizacion.penalizacionMedio(p2MatrizA, p2MatrizB);
                break;
            case 3:
                penalizacion.penalizacionMedio(p3MatrizA, p3MatrizB);
                break;
        }
    }

    //metodo para realizar el proceso de la penalizacion dificil
    public void penalizacionDificil() {
        double[][] p1MatrizA = {{5, 10, 1, 3}, {9, 14, 2, 6}, {7, 8, 15, 3}, {6, 8, 9, 2}}; //opcion 1 
        double[][] p1MatrizB = {{5, 13, 9, 4}, {1, 9, 6, 3}, {8, 11, 69, 33}, {25, 6, 7, 4}}; //opcion 1 

        double[][] p2MatrizA = {{1, 12, 9, 8}, {7, 6, 3, 2}, {0, 5, 6, 14}, {6, 9, 6, 10}}; //opcion 2 
        double[][] p2MatrizB = {{8, 19, 20, 4}, {12, 33, 6, 8}, {4, 5, 9, 7}, {8, 22, 14, 6}}; //opcion 2 

        double[][] p3MatrizA = {{5, 9, 14, 5}, {6, 0, 5, 3}, {1, 14, 68, 8}, {7, 5, 3, 9}}; //opcion 3 
        double[][] p3MatrizB = {{0, 9, 7, 19}, {2, 30, 5, 48}, {1, 31, 2, 5}, {15, 8, 4, 3}}; //opcion 3 

        int seleccion = (int) (Math.random() * 3 + 1);
        switch (seleccion) {
            case 1:
                penalizacion.penalizacionDificil(p1MatrizA, p1MatrizB);
                break;
            case 2:
                penalizacion.penalizacionDificil(p2MatrizA, p2MatrizB);
                break;
            case 3:
                penalizacion.penalizacionDificil(p3MatrizA, p3MatrizB);
                break;
        }
    }

    public void addPenalizacionesGame() {
        reporte += "			<article class=\"post featured\">\n"
                + "				<header class=\"R3\">\n"
                + "					<h2>Casillas Penalizadas en el Juego</h2>\n"
                + "				</header>\n"
                + "				<table id=\"tabla\">\n"
                + "					<tbody>\n";
        for (int i = 0; i < tablaPenalizacion.length; i++) {
            reporte += "						<tr>\n";
            for (int j = 0; j < tablaPenalizacion[i].length; j++) {
                reporte += "							<th><center>" + tablaPenalizacion[i][j] + "</center></th>\n";
            }
            reporte += "						</tr>\n";
        }
        reporte += "					</tbody>\n"
                + "				</table>\n"
                + "			</article>";
    }

}
