package uidai.explore.util;

public class Result {

	private String groupedAttribute;
	private int count;
	
	public Result(String groupedAttribute, long count) {
		this.groupedAttribute = groupedAttribute;
		this.count = (int) count;
	}
	
	public Result(char groupedAttribute, long count){
		this.groupedAttribute = groupedAttribute + "";
		this.count = (int) count;
	}
	
	public Result() {
		// TODO Auto-generated constructor stub
	}

	public String getGroupedAttribute() {
		return groupedAttribute;
	}
	public void setGroupedAttribute(String groupedAttribute) {
		this.groupedAttribute = groupedAttribute;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
