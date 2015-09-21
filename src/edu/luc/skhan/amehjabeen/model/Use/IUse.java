package edu.luc.skhan.amehjabeen.model.Use;

import java.util.Date;

import edu.luc.skhan.amehjabeen.model.Facility.IFacilityGetterSetter;

public interface IUse extends IFacilityGetterSetter{

	Date getEndTime();
	Date getStartTime();
	void setEndTime(Date endTime);
	void setStartTime(Date startTime);
	boolean isInUse();
	void setInUse(boolean isInUse);
	
}
