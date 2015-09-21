package edu.luc.skhan.amehjabeen.model.Maintenance;

import java.util.ArrayList;

import edu.luc.skhan.amehjabeen.model.Facility.IFacility;

public interface IProblemBasics {
	public void addFacilityProblem(IProblem problem);
	public double calcProblemRateForFacility(IFacility facility);
	public ArrayList<IProblem> listFacilityProblems(IFacility facility);
}
