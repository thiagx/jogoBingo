
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author estudos
 */
public class Bingo {

    static int bolinha = 0;
    static int primeiraCartela[][] = new int[5][5];
    static int segundaCartela[][] = new int[5][5];
    static int aux1 = 0;
    static int aux2 = 0;
    static boolean rodando = true;

    public static void main(String[] args) throws IOException {
        System.out.println("#### JOGO DO BINGO ####");

        gerarCartela(primeiraCartela);
        gerarCartela(segundaCartela);

        while (rodando) {
            System.out.println("=============================");
            mostrarCartela(primeiraCartela);
            System.out.println("=============================");
            mostrarCartela(segundaCartela);
            System.out.println("################################");
            sorteio();
            verificarAcerto(primeiraCartela, 1);
            verificarAcerto(segundaCartela, 2);
            verificarGanhador(aux1);
            verificarGanhador(aux2
            );
        }

    }//############################################################################

    public static void gerarCartela(int cartela[][]) throws IOException {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                int gerar = (int) (Math.random() * 100);
                if (gerar == 0) {
                    gerar = (int) (Math.random() * 100);
                }
                boolean achou = false;

                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {

                        if (gerar == cartela[k][l]) {
                            achou = true;
                        }

                    }

                }

                if (!achou) {

                    cartela[i][j] = gerar;

                } else {
                    j--;
                }

            }
        }
        ordenarCartela(cartela);
    }//###############################################################################

    public static void ordenarCartela(int cartela[][]) throws IOException {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ((j == 2) && (i == 2)) {
                    cartela[i][j] = 00;
                    // continue;
                } else {

                    int valor = cartela[i][j];
                    int l = j + 1;
                    for (int k = i; k < 5; k++) {
                        while (l < 5) {
                            if (cartela[i][j] > cartela[k][l]) {
                                int aux = cartela[i][j];
                                cartela[i][j] = cartela[k][l];
                                cartela[k][l] = aux;

                            }
                            l++;
                        }
                        l = 0;
                    }
                }
            }
        }

    }//###############################################################################

    public static void sorteio() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        bolinha = (int) (Math.random() * 100);

        if (bolinha == 0) {
            bolinha = (int) (Math.random() * 100);
        }
        if (bolinha < 10) {
            System.out.println("O numero sorteado foi: 0" + bolinha);
            System.out.println("Acertos Cartela 1:  " + aux1);
            System.out.println("Acertos Cartela 2: " + aux2);
            System.out.println("");
        } else {
            System.out.println("O numero sorteado foi: " + bolinha);
            System.out.println("Acertos Cartela 1:  " + aux1);
            System.out.println("Acertos Cartela 2: " + aux2);
            System.out.println("");
        }
        input.readLine();

    }//################################################################################

    public static void verificarAcerto(int cartela[][], int numero) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cartela[i][j] != 00) {
                    if (cartela[i][j] == bolinha) {
                        cartela[i][j] = 00;
                        if (numero == 1) {
                            aux1++;
                        } else {
                            aux2++;
                        }

                    }
                }
            }
        }
    }//################################################################################

    public static void verificarGanhador(int auxiliares) {
        if (aux1 == 24 && aux2 == 24) {
            System.out.println("O jogo empatou!");
            rodando = false;
        } else if (aux1 == 24 && aux2 < 24) {
            System.out.println("O jogador 1 venceu!");
            rodando = false;
        } else if (aux2 == 24 && aux1 < 24) {
            System.out.println("O jogador 2 venceu!");
            rodando = false;
        }

    }//################################################################################

    public static void mostrarCartela(int cartela[][]) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cartela[i][j] < 10) {
                    System.out.print("|" +  "0" + cartela[i][j] + "|  ");
                } else {
                    System.out.print("|" +cartela[i][j] + "|  ");
                }
            }
            System.out.println("");
            System.out.println("");

        }

    }//################################################################################
}
