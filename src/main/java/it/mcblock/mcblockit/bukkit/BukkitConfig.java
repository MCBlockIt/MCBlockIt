package it.mcblock.mcblockit.bukkit;

import it.mcblock.mcblockit.api.MCBIConfig;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.Configuration;

public class BukkitConfig implements MCBIConfig {

    private final int banRestriction;
    private List<String> flagRestriction;
    private final double reputationRestriction;
    private final boolean banRestrictionEnabled;
    private final boolean flagRestrictionEnabled;
    private final boolean reputationRestrictionEnabled;
    private int timeout;

    public BukkitConfig(Configuration config) {
        this.banRestrictionEnabled = config.getBoolean("restriction.bans.enable", false);
        this.banRestriction = config.getInt("restrictions.bans.value", 5);
        this.flagRestrictionEnabled = config.getBoolean("restriction.flags.enable", false);
        this.flagRestriction = config.getStringList("restrictions.flags.value");
        if (this.flagRestriction == null) {
            this.flagRestriction = new ArrayList<String>();
        }
        this.reputationRestrictionEnabled = config.getBoolean("restriction.reputation.enable", true);
        this.reputationRestriction = config.getDouble("restriction.reputation.value", 0.0D);
        this.timeout = config.getInt("settings.timeout", 5);
        if (this.timeout < 5) {
            this.timeout = 5;
        }
    }

    @Override
    public int getBanRestriction() {
        return this.banRestriction;
    }

    @Override
    public int getCacheTimeout() {
        return this.timeout;
    }

    @Override
    public List<String> getFlagRestriction() {
        return this.flagRestriction;
    }

    @Override
    public double getReputationRestriction() {
        return this.reputationRestriction;
    }

    @Override
    public boolean isBanRestrictionEnabled() {
        return this.banRestrictionEnabled;
    }

    @Override
    public boolean isFlagRestrictionEnabled() {
        return this.flagRestrictionEnabled;
    }

    @Override
    public boolean isReputationRestrictionEnabled() {
        return this.reputationRestrictionEnabled;
    }

}
