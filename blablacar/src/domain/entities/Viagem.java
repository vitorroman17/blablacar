package domain.entities;

import java.util.ArrayList;
import java.util.List;
import domain.enuns.StatusVIagem;

public class Viagem {
    private static int proximoId = 1;
    private int id = 0  ;
    private  Veiculo veiculo;
    private  List<PassageiroViagem> passageiros;
    private  Usuario motorista;
    private  String cidadeOrigem;
    private  String cidadeDestino;
    private  double preco;
    private  String data;
    private  int vagas;
    private String status;
    private int lugaresOcupados;
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
        status = StatusVIagem.PENDENTE;
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
        }else if (pessoas > vagas - lugaresOcupados) {
            throw new IllegalStateException("Não há vagas suficientes. Disponíveis: " + (vagas - lugaresOcupados));
        }else{
            PassageiroViagem pv = new PassageiroViagem(passageiro, pessoas);
            passageiros.add(pv);
            lugaresOcupados += pessoas;
        }
    }
    public void removerPassageiro(Usuario passageiro, int pessoas) {
        if (passageiros.stream().anyMatch(pv -> pv.getPassageiro().equals(passageiro))) {
            passageiros.removeIf(pv -> pv.getPassageiro().equals(passageiro));
            lugaresOcupados -= pessoas;
        } else {
            throw new IllegalArgumentException("Passageiro não encontrado na viagem.");
        }
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
        if ((!novoStatus.equals(StatusVIagem.PENDENTE) &&
                !novoStatus.equals(StatusVIagem.CONCLUIDA))) {
            throw new IllegalArgumentException("Status inválido para a viagem.");
        }
        this.status = novoStatus;
    }
    public int getTotalPessoasDoPassageiro(Usuario passageiro) {
        int total = 0;
        for (PassageiroViagem pv : passageiros) {
            if (pv.getPassageiro().equals(passageiro)) {
                total += pv.getNumeroDeLugares();
            }
        }
        return total;
    }

    public int getTotalPessoas() {
        int total = 0;
        for (PassageiroViagem pv : passageiros) {
            total += pv.getNumeroDeLugares();
        }
        return total;
    }

    public int getVagas() {
        return vagas - getTotalPessoas();
    }
    
    public void aumentarLugaresDisponiveis(int lugares) {
        this.lugaresOcupados -= lugares;
    }

}