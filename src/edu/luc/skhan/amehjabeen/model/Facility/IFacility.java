package edu.luc.skhan.amehjabeen.model.Facility;


public interface IFacility{
	public int getId();
	public void setId(int id);	
	public IInformation getFacilityInformation();
	public void setFacilityInformation(IInformation iInformation);
	public String getName();
	public void setName(String name);
}
