package com.chhimek.supportmgmt.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.chhimek.supportmgmt.model.Branch;
import com.chhimek.supportmgmt.model.SupportRegion;

@Repository
public class BranchDaoImpl implements BranchDao{

	@Resource
	private SessionFactory sessionFactory;
	
	@Autowired
	private RegionDao rDao;
	
	@Override
	@Transactional
	public void saveBranch(Branch branch) {
		Session session = sessionFactory.getCurrentSession();
		session.save(branch);
		
	}

	@Override
	@Transactional
	public List<Branch> listAll() {
		Session session = sessionFactory.getCurrentSession();
		Criteria crt = session.createCriteria(Branch.class);
		return crt.list();
	}

	@Override
	@Transactional
	public void deleteBranch(int id) {
		Session session = sessionFactory.getCurrentSession();
		Branch branch = (Branch) session.get(Branch.class, id);
		session.delete(branch);
		
	}

	@Override
	@Transactional
	public Branch getBranchById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Branch branch = (Branch)session.get(Branch.class, id);
		return branch;
	}

	@Override
	@Transactional
	public void updateBranch(Branch branch) {
		Session session = sessionFactory.getCurrentSession();
		session.update(branch);
		
	}

	@Override
	@Transactional
	public List<Branch> listBranchByRegionId(int id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crt = session.createCriteria(Branch.class);
		crt.add(Restrictions.eq("branchRegion.id",id));
		return crt.list();
	}

	@Override
	@Transactional
	public void updateBranchByCsv(MultipartFile file) {
		try {
			byte[] bytes = file.getBytes();
			String completeData = new String(bytes);
			String[] rows = completeData.split("\r\n");
			for(int i = 1; i< rows.length; i++) {
				if(rows[i].equals(",,,,,")) {
					continue;
				}
				List<String> newColumn = getColumnData(rows[i]);
				Branch branches = getBranchById(Integer.parseInt(newColumn.get(0)));
				int index = 1;
				branches.setName(newColumn.get(index));
				branches.setAddress(newColumn.get(index+1));
				branches.setBranchManager(newColumn.get(index+2));
				branches.setPhone(newColumn.get(index+3));
				SupportRegion region = rDao.getRegionByName(newColumn.get(index+4));
				if(region!=null) {
					branches.setBranchRegion(region);
				}
				updateBranch(branches);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	@Override
	public List<String> getColumnData(String row) {
		String columns[] = row.split(",");
		List<String> newColumn = new ArrayList<String>();
		for(int i = 0; i < columns.length; i++) {
			if(columns[i].startsWith("\"") ) {
				for(int j = i+1; j < 7; j++) {
					if(!columns[j].endsWith("\"")) {
						columns[i] += ", "+columns[j];
						columns[j] = "done";
						continue;
					}else {
						columns[i] = columns[i] + ", " + columns[j];
						columns[i] = columns[i].replaceAll("^\"|\"$", "");
						newColumn.add(columns[i]);
						columns[j] = "done";
						break;
					
					}
				}
			}else if(columns[i]==null) {
				break;
			}else if(columns[i].equals("done")) {
				continue;
			}
			else {
				newColumn.add(columns[i]);
			}
		}
		return newColumn;
	}

	
	
	

}
