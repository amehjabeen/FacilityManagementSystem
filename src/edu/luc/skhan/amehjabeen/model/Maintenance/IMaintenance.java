package edu.luc.skhan.amehjabeen.model.Maintenance;

import java.util.Date;

import edu.luc.skhan.amehjabeen.model.Facility.IFacilityGetterSetter;


public interface IMaintenance extends IFacilityGetterSetter{
	public long calcDownTimeForFacility();
	public double getCost();
	public void setCost(double cost);
	public Date getStartTime();
	public void setStartTime(Date startTime);
	public Date getEndTime();
	public void setEndTime(Date endTime);
}
