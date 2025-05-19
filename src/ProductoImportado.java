
public class ProductoImportado {
    private String nombre;
    private String paisOrigen;
    private Double valorDeclaradoUSD;
    private String categoria;

    public ProductoImportado(String nombre, String paisOrigen, double valorDeclaradoUSD, String categoria) {
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.valorDeclaradoUSD = valorDeclaradoUSD;
        this.categoria = categoria;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public double getValorDeclaradoUSD() {
        return valorDeclaradoUSD;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "ProductoImportado{" +
                "nombre='" + nombre + '\'' +
                ", paisOrigen='" + paisOrigen + '\'' +
                ", valorDeclaradoUSD=" + valorDeclaradoUSD +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}