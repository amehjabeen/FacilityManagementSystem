package edu.luc.skhan.amehjabeen.model.Use;

import java.util.Date;

import edu.luc.skhan.amehjabeen.model.Facility.IFacility;

public class Use implements IUse  {
	private Date startTime;
	private Date endTime;
	private boolean isInUse;
	IFacility facility;
	
	public Use(IFacility facility, Date startTime, Date endTime, boolean isInUse) {
		this.setEndTime(endTime);
		this.setStartTime(startTime);
		this.setFacility(facility);
		this.setInUse(isInUse);
	}
	
	@Override
	public Date getEndTime() {
		return endTime;
	}

	@Override
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public Date getStartTime() {
		return startTime;
	}

	@Override
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Override
	public boolean isInUse() {
		return isInUse;
	}

	@Override
	public void setInUse(boolean isInUse) {
		this.isInUse = isInUse;
	}

	@Override
	public void setFacility(IFacility facility) {
		this.facility = facility;
		
	}

	@Override
	public IFacility getFacility() {
		return facility;
	}
	
}
