package com.windows.ABH;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class ABH extends JavaPlugin implements Listener {
	
	String prefix = "§e[WINDOWS] §b";
	private final String USER_AGENT = "Mozilla/5.0";
	private String Address = "http://ipip.kr";
	private URL Url;
	private BufferedReader br;
	private HttpURLConnection con;
	private String protocol = "GET";
	private String IP = null;
	private String Domain = "";
	public Economy econ = null;
	
	  public void onEnable()
	  {
			try {
				Url = new URL(this.Address);
			} catch (MalformedURLException e) {
			}
			try {
				con = (HttpURLConnection)Url.openConnection();
			} catch (IOException e) {
			}
			try {
				con.setRequestMethod(protocol);
			} catch (ProtocolException e) {
			}
			con.setRequestProperty("USER-Agent", USER_AGENT);
			try {
				br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			} catch (IOException e) {
			}
			String line;
			String ip = null;
			try {
				while((line = br.readLine())!= null){
				if (line.startsWith("<title>Your IP is ")){
					ip = line.replaceAll("Your IP is ", "").replaceAll("<title>", "").replaceAll("</title>", "");
				}
				}
			} catch (NullPointerException e1) {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Auto Buy Home ]");
  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "인터넷 연결 상태가 올바르지 않습니다.");
  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
  				try {
					Thread.sleep(10000L);
				} catch (InterruptedException e) {
				}
  				Bukkit.shutdown();
			} catch (IOException e) {
			}
			try {
				br.close();
			} catch (IOException e) {
			}
	  	      try {
		  	        IP = InetAddress.getAllByName(Domain)[0].getHostAddress();
		  	        if (!ip.equalsIgnoreCase(IP)) {
		  	        	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Auto Buy Home ]");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "구매자 도메인의 아이피와 일치하지 않습니다.");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
		  				Thread.sleep(10000L);
		  				Bukkit.shutdown();
		  	        }
		  	      }
		  	      catch (UnknownHostException e1) {
			  	    	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Auto Buy Home ]");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "구매 시 등록했던 도메인이 유효하지 않습니다.");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
		  				try {
							Thread.sleep(10000L);
						} catch (InterruptedException e) {
						}
		  				Bukkit.shutdown();
		  	      } catch (InterruptedException e) {
				}
			  BufferedInputStream in = null;
				String strUrl = "http://cafe.naver.com/MemoList.nhn?search.clubid=27833593&search.menuid=5";
				StringBuffer sb = new StringBuffer();
				
				try {
					URL url = new URL(strUrl);
					URLConnection urlConnection = url.openConnection();
					in = new BufferedInputStream(urlConnection.getInputStream());
					
					byte[] bufRead = new byte[40960];
					int lenRead = 0;
					while ((lenRead = in.read(bufRead)) > 0)
						sb.append(new String(bufRead, 0, lenRead));

				} catch (IOException ioe) {}
				if (sb.toString().contains("[" + Domain + "]")) {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Auto Buy Home ]");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "해당 플러그인은 현재 차단된 상태이므로 사용하실 수 없습니다.");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
				try {
					Thread.sleep(10000L);
				} catch (InterruptedException e1) {
				}
				Bukkit.shutdown();
				return;
				}
			if (Bukkit.getPluginManager().getPlugin("Lockette") == null) {
				Bukkit.getConsoleSender().sendMessage("§e[WINDOWS] §cVault 플러그인을 찾을 수 없습니다! 플러그인을 강제 종료합니다.");
				getServer().getPluginManager().disablePlugin(this);
				try {
					Thread.sleep(5000L);
				} catch (InterruptedException e) {
				}
				return;
			}
			if (Bukkit.getPluginManager().getPlugin("Lockette") == null) {
				Bukkit.getConsoleSender().sendMessage("§e[WINDOWS] §cLockette(창고잠금) 플러그인을 찾을 수 없습니다! 플러그인을 강제 종료합니다.");
				getServer().getPluginManager().disablePlugin(this);
				try {
					Thread.sleep(5000L);
				} catch (InterruptedException e) {
				}
				return;
			}
			setupEconomy();
		getServer().getPluginManager().registerEvents(this, this);
	    Bukkit.getConsoleSender().sendMessage("§e[WINDOWS] §a자동 집구매 플러그인 활성화");
	  }
	  
	  public void onDisable() {
		  Bukkit.getConsoleSender().sendMessage("§e[WINDOWS] §c자동 집구매 플러그인 비활성화");
	  }
	  
	  public void DecompileProtect() {
		  ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));
		  list.stream().filter((Integer num) -> num % 2 == 0);
	  }
	  
	  private boolean setupEconomy() {
			if (getServer().getPluginManager().getPlugin("Vault") == null) {
				return false;
			}
			RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
			if (rsp == null) {
				return false;
			}
			econ = rsp.getProvider();
			return econ != null;
		}
	  
	  @EventHandler(priority=EventPriority.HIGHEST)
		private void onJoin(PlayerLoginEvent event) {
		  BufferedInputStream in = null;
			String strUrl = "http://cafe.naver.com/MemoList.nhn?search.clubid=27833593&search.menuid=5";
			StringBuffer sb = new StringBuffer();
			
			try {
				URL url = new URL(strUrl);
				URLConnection urlConnection = url.openConnection();
				in = new BufferedInputStream(urlConnection.getInputStream());
				
				byte[] bufRead = new byte[40960];
				int lenRead = 0;
				while ((lenRead = in.read(bufRead)) > 0)
					sb.append(new String(bufRead, 0, lenRead));

			} catch (IOException ioe) {}
			if (sb.toString().contains("[" + Domain + "]")) {
				event.disallow(null, "§4[WINDOWS]\n§e자동집구매 플러그인이 제작자에 의해 차단되었습니다.");
				for (Player p : Bukkit.getOnlinePlayers()) {
				p.kickPlayer("§4[WINDOWS]\n§e자동집구매 플러그인이 제작자에 의해 차단된 상태입니다.\n차단이 풀리기전까지 해당 플러그인을 사용할 수 없습니다.");
				}
				Bukkit.shutdown();
				} else if (sb.toString().contains("[" + event.getPlayer().getName().toLowerCase() + "]")) {
				event.disallow(null, "§4[WINDOWS] §e해당 아이디는 블랙리스트에 등록된 아이디입니다.");
				}
			try {
				Url = new URL(this.Address);
			} catch (MalformedURLException e) {
			}
			try {
				con = (HttpURLConnection)Url.openConnection();
			} catch (IOException e) {
			}
			try {
				con.setRequestMethod(protocol);
			} catch (ProtocolException e) {
			}
			con.setRequestProperty("USER-Agent", USER_AGENT);
			try {
				br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			} catch (IOException e) {
			}
			String line;
			String ip = null;
			try {
				while((line = br.readLine())!= null){
				if (line.startsWith("<title>Your IP is ")){
					ip = line.replaceAll("Your IP is ", "").replaceAll("<title>", "").replaceAll("</title>", "");
				}
				}
			} catch (IOException e) {
			}
			try {
				br.close();
			} catch (IOException e) {
			}
	  	      try {
		  	        IP = InetAddress.getAllByName(Domain)[0].getHostAddress();
		  	        if (!ip.equalsIgnoreCase(IP)) {
		  	        	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Auto Buy Home ]");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "구매자 도메인의 아이피와 일치하지 않습니다.");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
		  				Thread.sleep(10000L);
		  				Bukkit.shutdown();
		  	        }
		  	      }
		  	      catch (UnknownHostException e1) {
			  	    	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Auto Buy Home ]");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "구매 시 등록했던 도메인이 유효하지 않습니다.");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
		  				try {
							Thread.sleep(10000L);
						} catch (InterruptedException e) {
						}
		  				Bukkit.shutdown();
		  	      } catch (InterruptedException e) {
				}
		}
	  
	  @EventHandler(priority=EventPriority.HIGHEST)
	  public void onClick(PlayerInteractEvent event) {
		  if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.WALL_SIGN) {
			  if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
				  return;
			  }
			  Player player = event.getPlayer();
			  Sign sign = (Sign)event.getClickedBlock().getState();
			  String line1 = sign.getLine(0);
			  String line2 = sign.getLine(1);
			  String line3 = sign.getLine(2);
			  //String line4 = sign.getLine(3);
			  if (line1.equals("[자동집구매]") && !line2.equals("")) {
				  if (!econ.has(player.getName(), Double.parseDouble(line2))) {
					  event.setCancelled(true);
					  player.sendMessage(prefix + "§c당신은 돈이 부족합니다.");
					  return;
				  }
				  if (line3.equals("창고")) {
					  econ.withdrawPlayer(player.getName(), Double.parseDouble(line2));
					  player.sendMessage(prefix + "창고가 정상적으로 구매되었습니다.");
					  saveConfig();
					  sign.setLine(0, "[private]");
					  sign.setLine(1, player.getName());
					  sign.setLine(2, "");
					  sign.update();
					  event.setCancelled(true);
					  return;
				  }
				  if (getConfig().contains(player.getName().toLowerCase())) {
					  event.setCancelled(true);
					  player.sendMessage(prefix + "§c당신은 이미 집을 소유하고 있습니다.");
					  return;
				  }
				  econ.withdrawPlayer(player.getName(), Double.parseDouble(line2));
				  player.sendMessage(prefix + "집이 정상적으로 구매되었습니다.");
				  getConfig().set(player.getName().toLowerCase(), "구매완료");
				  saveConfig();
				  sign.setLine(0, "[private]");
				  sign.setLine(1, player.getName());
				  sign.setLine(2, "");
				  sign.update();
				  event.setCancelled(true);
				  return;
			  }
			  if (line1.equals("자동집구매")) {
				  if (line2.equals("")) {
					  event.setCancelled(true);
					  player.sendMessage(prefix + "§c가격이 설정되어있지 않습니다. 가격을 설정해주세요.");
					  return;
				  }
				  if (line3.equals("")) {
					  event.setCancelled(true);
					  player.sendMessage(prefix + "§c집 정보가 설정되어있지 않습니다. 집 정보를 설정해주세요.");
					  return;
				  }
			  }
		  }
	  }
	  
	  @EventHandler(priority=EventPriority.HIGHEST)
	  public void onSignChange(SignChangeEvent event) {
	    Player player = event.getPlayer();
	    Block block = event.getBlock();

	      if (event.getLine(0).equals("[자동집구매]")) {
	    	  if (!player.hasPermission("windows.admin")) {
	    		  player.sendMessage(prefix + "§c당신은 권한이 없습니다.");
	    		  block.setType(Material.AIR);
	              block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.SIGN, 1));
	    		  return;
	    	  }
	      }
	    }
				/*if (args.length == 3) {
					if (args[0].equalsIgnoreCase("remove")) {
						if (Bukkit.getPlayer(args[1]) != null) {
							if (getConfig().contains(args[2])) { // 해당 집 정보가 있을 경우
								if (getConfig().contains(args[2] + "." + Bukkit.getPlayer(args[1]).getName().toLowerCase())) { // 해당 플레이어가 집 구매 목록에 있을 경우
									
								}
							}
						} else { // 접속중이 아닐경우
							
						}
					}
				}*/
}
