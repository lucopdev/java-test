package src.com.prothera.test01;

import java.util.List;
import src.com.prothera.test01.model.Funcionario;
import src.com.prothera.test01.model.ListaDeFuncionarios;
import src.com.prothera.test01.model.Pessoa;
import src.com.prothera.test01.service.LeitorCsv;

public class main {

  public static void main(String[] args) {
    ListaDeFuncionarios funcionarios = new ListaDeFuncionarios();
    List<Funcionario> novaLista = LeitorCsv.lerCSV("src/com/prothera/test01/data/data.csv");

    Pessoa pessoa = new Pessoa("lucas", "18/10/2000");

    for (Funcionario funcionario : novaLista) {
      funcionarios.adicionarFuncionario(funcionario);
    }

    funcionarios.removerFuncionarioPeloNome("João");
    funcionarios.adicionarAumento("Todos", 10);

    System.out.println(funcionarios.exibirFuncionarios());
    System.out.println(funcionarios.exibirFuncionariosPorFuncao());
    System.out.println(funcionarios.exibirFuncionarioPorMesDeNascimento("10"));
    System.out.println(funcionarios.exibirFuncionarioPorMesDeNascimento("12"));
    System.out.println(funcionarios.exibirFuncionarioDeMaiorIdade());
    System.out.println(funcionarios.exibirFuncionariosEmOrdemAlfabetica());
    System.out.println(funcionarios.exibirTotalDosSalários());
    System.out.println(funcionarios.exibirRelacaoSalarioMinimo());
  }
}
