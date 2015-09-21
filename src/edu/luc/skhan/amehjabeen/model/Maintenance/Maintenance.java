package edu.luc.skhan.amehjabeen.model.Maintenance;

import java.util.Date;

import edu.luc.skhan.amehjabeen.model.Facility.IFacility;

public class Maintenance implements IMaintenance {

	private double cost;
	private Date startTime;
	private Date endTime;
	IFacility facility;

	public Maintenance(double cost,Date startTime,Date endTime,IFacility facility) {
		this.setCost(cost);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.setFacility(facility);
	}

	@Override
	public long calcDownTimeForFacility() {		
		return endTime.getTime() - startTime.getTime();
	}

	@Override
	public double getCost() {
		return cost;
	}

	@Override
	public void setCost(double cost) {
		this.cost = cost;
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
	public Date getEndTime() {
		return endTime;
	}

	@Override
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
