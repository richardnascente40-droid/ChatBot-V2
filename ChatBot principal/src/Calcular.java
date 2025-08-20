public class Calcular {
public String Calcular (String expressao) {
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
}}