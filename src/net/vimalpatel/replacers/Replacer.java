package net.vimalpatel.replacers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.vimalpatel.utils.LogReaderWriter;

public abstract class Replacer implements IReplacer {
	LogReaderWriter logReaderWriter = null;
	Map<String,String> replacementMapings = null;
	
	public void initTextReplacement(String logFilePath, String propFilePath, String outputFilePath) throws IOException {
		logReaderWriter = new LogReaderWriter(logFilePath, outputFilePath);
		replacementMapings = new HashMap<String, String>();
		Properties prop = new Properties();
		prop.load(new FileInputStream(propFilePath));
		for(Object key: prop.keySet()){
			replacementMapings.put(key.toString(),prop.get(key).toString());
		}
	}
	
	public void cleanup(){
		if(null != logReaderWriter){
			logReaderWriter.cleanup();
		}
	}
}
