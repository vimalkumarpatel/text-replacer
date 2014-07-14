/**
 * 
 */
package net.vimalpatel.utils;

/**
 * @author vimalkumarpatel
 *
 */
public class Constants {
	public static String IP_REPLACEMENT = "IP_REPLACEMENT";
	public static String WORD_REPLACEMENT = "WORD_REPLACEMENT";
	public static String LITERAL_REPLACEMENT = "LITERAL_REPLACEMENT";
//	public static String IPADDRESS_PATTERN = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
	
	public static String IPADDRESS_PATTERN = "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
	
}
