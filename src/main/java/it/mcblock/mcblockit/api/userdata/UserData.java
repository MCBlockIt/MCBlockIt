package it.mcblock.mcblockit.api.userdata;

public class UserData {
    public class BanData {
        private String server;
        private long timestamp;
        private String admin;
        private String reason;

        public String getAdmin() {
            return this.admin;
        }

        public String getReason() {
            return this.reason;
        }

        public String getServer() {
            return this.server;
        }

        public long getTimestamp() {
            return this.timestamp;
        }
    }

    private String username;
    private double reputation;
    private String[] flags;

    private BanData[] bans;

    public BanData[] getBans() {
        return this.bans;
    }

    public String[] getFlags() {
        return this.flags;
    }

    public double getReputation() {
        return this.reputation;
    }

    public String getUsername() {
        return this.username;
    }
}
