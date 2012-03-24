package it.mcblock.mcblockit.api.queue;

@SuppressWarnings("unused")
public class BanItem extends QueueItem {
    private final String name;
    private final String reason;
    private final String admin;

    public BanItem(String name, String admin, String reason) {
        this.name = name;
        this.admin = admin;
        this.reason = reason;
    }

}
