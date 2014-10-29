package uidai.explore.shard;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import uidai.explore.dataloader.ShardDataLoader;
import uidai.explore.shard.conf.ShardConf;
import uidai.explore.util.CSVParser;
import uidai.explore.util.JsonUtil;

public class WriteClient {
	
	private ShardConf conf;
	
	public WriteClient(File cfg){
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
			e.printStackTrace();
		}
	}
	
	public void run(){
		if (conf != null){
			ShardDataLoader dataLoader = new ShardDataLoader(conf.getNodes());
			CSVParser parser = new CSVParser(dataLoader);
			try {
				parser.loadCSVFilesToDatabase(new File(conf.getPath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		if (args.length < 0){
			System.err.println("Please specify the shard config file.");
			return;
		}
		
		File file = new File(args[0]);
		new WriteClient(file).run();
	}
}
