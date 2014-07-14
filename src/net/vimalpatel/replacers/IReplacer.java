/**
 * 
 */
package net.vimalpatel.replacers;

/**
 * @author vomi
 *
 */
public interface IReplacer {

	public void initTextReplacement(String logFilePath, String propFilePath, String outputFilePath) throws Throwable;

	public void replaceAndCreateOutputFile();

	public void cleanup();
}
