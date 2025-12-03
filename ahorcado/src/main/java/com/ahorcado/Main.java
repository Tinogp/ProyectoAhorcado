package com.ahorcado;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static final String[] Estados_Ahorcados = {
            // Etapa 0: Inicial
            """
                       +---+
                       |   |
                       |
                       |
                      / \\
                    """,
            // Etapa 1: Cabeza
            """
                       +---+
                       |   |
                       |   O
                       |
                      / \\
                    """,
            // Etapa 2: Torso
            """
                       +---+
                       |   |
                       |   O
                       |   |
                      / \\
                    """,
            // Etapa 3: Brazo derecho (visto de frente, izquierda del muñeco)
            """
                       +---+
                       |   |
                       |   O
                       |   |\\
                      / \\
                    """,
            // Etapa 4: Ambos brazos
            """
                       +---+
                       |   |
                       |   O
                       |  /|\\
                      / \\
                    """,
            // Etapa 5: Una pierna
            """
                       +---+
                       |   |
                       |   O
                       |  /|\\
                      / \\   \\
                    """,
            // Etapa 6: Completo (Game Over)
            """
                       +---+
                       |   |
                       |   O
                       |  /|\\
                      / \\ / \\
                    """
    };

    public static void main(String[] args) {
        String palabra = "";
        String respuesta = "";
        List<Character> letras = new ArrayList<>();
        Integer errores = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el numero de partidas: ");
        int numPartidas = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numPartidas; i++) {
            do {
                errores = 0;
                letras.clear();
                System.out.println("Jugador 1, introduce la palabra: ");
                palabra = scanner.nextLine();
                System.out.println("Quieres que tu palabra sea " + palabra + "? (s/n)");
                respuesta = scanner.nextLine().toLowerCase();
            } while (!respuesta.equals("s"));
            for (int j = 0; j < 100; j++) {
                System.out.println();
            }
            while (errores < 6 && !palabra.equals(letras.stream().map(Object::toString).collect(Collectors.joining()))) {
                System.out.println("Jugador 2, introduce la letra: ");
                char letra = scanner.nextLine().charAt(0);
                if (!letras.contains(letra)) {
                    letras.add(letra);
                    if (!palabra.contains(letra + "")) {
                        errores++;
                    }
                } else {
                    System.out.println("Letra repetida");
                }
                mostrarEstadoPartida(palabra, letras);
            }
            if (errores >= 6) {
                System.out.println("Has perdido");
            } else {
                System.out.println("Has ganado!!!");
            }
            System.out.println("La palabra era: " + palabra);
            System.out.println("Asi quedo la partida: ");
            mostrarEstadoPartida(palabra, letras);

        }
        scanner.close();
    }

    private static void mostrarEstadoPartida(String palabra, List<Character> letras) {
        int errores = 0;
        for (char c : palabra.toCharArray()) {
            if (letras.contains(c)) {
                System.out.print(c);
            } else {
                System.out.print("_");
                errores++;
            }
        }
        System.out.println();
        System.out.println(Estados_Ahorcados[6-errores]);
    }
}