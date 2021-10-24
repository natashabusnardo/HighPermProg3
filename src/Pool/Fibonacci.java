package Pool;

import java.util.LinkedList;

public class Fibonacci implements Runnable {
    private String nome;
    private int fib;
    private final int capacity = 10;
    private static LinkedList<Integer> buffer = new LinkedList<Integer>();

    public Fibonacci(String nome, int num) {
        this.nome = nome;
        this.fib = FibonacciN(num);
    }

    public LinkedList<Integer> getBuffer() {
        return buffer;
    }

    public void run() {
        add(this.fib);
    }

    public int FibonacciN(int n) {
        if (n <= 1)
            return n;
        return FibonacciN(n - 1) + FibonacciN(n - 2);
    }

    public synchronized void add(int value) {
        boolean b = true;
        synchronized(buffer) {
            while (b) {
                if(buffer.size() < capacity) {
                    buffer.add(value);
                    System.out.println("Adicionado:  " + value);
                    notify();
                    b = false;
                }
                else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public synchronized int remove() {

        int value = buffer.poll();
        System.out.println("Removido: " + value);
        return value;
    }

}
