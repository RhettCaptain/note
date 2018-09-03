package com.lannister.sparkhire.service;

import java.util.List;


import com.lannister.sparkhire.vo.StaffVo;

public interface StaffService {
	public List<StaffVo> load();
	public StaffVo findStaffByUsernameAndPassword(String username,String password);
	public void addStaff(StaffVo staffVo);
	public void deleteStaff(Integer staffId);
	public StaffVo queryStaffById(Integer userid);
	public void updateStaff(StaffVo staffVo);
	public List<StaffVo> loadStaffByRole(Integer i);
	public void initRole();
}
