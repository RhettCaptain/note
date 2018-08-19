package com.worksap.stm2017.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.worksap.stm2017.model.User;
import com.worksap.stm2017.vo.OtherUserVo;

@Component
public class UserDaoImpl implements UserDao{
	private JdbcTemplate template;
	
	private final String LOAD_SQL = "select * from users";
	private final String SEARCH_SQL = "select * from users where \"userId\" = ? ";
	private final String CHECK_SQL = "select * from users where \"userId\" = ? and password = ?";
	private final String ALL_ID_SQL =  "select \"userId\" from users order by \"userId\"";
	private final String INSERT_SQL = "insert into users(\"userId\",name,password,\"workLevel\", \"isManager\") values (?,?,?,?,?)";
	private final String UPDATE_SQL = "update users set name=?,\"workLevel\"=?, \"isManager\"=? where \"userId\"=?";
	private final String DELETE_SQL = "delete from users where \"userId\"=?";
	
	@Autowired
	public UserDaoImpl(JdbcTemplate template){
		this.template = template;
	}
	
	
	public boolean checkAdmin(User user) {
		List<User> res = template.query(CHECK_SQL, 
				ps -> {
					ps.setInt(1,user.getUserId());
					ps.setString(2, user.getPassword());
				},
				(rs,rowNum) -> new User(rs.getInt(1),rs.getString(2),
						rs.getString(3),rs.getInt(4),rs.getBoolean(5)));
		if(res.isEmpty() || res.get(0).getIsManager() == false ){
			return false;
		}else{
			return true;
		}
	}

	
	public boolean checkEmpl(User user) {
		List<User> res = template.query(CHECK_SQL, 
				ps -> {
					ps.setInt(1,user.getUserId());
					ps.setString(2, user.getPassword());
				},
				(rs,rowNum) -> new User(rs.getInt(1),rs.getString(2),
						rs.getString(3),rs.getInt(4),rs.getBoolean(5)));
		if(res.isEmpty()){
			return false;
		}else{
			return true;
		}
	}


	public User getUser(Integer userId) {
		List<User> res = template.query(SEARCH_SQL, 
				ps -> ps.setInt(1,userId),
				(rs,rowNum) -> new User(rs.getInt(1),rs.getString(2),
						rs.getString(3),rs.getInt(4),rs.getBoolean(5)));
		if(res.size() == 0){
			System.out.println("no such person");
			return null;
		}else if(res.size() > 1){
			System.out.println("warning:repeat userId");
		}
		return res.get(0);
	}

	@Override
	public List<OtherUserVo> getOtherUserVo() {
		List<OtherUserVo> list = template.query(LOAD_SQL, 
				(rs,rowNum) -> new OtherUserVo(rs.getInt(1),rs.getString(2),
						rs.getInt(4),rs.getBoolean(5)));
		return list;
	}

	@Override
	public OtherUserVo getOtherUserById(Integer id) {
		List<OtherUserVo> res = template.query(SEARCH_SQL, 
				ps -> ps.setInt(1,id),
				(rs,rowNum) -> new OtherUserVo(rs.getInt(1),rs.getString(2),
						rs.getInt(4),rs.getBoolean(5)));
		if(res.size() == 0){
			System.out.println("no such person");
			return null;
		}else if(res.size() > 1){
			System.out.println("warning:repeat userId");
		}
		return res.get(0);
	}

	private int generateId(){
		List<Integer> res = template.query(ALL_ID_SQL, 
				(rs,rowNum) -> new Integer(rs.getInt(1)));
		int newId = 1;
		for (Integer i : res){
			if(newId == i){
				newId++;
			}else{
				break;
			}
		}
		return newId;
	}
	@Override
	public void addUser(OtherUserVo stv) {
		Integer userId = generateId();
		String name = stv.getName();
		String password = userId.toString();
		Integer workLevel = stv.getWorkLevel();
		Boolean isManager = stv.getIsManager();
		template.update(INSERT_SQL,
				ps -> {
					ps.setInt(1, userId);
					ps.setString(2, name);
					ps.setString(3, password);
					ps.setInt(4, workLevel);
					ps.setBoolean(5, isManager);
				});
	}

	@Override
	public void updateUser(OtherUserVo stv) {
		Integer userId = stv.getId();
		String name = stv.getName();
		Integer workLevel = stv.getWorkLevel();
		Boolean isManager = stv.getIsManager();

		template.update(UPDATE_SQL,
				ps -> {
					ps.setString(1, name);
					ps.setInt(2, workLevel);
					ps.setBoolean(3, isManager);
					ps.setInt(4, userId);
				});
	}

	@Override
	public void deleteUser(Integer id) {
		template.update(DELETE_SQL,
				ps -> ps.setInt(1, id));
	}
}
