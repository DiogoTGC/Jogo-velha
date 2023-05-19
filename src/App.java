import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		String[][] tabuleiro = new String[3][3];
		
		apresentar_jogo();

		String[] competidores = new String[2];
		competidores = entrada_nome();
		String jogador1 = competidores[0], jogador2 = competidores[1];

		jogo(tabuleiro, jogador1, jogador2);
	}

	public static void apresentar_jogo() {
		System.out.println("Este é um jogo da velha, para dizer a casa escolhida digite dois numeros divididos por espaço.");
		System.out.println("Sendo o primeiro numero a linha e o segundo número a coluna.");
		System.out.println("Por exemplo, uma jogada: 1 2");
		System.out.println("   0  1  2");
		System.out.println("0 [ ][ ][ ]");
		System.out.println("1 [ ][ ][X]");
		System.out.println("2 [ ][ ][ ]");
	}

	public static String[] entrada_nome() {
		Scanner input = new Scanner(System.in);
		String[] jogadores = new String[2];

		for (int i = 0; i < jogadores.length; i++) {
			System.out.printf("Digite o nome do %dº jogador: ", i + 1);
			jogadores[i] = input.nextLine();
		}

		return jogadores;
	}

	public static void jogo(String[][] matriz, String jogador1, String jogador2) {
		Scanner input = new Scanner(System.in);

		boolean vez = true;
		String jogada = "";
		int n_jogada = 0;

		while (true) {
			boolean erro;
			int x = 0, y = 0;

			if (n_jogada >= 9) {
				System.out.println("O jogo empatou.");
				break;
			}

			do {
				erro = false;

				if (vez == true) {
					System.out.printf("%s, Digite sua jogada: ", jogador1);
				} else {
					System.out.printf("%s, Digite sua jogada: ", jogador2);
				}
				jogada = input.nextLine();

				String[] coordenada = jogada.split(" ");

				try {
					x = Integer.parseInt(coordenada[0]);
					y = Integer.parseInt(coordenada[1]);
				} catch (Exception e){
					erro = true;
					System.out.println("Digite uma jogada válida.");
				}
			} while (erro == true);

			if (((0 <= x) && (x <= 2)) && ((0 <= y) && (y <= 2))) {
				if (matriz[x][y] == null) {
					if (vez == true) {
						matriz[x][y] = "X";
					} else {
						matriz[x][y] = "O";
					}

					mostrar_tabuleiro(matriz);

					boolean chklin = checar_linha(matriz);
					boolean chkcol = checar_coluna(matriz);
					boolean chkdia = checar_diagonal(matriz);
					boolean chk = false;

					if ((chklin == true) || (chkcol == true) || (chkdia == true)) {
						chk = true;
					}

					if (chk == true && vez == true) {
						System.out.printf("O ganhador é o %s", jogador1);
						break;
					} else {
						if (chk == true && vez == false) {
							System.out.printf("O ganhador é o %s", jogador2);
							break;
						}
					}
					n_jogada += 1;
					vez = !vez;
				} else {
					System.out.println("Jogada já escolhida.");
				}
			} else {
				System.out.println("Digite uma coordenada válida.");
			}
		}
		input.close();
	}

	public static void mostrar_tabuleiro(String[][] matriz) {
		System.out.println("  0  1  2");
		for (int i = 0; i < matriz.length; i++) {
			System.out.print(i);
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] == null) {
					System.out.print("[ ]");    
				} else {
					System.out.printf("[%s]", matriz[i][j]);
				}
			}
			System.out.println();
		}

	}

	public static boolean checar_linha(String[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			int chklin = 0;
			for (int j = 0; j < matriz[i].length - 1; j++) {
				if (matriz[i][j] == matriz[i][j + 1] && matriz[i][j] != null) {
					chklin += 1;
					if (chklin == 2) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean checar_coluna(String[][] matriz) {
		for (int j = 0; j < matriz.length; j++) {
			int chkcol = 0;
			for (int i = 0; i < matriz[j].length - 1; i++) {
				if (matriz[i][j] == matriz[i + 1][j] && matriz[i][j] != null) {
					chkcol += 1;
					if (chkcol == 2) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean checar_diagonal(String[][] matriz) {
		int i = 0, j = 0;
		if (matriz[i][j] == matriz[i + 1][j + 1] && matriz[i + 1][j + 1] == matriz[i + 2][j + 2] && matriz[i][j] != null) {
			return true;
		}

		i = 0;
		j = 2;

		if (matriz[i][j] == matriz[i + 1][j - 1] && matriz[i + 1][j - 1] == matriz[i + 2][j - 2] && matriz[i][j] !=null){
			return true;
		}

		return false;
	}
}
