/**
 * 
 */
package net.vimalpatel.replacers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.vimalpatel.utils.Constants;

/**
 * @author vimalkumarpatel
 *
 */

/**
 * 
 * Word replacement, this one is more complicated.
 * For instance, the replacement is University -> Bicycle.
 * First, the replacement only happens when the character preceding 
 * “University” is not an letter, or if it is a letter, not the same caseness 
 * (e.g. CaliforniaUniverity ->CaliforniaBicycle). 
 * Second, the replacement should have case correspondence, 
 * such as university->bicycle  University->Bicycle and UNIVERSITY->BICYCLE.
 *
 */
public class WordReplacer extends Replacer {
	@Override
	public void replaceAndCreateOutputFile() {
		
		Map<String,String> secondaryReplacementMap = new HashMap<String,String>();
		
		String line = null;
		try{
			while ((line = logReaderWriter.getLogLine()) != null) {
				System.out.println("before:"+line);
				StringBuffer sb = new StringBuffer();
				for(String replacementKey : replacementMapings.keySet()){
					String replacementKeyRegex = getReplacementKeyRegex(replacementKey);
					updateSecondaryReplacementMap(secondaryReplacementMap,replacementKey);
					System.out.println("replacementKeyRegex=="+replacementKeyRegex);
					Pattern pattern = Pattern.compile(replacementKeyRegex);
				    Matcher matcher = pattern.matcher(line);
				    int lastMatchPoint = 0;
				    // Check all occurrences
				    while (matcher.find()) {
				    	int start = matcher.start();
				    	int end = matcher.end();
				    	String group = matcher.group();
				    	System.out.print("Start index: " + start);
				    	System.out.print(" End index: " + end);
				    	System.out.println(" Found: " + group);
				    	int preStartindex = (start == 0)?0:start-1;
				    	Character preMatchChar = line.charAt(preStartindex);
				    	System.out.println("preMatchChar="+preMatchChar);
				    	if( !Character.isLetter(preMatchChar) || !isSameCase(preMatchChar,line.charAt(start)) ){
				    		//replace the log line content with the replacement value;
				    		String key = line.substring(start, end);
				    		String repVal = secondaryReplacementMap.get(key);
				    		sb.append(
				    				line.substring(lastMatchPoint,start)
				    				+repVal );
				    		System.out.println("BUFFERED STR:"+sb.toString());
				    	}else{
				    		sb.append(line.substring(lastMatchPoint,end));
				    	}
				    	lastMatchPoint = end;
				    }
				    sb.append(line.substring(lastMatchPoint));
				    logReaderWriter.writeWithBufferedOutputStream(sb.toString());
				}
			}
		} catch(NoSuchElementException ex){
			return;
		}
	}

	private boolean isSameCase(Character preMatchChar, Character matchStartChar) {
		System.out.println("preMatchChar.isLowerCase() ="+Character.isLowerCase(preMatchChar)+" , matchStartChar.isLowerCase()="+Character.isLowerCase(matchStartChar));
		if ((Character.isLowerCase(preMatchChar) && Character.isLowerCase(matchStartChar))||
				(Character.isUpperCase(preMatchChar) && Character.isUpperCase(matchStartChar))) {
			System.out.println("returning true");
			return true;
		}
		return false;
	}

	private void updateSecondaryReplacementMap(
			Map<String, String> secondaryReplacementMap, String replacementKey) {
		if("".equals(replacementKey) || null == replacementKey ) return;
		
		String replacementVal = replacementMapings.get(replacementKey);
		
		String key = replacementKey.toUpperCase();
		String value = replacementVal.toUpperCase();
		secondaryReplacementMap.put(key, value);
		
		key = replacementKey.charAt(0) + ((replacementKey.length()>1)?replacementKey.substring(1):"");
		value = replacementVal.charAt(0) + ((replacementVal.length()>1)?replacementVal.substring(1):"");
		secondaryReplacementMap.put(key, value);
		
		secondaryReplacementMap.put(replacementKey, replacementVal);
	}

	private String getReplacementKeyRegex(String replacementKey) {
		String regex = null;
		/*
		 * 1. university -> bicycle
		 * 2. university => University -> Bicycle
		 * 3. university => UNIVERSITY -> BICYCLE
		 */
		if("".equals(replacementKey) || null == replacementKey) return "";
		Character firstChar = replacementKey.charAt(0);
		String subStr = "";
		if(replacementKey.length()>1) subStr = replacementKey.substring(1);
		regex = "("+firstChar+"|"+Character.toUpperCase(firstChar)+")(("+subStr+")|("+subStr.toUpperCase()+"))";
		
		return regex;
	}


	@Override
	public void cleanup() {
		super.cleanup();
	}

}
