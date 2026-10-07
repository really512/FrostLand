package ru.frostland.scoreboard;

import cn.nukkit.Player;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.scoreboard.Scoreboard;
import cn.nukkit.scoreboard.data.DisplaySlot;

public final class FrostScoreboard extends PluginBase {

    @Override
    public void onEnable() {
        getLogger().info("FrostScoreboard включён для PowerNukkitX 2.0.0.");

        new NukkitRunnable() {
            @Override
            public void run() {
                for (Player player : getServer().getOnlinePlayers().values()) {
                    update(player);
                }
            }
        }.runTaskTimer(this, 20, 20);
    }

    private void update(Player player) {
        Scoreboard board = new Scoreboard("frostland-" + player.getName(), "§b§lFROSTLAND");

        String name = player.getName();
        String donation = "Игрок";
        String money = "0 ₽";
        String coords = (int) player.getX() + ", " + (int) player.getY() + ", " + (int) player.getZ();
        int ping = player.getPing();
        int online = getServer().getOnlinePlayers().size();

        board.addLine("§7────────────", 7);
        board.addLine("§fНик: §b" + name, 6);
        board.addLine("§fДонат: §6" + donation, 5);
        board.addLine("§fДеньги: §e" + money, 4);
        board.addLine("§fОнлайн: §a" + online, 3);
        board.addLine("§fПинг: §a" + ping + " ms", 2);
        board.addLine("§fXYZ: §7" + coords, 1);

        board.addViewer(player, DisplaySlot.SIDEBAR);
    }
}
