public class Passageiro extends Usuario {
    private int numeroDeViagens;

    public Passageiro(String nome, String email, String senha, String telefone, String endereco, double avaliacao, int numeroDeViagens) {
        super(nome, email, senha, telefone, endereco, avaliacao);
    }
    
    public int getNumeroDeViagens() {
        return numeroDeViagens;
    }

    public void addViagem() {
        this.numeroDeViagens ++;
    }


    
}
