package ru.frostland.teleport;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class FrostTeleport extends PluginBase {

    private Config homes;
    private final Map<UUID, UUID> tpaRequests = new HashMap<>();

    private static final int RTP_RADIUS = 5000;
    private static final int RTP_ATTEMPTS = 20;

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        homes = new Config(
                new File(getDataFolder(), "homes.yml"),
                Config.YAML
        );

        getLogger().info("FrostTeleport включён.");
        getLogger().info("Команды: /home /sethome /delhome /rtp /tpa /tpaccept /tp");
    }

    @Override
    public void onDisable() {
        if (homes != null) {
            homes.save();
        }
        tpaRequests.clear();
        getLogger().info("FrostTeleport выключен.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String name = command.getName().toLowerCase();

        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cЭту команду можно использовать только в игре.");
            return true;
        }

        switch (name) {
            case "sethome" -> setHome(player, args);
            case "home" -> goHome(player, args);
            case "delhome" -> deleteHome(player, args);
            case "rtp" -> randomTeleport(player);
            case "tpa" -> requestTeleport(player, args);
            case "tpaccept" -> acceptTeleport(player);
            case "tp" -> teleportToPlayer(player, args);
            default -> {
                return false;
            }
        }
        return true;
    }

    private String homeKey(Player player, String homeName) {
        return player.getUniqueId().toString() + "." + homeName.toLowerCase();
    }

    private void setHome(Player player, String[] args) {
        String name = args.length == 0 ? "home" : args[0].toLowerCase();

        if (!name.matches("[a-z0-9_-]{1,16}")) {
            player.sendMessage("§cИмя дома: только латиница, цифры, _ и -, максимум 16 символов.");
            return;
        }

        String key = homeKey(player, name);
        Map<String, Object> data = new HashMap<>();
        data.put("world", player.getLevel().getName());
        data.put("x", player.getX());
        data.put("y", player.getY());
        data.put("z", player.getZ());
        data.put("yaw", player.getYaw());
        data.put("pitch", player.getPitch());

        homes.set(key, data);
        homes.save();

        player.sendMessage("§aДом §e" + name + " §aустановлен.");
    }

    @SuppressWarnings("unchecked")
    private void goHome(Player player, String[] args) {
        String name = args.length == 0 ? "home" : args[0].toLowerCase();
        String key = homeKey(player, name);

        Object raw = homes.get(key);
        if (!(raw instanceof Map<?, ?> data)) {
            player.sendMessage("§cДом §e" + name + " §cне найден.");
            return;
        }

        String worldName = String.valueOf(data.get("world"));
        Level level = getServer().getLevelByName(worldName);

        if (level == null) {
            player.sendMessage("§cМир дома не загружен: §e" + worldName);
            return;
        }

        double x = number(data.get("x"));
        double y = number(data.get("y"));
        double z = number(data.get("z"));
        float yaw = (float) number(data.get("yaw"));
        float pitch = (float) number(data.get("pitch"));

        player.teleport(new Position(x, y, z, level), yaw, pitch);
        player.sendMessage("§aТелепортация домой: §e" + name);
    }

    private void deleteHome(Player player, String[] args) {
        String name = args.length == 0 ? "home" : args[0].toLowerCase();
        String key = homeKey(player, name);

        if (homes.get(key) == null) {
            player.sendMessage("§cДом §e" + name + " §cне найден.");
            return;
        }

        homes.remove(key);
        homes.save();
        player.sendMessage("§aДом §e" + name + " §aудалён.");
    }

    private void randomTeleport(Player player) {
        Level level = player.getLevel();

        for (int attempt = 0; attempt < RTP_ATTEMPTS; attempt++) {
            int x = ThreadLocalRandom.current().nextInt(-RTP_RADIUS, RTP_RADIUS + 1);
            int z = ThreadLocalRandom.current().nextInt(-RTP_RADIUS, RTP_RADIUS + 1);

            int y = level.getHighestBlockAt(x, z) + 1;

            if (y < level.getMinHeight() || y > level.getMaxHeight()) {
                continue;
            }

            Position destination = new Position(x + 0.5, y, z + 0.5, level);
            player.teleport(destination);
            player.sendMessage("§bRTP §7» §aВы телепортированы на §e" + x + " " + y + " " + z + "§a.");
            return;
        }

        player.sendMessage("§cНе удалось найти безопасное место для RTP. Попробуйте ещё раз.");
    }

    private void requestTeleport(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage("§cИспользование: §e/tpa <игрок>");
            return;
        }

        Player target = findPlayer(args[0]);

        if (target == null) {
            player.sendMessage("§cИгрок §e" + args[0] + " §cне найден.");
            return;
        }

        if (target.getUniqueId().equals(player.getUniqueId())) {
            player.sendMessage("§cНельзя отправить запрос самому себе.");
            return;
        }

        tpaRequests.put(target.getUniqueId(), player.getUniqueId());

        player.sendMessage("§aЗапрос на телепортацию отправлен игроку §e" + target.getName() + "§a.");
        target.sendMessage("§b" + player.getName() + " §fхочет телепортироваться к вам.");
        target.sendMessage("§aИспользуйте §e/tpaccept §aдля принятия.");
    }

    private void acceptTeleport(Player target) {
        UUID requesterId = tpaRequests.remove(target.getUniqueId());

        if (requesterId == null) {
            target.sendMessage("§cУ вас нет входящих запросов на телепортацию.");
            return;
        }

        Player requester = getServer().getPlayer(requesterId);

        if (requester == null || !requester.isOnline()) {
            target.sendMessage("§cИгрок, отправивший запрос, уже вышел.");
            return;
        }

        requester.teleport(target.getPosition());
        requester.sendMessage("§aЗапрос принят. Вы телепортированы к §e" + target.getName() + "§a.");
        target.sendMessage("§aТелепортация игрока §e" + requester.getName() + " §aвыполнена.");
    }

    private void teleportToPlayer(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage("§cИспользование: §e/tp <игрок>");
            return;
        }

        Player target = findPlayer(args[0]);

        if (target == null) {
            player.sendMessage("§cИгрок §e" + args[0] + " §cне найден.");
            return;
        }

        player.teleport(target.getPosition());
        player.sendMessage("§aВы телепортированы к §e" + target.getName() + "§a.");
    }

    private Player findPlayer(String name) {
        for (Player online : getServer().getOnlinePlayers().values()) {
            if (online.getName().equalsIgnoreCase(name)) {
                return online;
            }
        }
        return null;
    }

    private double number(Object value) {
        if (value instanceof Number number) {
            return number.doubleValue();
        }
        return Double.parseDouble(String.valueOf(value));
    }
}
