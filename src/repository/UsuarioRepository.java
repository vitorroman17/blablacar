package repository;
import domain.entities.Usuario;
import domain.entities.Veiculo;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private final List<Usuario> usuarios = new ArrayList<>();
    private  List<Veiculo> veiculos = null;

    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public Usuario Login(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;

    }
    public List<Veiculo> getVeiculos(Usuario usuarioAtual){
        for (Usuario usuario : usuarios) {
            if (usuario == usuarioAtual) {
               veiculos = usuario.getMotorista().getVeiculos();
               if(veiculos == null){
                    return null;
               }
            }
        }
        return this.veiculos;
    }

    public Veiculo getVeiculoFromId(Usuario usuarioAtual, int id){
        for (Usuario usuario : usuarios) {
            if (usuario == usuarioAtual) {
                for (Veiculo veiculo : usuario.getMotorista().getVeiculos()) {
                    if(veiculo.getId() == id){
                        return veiculo;
                    }
                }
            }
        }
        return null;
    }
}
