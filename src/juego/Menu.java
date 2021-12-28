package juego;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public static Juego juego = new Juego();
    public static String reporte = "<!DOCTYPE HTML>\n"
            + "<html>\n"
            + "<head>\n"
            + "	<title>REPORTES</title>\n"
            + "	<link rel=\"icon\" href=\"images/2.png\">\n"
            + "	<meta charset=\"utf-8\" />\n"
            + "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=no\" />\n"
            + "	<link rel=\"stylesheet\" href=\"assets/css/main.css\" />\n"
            + "	<noscript>\n"
            + "		<link rel=\"stylesheet\" href=\"assets/css/noscript.css\" />\n"
            + "	</noscript>\n"
            + "</head>\n"
            + "<body class=\"is-preload\">\n"
            + "	<!-- Wrapper -->\n"
            + "	<div id=\"wrapper\" class=\"fade-in\">\n"
            + "		<!-- Intro -->\n"
            + "		<div id=\"intro\">\n"
            + "			<img class=\"masthead-avatar mb-5\" src=\"images/1.png\" alt=\"\" />\n"
            + "			<ul class=\"actions\">\n"
            + "				<li><a href=\"#header\" class=\"button icon solid solo fa-arrow-down scrolly\">Continue</a></li>\n"
            + "			</ul>\n"
            + "		</div>\n"
            + "		<!-- Header -->\n"
            + "		<header id=\"header\">\n"
            + "			<a href=\"index.html\" class=\"logo\">Reportes</a>\n"
            + "		</header>\n"
            + "		<!-- Main -->\n"
            + "		<div id=\"main\">";

    public Menu() {
        Scanner entrada = new Scanner(System.in);
        int seleccion = 0;
        do {
            try {
                System.out.println("======= MENU PRINCIPAL =======");
                System.out.println("1. Iniciar Juego");
                System.out.println("2. Retomar Juego");
                System.out.println("3. Generar Reportes");
                System.out.println("4. Salir");
                seleccion = entrada.nextInt();
                Opciones(seleccion);
            } catch (Exception errorEntrada) {
                System.out.println("Opcion Invalida");
                break;
            }
        } while (seleccion != 4);
    }

    public void Opciones(int seleccion) throws IOException {
        switch (seleccion) {
            case 1:
                juego.llenadotablero();
                juego.llenadoMPenalizacion();
                juego.posicionJugadorInicio();
                juego.inicializar();
                break;
            case 2:
                try {
                    juego.inicializar();
                } catch (Exception e) {
                    System.out.println("No hay partida en Curso");
                }

                break;
            case 3:
                reporte();
                break;
            case 4:
                System.out.println("Adios.......");
                break;
        }
    }

    public void reporte() throws IOException {
        File archivoHTML;
        FileWriter escritor = null;
        try {
            archivoHTML = new File("index.html");
            escritor = new FileWriter(archivoHTML);
            juego.addPenalizacionesGame();
            reporte += "		</div>\n"
                    + "		<!-- Scripts -->\n"
                    + "		<script src=\"assets/js/jquery.min.js\"></script>\n"
                    + "		<script src=\"assets/js/jquery.scrollex.min.js\"></script>\n"
                    + "		<script src=\"assets/js/jquery.scrolly.min.js\"></script>\n"
                    + "		<script src=\"assets/js/browser.min.js\"></script>\n"
                    + "		<script src=\"assets/js/breakpoints.min.js\"></script>\n"
                    + "		<script src=\"assets/js/util.js\"></script>\n"
                    + "		<script src=\"assets/js/main.js\"></script>\n"
                    + "</body>\n"
                    + "</html>";
            escritor.write(reporte);
            System.out.println("Reporte Generado");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                escritor.close();
            } catch (Exception e) {
            }
        }
    }
}
