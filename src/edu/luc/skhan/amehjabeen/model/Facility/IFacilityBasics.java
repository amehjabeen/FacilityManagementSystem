package edu.luc.skhan.amehjabeen.model.Facility;

import java.util.ArrayList;

public interface IFacilityBasics {
	public IFacility addNewFacility(IFacility facility);
	public boolean removeFaciltiy(IFacility facility);
	public ArrayList<IFacility> listFacilities();
}
