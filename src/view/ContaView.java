package view;

import dao.ContaDAO;
import model.Conta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContaView extends JFrame {
    private JTextField nomeField, numeroField, saldoField, agenciaField;
    private JRadioButton correnteRadio, poupancaRadio;
    private JButton salvarButton, removerButton, buscarButton;
    private ContaDAO contaDAO;

    public ContaView() {
        contaDAO = new ContaDAO();
        setTitle("Cadastro de Conta");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        // Nome do titular
        JLabel nomeLabel = new JLabel("Nome do Titular:");
        nomeField = new JTextField();
        nomeLabel.setLabelFor(nomeField);
        nomeField.setToolTipText("Digite o nome do titular da conta");
        nomeField.getAccessibleContext().setAccessibleName("Campo de entrada para o nome do titular");

        add(nomeLabel);
        add(nomeField);

        // Número da conta
        JLabel numeroLabel = new JLabel("Número da Conta:");
        numeroField = new JTextField();
        numeroLabel.setLabelFor(numeroField);
        numeroField.setToolTipText("Digite o número da conta");
        numeroField.getAccessibleContext().setAccessibleName("Campo de entrada para o número da conta");

        add(numeroLabel);
        add(numeroField);

        // Agência
        JLabel agenciaLabel = new JLabel("Agência:");
        agenciaField = new JTextField();
        agenciaLabel.setLabelFor(agenciaField);
        agenciaField.setToolTipText("Digite o número da agência");
        agenciaField.getAccessibleContext().setAccessibleName("Campo de entrada para a agência");

        add(agenciaLabel);
        add(agenciaField);

        // Saldo
        JLabel saldoLabel = new JLabel("Saldo:");
        saldoField = new JTextField();
        saldoLabel.setLabelFor(saldoField);
        saldoField.setToolTipText("Digite o saldo da conta");
        saldoField.getAccessibleContext().setAccessibleName("Campo de entrada para o saldo da conta");

        add(saldoLabel);
        add(saldoField);

        // Tipo de Conta (Radio Buttons)
        correnteRadio = new JRadioButton("Corrente");
        poupancaRadio = new JRadioButton("Poupança");
        ButtonGroup tipoContaGroup = new ButtonGroup();
        tipoContaGroup.add(correnteRadio);
        tipoContaGroup.add(poupancaRadio);

        correnteRadio.setToolTipText("Selecione se a conta é do tipo corrente");
        poupancaRadio.setToolTipText("Selecione se a conta é do tipo poupança");

        correnteRadio.getAccessibleContext().setAccessibleName("Botão de rádio para conta corrente");
        poupancaRadio.getAccessibleContext().setAccessibleName("Botão de rádio para conta poupança");

        add(correnteRadio);
        add(poupancaRadio);

        // Botões
        salvarButton = new JButton("Salvar");
        salvarButton.setToolTipText("Salvar os dados da conta");
        salvarButton.getAccessibleContext().setAccessibleName("Botão para salvar conta");

        removerButton = new JButton("Remover");
        removerButton.setToolTipText("Remover a conta existente");
        removerButton.getAccessibleContext().setAccessibleName("Botão para remover conta");

        buscarButton = new JButton("Buscar");
        buscarButton.setToolTipText("Buscar a conta pelo número");
        buscarButton.getAccessibleContext().setAccessibleName("Botão para buscar conta");

        add(salvarButton);
        add(removerButton);
        add(buscarButton);

        // Ações dos botões
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarConta();
            }
        });

        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerConta();
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarConta();
            }
        });

        setVisible(true);
    }

    // Funções CRUD
    private void salvarConta() {
        try {
            String nome = nomeField.getText();
            String numero = numeroField.getText();
            String agencia = agenciaField.getText();
            double saldo = Double.parseDouble(saldoField.getText());
            String tipoConta = correnteRadio.isSelected() ? "Corrente" : "Poupança";

            Conta conta = new Conta(nome, numero, tipoConta, saldo, agencia);
            contaDAO.adicionarConta(conta);

            JOptionPane.showMessageDialog(this, "Conta salva com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
        }
    }

    private void removerConta() {
        String numero = numeroField.getText();
        contaDAO.removerConta(numero);
        JOptionPane.showMessageDialog(this, "Conta removida.");
    }

    private void buscarConta() {
        String numero = numeroField.getText();
        Conta conta = contaDAO.buscarConta(numero);
        if (conta != null) {
            nomeField.setText(conta.getNomeTitular());
            saldoField.setText(String.valueOf(conta.getSaldo()));
            agenciaField.setText(conta.getAgencia());
            if (conta.getTipoConta().equals("Corrente")) {
                correnteRadio.setSelected(true);
            } else {
                poupancaRadio.setSelected(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Conta não encontrada.");
        }
    }

    public static void main(String[] args) {
        new ContaView();
    }
}
