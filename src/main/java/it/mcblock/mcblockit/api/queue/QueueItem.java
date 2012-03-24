package it.mcblock.mcblockit.api.queue;

import java.util.Date;

public abstract class QueueItem implements Comparable<QueueItem> {
    private final long localtimestamp;

    public QueueItem() {
        this.localtimestamp = (new Date()).getTime();
    }

    @Override
    public int compareTo(QueueItem other) {
        return (int) (this.localtimestamp - other.localtimestamp);
    }
}
