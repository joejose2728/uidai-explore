package uidai.explore.intf;

import java.util.List;

public interface IDataLoader {

	public void loadUidaiData(List<String[]> csvValues);
	
	public void close();
}
