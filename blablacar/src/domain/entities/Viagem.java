package domain.entities;

import java.util.ArrayList;
import java.util.List;

import domain.enuns.StatusParticipacao;
import domain.enuns.StatusViagem;

public class Viagem {
    private static int proximoId = 1;
    private int id = 0;
    private Veiculo veiculo;
    private List<PassageiroViagem> passageiros;
    private Usuario motorista;
    private String cidadeOrigem;
    private String cidadeDestino;
    private double preco;
    private String data;
    private int vagas;
    private int lugaresOcupados;

    private String status;
    private double avaliacao = 0;
    private double somaNotas = 0;
    private int totalAvaliacoes;
    

    public Viagem(Veiculo veiculo, Usuario motorista, String cidadeOrigem, String cidadeDestino, double preco, String data, int vagas) {
        this.veiculo = veiculo;
        this.motorista = motorista;
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.preco = preco;
        this.passageiros = new ArrayList<>();
        this.data = data;
        this.vagas = vagas;
        this.id = proximoId++;
        status = StatusViagem.PENDENTE;
    }
    
     public Veiculo getVeiculo() {
        return veiculo;
    }

    public List<PassageiroViagem> getPassageiros() {
        return passageiros;
    }

    public Usuario getMotorista() {
        return motorista;
    }

    public void avaliar(double novaAvaliacao) {
        if (novaAvaliacao < 0 || novaAvaliacao > 5) {
            throw new IllegalArgumentException("Avaliação deve ser entre 0 e 5");
        }else {
            somaNotas += novaAvaliacao;
            totalAvaliacoes++;
            avaliacao = somaNotas / totalAvaliacoes;
        }
    }
    public double getAvaliacao() {
        return avaliacao;
    }
    public String getCidadeOrigem() {
        return cidadeOrigem;
    }
    public String getCidadeDestino() {
        return cidadeDestino;
    }
    public double getPreco() {
        return preco;
    }

    public void adicionarPassageiro(Usuario passageiro, int pessoas) {
        if (pessoas <= 0) {
            throw new IllegalArgumentException("Número de pessoas deve ser maior que zero.");
        }
        int vagasDisponiveis = getVagas();
            if (pessoas > vagasDisponiveis) {
                throw new IllegalStateException("Não há vagas suficientes nesta viagem.");
            }
        PassageiroViagem pv = new PassageiroViagem(passageiro, pessoas);
        passageiros.add(pv);
    }

    public void removerPassageiro(Usuario passageiro, int pessoas) {
        if (pessoas <= 0) {
            throw new IllegalArgumentException("Quantidade de pessoas deve ser maior que zero.");
        }

        int totalAtivo = 0;
        for (PassageiroViagem pv : passageiros) {
            if (pv.getPassageiro().equals(passageiro) && !pv.getStatus().equals(StatusParticipacao.CANCELADO)) {
                totalAtivo += pv.getNumeroDeLugares();
            }
        }

        if (totalAtivo == 0) {
            throw new IllegalArgumentException("Passageiro não encontrado na viagem.");
        }
        if (pessoas > totalAtivo) {
            throw new IllegalArgumentException("Não é possível cancelar mais lugares do que o passageiro reservou.");
        }

        int restante = pessoas;

        for (PassageiroViagem pv : passageiros) {
            if (!pv.getPassageiro().equals(passageiro) || pv.getStatus().equals(StatusParticipacao.CANCELADO)) {
                continue;
            }

            int lugaresPv = pv.getNumeroDeLugares();

            if (restante >= lugaresPv) {
                restante -= lugaresPv;
                pv.alterarStatus(StatusParticipacao.CANCELADO);
            } else {
                pv.setNumeroDeLugares(lugaresPv - restante);
                restante = 0;
                break;
            }

            if (restante == 0) {
                break;
            }
        }
    }

    public void alterarQuantidadeLugares(Usuario passageiro, int novaQuantidade) {
        if (novaQuantidade <= 0) {
            throw new IllegalArgumentException("Nova quantidade deve ser maior que zero.");
        }
        PassageiroViagem pv = passageiros.stream()
            .filter(p -> p.getPassageiro().equals(passageiro))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Passageiro não encontrado na viagem."));
        
        int quantidadeAtual = pv.getNumeroDeLugares();
        int diferenca = novaQuantidade - quantidadeAtual;
        
        if (diferenca > 0 && diferenca > (vagas - lugaresOcupados)) {
            throw new IllegalStateException("Não há vagas suficientes. Disponíveis: " + (vagas - lugaresOcupados));
        }
        pv.setNumeroDeLugares(novaQuantidade);
        
        lugaresOcupados += diferenca;
    }

    public String getData() {
        return data;
    }
    public int getId() {
        return id;
    }
    public String getStatus() {
        return status;
    }
    public void alterarStatus(String novoStatus) {
        if ((!novoStatus.equals(StatusViagem.PENDENTE) &&
                !novoStatus.equals(StatusViagem.CONCLUIDA)
                && !novoStatus.equals(StatusViagem.INICIADA)
                && !novoStatus.equals(StatusViagem.CHEIA))
                ) {
            throw new IllegalArgumentException("Status inválido para a viagem.");
        }
        this.status = novoStatus;
    }
    public int getTotalPessoasDoPassageiro(Usuario passageiro) {
        int total = 0;
        for (PassageiroViagem pv : passageiros) {
            if (pv.getPassageiro().equals(passageiro) && !(pv.getStatus().equals(StatusParticipacao.CANCELADO))) {
                total += pv.getNumeroDeLugares();
            }
        }
        return total;
    }


    public int getTotalPassageiros() {
        int total = 0;
        for (PassageiroViagem pv : passageiros) {
            if (!pv.getStatus().equals(StatusParticipacao.CANCELADO)) {
                total += pv.getNumeroDeLugares();
            }
        }
        return total;
    }


    public int getVagas() {
        return vagas - getTotalPassageiros();
    }
    public int getLugaresOcupados() {
        return lugaresOcupados;
    }
}