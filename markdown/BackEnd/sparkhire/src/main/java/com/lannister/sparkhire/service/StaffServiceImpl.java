package com.lannister.sparkhire.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lannister.sparkhire.entity.Role;
import com.lannister.sparkhire.entity.Staff;
import com.lannister.sparkhire.repository.RoleRepository;
import com.lannister.sparkhire.repository.StaffRepository;
import com.lannister.sparkhire.vo.StaffVo;

@Component
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepository staffRepo;
	@Autowired
	private RoleRepository roleRepo;

	public List<StaffVo> load(){
		List<Staff> staffs = staffRepo.findAll();
		List<StaffVo> staffsVo = new ArrayList<StaffVo>();
		for(Staff s : staffs){
			staffsVo.add(new StaffVo(s.getUserid(),s.getUsername(),s.getRealname(),s.getPassword(),
					s.getRoleid(),s.getPhone(),s.getEmail(),s.getPortrait()));
		}
		return staffsVo;
	}

	@Override
	public StaffVo findStaffByUsernameAndPassword(String username,String password) {
		Staff staff =  staffRepo.findByUsernameAndPassword(username, password);
		if(staff == null){
			return null;
		}
		StaffVo staffVo = new StaffVo(staff.getUserid(),staff.getUsername(),staff.getRealname(),staff.getPassword(),
				staff.getRoleid(),staff.getPhone(),staff.getEmail(),staff.getPortrait());
		return staffVo;
	}

	@Override
	public void addStaff(StaffVo staffVo) {
		staffRepo.save(new Staff(staffVo.getUsername(),staffVo.getRealname(),staffVo.getPassword(),staffVo.getRoleid(),
				staffVo.getPhone(),staffVo.getEmail(),staffVo.getPortrait()));
		
	}

	@Override
	public void deleteStaff(Integer staffId) {
		staffRepo.delete(staffId);
		
	}

	@Override
	public StaffVo queryStaffById(Integer userid) {
		Staff staff = staffRepo.findOne(userid);
		if(staff == null){
			return null;
		}
		StaffVo staffVo = new StaffVo(staff.getUserid(),staff.getUsername(),staff.getRealname(),staff.getPassword(),
				staff.getRoleid(),staff.getPhone(),staff.getEmail(),staff.getPortrait());
		return staffVo;
	}

	@Override
	public void updateStaff(StaffVo staffVo) {
		staffRepo.save(new Staff(staffVo.getUsername(),staffVo.getRealname(),staffVo.getPassword(),staffVo.getRoleid(),
				staffVo.getPhone(),staffVo.getEmail(),staffVo.getPortrait()));
	}

	@Override
	public List<StaffVo> loadStaffByRole(Integer roleId) {
		List<Staff> staffs = staffRepo.findByRoleidOrderByUserid(roleId);
		List<StaffVo> staffsVo = new ArrayList<StaffVo>();
		for(Staff s : staffs){
			staffsVo.add(new StaffVo(s.getUserid(),s.getUsername(),s.getRealname(),s.getPassword(),
					s.getRoleid(),s.getPhone(),s.getEmail(),s.getPortrait()));
		}
		return staffsVo;
	}

	@Override
	public void initRole() {
		roleRepo.save(new Role(0,"Staff"));
		roleRepo.save(new Role(1,"HR"));
		roleRepo.save(new Role(2,"HR Manager"));
		roleRepo.save(new Role(3,"Interviewor"));
		roleRepo.save(new Role(4,"Department Manager"));
	}

	

}
