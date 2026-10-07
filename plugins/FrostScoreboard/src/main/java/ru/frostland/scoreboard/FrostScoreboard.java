package ru.frostland.scoreboard;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.scoreboard.Scoreboard;
import cn.nukkit.scoreboard.ScoreboardDisplaySlot;
import cn.nukkit.scoreboard.ScoreboardLine;
import cn.nukkit.Player;

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
        Scoreboard board = new Scoreboard(
                "frostland",
                "§b§lFROSTLAND",
                ScoreboardDisplaySlot.SIDEBAR
        );

        String name = player.getName();
        String donation = "Игрок";
        String money = "0 ₽";
        String coords = (int) player.getX() + ", " + (int) player.getY() + ", " + (int) player.getZ();
        int ping = player.getPing();
        int online = getServer().getOnlinePlayers().size();

        board.setLine(7, new ScoreboardLine(7, "§7────────────"));
        board.setLine(6, new ScoreboardLine(6, "§fНик: §b" + name));
        board.setLine(5, new ScoreboardLine(5, "§fДонат: §6" + donation));
        board.setLine(4, new ScoreboardLine(4, "§fДеньги: §e" + money));
        board.setLine(3, new ScoreboardLine(3, "§fОнлайн: §a" + online));
        board.setLine(2, new ScoreboardLine(2, "§fПинг: §a" + ping + " ms"));
        board.setLine(1, new ScoreboardLine(1, "§fXYZ: §7" + coords));

        player.setScoreboard(board);
    }
}
