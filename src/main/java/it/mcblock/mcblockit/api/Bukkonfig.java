package it.mcblock.mcblockit.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.Configuration;

public class Bukkonfig implements MCBIConfig{
    
    private int banRestriction;
    private List<String> flagRestriction;
    private double reputationRestriction;
    private boolean banRestrictionEnabled;
    private boolean flagRestrictionEnabled;
    private boolean reputationRestrictionEnabled;
    
    public Bukkonfig(Configuration config){
        this.banRestrictionEnabled=config.getBoolean("restriction.bans.enable", false);
        this.banRestriction=config.getInt("restrictions.bans.value",5);
        this.flagRestrictionEnabled=config.getBoolean("restriction.flags.enable",false);
        this.flagRestriction=config.getStringList("restrictions.flags.value");
        if(flagRestriction==null){
            flagRestriction=new ArrayList<String>();
        }
        this.reputationRestrictionEnabled=config.getBoolean("restriction.reputation.enable",true);
        this.reputationRestriction=config.getDouble("restriction.reputation.value",0.0D);
    }

    @Override
    public int getBanRestriction() {
        return this.banRestriction;
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
