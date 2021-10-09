import javax.swing.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class Principal {
    public static void main(String[] args) {

        String ano_que_o_cidadão_nasceu = JOptionPane.showInputDialog("Ano que o cidadão nasceu\nexemplo.: 1980");

        String[] options = {"venceu ano passado", "vence nesse ano (não vencido)"};
        int x = JOptionPane.showOptionDialog(null,
                "quando vence o produto ?",
                "escolha",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);


        int ano = 2021 - x;

        Usuario usuario = new Usuario(LocalDate.of(Integer.parseInt(ano_que_o_cidadão_nasceu), Month.MARCH, 14));
        Produtos produtos = new Produtos(LocalDate.of(ano, Month.MARCH, 14));

        boolean validador = validador(usuario);
        boolean validadeDoProduto = validador(produtos);
        String texto = validador ? "Pode entrar na balada"+(validadeDoProduto ? "mas vai tomar coisa vencida" : "legal!! tudo novo") : "Epa... fica de fora";

        JOptionPane.showMessageDialog(null, texto);

    }

    public static <T> boolean validador(T objeto) {
        Class<?> classe = objeto.getClass();
        for (Field field : classe.getDeclaredFields()) {
            if (field.isAnnotationPresent(Anos.class)) {
                Anos idadeMinima = field.getAnnotation(Anos.class);
                try {
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

    public static <T> boolean validadeDoProduto(T objeto) {
        Class<?> classe = objeto.getClass();
        for (Field field : classe.getDeclaredFields()) {
            if (field.isAnnotationPresent(Anos.class)) {
                Anos anoQueVence = field.getAnnotation(Anos.class);
                try {
                    field.setAccessible(true);
                    LocalDate ano = (LocalDate) field.get(objeto);
                    return Period.between(ano, LocalDate.now()).getYears() >= anoQueVence.valor();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
