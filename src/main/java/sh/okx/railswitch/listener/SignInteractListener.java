package sh.okx.railswitch.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import sh.okx.railswitch.RailSwitch;
public class SignInteractListener implements Listener {
  private final RailSwitch plugin;

  public SignInteractListener(RailSwitch plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void on(PlayerInteractEvent e) {
    if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
      Block b = e.getClickedBlock();
      if(b.getType() == Material.SIGN_POST || b.getType() == Material.WALL_SIGN) {
        Sign s = (Sign) b.getState();
        for(int i = 0; i < 4; i++) {
          String line = s.getLine(i);
          if(line.toLowerCase().startsWith("/dest ")) {
            Player player = e.getPlayer();
            String dest = line.substring(6);

            if(!dest.isEmpty())
              plugin.setDestination(player, dest);
          } else if(i < 3 && line.toLowerCase().endsWith("/dest")) {
            Player player = e.getPlayer();
            String dest = s.getLine(i + 1);

            if(!dest.isEmpty())
              plugin.setDestination(player, dest);
          }
        }
      }
    }
  }
}
