package Pool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

import comandos.*;

public class Handler extends Thread {
    private Socket socket;
    private final static Object lock = new Object();

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Random rand = new Random();
        BufferedReader entrada = null;

        try {
            entrada = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        do {
            String text = null;
            System.out.println("loop");
            try {
                text = entrada.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println(text);

            if (text == null) {
                break;
            }

            String[] msg = text.split(" ");

            iComandos comando = null;

            switch (msg[0]) {
                case "new":
                    comando = new New(msg);
                    executar(comando);
                    break;
                case "sleep":
                    comando = new Sleep(msg);
                    if (validar(comando)) {
                        try {
                            Sleep(Integer.parseInt(msg[1]));
                        } catch (NumberFormatException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    break;
                case "notify":
                    System.out.println("Notificando");
                    synchronized (lock) {
                        lock.notifyAll();
                    }
                    break;
                case "wait":
                    comando = new Wait(msg);
                    if (validar(comando))
                        Wait();
                    break;
            }
        } while (!"sair".equals(entrada.toString()));
    }

    public void executar(iComandos comando) {
        if (comando.validar()) {
            comando.executar();
        } else
            System.out.println("Comando inválido.");
    }

    public boolean validar(iComandos comando) {
        if (comando.validar())
            return true;
        System.out.println("Comando inválido.");
        return false;
    }

    public void Sleep(int mills) throws InterruptedException {
        synchronized (lock) {
            sleep(mills);
        }
    }

    public void Wait() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void Notify() {
        synchronized (lock) {
            lock.notify();
        }
    }
}
