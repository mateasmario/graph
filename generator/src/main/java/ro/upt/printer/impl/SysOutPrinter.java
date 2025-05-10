package ro.upt.printer.impl;

import ro.upt.printer.AbstractSysOutPrinter;

public class SysOutPrinter extends AbstractSysOutPrinter {
    @Override
    public String getHeader() {
        return "graph:";
    }
}
