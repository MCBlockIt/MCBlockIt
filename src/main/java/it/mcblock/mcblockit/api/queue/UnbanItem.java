package it.mcblock.mcblockit.api.queue;

public class UnbanItem extends QueueItem {
    public static UnbanItem loadData(String[] args) {
        if (args.length < 2) {
            return null;
        }
        return new UnbanItem(args[1]);
    }

    @SuppressWarnings("unused")
    private final String name;

    public UnbanItem(String name) {
        this.name = name;
    }

}
