package edu.luc.skhan.amehjabeen.model.Use;

import java.util.Date;

import edu.luc.skhan.amehjabeen.model.Facility.IFacilityGetterSetter;

public interface IInspections extends IFacilityGetterSetter{

	String getProblemStatus();

	Date getInspectionDate();

	void setInspectionDate(Date inspectionDate);

	void setProblemStatus(String problemStatus);

}
