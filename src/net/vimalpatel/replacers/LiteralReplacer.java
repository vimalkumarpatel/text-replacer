/**
 * 
 */
package net.vimalpatel.replacers;

import java.util.NoSuchElementException;

/**
 * @author vimalkumarpatel
 * 
 */
public class LiteralReplacer extends Replacer {

	@Override
	public void replaceAndCreateOutputFile() {
		String line = null;

		try {
			while ((line = logReaderWriter.getLogLine()) != null) {
				System.out.println("before:" + line);
				System.out.println("replacing literals:" + replacementMapings);
				for (String oldLiteral : replacementMapings.keySet()) {
					line = line.replaceAll(oldLiteral,
							replacementMapings.get(oldLiteral));
					System.out.println("after:" + line);
				}
				logReaderWriter.writeWithBufferedOutputStream(line);
			}
		} catch (NoSuchElementException ex) {
			return;
		}
	}

	@Override
	public void cleanup() {
		super.cleanup();
	}

}
