package net.vimalpatel.replacers;

import net.vimalpatel.utils.Constants;

public class ReplacerFactory {

	public static IReplacer getReplacerByType(String type){
		IReplacer r = null;
		type = type.trim().toUpperCase();
		if(type.equals(Constants.IP_REPLACEMENT)){
			r  = new IpReplacer();
		}else if(type.equals(Constants.WORD_REPLACEMENT)){
			r  = new WordReplacer();
		}else if(type.equals(Constants.LITERAL_REPLACEMENT)){
			r  = new LiteralReplacer();
		}
		return r;
	}
	
}
