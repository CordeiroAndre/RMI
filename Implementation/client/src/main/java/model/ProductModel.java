package client.src.main.java.model;

public class ProductModel {

    private String code;
    private String name;
    private String description;
    private double price;
    private int quantidadeEstoque;

    public ProductModel(String code, String name, String description, double price, int quantidadeEstoque) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public ProductModel(String name, String description, double price, int quantidadeEstoque) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    @Override
    public String toString() {
        return "Código:" + code +
                ", Nome: " + name +
                ", Descrição: " + description +
                ", Preço: R$" + price +
                ", Em estoque: " + quantidadeEstoque ;
    }
}
