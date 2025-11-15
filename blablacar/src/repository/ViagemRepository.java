package repository;

import domain.entities.PassageiroViagem;
import domain.entities.Usuario;
import domain.entities.Viagem;
import java.util.ArrayList;
import java.util.List;

public class ViagemRepository {

    private final List<Viagem> viagens = new ArrayList<>();
    List<Viagem> viagensFiltradas= null;

    public void addViagem(Viagem viagem) {
        viagens.add(viagem);
    }

    
    public List<Viagem> ListarViagens() {
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
    
    public List<Viagem> ListarViagensPorOrigemDestinoData(String origem, String destino, String data) {
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
    public List<Viagem> ListarViagensPorOrigemDestino(String origem, String destino) {
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
    
    public List<Viagem> ListarViagensPorPassageiro(Usuario passageiro) {

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


    public List<Viagem> ListarViagensPorMotorista(Usuario motorista) {
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
                receitaTotal += viagem.getPreco() * viagem.getPassageiros().size();
            }
        }
        return receitaTotal;
    }    
}