package Pool;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteSocket {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String texto = "";

        Socket cliente = null;

        PrintStream saida = null;

        try {
            cliente = new Socket("127.0.0.1", 7000);
            saida = new PrintStream(cliente.getOutputStream());

            do {
                texto = sc.nextLine();
                saida.println(texto);
            } while (!"sair".equals(texto));

        } catch (UnknownHostException e) {
            System.out.println("Algo errado aconteceu");
        } finally {
            cliente.close();
        }
    }
    public void ClientWait() throws InterruptedException {
        Thread.currentThread().wait();
    }

    public void ClientNotifyAll() {
        Thread.currentThread().notifyAll();
    }
}
