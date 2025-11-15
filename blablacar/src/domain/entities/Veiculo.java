package domain.entities;

public class Veiculo {
    private static int proximoId = 1;

    private int id = 0;
    private final String marca;
    private final String modelo;
    private final String placa;
    private final int ano;
    private final String cor;

    public Veiculo(String marca, String modelo, String placa, int ano, String cor) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
        this.cor = cor;
        this.id = proximoId++;
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
    public int getId(){
        return id;
    }
}