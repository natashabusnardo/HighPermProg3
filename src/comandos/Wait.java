package comandos;

public class Wait implements iComandos{
    public String[] comando;

    public Wait(String[] comando) {
        this.comando = comando;
    }

    @Override
    public boolean validar() {
        if(comando.length == 1)
            return true;
        return false;
    }

    @Override
    public void executar() {
    }
}
