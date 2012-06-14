package it.mcblock.mcblockit.bukkit;

import it.mcblock.mcblockit.api.MCBIConfig;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.Configuration;

/**
 * A Bukkit implementation of MCBlockIt API
 * 
 * @author Matt Baxter
 * 
 *         Copyright 2012 Matt Baxter
 * 
 *         Licensed under the Apache License, Version 2.0 (the "License");
 *         you may not use this file except in compliance with the License.
 *         You may obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *         See the License for the specific language governing permissions and
 *         limitations under the License.
 * 
 */
public class BukkitConfig implements MCBIConfig {

    private final String defaultBanReason;
    private final int banRestriction;
    private List<String> flagRestriction;
    private final double reputationRestriction;
    private final boolean banRestrictionEnabled;
    private final boolean flagRestrictionEnabled;
    private final boolean reputationRestrictionEnabled;
    private final boolean userIPRecordingEnabled;
    private final boolean loginNotificationEnabled;
    private final boolean autoRollbackEnabled;

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
        this.userIPRecordingEnabled = config.getBoolean("settings.useriprecording", true);
        this.timeout = config.getInt("settings.timeout", 5);
        this.defaultBanReason = config.getString("setting.defaultreason", "You have been banned!");
        this.loginNotificationEnabled = config.getBoolean("settings.loginnotification", true);
        this.autoRollbackEnabled = config.getBoolean("settings.logblock", false);
        if (this.timeout < 5) {
            this.timeout = 5;
        }
    }

    @Override
    public String getDefaultBanReason() {
        return this.defaultBanReason;
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

    @Override
    public boolean isUserIPRecordingEnabled() {
        return this.userIPRecordingEnabled;
    }

    @Override
    public boolean isLoginNotificationEnabled() {
        return this.loginNotificationEnabled;
    }

    public boolean isAutoRollbackEnabled () {
        return this.autoRollbackEnabled;
    }

}
