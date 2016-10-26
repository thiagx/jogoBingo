package trabalhoMetodo.jogoBingo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Thiago Oliveira Silva - Estudando do Instituto Federal de São Paulo - Campus Cubatão.
 * Todos os direitos reservados.
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

        gerarCartela(primeiraCartela);// Chamo a primeira cartela
        gerarCartela(segundaCartela);// chamo a segunda cartela

        while (rodando) {// enquanto rodando for verdadeiro, o jogo fica dentro de um loop
            System.out.println("=============================");
            mostrarCartela(primeiraCartela);// mostro minha primeira cartela
            System.out.println("=============================");
            mostrarCartela(segundaCartela);// mostro minh segunda cartela
            System.out.println("################################");
            sorteio();// chamo o método de sorteio para gerar um numero aleatório
            verificarAcerto(primeiraCartela, 1);// passo primeira cartela e um ID 1 para verificar se ela acertou
            verificarAcerto(segundaCartela, 2); // passo segunda cartela e um ID 2 para verificar se ela acertou
            verificarGanhador(); // verifico se há um ganhador

        }

    }//############################################################################

    public static void gerarCartela(int cartela[][]) throws IOException {// metodo para gerar minhas cartelas
// A cartela na pode haver numeros repetidos,para isso foi gerado uma booleana para verificar se achou o N repetido
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
// primeiro ele gera o numero, depois confere se cartela[k][l] possui aquele numero, como na linha  VERIFICANDO
                int gerar = (int) (Math.random() * 100);
                if (gerar == 0) {
                    gerar = (int) (Math.random() * 100);
                }
                boolean achou = false;

                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {

                        if (gerar == cartela[k][l]) { // VERIFICANDO
                            achou = true;
                        }

                    }

                }

                if (!achou) {// Se ele nao achar numero repetido, ele joga o numero dentro da cartela

                    cartela[i][j] = gerar;

                } else {//caso ache, ele força eu voltar uma coluna da minha matriz
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
                    // continue; metodo para pular uma interação. Nao esotu usando, só para recordar, caso precise utilizar
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
        bolinha = (int) (Math.random() * 100);// gero minha bolinha 

        if (bolinha == 0) {// se bolinha for igual a 0, eu forço ela gerar outro numero
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
        input.readLine();// força-me a apertar ENTER para prosseguir o programa

    }//################################################################################

    public static void verificarAcerto(int cartela[][], int numero) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                if (cartela[i][j] == bolinha) {// caso a posição seja igual o numero gerado, ele coloca um 00 no lugar da cartela
                    cartela[i][j] = 00;
                    if (numero == 1) {
                        aux1++;// caso meu ID seja 1, ele incrementa minha variavel aux1
                    } else {
                        aux2++; // o mesmo caso a cima

                    }
                }
            }
        }
    }//################################################################################

    public static void verificarGanhador() {// verifica meu ganhador atravez de condições.
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
                    System.out.print("|" + "0" + cartela[i][j] + "|  ");
                } else {
                    System.out.print("|" + cartela[i][j] + "|  ");
                }
            }
            System.out.println("");
            System.out.println("");

        }

    }//################################################################################
}
