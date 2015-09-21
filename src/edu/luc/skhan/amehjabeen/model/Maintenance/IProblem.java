package edu.luc.skhan.amehjabeen.model.Maintenance;

import edu.luc.skhan.amehjabeen.model.Facility.IFacilityGetterSetter;

public interface IProblem extends IFacilityGetterSetter {
	public String getTypeOfProblem();
	public void setTypeOfProblem(String typeOfProblem);
	public String getMaintenanceRequestStatus();
	public void setMaintenanceRequestStatus(String maintenanceRequestStatus);
}
