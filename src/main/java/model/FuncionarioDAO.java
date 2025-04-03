package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FuncionarioDAO {

    private List<Funcionario> funcionarios;
    private static final String arq = "funcionarios.txt";

    public FuncionarioDAO() {
        this.funcionarios = new ArrayList<>();
        carregarArquivo();
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void adicionar(Funcionario funcionario) {
        funcionarios.add(funcionario);
        salvarArquivo();
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

    // Salva os funcionários no arquivo
    public void salvarArquivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(arq))) {
            for (Funcionario f : funcionarios) {
                writer.println(f.getCodigo() + "," + f.getNome() + "," + f.getSalario());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados!");
        }
    }

    // Carrega os funcionários do txt
    private void carregarArquivo() {
        try (Scanner leitor = new Scanner(new File(arq))) {
            while (leitor.hasNextLine()) {
                String[] partes = leitor.nextLine().split(",");
                if (partes.length == 3) {
                    funcionarios.add(new Funcionario(partes[0], 
                            partes[1], Float.parseFloat(partes[2])));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
        }
    }
}
