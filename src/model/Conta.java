package model;

public class Conta {
    private String nomeTitular;
    private String numeroConta;
    private String tipoConta;
    private double saldo;
    private String agencia;

    public Conta(String nomeTitular, String numeroConta, String tipoConta, double saldo, String agencia) {
        this.setNomeTitular(nomeTitular);
        this.setNumeroConta(numeroConta);
        this.setTipoConta(tipoConta);
        this.setSaldo(saldo);
        this.setAgencia(agencia);
    }

    // Validações e Getters/Setters

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        if (nomeTitular == null || nomeTitular.isEmpty() || nomeTitular.length() < 3) {
            throw new IllegalArgumentException("Nome do titular inválido.");
        }
        this.nomeTitular = nomeTitular;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        if (numeroConta == null || numeroConta.isEmpty() || !numeroConta.matches("\\d{4,10}")) {
            throw new IllegalArgumentException("Número da conta inválido.");
        }
        this.numeroConta = numeroConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        if (!tipoConta.equalsIgnoreCase("Corrente") && !tipoConta.equalsIgnoreCase("Poupança")) {
            throw new IllegalArgumentException("Tipo de conta inválido.");
        }
        this.tipoConta = tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("Saldo não pode ser negativo.");
        }
        this.saldo = saldo;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        if (agencia == null || agencia.isEmpty() || !agencia.matches("\\d{4}")) {
            throw new IllegalArgumentException("Agência inválida.");
        }
        this.agencia = agencia;
    }
}
