package comandos;

public class Sleep implements iComandos {

    private String[] comando;

    public Sleep(String[] comando) {
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
