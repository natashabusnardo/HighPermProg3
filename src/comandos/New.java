package comandos;

import Pool.Fibonacci;

import java.util.Arrays;
import java.util.Random;

public class New implements iComandos {
    private String[] comando;
    private String[] acao = {"fib"};
    private Random random = new Random();

    public New(String[] comando) {
        this.comando = comando;
    }

    @Override
    public boolean validar() {
        if (comando.length == 4) {
            if (Arrays.asList(this.acao).contains(comando[1]) && isNumeric(comando[2]) && !comando[3].isBlank())
                return true;
        }
        return false;
    }

    @Override
    public void executar() {
        if(this.comando[1].equals("fib")) {
            new Thread(new Fibonacci(this.comando[3], Integer.parseInt(comando[2]))).start();
        }
    }

    public boolean isNumeric(String str) {
        if (str == null)
            return false;

        try {
            int n = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
