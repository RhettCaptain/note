package com.worksap.stm2017.dao;

import java.util.List;

import com.worksap.stm2017.vo.UserVo;
import com.worksap.stm2017.model.User;
import com.worksap.stm2017.vo.OtherUserVo;

public interface UserDao {
	public boolean checkAdmin(User user);
	
	public boolean checkEmpl(User user);
	
	public User getUser(Integer userId);
	
	public List<OtherUserVo> getOtherUserVo();

	public OtherUserVo getOtherUserById(Integer id);

	public void addUser(OtherUserVo stv);

	public void updateUser(OtherUserVo stv);

	public void deleteUser(Integer id);

	public UserVo getUserById(Integer id);

	public void updatePassword(Integer id, String password);
}
