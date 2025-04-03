package controller;

import model.Funcionario;
import model.FuncionarioDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {

    private static FuncionarioDAO dao = new FuncionarioDAO();
    private static final String arq = "funcionarios.txt";
    
    public FuncionarioController() {
        carregarArquivo(); // Carregar os dados ao criar o controller
    }

    // Cadastra e salva no arquivo
    public static boolean cadastrar(String codigo, String nome, double salario) {
    if (codigo.isEmpty() || nome.isEmpty()) {
        return false;
    }

    // Verifica se já existe um funcionário com esse código
    //Se retornar false ele não salva
    if (dao.recuperar(codigo) != null) {  
        return false;
    }

    Funcionario funcionario = new Funcionario(codigo, nome, salario);
    dao.adicionar(funcionario);
    salvarTudoNoArquivo();
    return true;
}

    // Busca um funcionário pelo código
    public static void buscar(String codigo) {
        Funcionario funcionario = dao.recuperar(codigo);

        if (funcionario != null) {
            JOptionPane.showMessageDialog(null,
                    "Código: " + funcionario.getCodigo()
                    + "\nNome: " + funcionario.getNome()
                    + "\nSalário: " + funcionario.getSalario(),
                    "Funcionário Encontrado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Preenche a tabela
    public static void preencherTabela(JTable tabela) {
        List<Funcionario> funcionarios = dao.recuperarTodos();
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);

        for (Funcionario f : funcionarios) {
            modelo.addRow(new Object[]{f.getCodigo(), f.getNome(), f.getSalario()});
        }
    }

    // Salva os funcionários no arquivo
    public static void salvarTudoNoArquivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(arq))) {
            for (Funcionario f : dao.recuperarTodos()) {
                writer.println(f.getCodigo() + ";" + f.getNome() + ";" + f.getSalario());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Carrega os funcionários do arquivo e atualiza o DAO
    public static void carregarArquivo() {
        List<Funcionario> listaFuncionarios = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(arq))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    String codigo = dados[0];
                    String nome = dados[1];
                    double salario = Double.parseDouble(dados[2]);
                    listaFuncionarios.add(new Funcionario(codigo, nome, salario));
                }
            }
            dao.setFuncionarios(listaFuncionarios);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Criando um novo.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar os dados!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
