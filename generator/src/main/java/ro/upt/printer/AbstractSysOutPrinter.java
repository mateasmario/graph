package ro.upt.printer;

public abstract class AbstractSysOutPrinter implements Printer {
    @Override
    public void printMessage(String message) {
        System.out.println(getHeader() + " " + message);
    }

    public abstract String getHeader();
}
