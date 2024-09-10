package dao;

import model.Conta;

import java.util.ArrayList;
import java.util.List;

public class ContaDAO {
    private List<Conta> contas = new ArrayList<>();

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public void removerConta(String numeroConta) {
        contas.removeIf(conta -> conta.getNumeroConta().equals(numeroConta));
    }

    public void atualizarConta(Conta contaAtualizada) {
        for (int i = 0; i < contas.size(); i++) {
            if (contas.get(i).getNumeroConta().equals(contaAtualizada.getNumeroConta())) {
                contas.set(i, contaAtualizada);
                return;
            }
        }
    }

    public Conta buscarConta(String numeroConta) {
        return contas.stream()
                .filter(conta -> conta.getNumeroConta().equals(numeroConta))
                .findFirst()
                .orElse(null);
    }

    public List<Conta> listarContas() {
        return contas;
    }