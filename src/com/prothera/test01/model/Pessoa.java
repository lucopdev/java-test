package src.com.prothera.test01.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {

  private String nome;
  private LocalDate data_nascimento;

  public Pessoa(String nome, String data_nascimento) {
    this.nome = nome;
    this.data_nascimento = LocalDate.parse(data_nascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public LocalDate getData_nascimento() {
    return data_nascimento;
  }

  public void setData_nascimento(LocalDate data_nascimento) {
    this.data_nascimento = data_nascimento;
  }
}