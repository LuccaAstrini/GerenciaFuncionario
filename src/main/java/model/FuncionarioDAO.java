package model;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    private List<Funcionario> funcionarios;

    public FuncionarioDAO() {
        this.funcionarios = new ArrayList<>();
    }

    //Lembrete utilizar o set na lista para recuperar os dados depois colocar
    //o carregar arquivo na main para puxar os dados do txt para a lista
    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void adicionar(Funcionario funcionario) {;
        funcionarios.add(funcionario);
    }

    public Funcionario recuperar(String codigo) {
        for (Funcionario f : funcionarios) {
            if (f.getCodigo().equals(codigo)) {
                return f;
            }
        }
        return null;
    }

    public List<Funcionario> recuperarTodos() {
        return new ArrayList<>(funcionarios);
    }
}