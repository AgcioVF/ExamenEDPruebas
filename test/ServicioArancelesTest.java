
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class ServicioArancelesTest {

    private static double ARANCEL_BASE = 0.10; // 10%

    private static Map<String, Double> ARANCELES_POR_PAIS = new HashMap<>();
    private static Map<String, Double> ARANCELES_POR_CATEGORIA = new HashMap<>();

    @BeforeEach
    void setUp() {
        ARANCELES_POR_PAIS.put("China", 0.34);
        ARANCELES_POR_PAIS.put("Unión Europea", 0.20);

        ARANCELES_POR_CATEGORIA.put("electrónica", 0.05);
        ARANCELES_POR_CATEGORIA.put("textil", 0.10);
    }

    @Test
    public void calcularArancelFunciona() {
        //Given
        String nombre = "sampleName";
        String pais = "China";
        double valor = 2;
        String categoria = "textil";
        ProductoImportado prod = new ProductoImportado(nombre, pais, valor, categoria);

        //When
        double res = ServicioAranceles.calcularArancel(prod);

        //Expected
        assertEquals(res, 0.88);
    }

    @Test
    public void calcularArancelProdNull() {
        //Given
        ProductoImportado prod = null;

        //When - Expected
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> ServicioAranceles.calcularArancel(prod));
        assertEquals("El producto no puede ser null.", ex.getMessage());
    }

    @Test
    public void agregarArancelPais() {
        //Given
        String pais = "China";
        double tasa = 0.49;
        String nombre = "sampleName";
        double valor = 2;
        String categoria = "textil";
        ProductoImportado prod = new ProductoImportado(nombre, pais, valor, categoria);


        //When
        ServicioAranceles.agregarArancelPais(pais, tasa);

        //Expected
        assertEquals(ARANCELES_POR_PAIS.getOrDefault(prod.getPaisOrigen(), ARANCEL_BASE), 0.34);

    }

    @Test
    public void agregarArancelPaisTasaMenor1() {
        //Given
        String pais = "China";
        double tasa = -1;
        String nombre = "sampleName";
        double valor = 2;
        String categoria = "textil";
        ProductoImportado prod = new ProductoImportado(nombre, pais, valor, categoria);


        //When
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> ServicioAranceles.agregarArancelPais(pais, tasa));
        assertEquals("La tasa de arancel debe estar entre 0.0 y 1.0", ex.getMessage());
    }

    @Test
    public void agregarArancelPaisTasaMayor1() {
        //Given
        String pais = "China";
        double tasa = 2;
        String nombre = "sampleName";
        double valor = 2;
        String categoria = "textil";
        ProductoImportado prod = new ProductoImportado(nombre, pais, valor, categoria);


        //When
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> ServicioAranceles.agregarArancelPais(pais, tasa));
        assertEquals("La tasa de arancel debe estar entre 0.0 y 1.0", ex.getMessage());
    }
}