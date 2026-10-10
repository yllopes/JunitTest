import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import org.semesperanca.CalculadoraIR;

class CalculadoraIRTest {

    private static final double DELTA = 0.01;

    @ParameterizedTest(name = "renda {0} deve lançar exceção")
    @ValueSource(doubles = {-100.0, -0.01})
    @DisplayName("CE1: renda negativa é inválida")
    void rendaNegativaDeveLancarExcecao(double renda) {
        assertThrows(IllegalArgumentException.class,
                () -> CalculadoraIR.calcularIR(renda));
    }

    @ParameterizedTest(name = "renda {0} -> IR {1}")
    @CsvSource({
            "0.00,      0.00",
            "4000.00,   0.00",
            "4999.99,   0.00",
            "5000.00,   0.00",
            "5000.01,   343.75",
            "5250.00,   360.94",
            "5500.00,   378.13",
            "5500.01,   756.25",
            "5750.00,   790.63",
            "6000.00,   825.00",
            "6000.01,   1237.50",
            "6250.00,   1289.06",
            "6500.00,   1340.63",
            "6500.01,   1608.75",
            "6900.00,   1707.75",
            "7350.00,   1819.13",
            "7350.01,   2021.25",
            "8000.00,   2200.00",
            "10000.00,  2750.00"
    })
    @DisplayName("CE2-CE7: classes válidas e valores limite")
    void deveCalcularIRConformeFaixa(double renda, double esperado) {
        assertEquals(esperado, CalculadoraIR.calcularIR(renda), DELTA);
    }

    @Test
    @DisplayName("Mensagem da exceção para renda inválida")
    void mensagemDaExcecao() {
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> CalculadoraIR.calcularIR(-1));
        assertEquals("Renda inválida", e.getMessage());
    }
}
