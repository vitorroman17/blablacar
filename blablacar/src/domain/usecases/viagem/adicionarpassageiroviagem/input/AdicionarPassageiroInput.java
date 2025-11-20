package domain.usecases.viagem.adicionarpassageiroviagem.input;

import domain.entities.Usuario;

public class AdicionarPassageiroInput {
    public int idViagem; 
    public int passageiroId; 
    public int quantidadeDePessoas;  

    public AdicionarPassageiroInput(int idViagem, int passageiroId, int quantidadeDePessoas) {
        this.idViagem = idViagem;
        this.passageiroId = passageiroId;
        this.quantidadeDePessoas = quantidadeDePessoas;
    }

}
