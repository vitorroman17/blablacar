package domain.usecases.viagem.adicionarpassageiroviagem.input;

import domain.entities.Usuario;
import domain.entities.Viagem;

public class AdicionarPassageiroInput {

    private final Viagem viagem;
    private final Usuario passageiro;
    private final int pessoas;

    public AdicionarPassageiroInput(Viagem viagem, Usuario passageiro, int pessoas) {
        this.viagem = viagem;
        this.passageiro = passageiro;
        this.pessoas = pessoas;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public Usuario getPassageiro() {
        return passageiro;
    }

    public int getPessoas() {
        return pessoas;
    }
}
