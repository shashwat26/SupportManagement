package com.chhimek.supportmgmt.service;

import java.util.List;

import com.chhimek.supportmgmt.model.SupportRegion;

public interface RegionDao {
	
	List<SupportRegion> listAllRegion();
	SupportRegion getRegionById(int id);
	SupportRegion getRegionByName(String name);

}
