import java.util.*;

public class ChatBot {
    private Scanner scanner;
    private List<String> historico;

    public ChatBot() {
        scanner = new Scanner(System.in);
        historico = new ArrayList<>();
    }

    public void iniciarChat() {
        System.out.println("ChatBot: Olá! Eu sou um chatbot mais completo.");
        System.out.println("Digite 'ajuda' para ver o que posso fazer ou 'sair' para encerrar.\n");

        while (true) {
            System.out.print("Você: ");
            String entrada = scanner.nextLine().toLowerCase();
            historico.add("Você: " + entrada);

            if (entrada.equals("sair")) {
                System.out.println("ChatBot: Até logo! 😊");
                break;
            }

            String resposta = processarEntrada(entrada);
            historico.add("ChatBot: " + resposta);
            System.out.println("ChatBot: " + resposta);
        }

        exibirHistorico();
    }

    private String processarEntrada(String entrada) {
        if (entrada.equals("ajuda")) {
            return exibirAjuda();
        }

        // Chamada para o método calcular se entrada começa com "calc "
        if (entrada.startsWith("calc ")) {
            return calcular(entrada.substring(5).trim());
        }

        if (contem(entrada, "oi", "olá", "ola", "e aí")) {
            return respostaAleatoria(new String[]{
                    "Oi! Tudo bem?",
                    "Olá! Como posso te ajudar hoje?",
                    "E aí! Pronto pra conversar?"
            });
        }

        if (contem(entrada, "tudo bem", "como vai")) {
            return respostaAleatoria(new String[]{
                    "Estou ótimo, obrigado!",
                    "Tudo certo por aqui!",
                    "Muito bem, e você?"
            });
        }

        if (contem(entrada, "seu nome", "quem é você")) {
            return "Sou um chatbot Java, feito para conversar e aprender com você!";
        }

        if (contem(entrada, "piada")) {
            return respostaAleatoria(new String[]{
                    "Por que o Java foi ao terapeuta? Porque tinha muitos problemas de classe.",
                    "O que o código disse para o bug? Você está fora da lógica!",
                    "Por que o programador foi ao bar? Para resolver seus problemas com um loop infinito."
            });
        }

        if (contem(entrada, "hora", "horas")) {
            return "Agora são " + java.time.LocalTime.now().withNano(0);
        }

        if (contem(entrada, "histórico", "historico", "mostre conversa")) {
            return "Você pode ver seu histórico ao final da conversa.";
        }

        return "Desculpe, ainda não sei como responder isso. Digite 'ajuda' para ver os comandos.";
    }

    private boolean contem(String entrada, String... palavras) {
        for (String palavra : palavras) {
            if (entrada.contains(palavra)) {
                return true;
            }
        }
        return false;
    }

    private String respostaAleatoria(String[] respostas) {
        Random random = new Random();
        return respostas[random.nextInt(respostas.length)];
    }

    private String exibirAjuda() {
        return """
        Aqui estão algumas coisas que você pode dizer:
        - oi / olá / e aí
        - tudo bem? / como vai?
        - qual o seu nome?
        - conte uma piada
        - que horas são?
        - histórico
        - sair
        - adição = calc num1 + num2
        - subtração = calc num1 - num2
        - multiplicação = calc num1 * num2
        - divisão = calc num1 / num2
        - potenciação = calc num1 ^ num2
        - radiciação = calc sqrt num
        """;
    }

    private void exibirHistorico() {
        System.out.println("\n--- Histórico da Conversa ---");
        for (String linha : historico) {
            System.out.println(linha);
        }
    }

    // Método para calcular expressões simples
    private String calcular(String expressao) {
        try {
            String[] partes = expressao.split(" ");

            if (partes.length == 3) {
                double num1 = Double.parseDouble(partes[0]);
                String operador = partes[1];
                double num2 = Double.parseDouble(partes[2]);

                return switch (operador) {
                    case "+" -> "O resultado é: " + (num1 + num2);
                    case "-" -> "O resultado é: " + (num1 - num2);
                    case "*" -> "O resultado é: " + (num1 * num2);
                    case "/" -> (num2 == 0) ? "Erro: divisão por zero!" : "O resultado é: " + (num1 / num2);
                    case "^" -> "O resultado é: " + Math.pow(num1, num2);
                    default -> "Operador desconhecido. Use +, -, *, /, ^.";
                };
            } else if (partes.length == 2 && (partes[0].equals("sqrt") || partes[0].equals("raiz"))) {
                double num = Double.parseDouble(partes[1]);
                if (num < 0) return "Erro: não é possível calcular a raiz de número negativo.";
                return "O resultado é: " + Math.sqrt(num);
            } else {
                return "Formato inválido. Exemplos:\n" +
                        "- calc 2 + 3\n" +
                        "- calc 5 ^ 2\n" +
                        "- calc sqrt 16";
            }
        } catch (NumberFormatException e) {
            return "Erro: formato numérico inválido.";
        } catch (Exception e) {
            return "Erro ao calcular: " + e.getMessage();
        }
    }
}
