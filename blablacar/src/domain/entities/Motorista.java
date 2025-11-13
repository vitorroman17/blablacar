public class Motorista extends Usuario {
    private  String cnh;
    private  int numeroDeViagens;

    public Motorista(String nome, String email, String senha, String telefone, String endereco, double avaliacao, String cnh, int numeroDeViagens) {
        super(nome, email, senha, telefone, endereco, avaliacao);
        this.cnh = cnh;
    }
    public String getCnh() {
        return cnh;
    }

    public int getNumeroDeViagens() {
        return numeroDeViagens;
    }
    public void addViagem() {
        this.numeroDeViagens ++;
    }
}