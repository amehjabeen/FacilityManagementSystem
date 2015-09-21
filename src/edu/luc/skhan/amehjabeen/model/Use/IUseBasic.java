package edu.luc.skhan.amehjabeen.model.Use;

import java.util.ArrayList;
import java.util.Date;

import edu.luc.skhan.amehjabeen.model.Facility.IFacility;

public interface IUseBasic {
	public boolean isInUseDuringInterval(IFacility facility,Date startTime,Date endTime);
	public void assignFacilityToUse(IUse use);
	public void vacateFacility(IFacility facility);
	public ArrayList<IUse> listActualUsage(IFacility facility);
	public double calcUsageRate(IFacility facility);
	public void changeIsInUseStatus(IUse use);
}
