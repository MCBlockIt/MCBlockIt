package it.mcblock.mcblockit.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Player;

import com.google.gson.Gson;

import it.mcblock.mcblockit.MCBlockItPlugin;
import it.mcblock.mcblockit.api.queue.BanItem;
import it.mcblock.mcblockit.api.queue.Queue;
import it.mcblock.mcblockit.api.queue.QueueItem;
import it.mcblock.mcblockit.api.queue.UnbanItem;
import it.mcblock.mcblockit.api.userdata.UserData;

public class MCBlockItAPI implements Runnable {
    public static final String KICK_REASON = ChatColor.RED + "Banned. " + ChatColor.WHITE + "Appeal at http://appeal.mcblock.it";
    private static MCBlockItAPI instance;

    public static void ban(String name, String admin, String reason) {
        if (MCBlockItAPI.instance != null) {
            MCBlockItAPI.instance.banInstance(name, admin, reason);
        } else {
            throw new MCBlockItNotEnabledException();
        }
    }

    public static boolean enabled() {
        return MCBlockItAPI.instance != null;
    }

    public static String getAPIKey() {
        if (MCBlockItAPI.instance != null) {
            return MCBlockItAPI.instance.APIKey;
        } else {
            throw new MCBlockItNotEnabledException();
        }
    }

    public static UserData getFreshUserData(String username) {
        if (MCBlockItAPI.instance != null) {
            return MCBlockItAPI.instance.getFreshUserDataInstance(username);
        } else {
            throw new MCBlockItNotEnabledException();
        }
    }

    public static UserData getUserData(String username) {
        if (MCBlockItAPI.instance != null) {
            return MCBlockItAPI.instance.getUserDataInstance(username);
        } else {
            throw new MCBlockItNotEnabledException();
        }
    }

    public static void initialize(MCBlockItAPI api) {
        MCBlockItAPI.instance = api;
    }

    public static void unban(String name) {
        if (MCBlockItAPI.instance != null) {
            MCBlockItAPI.instance.unbanInstance(name);
        } else {
            throw new MCBlockItNotEnabledException();
        }
    }

    private final Queue queue;

    private final String APIKey;

    private final MCBlockItPlugin plugin;

    private final String URL = "http://api.mcblock.it/";

    private final String banURL = this.URL + "ban";

    private final String unbanURL = this.URL + "unban";
    private final String userdataURL = this.URL + "userdata/";
    private final Gson gson;

    public MCBlockItAPI(MCBlockItPlugin plugin, String APIKey) {
        this.APIKey = APIKey;
        this.plugin = plugin;
        MCBlockItAPI.initialize(this);
        this.queue = new Queue();
        this.gson = new Gson();
    }

    @Override
    public void run() {
        try {
            QueueItem item;
            while (true) {
                item = this.queue.peek();
                if (item != null) {
                    if (this.process(item)) {
                        this.queue.remove(item);
                    }
                }
                Thread.sleep(1000);
            }
        } catch (final InterruptedException e) {
            //My work here is done.
        }
    }

    private void banInstance(final String name, String admin, String reason) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                ((CraftServer) Bukkit.getServer()).getHandle().addUserBan(name.toLowerCase());
                final OfflinePlayer player = Bukkit.getOfflinePlayer(name);
                if (player.isOnline()) {
                    ((Player) player).kickPlayer(MCBlockItAPI.KICK_REASON);
                }
            }
        });
        this.queue.add(new BanItem(name, admin, reason));
    }

    private UserData getFreshUserDataInstance(String username) {
        final StringBuilder response = new StringBuilder();
        try {
            final URL urlTarget = new URL(this.userdataURL + username);
            final URLConnection connection = urlTarget.openConnection();
            connection.setDoOutput(true);
            connection.setConnectTimeout(6000);
            connection.setReadTimeout(9000);
            connection.setRequestProperty("User-agent", "MCBlockIt");
            final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
        } catch (final Exception e) {
            System.out.println("Unexpected failure to call MCBlockIt");
            e.printStackTrace();
            return null;
        }
        return gson.fromJson(response.toString(),UserData.class);
    }

    private UserData getUserDataInstance(String username) {
        //if(cached){return cached}
        return this.getFreshUserDataInstance(username);
    }

    private boolean process(QueueItem item) {
        String url = null;
        if (item instanceof BanItem) {
            url = this.banURL;
        } else if (item instanceof UnbanItem) {
            url = this.unbanURL;
        } else {
            return true;//Dump whatever this is.
        }
        String POST = "API=" + this.APIKey + "&data=" + this.gson.toJson(item);
        final StringBuilder response = new StringBuilder();
        try {
            POST = URLEncoder.encode(POST, "UTF-8");
        } catch (final UnsupportedEncodingException e) {
            // Apparently hates UTF-8
        }
        //Time to send to the API!
        try {
            final URL urlTarget = new URL(url);
            final URLConnection connection = urlTarget.openConnection();
            connection.setDoOutput(true);
            connection.setConnectTimeout(6000);
            connection.setReadTimeout(9000);
            connection.setRequestProperty("User-agent", "MCBlockIt");
            final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(POST);
            writer.flush();

            final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            writer.close();
            reader.close();
        } catch (final Exception e) {
            System.out.println("Unexpected failure to call MCBlockIt");
            e.printStackTrace();
            return false;
        }

        //TODO Process reply
        return false;
    }

    private void unbanInstance(final String name) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                ((CraftServer) Bukkit.getServer()).getHandle().removeUserBan(name.toLowerCase());
            }
        });
        this.queue.add(new UnbanItem(name));
    }
}
