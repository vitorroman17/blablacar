package repository;

import domain.entities.PassageiroViagem;
import domain.entities.Usuario;
import domain.entities.Viagem;
import domain.enuns.StatusViagem;

import java.util.ArrayList;
import java.util.List;

public class ViagemRepository {

    private final List<Viagem> viagens = new ArrayList<>();

    public void addViagem(Viagem viagem) {
        viagens.add(viagem);
    }

    public List<Viagem> listarViagens() {
        return viagens;
    }

    public Viagem getViagemPorId(int id) {
        for (Viagem viagem : viagens) {
            if (viagem.getId() == id) {
                return viagem;
            }
        }
        return null;
    }

    public List<Viagem> listarViagensPorOrigemDestinoData(String origem, String destino, String data) {
        List<Viagem> viagensFiltradas = new ArrayList<>();
        for (Viagem viagem : viagens) {
            if (viagem.getCidadeOrigem().equalsIgnoreCase(origem)
                    && viagem.getCidadeDestino().equalsIgnoreCase(destino)
                    && viagem.getData().equalsIgnoreCase(data)) {
                viagensFiltradas.add(viagem);
            }
        }
        return viagensFiltradas;
    }

    public List<Viagem> listarViagensPorOrigemDestino(String origem, String destino) {
        List<Viagem> viagensFiltradas = new ArrayList<>();
        for (Viagem viagem : viagens) {
            if (viagem.getCidadeOrigem().equalsIgnoreCase(origem)
                    && viagem.getCidadeDestino().equalsIgnoreCase(destino)) {
                viagensFiltradas.add(viagem);
            }
        }
        return viagensFiltradas;
    }

    public Viagem reservarViagemPorId(int id, int pessoas, Usuario passageiro) {
        for (Viagem viagem : viagens) {
            if (viagem.getId() == id) {
                viagem.adicionarPassageiro(passageiro, pessoas);
                if (viagem.getLugaresOcupados() == viagem.getVagas()) {
                    viagem.alterarStatus(StatusViagem.CHEIA);
                    
                }
                return viagem;
            }
        }
        return null;
    }

    public double atualizarAvaliacaoViagem(double nota, int idViagem) {
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

    public List<Viagem> listarViagensPorPassageiro(Usuario passageiro) {
        List<Viagem> viagensFiltradas = new ArrayList<>(); 
        for (Viagem viagem : viagens) {
            for (PassageiroViagem pv : viagem.getPassageiros()) {
                if (pv.getPassageiro().equals(passageiro)) {
                    viagensFiltradas.add(viagem);
                    break;
                }
            }
        }
        return viagensFiltradas;
    }

    public List<Viagem> listarViagensPorMotorista(Usuario motorista) {
        List<Viagem> viagensFiltradas = new ArrayList<>();
        for (Viagem viagem : viagens) {
            if (viagem.getMotorista().equals(motorista)) {
                viagensFiltradas.add(viagem);
            }
        }
        return viagensFiltradas;
    }

    public int contarPassageirosPorMotorista(Usuario motorista) {
        int totalPassageiros = 0;
        for (Viagem viagem : viagens) {
            if (viagem.getMotorista().equals(motorista)) {
                for (PassageiroViagem pv : viagem.getPassageiros()) {
                    if (pv.getStatus() == "CONCLUIDO") {
                        totalPassageiros += pv.getNumeroDeLugares();
                    }
                }
            }
        }
        return totalPassageiros;
    }

    public double calcularReceitaTotalPorMotorista(Usuario motorista) {
        double receitaTotal = 0.0;
        for (Viagem viagem : viagens) {
            if (viagem.getMotorista().equals(motorista)) {
                for (PassageiroViagem pv : viagem.getPassageiros()) {
                    if (pv.getStatus() == "CONCLUIDO") {
                        receitaTotal += viagem.getPreco() * pv.getNumeroDeLugares();
                    }
                }
            }
        }
        return receitaTotal;
    }

    public void aumentarLugaresDisponiveis(Usuario passageiro) {
        for (Viagem viagem : viagens) {
            for (PassageiroViagem pv : viagem.getPassageiros()) {
                if (pv.getPassageiro().equals(passageiro)) {
                    viagem.aumentarLugaresDisponiveis(pv.getNumeroDeLugares());
                }
            }
        }
    }
}