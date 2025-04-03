package com.mycompany.gerenciafuncionario;

import model.FuncionarioDAO;
import view.Cadastra;

public class GerenciaFuncionario {

    public static void main(String[] args) {
        FuncionarioDAO controller = new FuncionarioDAO();
        Cadastra frame = new Cadastra();
        frame.setVisible(true);
    }
}