import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Servicio que permite calcular aranceles sobre productos importados,
 * en función del país de origen y la categoría del producto.
 */
public class ServicioAranceles {

    private static final Logger log = Logger.getLogger(ServicioAranceles.class.getName());

    private static final double ARANCEL_BASE = 0.10; // 10%

    private static final Map<String, Double> ARANCELES_POR_PAIS = new HashMap<>();
    private static final Map<String, Double> ARANCELES_POR_CATEGORIA = new HashMap<>();

    static {
        ARANCELES_POR_PAIS.put("China", 0.34);
        ARANCELES_POR_PAIS.put("Unión Europea", 0.20);

        ARANCELES_POR_CATEGORIA.put("electrónica", 0.05);
        ARANCELES_POR_CATEGORIA.put("textil", 0.10);
    }

    /**
     * Calcula el arancel total a aplicar a un producto importado.
     * Tiene en cuenta el país de origen y la categoría.
     * Si alguno no se encuentra en el sistema, se usa una tasa por defecto.
     *
     * @param producto Producto sobre el que se va a calcular el arancel.
     * @return Arancel redondeado a 2 decimales.
     * @throws IllegalArgumentException si el producto es null.
     */
    public static double calcularArancel(ProductoImportado producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null.");
        }

        log.info("Calculando arancel para producto: " + producto);

        double arancelPais = ARANCELES_POR_PAIS.getOrDefault(producto.getPaisOrigen(), ARANCEL_BASE);
        double arancelCategoria = ARANCELES_POR_CATEGORIA.getOrDefault(producto.getCategoria(), 0.0);
        double tasaTotal = arancelPais + arancelCategoria;
        double resultado = producto.getValorDeclaradoUSD() * tasaTotal;

        return redondearADosDecimales(resultado);
    }

    /**
     * Redondea un número a 2 decimales.
     *
     * @param valor Número a redondear.
     * @return Número redondeado.
     */
    private static double redondearADosDecimales(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }

    /**
     * Añade o actualiza el arancel para un país.
     *
     * @param pais Nombre del país.
     * @param tasa Tasa de arancel (entre 0.0 y 1.0).
     * @throws IllegalArgumentException si la tasa no es válida.
     */
    public static void agregarArancelPais(String pais, double tasa) {
        validarTasa(tasa);
        ARANCELES_POR_PAIS.put(pais, tasa);
    }

    /**
     * Verifica que una tasa esté entre 0.0 y 1.0.
     *
     * @param tasa Tasa a comprobar.
     * @throws IllegalArgumentException si no está en el rango válido.
     */
    private static void validarTasa(double tasa) {
        if (tasa < 0.0 || tasa > 1.0) {
            throw new IllegalArgumentException("La tasa de arancel debe estar entre 0.0 y 1.0");
        }
    }
}