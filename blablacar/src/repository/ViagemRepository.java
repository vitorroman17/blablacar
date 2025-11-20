package repository;

import domain.entities.PassageiroViagem;
import domain.entities.Usuario;
import domain.entities.Viagem;
import domain.enuns.StatusViagem;
import domain.enuns.StatusParticipacao;

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
                try {
                    viagem.adicionarPassageiro(passageiro, pessoas);
                } catch (IllegalArgumentException e) {
                    return null;
                }
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

    public boolean cancelarReserva(Viagem viagem, Usuario passageiro) {
        for (Viagem v : viagens) {
            if (v.equals(viagem)) {
                for (PassageiroViagem pv : viagem.getPassageiros()) {
                    if (pv.getPassageiro().equals(passageiro)) {
                        viagem.removerPassageiro(passageiro, pv.getNumeroDeLugares());
                        if (viagem.getStatus().equals(StatusViagem.CHEIA)) {
                            viagem.alterarStatus(StatusViagem.PENDENTE);
                        }
                        break;
                    }
                }
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
                    if (pv.getStatus().equals(StatusParticipacao.CONCLUIDO)) {
                        totalPassageiros += pv.getNumeroDeLugares();
                    }
                }
            }
        }
        return totalPassageiros;
    }

    public double calcularReceitaTotalPorMotorista(Usuario motorista) {
        double total = 0.0;

        for (Viagem viagem : viagens) {
            if (viagem.getMotorista().equals(motorista)) {
                for (PassageiroViagem pv : viagem.getPassageiros()) {
                    if (pv.getStatus().equals(StatusParticipacao.CONCLUIDO)) {
                        total += pv.getNumeroDeLugares() * viagem.getPreco();
                    }
                }
            }
        }

        return total;
    }


    public void atualizarReserva(Usuario passageiro, int lugares, Viagem viagem) {
        for(Viagem v : viagens) {
            if(v == viagem){
                for (PassageiroViagem pv : viagem.getPassageiros()) {
                    if (pv.getPassageiro().equals(passageiro)) {
                        pv.alterarQuantidadeLugares(lugares);
                    }
                }   
            }
        }
    }
    public boolean cancelarReserva(int idViagem, Usuario passageiro, int pessoas) {
        for (Viagem viagem : viagens) {
            if (viagem.getId() == idViagem) {
                try {
                    viagem.removerPassageiro(passageiro, pessoas);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        }
    return false;
    }
}