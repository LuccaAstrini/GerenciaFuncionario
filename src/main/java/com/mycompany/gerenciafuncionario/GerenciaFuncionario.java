package com.mycompany.gerenciafuncionario;

import controller.FuncionarioController;
import view.Cadastra;

public class GerenciaFuncionario {

    public static void main(String[] args) {
        FuncionarioController controller = new FuncionarioController();
        Cadastra frame = new Cadastra();
        frame.setVisible(true);
    }
}