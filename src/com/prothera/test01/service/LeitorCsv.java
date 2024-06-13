package src.com.prothera.test01.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import src.com.prothera.test01.model.Funcionario;
import src.com.prothera.test01.model.ListaDeFuncionarios;

public class LeitorCsv {
  //  para fazer esta classe, precisei pesquisar informações;
  public static List<Funcionario> lerCSV(String filePath) {
    List<Funcionario> funcionarios = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String linha;
      br.readLine(); // Ignora o cabeçalho
      while ((linha = br.readLine()) != null) {
        String[] valores = linha.split(",");

        String nome = valores[0].trim();
        String dataNascimento = valores[1].trim();
        BigDecimal salario = new BigDecimal(valores[2].trim().replace(" ", ""));
        String funcao = valores[3].trim();

        Funcionario funcionario = new Funcionario(nome, dataNascimento, salario, funcao);
        funcionarios.add(funcionario);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return funcionarios;
  }
}