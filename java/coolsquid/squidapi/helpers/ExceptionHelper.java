/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.helpers;

import java.io.File;

import net.minecraft.crash.CrashReport;

import org.apache.logging.log4j.Level;

import coolsquid.squidapi.logging.ILogger;
import coolsquid.squidapi.util.DateUtils;
import coolsquid.squidapi.util.ModManager;
import coolsquid.squidapi.util.StringUtils;

public class ExceptionHelper {

	private final ILogger logger;

	public ExceptionHelper(ILogger logger) {
		this.logger = logger;
	}

	public static ExceptionHelper getInstance() {
		return new ExceptionHelper(ModManager.INSTANCE.activeMod());
	}

	public static ExceptionHelper getInstance(String modid) {
		return new ExceptionHelper(ModManager.INSTANCE.getMod(modid));
	}

	public static ExceptionHelper getInstance(ILogger logger) {
		return new ExceptionHelper(logger);
	}

	public void log(Level level,Throwable t, CrashReport report, File file) {
		report.saveToFile(file);

		String marker = StringUtils.repeat('#', 70);
		this.logger.error(marker);
		this.logger.error(t.toString());
		for (StackTraceElement s: t.getStackTrace()) {
			this.logger.error(s.toString());
		}
		this.logger.error(marker);
	}

	public void log(Level level, Throwable t, File file) {
		this.log(level, t, new CrashReport(t.toString(), t), file);
	}

	public void log(Level level, Throwable t) {
		this.log(level, t, new File("./SquidAPI/errors/ErrorLog-" + DateUtils.getTime("h-m-s-d-M-y") + ".txt"));
	}
}