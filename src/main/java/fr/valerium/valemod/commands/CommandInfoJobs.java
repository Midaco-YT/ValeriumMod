package fr.valerium.valemod.commands;

import fr.valerium.valemod.jobs.JobManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class CommandInfoJobs extends CommandBase {

	public static final int MINER = 1;
	public static final int FARMER = 2;
	public static final int HUNTER = 3;
	public static final int ALCHIMIST = 4;

	public static final String MINER_NAME = "miner";
	public static final String FARMER_NAME = "farmer";
	public static final String HUNTER_NAME = "hunter";
	public static final String ALCHIMIST_NAME = "alchimist";

	@Override
	public String getCommandUsage(ICommandSender sender) {
	    return "/jobs <id> info : affiche l'expérience et le niveau du métier correspondant";
	}


	public String getCommandName() {
		return "jobs";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		EntityPlayer player = (EntityPlayer) sender;
		JobManager jobManager = JobManager.get(player);

		if (args.length == 0) {
			sender.addChatMessage(new ChatComponentText("/jobs <id> info : affiche l'expérience et le niveau du métier correspondant"));
			return;
		} else if (args.length == 1) {
			// Afficher l'expérience de tous les métiers si un seul argument est fourni
			for (String job : jobManager.levels.keySet()) {
				int level = jobManager.levels.get(job);
				int exp = jobManager.experience.get(job);
				long maxExp = jobManager.maxExperience.get(job);
				sender.addChatMessage(
						new ChatComponentText(job + " : niveau " + level + ", experience " + exp + "/" + maxExp));
			}
		} else {
			try {
				int jobId = Integer.parseInt(args[0]);
				String action = args[1];

				if (action.equals("info")) {
					if (jobId == MINER) {
						// Afficher l'expérience du métier de mineur
						int level = jobManager.levels.get(MINER_NAME);
						int exp = jobManager.experience.get(MINER_NAME);
						long maxExp = jobManager.maxExperience.get(MINER_NAME);
						sender.addChatMessage(new ChatComponentText(
								"Metier de miner : niveau " + level + ", experience " + exp + "/" + maxExp));
					} else if (jobId == FARMER) {
						// Afficher l'expérience du métier de fermier
						int level = jobManager.levels.get(FARMER_NAME);
						int exp = jobManager.experience.get(FARMER_NAME);
						long maxExp = jobManager.maxExperience.get(FARMER_NAME);
						sender.addChatMessage(new ChatComponentText(
								"Metier de farmer : niveau " + level + ", experience " + exp + "/" + maxExp));
					} else if (jobId == HUNTER) {
						// Afficher l'expérience du métier de chasseur
						int level = jobManager.levels.get(HUNTER_NAME);
						int exp = jobManager.experience.get(HUNTER_NAME);
						long maxExp = jobManager.maxExperience.get(HUNTER_NAME);
						sender.addChatMessage(new ChatComponentText(
								"Metier de hunter : niveau " + level + ", experience " + exp + "/" + maxExp));
					} else if (jobId == ALCHIMIST) {
						int level = jobManager.levels.get(ALCHIMIST_NAME);
						int exp = jobManager.experience.get(ALCHIMIST_NAME);
						long maxExp = jobManager.maxExperience.get(ALCHIMIST_NAME);
						sender.addChatMessage(new ChatComponentText(
								"Metier de alchimiste : niveau " + level + ", experience " + exp + "/" + maxExp));
					}
				} else {
					// Afficher un message d'erreur si l'argument "info" n'est pas fourni
					sender.addChatMessage(new ChatComponentText("Erreur : argument manquant ou incorrect"));
				}
			} catch (NumberFormatException e) {
				// Afficher un message d'erreur si l'argument n'est pas un entier
				sender.addChatMessage(new ChatComponentText("Erreur : l'argument doit etre un entier"));
			}
		}
	}


	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}
}
