package pl.AYuPro.Enchanter;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Enchanter extends JavaPlugin {

	public static Economy econ = null;
	private String arg;

	public void onEnable() {

		getConfig().options().copyDefaults(true);
		saveConfig();
		if (!setupEconomy()) {Bukkit.getServer().getLogger().severe(String.format("[%s] - ", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		
		Bukkit.getServer().getLogger().info("EnchanterPlugin by AYuPro Loaded");
	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer()
				.getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

	public void onDisable() {
		Bukkit.getServer().getLogger().info("EnchantedPlugin by AYuPro Unloaded");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
		if (!(sender.hasPermission("enchanter.cmd"))){
			sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав.");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("enchant")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED
						+ getConfig().getString("Данная команда не работает в консоли."));
				return true;
			} else {
				Player pl = (Player) sender;
				try {
					arg = args[0];
				} catch (Exception e) {
					sender.sendMessage(ChatColor.RED + "Не указан тип зачарования");
					return true;
				}
				if (pl.getItemInHand().getType() == Material.AIR) {
					sender.sendMessage(ChatColor.RED + "Для зачарования возьмите предмет в руки.");
					return true;
				}
				switch (arg) {
				
				// Clothes
				case "protect": {
					if (getConfig().getBoolean("Enchantement.protect.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.protect.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, getConfig().getInt("Enchantement.protect.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.protect.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				case "fireprotect": {
					if (getConfig().getBoolean("Enchantement.fireprotect.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.fireprotect.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.PROTECTION_FIRE, getConfig().getInt("Enchantement.fireprotect.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.fireprotect.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				case "blastprotect": {
					if (getConfig().getBoolean("Enchantement.blastprotect.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.blastprotect.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, getConfig().getInt("Enchantement.blastprotect.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.blastprotect.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				case "arrowprotect": {
					if (getConfig().getBoolean("Enchantement.arrowprotect.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.arrowprotect.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.PROTECTION_PROJECTILE, getConfig().getInt("Enchantement.arrowprotect.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.arrowprotect.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				
				//Clothes add
				
				case "feather": {
					if (getConfig().getBoolean("Enchantement.feather.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.feather.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.PROTECTION_FALL, getConfig().getInt("Enchantement.feather.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.feather.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				case "breeding": {
					if (getConfig().getBoolean("Enchantement.breeding.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.breeding.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.OXYGEN, getConfig().getInt("Enchantement.breeding.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.breeding.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				case "water": {
					if (getConfig().getBoolean("Enchantement.water.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.water.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.WATER_WORKER, getConfig().getInt("Enchantement.water.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.water.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				case "thorns": {
					if (getConfig().getBoolean("Enchantement.thorns.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.thorns.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.THORNS, getConfig().getInt("Enchantement.thorns.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.thorns.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				
				//Weapon
				
				case "sharp": {
					if (getConfig().getBoolean("Enchantement.sharp.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.sharp.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.DAMAGE_ALL, getConfig().getInt("Enchantement.sharp.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.sharp.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				case "smite": {
					if (getConfig().getBoolean("Enchantement.smite.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.smite.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.DAMAGE_UNDEAD, getConfig().getInt("Enchantement.smite.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.smite.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				case "spider": {
					if (getConfig().getBoolean("Enchantement.spider.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.spider.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.DAMAGE_ARTHROPODS, getConfig().getInt("Enchantement.spider.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.spider.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				case "knock": {
					if (getConfig().getBoolean("Enchantement.knock.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.knock.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.KNOCKBACK, getConfig().getInt("Enchantement.knock.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.knock.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				case "fireaspect": {
					if (getConfig().getBoolean("Enchantement.fireaspect.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.fireaspect.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.FIRE_ASPECT, getConfig().getInt("Enchantement.fireaspect.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.fireaspect.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				case "looting": {
					if (getConfig().getBoolean("Enchantement.looting.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.looting.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.LOOT_BONUS_MOBS, getConfig().getInt("Enchantement.looting.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.looting.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				
				//Bow
				
				case "power": {
					if (getConfig().getBoolean("Enchantement.power.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.power.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.ARROW_DAMAGE, getConfig().getInt("Enchantement.power.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.power.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				case "punch": {
					if (getConfig().getBoolean("Enchantement.punch.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.punch.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.ARROW_KNOCKBACK, getConfig().getInt("Enchantement.punch.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.punch.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				case "flame": {
					if (getConfig().getBoolean("Enchantement.flame.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.flame.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.ARROW_FIRE, getConfig().getInt("Enchantement.flame.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.flame.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				case "infinity": {
					if (getConfig().getBoolean("Enchantement.infifity.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.infinity.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.ARROW_INFINITE, getConfig().getInt("Enchantement.infinity.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.infinity.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				
				// Tools
				
				case "effect": {
					if (getConfig().getBoolean("Enchantement.effect.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.effect.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.DIG_SPEED, getConfig().getInt("Enchantement.effect.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.effect.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				case "silktouch": {
					if (getConfig().getBoolean("Enchantement.silktouch.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.silktouch.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.SILK_TOUCH, getConfig().getInt("Enchantement.silktouch.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.silktouch.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				case "unbreak": {
					if (getConfig().getBoolean("Enchantement.unbreak.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.unbreak.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.DURABILITY, getConfig().getInt("Enchantement.unbreak.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.unbreak.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				case "luck": {
					if (getConfig().getBoolean("Enchantement.luck.vip")){
						if (!(sender.hasPermission("enchanter.vip"))){
							sender.sendMessage(ChatColor.RED + "У Вас не достаточно прав для этого чара.");
							return true;
						}
					}
					EconomyResponse r = econ.withdrawPlayer(pl.getName(), getConfig().getInt("Enchantement.luck.price"));
					if (r.transactionSuccess()) {
						try {
							pl.getItemInHand().addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, getConfig().getInt("Enchantement.luck.level"));
							sender.sendMessage(ChatColor.GREEN +"Зачарование успешно наложено.");
							return true;
						} catch (Exception e) {
							econ.depositPlayer(pl.getName(), getConfig().getInt("Enchantement.luck.price"));
							sender.sendMessage(ChatColor.RED + "Зачарование отменено, возможно предмет не поддается этой чаровке.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Недостаточно средств на Вашем счету.");
						return true;
					}
				}
				case "help": {
					sender.sendMessage(ChatColor.GREEN + "Доступные чарования - protect, fireprotect, blastprotect, arrowprotect, feather, breeding, water, thorns, sharp, smite, spider, knock, fireaspect, looting, power, punch, flame, infinity, effect, silktouch, unbreak, luck.");
					return true;
				}

				}
				sender.sendMessage(ChatColor.RED + "Неправильно указан тип зачарования.");
				return true;
			}

		}
		return true;
	}
}
