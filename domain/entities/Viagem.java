
import java.util.List;

public class Viagem {
    private final Veiculo veiculo;
    List<Passageiro> passageiros;
    private final Motorista motorista;
    private final String cidadeOrigem;
    private final String cidadeDestino;
    private final double preco;
    private double avaliacao = 0.0;
    private  double somaNotas = 0;
    private  int totalAvaliacoes = 0;

    public Viagem(Veiculo veiculo, Motorista motorista, String cidadeOrigem, String cidadeDestino, double preco) {
        this.veiculo = veiculo;
        this.motorista = motorista;
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.preco = preco;
    }
     public void Avaliar(double novaAvaliacao) {
        somaNotas += novaAvaliacao;
        totalAvaliacoes ++;
        this.avaliacao = somaNotas / totalAvaliacoes;
    }
}