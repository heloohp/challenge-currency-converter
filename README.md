# 💱 Conversor de Moedas em Java

> Projeto desenvolvido com fins educacionais, como parte de um **challenge da Alura**.

![Java](https://img.shields.io/badge/Java-17+-blue?style=flat-square)
![ExchangeRate API](https://img.shields.io/badge/ExchangeRate-API-green?style=flat-square)
![Alura Challenge](https://img.shields.io/badge/Alura-Challenge-red?style=flat-square)

---

## 🔹 Sobre o Projeto

Um conversor de moedas que permite transformar valores entre diferentes moedas utilizando **taxas de câmbio em tempo real** via API.  
O usuário escolhe a moeda de origem e destino e recebe o valor convertido, formatado com **duas casas decimais**.

---

## 🔹 Funcionalidades

- Conversão entre Dólar (USD), Real (BRL), Peso Argentino (ARS) e Peso Colombiano (COP)
- Menu interativo para seleção de moedas
- Requisições HTTP em tempo real para obter taxas de câmbio
- Tratamento de erros e valores inválidos

---

## 🔹 Tecnologias

- Java 17+
- ExchangeRate-API
- HttpClient (Java)
- IntelliJ IDEA

---

## 🔹 API Utilizada

Este projeto utiliza a **ExchangeRate API** para obter taxas de câmbio em tempo real.

- Nome: ExchangeRate API  
- Tipo: REST  
- Formato de resposta: JSON  
- Autenticação: API Key  
- Site: https://www.exchangerate-api.com/

---

## 🔹 Estrutura do Projeto

<pre>challenge-currency-converter-java/
├─ src/                       
│  └─ br/com/heloisa/alura/currencyconverter/
│      ├─ Main.java
│      └─ service/
│          ├─ CurrencyApiClient.java
│          ├─ CurrencyConverter.java
│          └─ CurrencyJsonParser.java
├─ .gitignore
├─ README.md</pre>          

## 🔹 Como Rodar

```bash
#Clone o reposítorio 
git clone git@github.com:heloohp/challenge-currency-converter-java.git
```
1. Abra o projeto em **IntelliJ IDEA** ou outra IDE Java.
2. Na raiz do projeto, crie um arquivo chamado config.properties.
3. Dentro desse arquivo, adicione sua chave da API no seguinte formato:
```bash
API_KEY=SUA_API_KEY_AQUI
```
4. Salve o arquivo.

⚠️ Importante: o arquivo config.properties não deve ser versionado, pois contém dados sensíveis (API Key).

5. Execute a classe Main.java.
6. Utilize o menu interativo no console para escolher a conversão e informar o valor desejado.

## 🔹 Observações

- Conexão com internet necessária para obter taxas de câmbio em tempo real
- Valores convertidos são exibidos com duas casas decimais
- Projeto feito como parte de um **challenge da Alura**, com foco em aprendizado de Java e consumo de APIs.

---

Feito com ❤️ por **Heloisa**
