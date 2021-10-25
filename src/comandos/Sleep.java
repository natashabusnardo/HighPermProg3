package comandos;

public class Sleep implements iComandos {

    private String[] comando;

    public Sleep(String[] comando) {
        this.comando = comando;
    }

    @Override
    public boolean validar() {
        if(comando.length == 2) {
            if(isNumeric(comando[1])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void executar() {
    }

    public boolean isNumeric(String str) {
        if(str == null)
            return false;

        try {
            Integer.parseInt(str);
        } catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
