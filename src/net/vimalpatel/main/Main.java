/**
 * 
 */
package net.vimalpatel.main;

import net.vimalpatel.replacers.IReplacer;
import net.vimalpatel.replacers.ReplacerFactory;

/**
 * @author vimalkumarpatel
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length != 3){
			System.out.println("PARAMETERS REQUIRED: log//File//path properties//File//path replacertype");
			return;
		}
		String logFilePath = args[0];
		String propFilePath = args[1];
		String replacerType = args[2];
		String outputFilePath = logFilePath+".OUTPUT";
		System.out.println("PARAMETERS PASSED: "+logFilePath+" "+propFilePath+" "+replacerType);
		IReplacer replacer = ReplacerFactory.getReplacerByType(replacerType);
		try {
			replacer.initTextReplacement(logFilePath, propFilePath, outputFilePath);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		replacer.replaceAndCreateOutputFile();
		replacer.cleanup();
	}

}
