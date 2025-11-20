package domain.usecases.viagem.adicionarpassageiroviagem.output;
import domain.entities.PassageiroViagem;

public class AdicionarPassageiroOutput {

    private final PassageiroViagem passageiroViagem;
    private final int vagasRestantes;

    public AdicionarPassageiroOutput(PassageiroViagem passageiroViagem, int vagasRestantes) {
        this.passageiroViagem = passageiroViagem;
        this.vagasRestantes = vagasRestantes;
    }

    public PassageiroViagem getPassageiroViagem() {
        return passageiroViagem;
    }

    public int getVagasRestantes() {
        return vagasRestantes;
    }
}
