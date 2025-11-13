public class Veiculo {
    private final String marca;
    private final String modelo;
    private final String placa;
    private final int ano;
    private final String cor;
    private final Motorista motorista;

    public Veiculo(String marca, String modelo, String placa, int ano, String cor, Motorista motorista) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
        this.cor = cor;
        this.motorista = motorista;
    }
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public String getPlaca() {
        return placa;
    }
    public int getAno() {
        return ano;
    }
    public String getCor() {
        return cor;
    }
    public Motorista getMotorista() {
        return motorista;
    }
}