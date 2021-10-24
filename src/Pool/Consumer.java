package Pool;

import java.util.ArrayList;

public class Consumer {
    private ArrayList<Integer> list = new ArrayList<Integer>();
    private Fibonacci fibonacci;

    public Consumer(Fibonacci fibonacci) {
        this.fibonacci = fibonacci;
    }

    public void setFibThread(Fibonacci fibonacci) {
        this.fibonacci = fibonacci;
    }

    public void run() {
        synchronized (fibonacci) {
            boolean b = true;

            while (b) {
                if (this.fibonacci.getBuffer().size() > 0) {
                    this.list.add(fibonacci.remove());
                    b = false;
                } else {
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
}
