import lombok.Data;

import java.time.LocalDate;

@Data
public class Produtos {
    private String produto;
    @Anos(valor=5)
    private LocalDate produtoVenceNesseDia;

    public Produtos(LocalDate vencimentoDoProduto) {
        this.produtoVenceNesseDia = vencimentoDoProduto;
    }
}
