import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoImportadoTest {

    @Test
    public void constructorFunciona(){
        //Given
        String nombre = "sampleNombre";
        String pais = "samplePais";
        double valor = 2;
        String categoria = "sampleCategoria";

        //When
        ProductoImportado prod = new ProductoImportado(nombre,pais,valor,categoria);

        //Expected
        assertNotNull(prod);
        assertEquals(prod.getNombre(), "sampleNombre");
        assertEquals(prod.getPaisOrigen(), "samplePais");
        assertEquals(prod.getValorDeclaradoUSD(), 2);
        assertEquals(prod.getCategoria(), "sampleCategoria");
    }

    @Test
    public void toStringFunciona(){
        //Given
        String nombre = "sampleNombre";
        String pais = "samplePais";
        double valor = 2;
        String categoria = "sampleCategoria";
        ProductoImportado prod = new ProductoImportado(nombre,pais,valor,categoria);

        //When
        String prueba = prod.toString();
        //Expected
        assertEquals(prueba, "ProductoImportado{" +
                "nombre='" + prod.getNombre() + '\'' +
                ", paisOrigen='" + prod.getPaisOrigen() + '\'' +
                ", valorDeclaradoUSD=" + prod.getValorDeclaradoUSD() +
                ", categoria='" + prod.getCategoria() + '\'' +
                '}');
    }
}
