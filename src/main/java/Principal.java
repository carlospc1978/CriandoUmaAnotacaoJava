import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class Principal {
    public static void main(String[] args) {
        Usuario usuario = new Usuario( LocalDate.of(2011, Month.MARCH, 14));

        boolean validador = validador(usuario);
        String texto = validador?"Pode entrar na balada":"Epa... fica de fora";


        System.out.println(texto);
    }

    public static <T> boolean validador(T objeto) {
        Class<?> classe = objeto.getClass();
        for (Field field : classe.getDeclaredFields()) {
            if (field.isAnnotationPresent(IdadeMinima.class)) {
                IdadeMinima idadeMinima = field.getAnnotation(IdadeMinima.class);
                try{
                    field.setAccessible(true);
                    LocalDate dataNascimento = (LocalDate) field.get(objeto);
                    return Period.between(dataNascimento, LocalDate.now()).getYears() >= idadeMinima.valor();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
