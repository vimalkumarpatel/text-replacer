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
public class IpReplacer extends Replacer {
	@Override
	public void replaceAndCreateOutputFile() {
		String line = null;
		List<String> IpList = null;
		Map<String,String> ipMappings = null;
		try{
			while ((line = logReaderWriter.getLogLine()) != null) {
				System.out.println("before:"+line);
				IpList = extractAllIP(line);
				ipMappings = createMappingForIps(IpList);
				System.out.println("replacing ip:"+ipMappings);
				for(String oldIp : ipMappings.keySet()){
					line = line.replaceAll(oldIp, ipMappings.get(oldIp));
					System.out.println("after:"+line);
				}
				logReaderWriter.writeWithBufferedOutputStream(line);
			}
		} catch(NoSuchElementException ex){
			return;
		}
	}

	private Map<String, String> createMappingForIps(List<String> ipList) {
		List<String> replacedIpList = new ArrayList<String>();
		for(String ip:ipList){
			replacedIpList.add(String.copyValueOf(ip.toCharArray()));
		}
		for(String replacementKey:this.replacementMapings.keySet()){
			for(int i=0;i<replacedIpList.size();i++){
				System.out.println("before:"+replacedIpList.get(i));
				String modifiedIp = replacedIpList.remove(i).replaceAll(replacementKey, replacementMapings.get(replacementKey));
				replacedIpList.add(i, modifiedIp);
				System.out.println("after:"+replacedIpList.get(i));
				System.out.println("Size:"+replacedIpList.size());
			}
		}
		
		Map<String, String> retMap = new HashMap<String, String>();
		for(int i=0;i<ipList.size();i++){
			System.out.println("OLD="+ipList.get(i)+", NEW="+replacedIpList.get(i));
			retMap.put(ipList.get(i), replacedIpList.get(i));
		}
		
		return retMap;
	}

	private List<String> extractAllIP(String line) {
		List<String> ips = new ArrayList<String>();
		Pattern pattern = Pattern.compile(Constants.IPADDRESS_PATTERN);
		Matcher matcher = pattern.matcher(line);
		
		while (matcher.find()) {
			System.out.println("IP FOUND");
			ips.add(matcher.group());
		}
		System.out.println("ips="+ips);
		return ips;
	}

	@Override
	public void cleanup() {
		super.cleanup();
	}

}
