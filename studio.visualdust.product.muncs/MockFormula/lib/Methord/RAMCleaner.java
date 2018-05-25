package MockFormula.lib.Methord;

public class RAMCleaner {
    public RAMCleaner() {
        cleaner cls = new cleaner();
        cls.start();
    }

    public class cleaner extends Thread {
        @Override
        public void run() {
            while (true) {
                System.gc();
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    Exceptionoutput.outputException(e);
                }
            }
        }
    }
}
