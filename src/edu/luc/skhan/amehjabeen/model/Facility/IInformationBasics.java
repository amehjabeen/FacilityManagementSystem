package edu.luc.skhan.amehjabeen.model.Facility;


public interface IInformationBasics {
	public void addFacilityDetail(IFacility facility);
	public void removeFacilityDetail(IFacility facility);
	public int requestAvailableCapacity(IFacility facility);
}
