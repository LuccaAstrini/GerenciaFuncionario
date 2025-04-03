package controller;

import model.Funcionario;
import model.FuncionarioDAO;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FuncionarioController {

    private FuncionarioDAO dao;

    public FuncionarioController() {
        this.dao = new FuncionarioDAO();
    }

    public boolean cadastrar(String codigo, String nome, double salario) {
        if (codigo.isEmpty() || nome.isEmpty() || dao.recuperar(codigo) != null) {
            return false;
        }
        dao.adicionar(new Funcionario(codigo, nome, salario));
        return true;
    }

    public void preencherTabela(JTable tabela) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);

        for (Funcionario f : dao.recuperarTodos()) {
            modelo.addRow(new Object[]{f.getCodigo(), f.getNome(), f.getSalario()});
        }
    }

    public void salvarDados() {
        dao.salvarArquivo();
    }

    public Funcionario buscar(String codigo) {
        return dao.recuperar(codigo);
    }

    public List<Funcionario> listarTodos() {
        return dao.recuperarTodos();
    }
}
