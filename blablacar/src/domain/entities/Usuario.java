package domain.entities;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String endereco;
    Passageiro passageiro;
    Motorista motorista;


    public Usuario(String nome, String email, String senha, String telefone, String endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getEndereco() {
        return endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public void setSenha(String senhaAntiga, String novaSenha) {
        if (this.senha.equals(senhaAntiga)) {
            this.senha = novaSenha;
        } else {
            System.out.println("Senha antiga incorreta.");
        }
    }
    public String getSenha() {
        return senha;
    }
    public Motorista getMotorista() {
        return motorista;
    }
    public Passageiro getPassageiro() {
        return passageiro;
    }
    public void setMotorista(String cnh) {
        this.motorista = new Motorista(cnh);
    }
    public void setPassageiro() {
        passageiro = new Passageiro();
    }
}