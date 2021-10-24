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
            System.out.println("do/while loop");
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
                    execute(comando);
                    break;
                case "sleep":
                    comando = new Sleep(msg);
                    execute(comando);
                    break;
                case "wait":
                    synchronized (lock) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    break;
                case "notify":
                    //command = new CommandNotify(msg);
                    System.out.println("notifying");
                    synchronized (lock) {
                        lock.notifyAll();
                    }
                    break;
            }

        } while (!"sair".equals(entrada.toString()));
    }

    public void execute(iComandos comando) {
        if (comando.validar()) {
            comando.executar();
        } else
            System.out.println("Invalid command");
    }

    public void clientWait() throws InterruptedException {
        lock.wait();
    }

    public void clientNotify() {
        lock.notifyAll();
    }
}
