import java.time.LocalDate;
import java.time.Period;

public class TesteCPF {
    public boolean usuarioValido(Usuario usuario){
        if(!usuario.getNome().matches("[a-zA-Záàâãéèêíïóôõöúçñ\\s]+")){
            return false;
        }
        if(!usuario.getCpf().matches("[^0-9]+")){
            return false;
        }
        return Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears() >= 18;
    }
}
