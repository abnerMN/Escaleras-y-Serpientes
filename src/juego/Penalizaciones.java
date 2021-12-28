package juego;

import java.math.BigDecimal;
import java.math.RoundingMode;
import static juego.Menu.reporte;

public class Penalizaciones {

    public Penalizaciones() {

    }

    //metodo para realizar la dicvision de matrices
    public void penalizacionDificil(double[][] matrizA, double[][] matrizB) {
        System.out.println("Matriz A:");
        impresionMatriz(matrizA);
        System.out.println("Matriz B: ");
        impresionMatriz(matrizB);
        double inversa[][] = definirInversa(matrizB);
        double respuesta[][] = multiplicacion(matrizA, inversa);
        redondeoMatriz(respuesta);
        System.out.println("Resultado: ");
        impresionMatriz(respuesta);
        addReporteMultiplicacion(matrizA, matrizB, respuesta);
    }

    //metodo para realizar la suma de matrices
    public void penalizacionMedio(int[][] matrizA, int[][] matrizB) {
        int[][] resultado = suma(matrizA, matrizB);
        System.out.println("Matriz A: ");
        impresionMatriz(matrizA);
        System.out.println("Matriz B: ");
        impresionMatriz(matrizB);
        System.out.println("Matriz Resultante: ");
        impresionMatriz(resultado);
        addReporteSuma(matrizA, matrizB, resultado);
    }

    //metodo para realizar la penalizacion Facil resolviendo para B angulo b y angulo y
    public void penalizacionFacilparaB(int ladoA, int ladoC, int angulo_a) {
        double aCuadrado = ladoA * ladoA;
        double cCuadrado = ladoC * ladoC;
        double temp = (aCuadrado) + (cCuadrado) - ((2 * ladoA * ladoC) * Math.cos(Math.toRadians(angulo_a)));
        double b = Math.sqrt(temp);
        double bCuadrado = b * b;
        double beta = Math.toDegrees(Math.acos((bCuadrado + cCuadrado - aCuadrado) / (2 * b * ladoC)));
        double anguloY = Math.toDegrees(Math.acos((aCuadrado + bCuadrado - cCuadrado) / (2 * ladoA * b)));
        BigDecimal bdb = new BigDecimal(b).setScale(3, RoundingMode.HALF_UP);
        b = bdb.doubleValue();
        BigDecimal bdy = new BigDecimal(anguloY).setScale(3, RoundingMode.HALF_UP);
        anguloY = bdy.doubleValue();
        BigDecimal bdt = new BigDecimal(beta).setScale(3, RoundingMode.HALF_UP);
        beta = bdt.doubleValue();
        System.out.println("Lado A: " + ladoA);
        System.out.println("Lado B: " + b);
        System.out.println("Lado C: " + ladoC);
        System.out.println("Angulo alfa: " + angulo_a);
        System.out.println("Angulo beta: " + beta);
        System.out.println("Angulo Y: " + anguloY);
        addReporteAngulo(angulo_a, beta, anguloY, ladoA, b, ladoC);//anguloA,anguloB,anguloC,ladoA, ladoB, LadoC

    }

    //metodo para realizar la penalizacion Facil resolviendo para A angulo a y angulo Y
    public void penalizacionFacilparaA(int ladoB, int ladoC, int angulo_b) {
        double bCuadrado = ladoB * ladoB;
        double cCuadrado = ladoC * ladoC;
        double temp = (bCuadrado) + (cCuadrado) - ((2 * ladoB * ladoC) * Math.cos(Math.toRadians(angulo_b)));
        double a = Math.sqrt(temp);
        double aCuadrado = a * a;
        double angulo_alfa = Math.toDegrees(Math.acos((aCuadrado + cCuadrado - bCuadrado) / (2 * a * ladoC)));
        double anguloY = Math.toDegrees(Math.acos((aCuadrado + bCuadrado - cCuadrado) / (2 * a * ladoB)));
        BigDecimal bda = new BigDecimal(a).setScale(3, RoundingMode.HALF_UP);
        a = bda.doubleValue();
        BigDecimal bdf = new BigDecimal(angulo_alfa).setScale(3, RoundingMode.HALF_UP);
        angulo_alfa = bdf.doubleValue();
        BigDecimal bdy = new BigDecimal(anguloY).setScale(3, RoundingMode.HALF_UP);
        anguloY = bdy.doubleValue();
        System.out.println("Lado A: " + a);
        System.out.println("Lado B: " + ladoB);
        System.out.println("Lado C: " + ladoC);
        System.out.println("Angulo Alfa: " + angulo_alfa);
        System.out.println("Angulo Beta: " + angulo_b);
        System.out.println("Angulo Y: " + anguloY);
        addReporteAngulo(a, angulo_b, anguloY, a, ladoB, ladoC);//anguloA,anguloB,anguloC,ladoA, ladoB, LadoC
    }
    //metodo para realizar la penalizacion Facil resolviendo para C angulo c y angulo B

    public void penalizacionFacilparaC(int ladoA, int ladoB, int angulo_y) {
        double aCuadrado = ladoA * ladoA;
        double bCuadrado = ladoB * ladoB;
        double var = aCuadrado + bCuadrado;
        double angulo = Math.toRadians(angulo_y); // se agrega toRadians para calculo en grados
        double temp = var - ((2 * ladoA * ladoB) * Math.cos(angulo));
        double c = Math.sqrt(temp);
        double cCuadrado = c * c;
        double angulo_alfa = Math.toDegrees(Math.acos((aCuadrado + cCuadrado - bCuadrado) / (2 * ladoA * c))); // se usa todegree para tener en grados
        double angulo_beta = Math.toDegrees(Math.acos((bCuadrado + cCuadrado - aCuadrado) / (2 * ladoB * c)));
        BigDecimal bdc = new BigDecimal(c).setScale(3, RoundingMode.HALF_UP);
        c = bdc.doubleValue();
        BigDecimal bda = new BigDecimal(angulo_alfa).setScale(3, RoundingMode.HALF_UP);
        angulo_alfa = bda.doubleValue();
        BigDecimal bdb = new BigDecimal(angulo_beta).setScale(3, RoundingMode.HALF_UP);
        angulo_beta = bdb.doubleValue();
        System.out.println("Lado A: " + ladoA);
        System.out.println("Lado B: " + ladoB);
        System.out.println("Lado C: " + c);
        System.out.println("Angulo Alfa: " + angulo_alfa);
        System.out.println("Angulo Beta: " + angulo_beta);
        System.out.println("Angulo Y: " + angulo_y);
        addReporteAngulo(angulo_alfa, angulo_beta, angulo_y, ladoA, ladoB, c);//anguloA,anguloB,anguloC,ladoA, ladoB, LadoC
    }

    //funcion para encontrar la suma de matrices
    private int[][] suma(int[][] matrizA, int[][] matrizB) {
        int[][] resultado = null;
        if (matrizA.length == matrizB.length && matrizA[0].length == matrizB[0].length) {
            resultado = new int[matrizA.length][matrizA[0].length];
            for (int i = 0; i < resultado.length; i++) {
                for (int j = 0; j < resultado[0].length; j++) {
                    resultado[i][j] = matrizA[i][j] + matrizB[i][j];
                }
            }
            return resultado;
        } else {
            System.out.println("****** LAS MATRICES TIENEN QUE TENER LA MISMA DIMENSION ******");

        }
        return resultado;
    }

    //funcion para encontrar la multiplicion de matrices
    private double[][] multiplicacion(double[][] matrizA, double[][] matrizB) {
        double[][] c = new double[matrizA.length][matrizB[0].length];
        // se comprueba si las matrices se pueden multiplicar
        if (matrizA[0].length == matrizB.length) {
            for (int i = 0; i < matrizA.length; i++) {
                for (int j = 0; j < matrizB[0].length; j++) {
                    for (int k = 0; k < matrizA[0].length; k++) {
                        // aquí se multiplica la matriz
                        c[i][j] += matrizA[i][k] * matrizB[k][j];
                    }
                }
            }
        } else {
            System.out.println("\n" + "las columnas de la matriz de la frase debe ser igual a las filas de la matriz llave");
        }
        return c;
    }

    //metodo para calcular la inversa 
    public double[][] definirInversa(double[][] matriz) {
        double determinante = calculoDeterminante(matriz);
        double[][] adjunta = matrizAdjunta(matriz);
        double[][] transpuesta = Traspuesta(adjunta);
        double[][] inversa = inversa(transpuesta, determinante);
        return inversa;
    }

    //funcion para calcular el determinante
    private double calculoDeterminante(double[][] matriz) {
        double determinante = 0;
        int i = 0;
        if (matriz.length == 2) {
            determinante = matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
            return determinante;
        } else {
            determinante = 0;
            for (int j = 0; j < matriz.length; j++) {
                double[][] matrizM = calculoMatrizMenor(matriz, i, j);
                int val = calculoSigno(-1, i + j);
                determinante = determinante + val * matriz[i][j] * calculoDeterminante(matrizM);
            }
            return determinante;
        }
    }

    //funcion para calcular la sub matriz
    private double[][] calculoMatrizMenor(double[][] matriz, int val, int val2) {
        int filas = matriz.length - 1, columnas = matriz[0].length - 1, filasR = 0, columnasR = 0;
        double[][] respuesta = new double[filas][columnas];
        for (int i = 0; i < matriz.length; i++) {
            if (i != val) {
                columnasR = 0;
                for (int j = 0; j < matriz[0].length; j++) {
                    if (j != val2) {
                        respuesta[filasR][columnasR] = matriz[i][j];
                        columnasR++;
                    }
                }
                filasR++;
            }
        }
        return respuesta;
    }

    //funcion para calcular la matriz adjunta
    private double[][] matrizAdjunta(double[][] matriz) {
        int filas = matriz.length, columnas = matriz[0].length;
        double[][] respuesta = new double[filas][columnas];
        for (int i = 0; i < respuesta.length; i++) {
            for (int j = 0; j < respuesta.length; j++) {
                double[][] temp = calculoMatrizMenor(matriz, i, j);
                int var = calculoSigno(-1, i + j);
                double valor = var * calculoDeterminante(temp);
                respuesta[i][j] = valor;
            }
        }
        return respuesta;
    }

    //funcio para calcular el signo correspondiente
    private int calculoSigno(int a, int b) {
        int signo = 1;
        for (int k = 1; k <= b; k++) {
            signo = signo * a;
        }
        return signo;
    }

    //funcion para calcular la transpuesta
    private double[][] Traspuesta(double[][] matriz) {
        double rMatriz[][] = new double[matriz.length][matriz.length];
        for (int i = 0; i < rMatriz.length; i++) {
            for (int j = 0; j < rMatriz.length; j++) {
                rMatriz[i][j] = matriz[j][i];
            }
        }
        return rMatriz;
    }

    //funcion para calcular los valores correspondientes para la matriz inversa 
    private double[][] inversa(double[][] transpuesta, double determinante) {
        double inversa[][] = new double[transpuesta.length][transpuesta[0].length];
        for (int i = 0; i < inversa.length; i++) {
            for (int j = 0; j < inversa[0].length; j++) {
                inversa[i][j] = transpuesta[i][j] / determinante;
            }
        }
        return inversa;
    }

    //metodo para imprimir matriz 
    public void impresionMatriz(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    //metodo para imprimir matriz
    public void impresionMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public void redondeoMatriz(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] <= 0 || matriz[i][j] > 0) {
                    BigDecimal bd = new BigDecimal(matriz[i][j]).setScale(3, RoundingMode.HALF_UP);
                    double val2 = bd.doubleValue();
                    matriz[i][j] = val2;
                }
            }
        }
    }

    public void addReporteAngulo(double angulo_a, double angulo_b, double angulo_c, double ladoA, double ladoB, double ladoC) {
        reporte += "			<article class=\"post featured\">\n"
                 + "				<header class=\"R1\">\n"
                + "					<h2>Penalizacion Facil Realizada</h2>\n"
                + "					<h3>Datos de un triangulo</h3>\n"
                + "				</header>\n"
                + "				<table id=\"datos\">\n"
                + "					<thead>\n"
                + "						<tr>\n"
                + "							<th>\n"
                + "								<center>Lado A</center>\n"
                + "							</th>\n"
                + "							<th>\n"
                + "								<center>Lado B</center>\n"
                + "							</th>\n"
                + "							<th>\n"
                + "								<center>Lado C</center>\n"
                + "							</th>\n"
                + "							<th>\n"
                + "								<center>Angulo A</center>\n"
                + "							<th>\n"
                + "								<center>Angulo B</center>\n"
                + "							</th>\n"
                + "							<th>\n"
                + "								<center>Angulo Y</center>\n"
                + "							</th>\n"
                + "							</th>\n"
                + "						</tr>\n"
                + "					</thead>\n"
                + "					<tbody>\n"
                + "						<tr>\n"
                + "							<th><center>" + ladoA + "</center></th>\n"
                + "							<th><center>" + ladoB + "</center></th>\n"
                + "							<th><center>" + ladoC + "</center></th>\n"
                + "							<th><center>" + angulo_a + "</center></th>\n"
                + "							<th><center>" + angulo_b + "</center></th>\n"
                + "							<th><center>" + angulo_c + "</center></th>\n"
                + "						</tr>\n"
                + "					</tbody>\n"
                + "				</table>\n"
                + "			</article>";
    }

    public void addReporteSuma(int[][] matrizA, int[][] matrizB, int[][] respuesta) {
        reporte += "			<article class=\"post featured\">\n"
                + "				<header class=\"R2\">\n"
                + "					<h2>Penalizacion Intermedia Realizada</h2>\n"
                + "					<h3>Matriz A</h3>\n"
                + "				</header>\n"
                + "				<table id=\"tabla\">\n"
                + "					<tbody>\n";
        addMatriz(matrizA);
        reporte += "					</tbody>\n"
                + "				</table>\n"
                + "				<header class=\"R2\">\n"
                + "					<h3>Matriz B</h3>\n"
                + "				</header>\n"
                + "				<table id=\"tabla\">\n"
                + "					<tbody>\n";
        addMatriz(matrizB);
        reporte += "					</tbody>\n"
                + "				</table>\n"
                + "				<header class=\"R2\">\n"
                + "					<h3>Respuesta</h3>\n"
                + "				</header>\n"
                + "				<table id=\"tabla\">\n"
                + "					<tbody>\n";
        addMatriz(respuesta);
        reporte += "					</tbody>\n"
                + "				</table>\n"
                + "			</article>";
    }

    public void addReporteMultiplicacion(double[][] matrizA, double[][] matrizB, double[][] respuesta) {
        reporte += "			<article class=\"post featured\">\n"
                + "				<header class=\"R3\">\n"
                + "					<h2>Penalizacion Dificil Realizada</h2>\n"
                + "					<h3>Matriz A</h3>\n"
                + "				</header>\n"
                + "				<table id=\"tabla\">\n"
                + "					<tbody>\n";
        addMatriz(matrizA);
        reporte += "					</tbody>\n"
                + "				</table>\n"
                + "				<header class=\"R3\">\n"
                + "					<h3>Matriz B</h3>\n"
                + "				</header>\n"
                + "				<table id=\"tabla\">\n"
                + "					<tbody>\n";
        addMatriz(matrizB);
        reporte += "					</tbody>\n"
                + "				</table>\n"
                + "				<header class=\"R3\">\n"
                + "					<h3>Respuesta</h3>\n"
                + "				</header>\n"
                + "				<table id=\"tabla\">\n"
                + "					<tbody>\n";
        addMatriz(respuesta);
        reporte += "					</tbody>\n"
                + "				</table>\n"
                + "			</article>";
    }

    public void addMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            reporte += "						<tr>\n";
            for (int j = 0; j < matriz[i].length; j++) {
                reporte += "							<th><center>" + matriz[i][j] + "</center></th>\n";
            }
            reporte += "						</tr>\n";
        }
    }

    public void addMatriz(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            reporte += "						<tr>\n";
            for (int j = 0; j < matriz[i].length; j++) {
                reporte += "							<th><center>" + matriz[i][j] + "</center></th>\n";
            }
            reporte += "						</tr>\n";
        }
    }
    
}
