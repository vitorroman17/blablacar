package repository;

import domain.entities.Usuario;
import domain.entities.Viagem;

public class ViagemRepository {

    private final java.util.List<Viagem> viagens = new java.util.ArrayList<>();

    public void addViagem(Viagem viagem) {
        viagens.add(viagem);
    }

    
    public java.util.List<Viagem> ListarViagens() {
        return viagens;
    }

    
    public java.util.List<Viagem> ListarViagensPorOrigemDestino(String origem, String destino, String data) {
        java.util.List<Viagem> viagensFiltradas = new java.util.ArrayList<>();
        for (Viagem viagem : viagens) {
            if (viagem.getCidadeOrigem().equalsIgnoreCase(origem)
                    && viagem.getCidadeDestino().equalsIgnoreCase(destino)
                    && viagem.getData().equalsIgnoreCase(data)) {
                viagensFiltradas.add(viagem);
            }
        }
        return viagensFiltradas;
    }

    
    public Viagem reservarViagemPorId(int id, int pessoas, Usuario passageiro) {
        for (Viagem viagem : viagens) {
            if (viagem.getId() == id) {
                viagem.adicionarPassageiro(passageiro, pessoas);
                return viagem;
            }
        }
        return null;
    }

    public double atualizarAvaliacaoViagem(double  nota, int idViagem) {
        for (Viagem viagem : viagens) {
            if (viagem.getId() == idViagem) {
                viagem.avaliar(nota);
                return viagem.getAvaliacao();
            }
        }
        return nota; 
    }
    
    public boolean cancelarReserva(int idViagem, Usuario passageiro, int pessoas) {
        for (Viagem viagem : viagens) {
            if (viagem.getId() == idViagem) {
                viagem.removerPassageiro(passageiro, pessoas);
                return true;
            }
        }
        return false;
    }
}