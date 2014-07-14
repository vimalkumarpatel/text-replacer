/**
 * 
 */
package net.vimalpatel.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 * @author vimalkumarpatel
 *
 */
public class LogReaderWriter {

	LineIterator lineIter = null;
	BufferedOutputStream outputStream = null;
	
	public LogReaderWriter(String inputFilePath, String outputFilePath) throws IOException{
		File file = new File(inputFilePath);
		if(!file.exists()) throw new FileNotFoundException("Input Log File '"+inputFilePath+"' Not Found !");
		this.lineIter = FileUtils.lineIterator(file);
		
		Path fileP = Paths.get(outputFilePath);
		this.outputStream = new BufferedOutputStream(Files.newOutputStream(fileP));
	}
	
	public String [] logLines2Array(String logLine){
		String [] arr = logLine.split(",");
		return arr;
	}
	
	public String getLogLine() throws NoSuchElementException {
		return lineIter.nextLine();
	}

	public void writeWithBufferedOutputStream(String content) {
		try {
			content += System.getProperty("line.separator");
			outputStream.write(content.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cleanup() {
		LineIterator.closeQuietly(lineIter);
		try {
			outputStream.close();
		} catch (IOException e) {}
	}
}
