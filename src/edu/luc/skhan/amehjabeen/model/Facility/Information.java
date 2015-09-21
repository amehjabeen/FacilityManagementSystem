package edu.luc.skhan.amehjabeen.model.Facility;


import edu.luc.skhan.amehjabeen.common.Constants;
import edu.luc.skhan.amehjabeen.data.DatabaseMethods;


public class Information implements IInformation{
	 
	private int total_capacity;
	private String description;
	private String status;
	DatabaseMethods databaseMethods = DatabaseMethods.getInstance();
	
	public Information(){
		this.setCapacity(0);
		this.setDescription(""); 
		this.setStatus(Constants.STATUS_AVAILABLE);
	}
	
	@Override
	public int getCapacity() {
		return total_capacity;
	}

	@Override
	public void setCapacity(int total_capacity) {
		this.total_capacity = total_capacity;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public void addFacilityDetail(IFacility facility) {
		databaseMethods.addFacilityDetail(facility);
		
	}


	@Override
	public int requestAvailableCapacity(IFacility facility) {
		return databaseMethods.requestAvailableCapacity(facility);
	}

	@Override
	public void removeFacilityDetail(IFacility facility) {
		databaseMethods.removeFacilityDetail(facility);
		
	}
}
