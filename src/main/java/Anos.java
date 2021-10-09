import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) //@Target - Aqui passaremos os elementos que podem ser anotados com essa anotação.
@Retention(RetentionPolicy.RUNTIME) //Aqui nós falaremos para a nossa aplicação até quando nossa anotação estará disponível.
public @interface Anos {
    int valor();
}
