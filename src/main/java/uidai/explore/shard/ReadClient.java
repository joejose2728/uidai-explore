package uidai.explore.shard;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import uidai.explore.query.impl.ShardDataReader;
import uidai.explore.shard.conf.ShardConf;
import uidai.explore.util.JsonUtil;

public class ReadClient {
	
	private ShardConf conf;
	
	public ReadClient(File cfg){
		init(cfg);
	}
	
	private void init(File cfg){
		BufferedInputStream br = null;
		try {
			byte[] in = new byte[(int) cfg.length()];
			br = new BufferedInputStream(new FileInputStream(cfg));
			br.read(in);
			conf = JsonUtil.decode(new String(in), ShardConf.class);
		} catch (Exception e) {
		}
	}
	
	public void run(){
		if (conf != null){
			ShardDataReader reader = new ShardDataReader(conf.getNodes());
			//reader.getNoOfEnrollmentsForAgencies();
			//reader.getNoOfRejectionsForAgencies();
			//reader.getNoOfSeniorCitizensEnrolledGroupedByGender();
			//reader.getNoOfAadhaarUsersProvidingPhoneGroupedByState();
			reader.getNoOfAadhaarGeneratedForDateRangeGroupedByState("20120101", "20120131");
		}
	}
	
	public static void main(String[] args) {
		if (args.length < 0){
			System.err.println("Please specify the shard config file.");
			return;
		}
		
		File file = new File(args[0]);
		new ReadClient(file).run();
	}
	
}
