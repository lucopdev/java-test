package src.com.prothera.test01.model;

import java.math.BigDecimal;

public class Funcionario extends Pessoa {

  public Funcionario(String nome, String data_nascimento, BigDecimal salario,
      String funcao) {
    super(nome, data_nascimento);
    this.salario = salario;
    this.funcao = funcao;
  }

  private BigDecimal salario;
  private String funcao;


  public BigDecimal getSalario() {
    return salario;
  }

  public void setSalario(BigDecimal salario) {
    this.salario = salario;
  }

  public String getFuncao() {
    return funcao;
  }

  public void setFuncao(String funcao) {
    this.funcao = funcao;
  }
}
