import br.com.heloisa.alura.currencyconverter.service.CurrencyApiClient;
import br.com.heloisa.alura.currencyconverter.service.CurrencyConverter;
import br.com.heloisa.alura.currencyconverter.service.CurrencyJsonParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int opcao = 0;

        System.out.println("****************************");
        System.out.println("Bem-vindo ao Conversor de Moedas :)");
        System.out.println("****************************");

        String menu = """
                \n ** Digite uma opção válida: **
                1) Dólar =>> Peso argentino
                2) Peso argentino =>> Dólar
                3) Dólar =>> Real
                4) Real =>> Dólar
                5) Dólar =>> Peso colombiano
                6) Peso colombiano =>> Dólar
                7) Sair
                
                ****************************
                """;

        Scanner leitura = new Scanner(System.in);

        // Criar objetos uma vez para reutilizar
        CurrencyApiClient apiClient = new CurrencyApiClient();
        CurrencyJsonParser parser = new CurrencyJsonParser();
        CurrencyConverter converter = new CurrencyConverter();
        DecimalFormat df = new DecimalFormat("#,##0.00");

        // Ler API Key do arquivo config.properties
        String apiKey = lerApiKey();
        if (apiKey.isEmpty()) {
            System.out.println("API Key não encontrada. Configure a chave no arquivo config.properties.");
            return;
        }

        while (opcao != 7) {
            System.out.println(menu);

            // Trata entrada inválida
            try {
                opcao = leitura.nextByte();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número entre 1 e 7!");
                leitura.nextLine(); // limpa buffer do Scanner
                continue; // volta para mostrar o menu
            }

            switch (opcao) {
                case 1:
                    converterEImprimir(leitura, apiClient, parser, converter, df, apiKey, "USD", "ARS");
                    break;
                case 2:
                    converterEImprimir(leitura, apiClient, parser, converter, df, apiKey, "ARS", "USD");
                    break;
                case 3:
                    converterEImprimir(leitura, apiClient, parser, converter, df, apiKey, "USD", "BRL");
                    break;
                case 4:
                    converterEImprimir(leitura, apiClient, parser, converter, df, apiKey, "BRL", "USD");
                    break;
                case 5:
                    converterEImprimir(leitura, apiClient, parser, converter, df, apiKey, "USD", "COP");
                    break;
                case 6:
                    converterEImprimir(leitura, apiClient, parser, converter, df, apiKey, "COP", "USD");
                    break;
                case 7:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    // Método para ler API Key do config.properties
    private static String lerApiKey() {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            prop.load(fis);
            return prop.getProperty("API_KEY", "").trim();
        } catch (IOException e) {
            return "";
        }
    }

    // Método para conversão e impressão
    public static void converterEImprimir(Scanner leitura, CurrencyApiClient apiClient,
                                          CurrencyJsonParser parser, CurrencyConverter converter,
                                          DecimalFormat df, String apiKey, String moedaBase, String moedaDestino) {
        try {
            System.out.println("Digite o valor que deseja converter:");
            double valor = leitura.nextDouble();

            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + moedaBase;
            HttpRequest request = apiClient.criarRequisicao(url);
            String jsonResponse = apiClient.enviarRequisicao(request);

            if (jsonResponse == null || jsonResponse.isEmpty()) {
                System.out.println("Não foi possível obter a taxa da API.");
                return;
            }

            double taxa = parser.getRate(parser.parseJson(jsonResponse), moedaDestino);
            double resultado = converter.convert(valor, taxa);

            System.out.println(df.format(valor) + " " + moedaBase + " = " + df.format(resultado) + " " + moedaDestino);

        } catch (InputMismatchException e) {
            System.out.println("Por favor, digite um número válido!");
            leitura.nextLine(); // limpa buffer do Scanner
        } catch (Exception e) {
            System.out.println("Erro ao converter a moeda: " + e.getMessage());
            leitura.nextLine(); // limpa buffer do Scanner
        }
    }
}