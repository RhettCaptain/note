package com.worksap.stm2017.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.worksap.stm2017.model.User;
import com.worksap.stm2017.vo.OtherUserVo;
import com.worksap.stm2017.vo.UserVo;

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
	private final String UPDATE_PWD_SQL = "update users set \"password\"=? where \"userId\"=?";
	
	private final String INSERT_THIS_ROSTER_SQL = "insert into \"thisWeekRoster\" (\"userId\",\"monShift\",\"tusShift\",\"wedShift\",\"thuShift\",\"friShift\",\"satShift\",\"sunShift\") "
			+ "values (?,?,?,?,?,?,?,?)";
	private final String INSERT_NEXT_ROSTER_SQL = "insert into \"nextWeekRoster\" (\"userId\",\"monShift\",\"tusShift\",\"wedShift\",\"thuShift\",\"friShift\",\"satShift\",\"sunShift\") "
			+ "values (?,?,?,?,?,?,?,?)";
	
	private final String GET_SHIFT_TYPE_SQL = "select id from \"shiftType\"";
	private final String INSERT_SHIFT_SCORE_SQL = "insert into \"shiftScore\" (\"shiftId\",\"userId\",\"lv1\",\"lv2\",\"lv3\",\"lv4\",\"lv5\",\"score\") "
			+ "values (?,?,?,?,?,?,?,?)";
	private final String INSERT_LIMIT_REC_SQL = "insert into \"timeLimitRec\" (\"userId\",\"minTimeRec\",\"maxTimeRec\") "
			+ "values (?,?,?)";
	private final String INSERT_WORKDAY_SQL = "insert into \"workDay\" (\"userId\",\"mon\",\"tus\",\"wed\",\"thu\",\"fri\",\"sat\",\"sun\") "
			+ "values (?,?,?,?,?,?,?,?)";
	
	private final String DELETE_THIS_ROSTER_SQL = "delete from \"thisWeekRoster\" where \"userId\"=?";
	private final String DELETE_NEXT_ROSTER_SQL = "delete from \"nextWeekRoster\" where \"userId\"=?";
	private final String DELETE_SHIFT_SCORE_SQL = "delete from \"shiftScore\" where \"userId\"=?";
	private final String DELETE_LIMIT_REC_SQL = "delete from \"timeLimitRec\" where \"userId\"=?";
	private final String DELETE_WORKDAY_SQL = "delete from \"workDay\" where \"userId\"=?";
	
	
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
		int newId = 0;
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
		//add user
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
		//add def roster
		template.update(INSERT_THIS_ROSTER_SQL,
				ps -> {
					ps.setInt(1, userId);
					for(int i=0;i<7;i++){
						ps.setInt(i+2, 0);
					}
				});
		template.update(INSERT_NEXT_ROSTER_SQL,
				ps -> {
					ps.setInt(1, userId);
					for(int i=0;i<7;i++){
						ps.setInt(i+2, 0);
					}
				});
		
		//add def shiftScore
		List<Integer> shiftType = template.query(GET_SHIFT_TYPE_SQL, 
				(rs,rowNum) -> new Integer(rs.getInt(1)));
		for(Integer type : shiftType){
			template.update(INSERT_SHIFT_SCORE_SQL,
					ps -> {
						ps.setInt(1, type);
						ps.setInt(2, userId);
						for(int i=0;i<5;i++){
							ps.setInt(i+3, 0);
						}
						ps.setDouble(8, 3.0);
					});
		}
		
		//add def time limit rec
		template.update(INSERT_LIMIT_REC_SQL,
				ps -> {
					ps.setInt(1, userId);
					ps.setDouble(2, 0);
					ps.setDouble(3, 56);
				});
		
		//add def workday
		template.update(INSERT_WORKDAY_SQL,
				ps -> {
					ps.setInt(1, userId);
					for(int i=0;i<7;i++){
						ps.setDouble(i+2, 3.0);
					}
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
		//save root
		if(id == 0){	
			return;
		}
		//del user
		template.update(DELETE_SQL,
				ps -> ps.setInt(1, id));
		
		//del roster
		template.update(DELETE_THIS_ROSTER_SQL,
				ps -> ps.setInt(1, id));
		template.update(DELETE_NEXT_ROSTER_SQL,
				ps -> ps.setInt(1, id));
		
		//del shift score
		template.update(DELETE_SHIFT_SCORE_SQL,
				ps -> ps.setInt(1, id));
		
		//del time limit rec
		template.update(DELETE_LIMIT_REC_SQL,
				ps -> ps.setInt(1, id));
		
		//del workday
		template.update(DELETE_WORKDAY_SQL,
				ps -> ps.setInt(1, id));		
		
	}


	@Override
	public UserVo getUserById(Integer id) {
		List<UserVo> res = template.query(SEARCH_SQL, 
				ps -> ps.setInt(1,id),
				(rs,rowNum) -> new UserVo(rs.getInt(1),rs.getString(2),
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
	public void updatePassword(Integer id, String password) {
		// TODO Auto-generated method stub UPDATE_PWD_SQL
		template.update(UPDATE_PWD_SQL,
				ps -> {
					ps.setString(1, password);
					ps.setInt(2, id);
				});
	}
}
