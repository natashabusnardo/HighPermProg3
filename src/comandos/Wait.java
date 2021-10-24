package comandos;

public class Wait implements iComandos{
    public String[] comando;

    public Wait(String[] comando) {
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
