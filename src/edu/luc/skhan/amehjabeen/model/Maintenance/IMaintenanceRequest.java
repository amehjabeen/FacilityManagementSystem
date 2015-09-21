package edu.luc.skhan.amehjabeen.model.Maintenance;

import java.util.Date;

import edu.luc.skhan.amehjabeen.model.Facility.IFacilityGetterSetter;

public interface IMaintenanceRequest extends IFacilityGetterSetter {

	void setRequestDate(Date requestDate);
	Date getRequestDate();
	void setInspectionStatus(String inspectionStatus);
	String getInspectionStatus();
}
