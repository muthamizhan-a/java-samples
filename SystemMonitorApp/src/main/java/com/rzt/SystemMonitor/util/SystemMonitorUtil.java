package com.rzt.systemMonitor.util;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.apache.commons.validator.routines.EmailValidator;

public class SystemMonitorUtil {

	public static Long totalMemory;
	public static Long usedMemory;
	public static Long freeMemory;
	public static Long maxMemory;
	public static Long totalDiskSpace;
	public static Long usedDiskSpace;
	public static Long freeDiskSpace;

	public static boolean isValidMailId( String mailId )
	{
		return EmailValidator.getInstance().isValid(mailId);
	}

	public static void updateLatestMemoryUtilization()
	{
		int mb = 1024 * 1024;
		int gb = 1024 * 1024 * 1024;
		Runtime instance = Runtime.getRuntime();
		totalMemory = instance.totalMemory() / mb;
		freeMemory = instance.freeMemory() / mb;
		usedMemory = (instance.totalMemory() - instance.freeMemory()) / mb;
		maxMemory = instance.maxMemory() / mb;

		File[] roots = File.listRoots();

		for( File root : roots )
		{
			totalDiskSpace = root.getTotalSpace() / gb;
			freeDiskSpace = root.getFreeSpace() / gb;
			usedDiskSpace = root.getUsableSpace() / gb;

		}
	}

	public static String getResponceString() throws Exception
	{
		String responce = "";
		SystemMonitorUtil.updateLatestMemoryUtilization();
		SystemMonitorUtil.takeSystemScreenshot();

		responce += " ***** Memory Utilization ***** <br>";
		responce += "Total JVM Memory:" + SystemMonitorUtil.totalMemory + " MB <br>";
		responce += "Used JVM Memory:" + SystemMonitorUtil.usedMemory + " MB  <br>";
		responce += "Free JVM Memory:" + SystemMonitorUtil.freeMemory + " MB  <br>";
		responce += "Max JVM Memory:" + SystemMonitorUtil.maxMemory + " MB  <br>";
		responce += "<br>";
		responce += "Total Disk space (GB): " + SystemMonitorUtil.totalDiskSpace + "<br>";
		responce += "Free Disk space (GB): " + SystemMonitorUtil.freeDiskSpace + "<br>";
		responce += "Usable Disk space (GB): " + SystemMonitorUtil.usedDiskSpace + "<br>";
		responce += "------------------------------------------ <br>";
		return responce;

	}

	public static void takeSystemScreenshot() throws Exception
	{
		BufferedImage image = new Robot()
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(image, "png", new File("src/main/resources/screenshot.png"));
	}
}
