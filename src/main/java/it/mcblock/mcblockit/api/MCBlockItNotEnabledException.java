package it.mcblock.mcblockit.api;

public class MCBlockItNotEnabledException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MCBlockItNotEnabledException() {
        super("MCBlockIt is not enabled!");
    }

}
