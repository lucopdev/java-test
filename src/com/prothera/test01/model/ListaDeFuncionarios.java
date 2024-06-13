package src.com.prothera.test01.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

public class ListaDeFuncionarios {

  private List<Funcionario> listaDeFuncionarios = new ArrayList<>();

  public List<Funcionario> getListaDeFuncionarios() {
    return Collections.unmodifiableList(new ArrayList<>(listaDeFuncionarios));
  }

  public void adicionarFuncionario(Funcionario novoFuncionario) {
    listaDeFuncionarios.add(novoFuncionario);
  }

  public void removerFuncionarioPeloNome(String nome) {
    listaDeFuncionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
  }

  public void adicionarAumento(String alvo, int percentagem) {
    BigDecimal divide = BigDecimal.valueOf(percentagem).divide(BigDecimal.valueOf(100));

    if ("todos".equalsIgnoreCase(alvo)) {
      for (Funcionario funcionario : listaDeFuncionarios) {
        BigDecimal aumento = funcionario.getSalario().multiply(divide);
        BigDecimal novoSalario = funcionario.getSalario().add(aumento);
        funcionario.setSalario(novoSalario);

      }
    } else {
      for (Funcionario funcionario : listaDeFuncionarios) {
        if (funcionario.getNome().equalsIgnoreCase(alvo)) {
          BigDecimal aumento = funcionario.getSalario().multiply(divide);
          BigDecimal novoSalario = funcionario.getSalario().add(aumento);
          funcionario.setSalario(novoSalario);
        }
      }
    }
  }

  public String exibirFuncionarios() {
    NumberFormat salarioBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    StringBuilder listaTexto = new StringBuilder("\nLista de Funcionários:\n");
    for (Funcionario funcionario : listaDeFuncionarios) {
      listaTexto.append(
          String.format(" - Nome: %s, Data de Nascimento: %s, Salário: %s, Função: %s\n",
              funcionario.getNome(),
              funcionario.getData_nascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyy")),
              salarioBR.format(funcionario.getSalario()),
              funcionario.getFuncao()));
    }
    return listaTexto.toString();
  }

  public String exibirFuncionariosPorFuncao() {
    NumberFormat salarioBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    Map<String, List<Funcionario>> mapFuncionarios = listaDeFuncionarios.stream().collect(
        Collectors.groupingBy(Funcionario::getFuncao));

    StringBuilder mapTexto = new StringBuilder("\nFuncionários por função:\n");

    for (Entry<String, List<Funcionario>> entry : mapFuncionarios.entrySet()) {

      mapTexto.append(String.format("\nFunção: %s\n", entry.getKey()));
      for (Funcionario funcionario : entry.getValue()) {
        mapTexto.append(String.format(" - Nome %s, Data de Nascimento: %s, Salário: %s\n",
            funcionario.getNome(),
            funcionario.getData_nascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyy")),
            salarioBR.format(funcionario.getSalario())
        ));
      }
    }

    return mapTexto.toString();
  }

  public StringBuilder exibirFuncionarioPorMesDeNascimento(String mes) {
    Month mesEnum = null;

    switch (mes) {
      case "1":
        mesEnum = Month.JANUARY;
        break;
      case "2":
        mesEnum = Month.FEBRUARY;
        break;
      case "3":
        mesEnum = Month.MARCH;
        break;
      case "4":
        mesEnum = Month.APRIL;
        break;
      case "5":
        mesEnum = Month.MAY;
        break;
      case "6":
        mesEnum = Month.JUNE;
        break;
      case "7":
        mesEnum = Month.JULY;
        break;
      case "8":
        mesEnum = Month.AUGUST;
        break;
      case "9":
        mesEnum = Month.SEPTEMBER;
        break;
      case "10":
        mesEnum = Month.OCTOBER;
        break;
      case "11":
        mesEnum = Month.NOVEMBER;
        break;
      case "12":
        mesEnum = Month.DECEMBER;
        break;

      default:
        mesEnum = null;
        break;
    }

    Month finalMesEnum = mesEnum;

    List<Funcionario> funcionarios = listaDeFuncionarios.stream()
        .filter(funcionario -> funcionario.getData_nascimento().getMonth().equals(finalMesEnum))
        .toList();

    NumberFormat salarioBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    StringBuilder resultadoTexto = new StringBuilder("Funcionários que fazem aniversário no mês ");
    resultadoTexto.append(String.format("%s\n", mes));

    if (funcionarios.size() <= 0) {
      resultadoTexto.append(" - Nenhum funcionário faz aniversário neste mês.");
      return resultadoTexto;
    }
    for (Funcionario funcionario : funcionarios) {
      resultadoTexto.append(
          String.format(" - Nome %s, Data de Nascimento: %s, Salário: %s, Função: %s\n",
              funcionario.getNome(),
              funcionario.getData_nascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyy")),
              salarioBR.format(funcionario.getSalario()),
              funcionario.getFuncao()
          ));
    }

    return resultadoTexto;
  }

  public String exibirFuncionarioDeMaiorIdade() {
    Optional<Funcionario> maisVelho = listaDeFuncionarios.stream()
        .min(Comparator.comparing(Funcionario::getData_nascimento));

    int anoAgora = LocalDate.now().getYear();
    int mesAgora = LocalDate.now().getMonthValue();
    int diaAgora = LocalDate.now().getDayOfMonth();
    int anoFuncionario = maisVelho.get().getData_nascimento().getYear();
    int mesFuncionario = maisVelho.get().getData_nascimento().getMonthValue();
    int diaFuncionario = maisVelho.get().getData_nascimento().getDayOfMonth();

    StringBuilder listaTexto = new StringBuilder("\nFuncionário mais velho:\n");
    if (maisVelho.isPresent()) {
      if (anoAgora > anoFuncionario && mesAgora > mesFuncionario && diaAgora > diaFuncionario) {
        listaTexto.append(
            String.format("Nome: %s, Idade: %s\n",
                maisVelho.get().getNome(),
                anoAgora - anoFuncionario
            ));
      } else {
        listaTexto.append(
            String.format("Nome: %s, Idade: %s\n",
                maisVelho.get().getNome(),
                anoAgora - anoFuncionario - 1
            ));
      }

    } else {
      return "Não existe um funcionário mais velho";
    }
    return listaTexto.toString();
  }

  public String exibirFuncionariosEmOrdemAlfabetica() {
    NumberFormat salarioBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    List<Funcionario> copiaFuncionarios = new ArrayList<>(listaDeFuncionarios);
    copiaFuncionarios.sort(Comparator.comparing(Funcionario::getNome));

    StringBuilder listaTexto = new StringBuilder("\nLista de Funcionários em ordem alfabética:\n");
    for (Funcionario funcionario : copiaFuncionarios) {
      listaTexto.append(
          String.format(" - Nome: %s, Data de Nascimento: %s, Salário: %s, Função: %s\n",
              funcionario.getNome(),
              funcionario.getData_nascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyy")),
              salarioBR.format(funcionario.getSalario()),
              funcionario.getFuncao()));
    }

    return listaTexto.toString();
  }

  public String exibirTotalDosSalários() {
    NumberFormat salarioBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    StringBuilder listaTexto = new StringBuilder("\nTotal de Salários:\n");
    BigDecimal total = BigDecimal.ZERO;

    for (Funcionario funcionario : listaDeFuncionarios) {
      total = total.add(funcionario.getSalario());
    }

    listaTexto.append(
        String.format(" - Total: %s\n",
            salarioBR.format(total)));
    return listaTexto.toString();
  }

  public StringBuilder exibirRelacaoSalarioMinimo() {
    NumberFormat salarioBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    double salarioMinimo = 1212.00;

    StringBuilder listaTexto = new StringBuilder("\nRelação de Salários mínimos - ");
    listaTexto.append(String.format("(salário mínimo: R$ %s):\n", salarioBR.format(salarioMinimo)));

    for (Funcionario funcionario : listaDeFuncionarios) {
      double relacaoSalarioMinimo =
          Double.parseDouble(funcionario.getSalario().toString()) / salarioMinimo;
      listaTexto.append(
          String.format(" - Nome: %s, Salário: %s, equivale à %.1f salários mínimos\n",
              funcionario.getNome(),
              salarioBR.format(funcionario.getSalario()),
              relacaoSalarioMinimo));
    }

    return listaTexto;
  }
}
