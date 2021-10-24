package comandos;

public class Notify implements iComandos{
    private String[] comando;

    public Notify(String[] comando) {
        this.comando = comando;
    }

    @Override
    public boolean validar() {
        return false;
    }

    @Override
    public void executar() {
    }
}
