package com.worksap.stm2017.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.worksap.stm2017.model.ShiftDemand;
import com.worksap.stm2017.model.User;
import com.worksap.stm2017.vo.OtherUserVo;
import com.worksap.stm2017.vo.UserVo;

@Component
public class UserDaoImpl implements UserDao{
	private JdbcTemplate template;
	
	private final String LOAD_SQL = "select * from users where \"deleted\"=false order by \"userId\"" ;
	private final String LOAD_DELETED_SQL = "select * from users where \"deleted\"=true order by \"userId\" ";
	private final String LOAD_BY_LV_SQL = "select * from users where \"workLevel\" = ? and \"deleted\"=false order by \"userId\" ";
	private final String SEARCH_SQL = "select * from users where \"userId\" = ? and \"deleted\"=false";
	private final String CHECK_BY_NICKNAME_SQL = "select * from users where \"nickName\" = ? and password = ? and \"deleted\"=false ";
	private final String CHECK_NICKNAME_SQL = "select * from users where \"nickName\" = ?";
	private final String CHECK_BY_ID_SQL = "select * from users where \"userId\" = ? and password = ? \"deleted\"=false";
	private final String ALL_ID_SQL =  "select \"userId\" from users order by \"userId\"";
	private final String INSERT_SQL = "insert into users(\"userId\",name,password,\"workLevel\", \"isManager\",\"nickName\",\"deleted\") values (?,?,?,?,?,?,?)";
	private final String UPDATE_SQL = "update users set name=?,\"workLevel\"=?, \"isManager\"=? where \"userId\"=?";
//	private final String DELETE_SQL = "delete from users where \"userId\"=?";
	private final String DELETE_SQL = "update users set \"deleted\"=true where \"userId\"=?";
	private final String UPDATE_PWD_SQL = "update users set \"password\"=? where \"userId\"=?";
	private final String UPDATE_NICKNAME_SQL = "update users set \"nickName\"=? where \"userId\"=?";
	private final String RECOVER_BY_NICKNAME_SQL = "update users set \"deleted\"=false where \"nickName\"=?";
	
	private final String INSERT_THIS_ROSTER_SQL = "insert into \"thisWeekRoster\" (\"userId\",\"monShift\",\"tueShift\",\"wedShift\",\"thuShift\",\"friShift\",\"satShift\",\"sunShift\") "
			+ "values (?,?,?,?,?,?,?,?)";
	private final String INSERT_NEXT_ROSTER_SQL = "insert into \"nextWeekRoster\" (\"userId\",\"monShift\",\"tueShift\",\"wedShift\",\"thuShift\",\"friShift\",\"satShift\",\"sunShift\") "
			+ "values (?,?,?,?,?,?,?,?)";
	
	private final String GET_SHIFT_TYPE_ID_SQL = "select id from \"shiftType\" order by id";
	private final String GET_SHIFT_DEMAND_SQL = "SELECT * FROM \"shiftDemand\" ORDER BY id";
	private final String INSERT_SHIFT_SCORE_SQL = "insert into \"shiftScore\" (\"shiftId\",\"userId\",\"lv1\",\"lv2\",\"lv3\",\"lv4\",\"lv5\",\"score\") "
			+ "values (?,?,?,?,?,?,?,?)";
	private final String INSERT_LIMIT_REC_SQL = "insert into \"timeLimitRec\" (\"userId\",\"minTimeRec\",\"maxTimeRec\") "
			+ "values (?,?,?)";
	private final String INSERT_WORKDAY_SQL = "insert into \"workDay\" (\"userId\",\"mon\",\"tue\",\"wed\",\"thu\",\"fri\",\"sat\",\"sun\") "
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
	
	
	public Integer checkAdmin(User user) {
		List<User> res = template.query(CHECK_BY_NICKNAME_SQL, 
				ps -> {
					ps.setString(1,user.getNickName());
					ps.setString(2, user.getPassword());
				},
				(rs,rowNum) -> new User(rs.getInt(1),rs.getString(2),
						rs.getString(3),rs.getInt(4),rs.getBoolean(5),rs.getString(6),rs.getBoolean(7)));
		if(res.isEmpty() || res.get(0).getIsManager() == false ){
			return -1;
		}else{
			return res.get(0).getUserId();
		}
	}

	
	public Integer checkEmpl(User user) {
		List<User> res = template.query(CHECK_BY_NICKNAME_SQL, 
				ps -> {
					ps.setString(1,user.getNickName());
					ps.setString(2, user.getPassword());
				},
				(rs,rowNum) -> new User(rs.getInt(1),rs.getString(2),
						rs.getString(3),rs.getInt(4),rs.getBoolean(5),rs.getString(6),rs.getBoolean(7)));
		if(res.isEmpty()){
			return -1;
		}else{
			return res.get(0).getUserId();
		}
	}


	public User getUser(Integer userId) {
		List<User> res = template.query(SEARCH_SQL, 
				ps -> ps.setInt(1,userId),
				(rs,rowNum) -> new User(rs.getInt(1),rs.getString(2),
						rs.getString(3),rs.getInt(4),rs.getBoolean(5),rs.getString(6),rs.getBoolean(7)));
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
						rs.getInt(4),rs.getBoolean(5),rs.getString(6),rs.getBoolean(7)));
		return list;
	}
	
	@Override
	public List<OtherUserVo> getOtherUserVoByLevel(Integer lv) {
		List<OtherUserVo> list = template.query(LOAD_BY_LV_SQL, 
				ps -> ps.setInt(1,lv),
				(rs,rowNum) -> new OtherUserVo(rs.getInt(1),rs.getString(2),
						rs.getInt(4),rs.getBoolean(5),rs.getString(6),rs.getBoolean(7)));
		return list;
	}
	@Override
	public OtherUserVo getOtherUserById(Integer id) {
		List<OtherUserVo> res = template.query(SEARCH_SQL, 
				ps -> ps.setInt(1,id),
				(rs,rowNum) -> new OtherUserVo(rs.getInt(1),rs.getString(2),
						rs.getInt(4),rs.getBoolean(5),rs.getString(6),rs.getBoolean(7)));
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
		int newId = res.get(res.size()-1)+1;
		return newId;
	}
	

	public Boolean checkNickName(String nickName){
		List<Integer> res = template.query(CHECK_NICKNAME_SQL, 
				ps -> ps.setString(1,nickName),
				(rs,rowNum) -> new Integer(0));
		if(res==null || res.size()==0){
			return false;
		}else{
			return true;
		}
	}
	
	
	@Override
	public void addUser(OtherUserVo stv) {
		//add user
		Integer userId = generateId();
		String name = stv.getName();
		String password = name;
		Integer workLevel = stv.getWorkLevel();
		Boolean isManager = stv.getIsManager();
		String nickName = name;
		template.update(INSERT_SQL,
				ps -> {
					ps.setInt(1, userId);
					ps.setString(2, name);
					ps.setString(3, password);
					ps.setInt(4, workLevel);
					ps.setBoolean(5, isManager);
					if(checkNickName(nickName)){
						String tmp=nickName;
						while(checkNickName(tmp)){
							tmp+=userId;
						}
						ps.setString(6, tmp);
					}else{
						ps.setString(6, nickName);
					}
					
					ps.setBoolean(7, false);
				});
		//add def roster
		//round shift
		List<Integer> shiftType = template.query(GET_SHIFT_TYPE_ID_SQL, 
				(rs,rowNum) -> new Integer(rs.getInt(1)));
		List<Integer> shiftType7 = new ArrayList();
		for(int i=0;i<7;i++){
			shiftType7.addAll(shiftType);
		}
		template.update(INSERT_THIS_ROSTER_SQL,
				ps -> {
					ps.setInt(1, userId);
					for(int i=0;i<7;i++){
						ps.setInt(i+2, shiftType7.get(i));
					}
				});
		template.update(INSERT_NEXT_ROSTER_SQL,
				ps -> {
					ps.setInt(1, userId);
					for(int i=0;i<7;i++){
						ps.setInt(i+2, shiftType7.get(i));
					}
				});
		
		//add def shiftScore
		List<ShiftDemand> cur = template.query(GET_SHIFT_DEMAND_SQL, 
				(rs,rowNum) -> new ShiftDemand(rs.getInt(1),rs.getInt(2),rs.getInt(3),
						rs.getInt(4),rs.getInt(5),rs.getInt(6)));
		ShiftDemand sum = new ShiftDemand(0,0,0,0,0,0);
		int size = cur.size();
		for(int i=1;i<size;i++){
			sum.setLv1(sum.getLv1()+cur.get(i).getLv1());
			sum.setLv2(sum.getLv2()+cur.get(i).getLv2());
			sum.setLv3(sum.getLv3()+cur.get(i).getLv3());
			sum.setLv4(sum.getLv4()+cur.get(i).getLv4());
			sum.setLv5(sum.getLv5()+cur.get(i).getLv5());
		}
		if(size!=0){
			sum.setLv1(sum.getLv1()/size);
			sum.setLv2(sum.getLv2()/size);
			sum.setLv3(sum.getLv3()/size);
			sum.setLv4(sum.getLv4()/size);
			sum.setLv5(sum.getLv5()/size);
		}
		
		for(Integer type : shiftType){
			template.update(INSERT_SHIFT_SCORE_SQL,
					ps -> {
						ps.setInt(1, type);
						ps.setInt(2, userId);
						ps.setInt(3, sum.getLv1());
						ps.setInt(4, sum.getLv2());
						ps.setInt(5, sum.getLv3());
						ps.setInt(6, sum.getLv4());
						ps.setInt(7, sum.getLv5());
						ps.setDouble(8, 3.0);
					});
		}
		
		//add def time limit rec
		template.update(INSERT_LIMIT_REC_SQL,
				ps -> {
					ps.setInt(1, userId);
					ps.setDouble(2, 40);
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
						rs.getString(3),rs.getInt(4),rs.getBoolean(5),rs.getString(6),rs.getBoolean(7)));
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
		template.update(UPDATE_PWD_SQL,
				ps -> {
					ps.setString(1, password);
					ps.setInt(2, id);
				});
	}

	@Override
	public void updateNickName(Integer id, String nickName) {
		template.update(UPDATE_NICKNAME_SQL,
				ps -> {
					ps.setString(1, nickName);
					ps.setInt(2, id);
				});
	}


	@Override
	public void recoverByNickName(String nickName) {
		template.update(RECOVER_BY_NICKNAME_SQL,
				nickName);
		
		List<Integer> id = template.query(CHECK_NICKNAME_SQL, 
				ps -> ps.setString(1,nickName),
				(rs,rowNum) -> new Integer(rs.getInt(1)));
		
		Integer userId = id.get(0);
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
				List<Integer> shiftType = template.query(GET_SHIFT_TYPE_ID_SQL, 
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
	public List<OtherUserVo> getDeletedUser() {
		List<OtherUserVo> list = template.query(LOAD_DELETED_SQL, 
				(rs,rowNum) -> new OtherUserVo(rs.getInt(1),rs.getString(2),
						rs.getInt(4),rs.getBoolean(5),rs.getString(6),rs.getBoolean(7)));
		return list;
	}

	
}
