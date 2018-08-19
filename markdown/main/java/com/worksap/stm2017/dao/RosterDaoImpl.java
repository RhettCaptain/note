package com.worksap.stm2017.dao;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.worksap.stm2017.model.Roster;
import com.worksap.stm2017.model.ShiftType;
import com.worksap.stm2017.model.TimeLimit;
import com.worksap.stm2017.model.User;
import com.worksap.stm2017.vo.OtherUserVo;
import com.worksap.stm2017.vo.RosterReportVo;
import com.worksap.stm2017.vo.RosterVo;
import com.worksap.stm2017.vo.ShiftDemandVo;
import com.worksap.stm2017.vo.ShiftScoreVo;
import com.worksap.stm2017.vo.ShiftTypeVo;
import com.worksap.stm2017.vo.TimeLimitRecVo;
import com.worksap.stm2017.vo.TimeLimitVo;
import com.worksap.stm2017.vo.WorkDayVo;

@Component
public class RosterDaoImpl implements RosterDao{
	private JdbcTemplate template;
	
	private final String GET_SHIFT_TYPE_SQL = "SELECT * FROM \"shiftType\" ORDER BY id";
	private final String ADD_SHIFT_TYPE_SQL = "INSERT INTO \"shiftType\" (id, name, \"beginTime\", \"endTime\", used)"
			+ "VALUES (?, ?, ?, ?, ?)";
	private final String GET_SHIFT_TYPE_BYID_SQL = "SELECT * FROM \"shiftType\" WHERE id=?";
	private final String UPDATE_SHIFT_TYPE_SQL = "UPDATE \"shiftType\" SET name=?,\"beginTime\"=?,\"endTime\"=?,used=? WHERE id=?";
	private final String DELETE_SHIFT_TYPE_BYID_SQL = "DELETE FROM \"shiftType\" WHERE id=?";
	
	private final String ADD_SHIFT_SCORE_SQL = "INSERT INTO \"shiftScore\" (\"shiftId\", \"userId\", \"lv1\", \"lv2\" ,"
			+ " \"lv3\", \"lv4\", \"lv5\",\"score\")"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private final String AVG_SHIFT_SCORE_BY_SHIFTID_SQL = "SELECT AVG(\"lv1\"),AVG(\"lv2\"),AVG(\"lv3\"),AVG(\"lv4\"),AVG(\"lv5\"),AVG(\"score\")"
			+ " FROM \"shiftScore\" WHERE \"shiftId\"=?";
	private final String DELETE_SHIFT_SCORE_BY_SHIFTID_SQL = "DELETE FROM \"shiftScore\" WHERE \"shiftId\"=?";
	private final String GET_SHIFT_SCORE_BY_USERID_SQL = "SELECT * FROM \"shiftScore\" WHERE \"userId\"=? ORDER BY \"shiftId\"";
	private final String UPDATE_SHIFT_SCORE_SQL = "UPDATE \"shiftScore\" SET lv1=?, lv2=?, lv3=?, lv4=?, lv5=? score=?"
			+ " WHERE \"userId\"=? AND \"shiftId\"=?";
	
	private final String ADD_SHIFT_DEMAND_SQL = "INSERT INTO \"shiftDemand\" (id, \"lv1\", \"lv2\" ,"
			+ " \"lv3\", \"lv4\", \"lv5\")"
			+ "VALUES (?, ?, ?, ?, ?, ?)";
	private final String GET_SHIFT_DEMAND_SQL = "SELECT * FROM \"shiftDemand\" ORDER BY id";
	private final String UPDATE_SHIFT_DEMAND_SQL = "UPDATE \"shiftDemand\" SET lv1=?, lv2=?, lv3=?, lv4=?, lv5=? WHERE id=?";
	private final String DELETE_SHIFT_DEMAND_BY_SHIFTID_SQL = "DELETE FROM \"shiftDemand\" WHERE id=?";
	
	private final String GET_TIME_LIMIT_SQL = "SELECT * FROM \"timeLimit\"";
	private final String AVG_TIME_LIMIT_REC_SQL = "SELECT AVG(\"minTimeRec\"),AVG(\"maxTimeRec\") FROM \"timeLimitRec\"";
	private final String UPDATE_TIME_LIMIT_SQL = "UPDATE \"timeLimit\" SET \"minTime\"=?, \"maxTime\"=?";
	private final String GET_TIME_LIMIT_REC_BY_USERID_SQL = "SELECT * FROM \"timeLimitRec\" WHERE \"userId\"=?";
	private final String UPDATE_TIME_LIMIT_REC_SQL = "UPDATE \"timeLimitRec\" SET \"minTimeRec\"=?, \"maxTimeRec\"=? WHERE \"userId\"=?";
	
	private final String GET_WORKDAY_BY_USERID_SQL = "SELECT * FROM \"workDay\" WHERE \"userId\"=?";
	private final String UPDATE_WORKDAY_BY_USERID_SQL = "UPDATE \"workDay\" SET \"mon\"=?, \"tus\"=?,\"wed\"=?, \"thu\"=?, "
			+ "\"fri\"=?, \"sat\"=?,\"sun\"=? WHERE \"userId\"=?";
	
	private final String GET_USER_SQL = "SELECT * FROM \"users\" ORDER BY \"userId\"";
	private final String GET_USER_IDS_SQL = "SELECT \"userId\" FROM \"users\"";
	private final String GET_USER_BY_LEVEL_SQL = "SELECT * FROM \"users\" WHERE \"workLevel\"=?";
	
	private final String GET_THIS_ROSTER_SQL = "SELECT * FROM \"thisWeekRoster\" ORDER BY \"userId\"";
	private final String GET_NEXT_ROSTER_SQL = "SELECT * FROM \"nextWeekRoster\" ORDER BY \"userId\"";
	private final String UPDATE_THISWEEK_ROSTER_BYID_SQL = "UPDATE \"thisWeekRoster\" SET \"monShift\"=?,\"tusShift\"=?,\"wedShift\"=?,"
			+ "\"thuShift\"=?,\"friShift\"=?,\"satShift\"=?,\"sunShift\"=? WHERE \"userId\"=? ";
	private final String UPDATE_NEXTWEEK_ROSTER_BYID_SQL = "UPDATE \"nextWeekRoster\" SET \"monShift\"=?,\"tusShift\"=?,\"wedShift\"=?,"
			+ "\"thuShift\"=?,\"friShift\"=?,\"satShift\"=?,\"sunShift\"=? WHERE \"userId\"=? ";
	
	private List<RosterVo> recommend = null;
	private List<RosterVo> shiftPrefMin = null;
	private List<RosterVo> shiftPrefAvg = null;
	private List<RosterVo> workTimeLong = null;
	private List<RosterVo> workTimeShort = null;
	private List<RosterVo> workdayPrefMin = null;
	private List<RosterVo> workdayPrefAvg = null;
	
	
	@Autowired 
	public RosterDaoImpl(JdbcTemplate template){
		this.template = template;
	}
	
	@Override
	public List<RosterVo> getRosterVo(int idx) {
		//1 this week,2 next week
		Map<Integer,String> typeMap= new HashMap<Integer,String>();
		Map<Integer,Double> timeMap= new HashMap<Integer,Double>();
		Map<Integer,String> userMap= new HashMap<Integer,String>();
		
		
		List<ShiftType> shiftType = template.query(GET_SHIFT_TYPE_SQL, 
				(rs,rowNum) -> {
					typeMap.put(rs.getInt(1),rs.getString(2));
					Double totalTime = (rs.getTime(4).getHours() + rs.getTime(4).getMinutes()/60.0) 
							- (rs.getTime(3).getHours() + rs.getTime(3).getMinutes()/60.0);
					if(totalTime < 0){
						totalTime += 24;
					}
					timeMap.put(rs.getInt(1),totalTime);
					return new ShiftType();
				});
		
		List<User> users = template.query(GET_USER_SQL, 
				(rs,rowNum) -> {
					userMap.put(rs.getInt(1),rs.getString(2));
					return new User();
				});
		
		List<RosterVo> rv = null;
		if(idx == 1){
			rv =template.query(GET_THIS_ROSTER_SQL,
					(rs,rowNum) -> {
						Integer userId = rs.getInt(1);
						String userName = userMap.get(userId);
						List<Integer> shiftId = new ArrayList<Integer>();
						List<String> shiftName = new ArrayList<String>();
						Double totalTime = 0.0;
						for(int i=0;i<7;i++){
							int typeId = rs.getInt(i+2);
							shiftId.add(typeId);
							shiftName.add(typeMap.get(typeId));
							totalTime += timeMap.get(typeId);
						}
						return new RosterVo(userId,userName,shiftName.get(0),shiftName.get(1),shiftName.get(2),shiftName.get(3),shiftName.get(4),
								shiftName.get(5),shiftName.get(6),totalTime,shiftId.get(0),shiftId.get(1),shiftId.get(2),shiftId.get(3),
								shiftId.get(4),shiftId.get(5),shiftId.get(6));
					});
		}else if(idx == 2){
			rv =template.query(GET_NEXT_ROSTER_SQL,
					(rs,rowNum) -> {
						Integer userId = rs.getInt(1);
						String userName = userMap.get(userId);
						List<Integer> shiftId = new ArrayList<Integer>();
						List<String> shiftName = new ArrayList<String>();
						Double totalTime = 0.0;
						for(int i=0;i<7;i++){
							int typeId = rs.getInt(i+2);
							shiftId.add(typeId);
							shiftName.add(typeMap.get(typeId));
							totalTime += timeMap.get(typeId);
						}
						return new RosterVo(userId,userName,shiftName.get(0),shiftName.get(1),shiftName.get(2),shiftName.get(3),shiftName.get(4),
								shiftName.get(5),shiftName.get(6),totalTime,shiftId.get(0),shiftId.get(1),shiftId.get(2),shiftId.get(3),
								shiftId.get(4),shiftId.get(5),shiftId.get(6));
					});
		}
		return rv;
	}

	@Override
	public List<ShiftTypeVo> getShiftTypeVo() {
		List<ShiftTypeVo> shiftType = template.query(GET_SHIFT_TYPE_SQL, 
				(rs,rowNum) -> new ShiftTypeVo(rs.getInt(1),rs.getString(2),
						rs.getTime(3),rs.getTime(4),0.0,rs.getBoolean(5),0.0) );
		//getShiftType
		for(ShiftTypeVo stv : shiftType){
			Double totalTime = (stv.getEndTime().getHours() + stv.getEndTime().getMinutes()/60.0) 
					- (stv.getBeginTime().getHours() + stv.getBeginTime().getMinutes()/60.0);
			if(totalTime < 0){
				totalTime += 24;
			}
			stv.setTotal(totalTime);
			
			//calculate score
			Double score = DataAccessUtils.requiredSingleResult(
					template.query(AVG_SHIFT_SCORE_BY_SHIFTID_SQL, 
							ps -> ps.setInt(1,stv.getId()),
							(rs,rowNum) -> new Double(rs.getDouble(6))
							));
			stv.setScore(score);
		}
		
		return shiftType;
		
		
	}

	@Override
	public ShiftTypeVo getShiftTypeById(Integer id) {
		// TODO Auto-generated method stub 
		List<ShiftTypeVo> shiftType = template.query(GET_SHIFT_TYPE_BYID_SQL,
				ps -> ps.setInt(1,id),
				(rs,rowNum) -> new ShiftTypeVo(rs.getInt(1),rs.getString(2),
						rs.getTime(3),rs.getTime(4),0.0,rs.getBoolean(5),0.0) );
		if(shiftType==null || shiftType.size()==0){
			System.out.println("no such shift type");
			return null;
		}
		if(shiftType.size()>1){
			System.out.println("warning: multi id!");
		}
		
		ShiftTypeVo stv = shiftType.get(0);
		Double totalTime = (stv.getEndTime().getHours() + stv.getEndTime().getMinutes()/60.0) 
				- (stv.getBeginTime().getHours() + stv.getBeginTime().getMinutes()/60.0);
		if(totalTime < 0){
			totalTime += 24;
		}
		stv.setTotal(totalTime);
		
		//calculate score
		Double score = DataAccessUtils.requiredSingleResult(
				template.query(AVG_SHIFT_SCORE_BY_SHIFTID_SQL, 
						ps -> ps.setInt(1,stv.getId()),
						(rs,rowNum) -> new Double(rs.getDouble(6))
						));
		stv.setScore(score);
		return stv;
	}

	@Override
	public void addShiftType(ShiftTypeVo stv) {
		Integer id = generateId(GET_USER_IDS_SQL,1);
		template.update(ADD_SHIFT_TYPE_SQL,
				ps -> {
					ps.setInt(1, id);
					ps.setString(2, stv.getName());
					ps.setTime(3, stv.getBeginTime());
					ps.setTime(4, stv.getEndTime());
					ps.setBoolean(5, stv.getUsed());
				});
		
		//modify shiftScore
		//add default scores for everyone
		List<Integer> userIds = template.query(GET_USER_IDS_SQL, 
				(rs,rowNum) -> new Integer(rs.getInt(1)));
		for(Integer user : userIds){
			template.update(ADD_SHIFT_SCORE_SQL,
					ps -> {
						ps.setInt(1, id);
						ps.setInt(2, user);
						for(int i=3;i<=7;i++){
							ps.setInt(i, 0);
						}
						ps.setDouble(8, 3.0);
					});
		}
		
		//modify shiftDemand
		//add default demand for new shift 
		template.update(ADD_SHIFT_DEMAND_SQL,
				ps -> {
					ps.setInt(1, id);
					for(int i=2;i<=6;i++){
						ps.setInt(i, 0);
					}
				});
		
	}

	@Override
	public void updateShiftType(ShiftTypeVo stv) {
		template.update(UPDATE_SHIFT_TYPE_SQL,
				ps -> {
					ps.setString(1, stv.getName());
					ps.setTime(2, stv.getBeginTime());
					ps.setTime(3, stv.getEndTime());
					ps.setBoolean(4, stv.getUsed());
					ps.setInt(5, stv.getId());
				});
	}

	@Override
	public void deleteShiftType(Integer id) {
		template.update(DELETE_SHIFT_TYPE_BYID_SQL,
				ps -> ps.setInt(1, id));
		
		//modify shiftScore
		//delete shift related recored
		template.update(DELETE_SHIFT_SCORE_BY_SHIFTID_SQL,
				ps -> ps.setInt(1, id));
		
		//modify shiftDemand
		//delete shift related recored
		template.update(DELETE_SHIFT_DEMAND_BY_SHIFTID_SQL,
				ps -> ps.setInt(1, id));
	}

	@Override
	public List<ShiftDemandVo> getShiftDemandVo() {
		//get type map
		Map<Integer,String> typeMap= new HashMap<Integer,String>();
		List<ShiftType> shiftType = template.query(GET_SHIFT_TYPE_SQL, 
				(rs,rowNum) -> {
					typeMap.put(rs.getInt(1),rs.getString(2));
					return new ShiftType();
				});
		
		
		//get demand
		List<ShiftDemandVo> list = template.query(GET_SHIFT_DEMAND_SQL, 
				(rs,rowNum) -> {
					Integer id = rs.getInt(1);
					ShiftDemandVo ret = new ShiftDemandVo(id,typeMap.get(id),
							rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),
							0,0,0,0,0);
					List<ShiftDemandVo> tmp = template.query(AVG_SHIFT_SCORE_BY_SHIFTID_SQL, 
							ps -> ps.setInt(1,id),
							(rsRec,rowNumRec) -> {
								ret.setLv1rec(rsRec.getInt(1));
								ret.setLv2rec(rsRec.getInt(2));
								ret.setLv3rec(rsRec.getInt(3));
								ret.setLv4rec(rsRec.getInt(4));
								ret.setLv5rec(rsRec.getInt(5));
								return new ShiftDemandVo();
							});
					
					return ret;
				});
						
		return list;
	}

	@Override
	public void updateShiftDemand(ShiftDemandVo sdv) {
		template.update(UPDATE_SHIFT_DEMAND_SQL,
				ps ->{
					ps.setInt(1, sdv.getLv1());
					ps.setInt(2, sdv.getLv2());
					ps.setInt(3, sdv.getLv3());
					ps.setInt(4, sdv.getLv4());
					ps.setInt(5, sdv.getLv5());
					ps.setInt(6, sdv.getId());
				});
	}

	@Override
	public TimeLimitVo getTimeLimitVo() {
		TimeLimitVo rec = DataAccessUtils.requiredSingleResult(
				template.query(AVG_TIME_LIMIT_REC_SQL,
						(rs,rowNum) -> new TimeLimitVo(0.0,0.0,rs.getDouble(1),rs.getDouble(2))
				));
				
		TimeLimitVo one = DataAccessUtils.requiredSingleResult(
				template.query(GET_TIME_LIMIT_SQL,
						(rs,rowNum) -> new TimeLimitVo(rs.getDouble(1),rs.getDouble(2),rec.getMinTimeRec(),rec.getMaxTimeRec())
				));
				
		return one;
	}

	@Override
	public void updateTimeLimit(TimeLimitVo tlv) {
		template.update(UPDATE_TIME_LIMIT_SQL,
				ps -> {
					ps.setDouble(1, tlv.getMinTime());
					ps.setDouble(2, tlv.getMaxTime());
				});
	}

	@Override
	public List<ShiftScoreVo> getShiftScoreVoByUser(Integer userId) {
		Map<Integer,String> typeMap= new HashMap<Integer,String>();
		
		List<ShiftType> tmp = template.query(GET_SHIFT_TYPE_SQL, 
				(rs,rowNum) -> {
					typeMap.put(rs.getInt(1),rs.getString(2));
					return new ShiftType();
				});

		
		List<ShiftScoreVo> list = template.query(GET_SHIFT_SCORE_BY_USERID_SQL, 
				ps -> ps.setInt(1,userId),
				(rs,rowNum) -> new ShiftScoreVo(rs.getInt(1),typeMap.get(rs.getInt(1)),
						rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),
						rs.getDouble(8)));
		return list;
	}

	@Override
	public void updateShiftScoreVo(Integer userId, ShiftScoreVo ssv) {
		template.update(UPDATE_SHIFT_SCORE_SQL,
				ps -> {
					ps.setInt(1, ssv.getLv1());
					ps.setInt(2, ssv.getLv2());
					ps.setInt(3, ssv.getLv3());
					ps.setInt(4, ssv.getLv4());
					ps.setInt(5, ssv.getLv5());
					ps.setDouble(6, ssv.getScore());
					ps.setInt(7, userId);
					ps.setInt(8, ssv.getId());
				});
		
	}

	@Override
	public TimeLimitRecVo getTimeLimitRecVoByUser(Integer userId) {
		List<TimeLimitRecVo> one = template.query(GET_TIME_LIMIT_REC_BY_USERID_SQL, 
				ps -> ps.setInt(1,userId),
				(rs,rowNum) -> new TimeLimitRecVo(rs.getDouble(2),rs.getDouble(3))
				);
		if(one==null || one.size() == 0){
			return null;
		}
		if(one.size() > 1){
			System.out.println("warning: multi res!");
		}
		return one.get(0);
	}

	@Override
	public void updateTimeLimitRec(Integer userId, TimeLimitRecVo tlrv) {
		template.update(UPDATE_TIME_LIMIT_REC_SQL,
				ps -> {
					ps.setDouble(1, tlrv.getMinTimeRec());
					ps.setDouble(2, tlrv.getMaxTimeRec());
					ps.setInt(3, userId);
				});
	}

	@Override
	public WorkDayVo getWorkDayVoByUser(Integer userId) {
		WorkDayVo wdv = DataAccessUtils.requiredSingleResult(
				template.query(GET_WORKDAY_BY_USERID_SQL, 
						ps -> ps.setInt(1,userId),
						(rs,rowNum) -> new WorkDayVo(rs.getDouble(2),rs.getDouble(3),
								rs.getDouble(4),rs.getDouble(5),rs.getDouble(6),
								rs.getDouble(7),rs.getDouble(8))));
		return wdv;
	}

	@Override
	public void updateWorkDay(Integer userId, WorkDayVo wdv) {
		template.update(UPDATE_WORKDAY_BY_USERID_SQL,
				ps -> {
					ps.setDouble(1, wdv.getMon());
					ps.setDouble(2, wdv.getTus());
					ps.setDouble(3, wdv.getWed());
					ps.setDouble(4, wdv.getThu());
					ps.setDouble(5, wdv.getFri());
					ps.setDouble(6, wdv.getSat());
					ps.setDouble(7, wdv.getSun());
					ps.setInt(8, userId);
				});
		
	}

	public RosterReportVo getRosterReportVo(List<Roster> rosters) {
				
		//mapper
		Map<Integer,String> typeMap= new HashMap<Integer,String>();
		Map<Integer,Double> timeMap= new HashMap<Integer,Double>();
		Map<Integer,String> userMap= new HashMap<Integer,String>();
		Map<Integer,Integer> levelMap= new HashMap<Integer,Integer>();
		Map<Integer,List<ShiftScoreVo>> scoreMap = new HashMap<Integer,List<ShiftScoreVo>>();
		Map<Integer,List<Double>> workdayMap= new HashMap<Integer,List<Double>>();
		
		
		List<ShiftType> tmpType = template.query(GET_SHIFT_TYPE_SQL, 
				(rs,rowNum) -> {
					typeMap.put(rs.getInt(1),rs.getString(2));
					Double totalTime = (rs.getTime(4).getHours() + rs.getTime(4).getMinutes()/60.0) 
							- (rs.getTime(3).getHours() + rs.getTime(3).getMinutes()/60.0);
					if(totalTime < 0){
						totalTime += 24;
					}
					timeMap.put(rs.getInt(1),totalTime);
					return new ShiftType();
				});
		
		List<User> tmpUser = template.query(GET_USER_SQL, 
				(rs,rowNum) -> {
					userMap.put(rs.getInt(1),rs.getString(2));
					levelMap.put(rs.getInt(1),rs.getInt(4));
					scoreMap.put(rs.getInt(1), getShiftScoreVoByUser(rs.getInt(1)));
					WorkDayVo wdv = getWorkDayVoByUser(rs.getInt(1));
					List<Double> tmp = Arrays.asList(wdv.getMon(),wdv.getTus(),wdv.getWed(),
							wdv.getThu(),wdv.getFri(),wdv.getSat(),wdv.getSun());
					workdayMap.put(rs.getInt(1), tmp);
					return new User();
				});
		
		//time limit related
		TimeLimit timeLimit = DataAccessUtils.requiredSingleResult(
				 template.query(GET_TIME_LIMIT_SQL, 
							(rs,rowNum) -> new TimeLimit(rs.getDouble(1),rs.getDouble(2))
				));
		Double minTimeLimit = timeLimit.getMinTime();
		Double maxTimeLimit = timeLimit.getMaxTime();
		Boolean timeLimitOk = true;
		List<Integer> timeLimitBadUsers = new ArrayList<Integer>();
		
		
		//demand related
		List<List<ShiftDemandVo>> llist = new ArrayList<List<ShiftDemandVo>>();
		for(int day =0 ;day < 7;day++){
			List<ShiftDemandVo> oneDay = new ArrayList();
			llist.add(oneDay);
		}
		//get real demand
		List<ShiftDemandVo> realDemand = template.query(GET_SHIFT_DEMAND_SQL, 
				(rs,rowNum) -> {
					Integer id = rs.getInt(1);
					for(int i=0;i<7;i++){
						llist.get(i).add(
								new ShiftDemandVo(id,typeMap.get(id),
										0,0,0,0,0,
								rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6)));
					}
					ShiftDemandVo ret = new ShiftDemandVo(id,typeMap.get(id),
							0,0,0,0,0,
							rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));
					return ret;
				});
		
		//pref & workTime
		List<Double> shiftPrefs = new ArrayList();
		List<Double> workTimes = new ArrayList();
		List<Double> workdayPrefs = new ArrayList();
	
		//each user
		for(Roster ros : rosters){
			//time limit
			Double[] dayTimes = {timeMap.get(ros.getMonShift()),timeMap.get(ros.getTusShift()),timeMap.get(ros.getWedShift())
					,timeMap.get(ros.getThuShift()), timeMap.get(ros.getFriShift()),timeMap.get(ros.getSatShift()),timeMap.get(ros.getSunShift())};
			Double weekTime = 0.0;
			Double workDays = 0.0;
			//pref
			Double weekShiftPref = 0.0;
			//demand
			Integer lv = levelMap.get(ros.getUserId());
			Integer[] shifts = {ros.getMonShift(),ros.getTusShift(),ros.getWedShift(),
					ros.getThuShift(),ros.getFriShift(),ros.getSatShift(),ros.getSunShift()};
			
			//each day
			for(int i=0;i<7;i++){
				weekTime += dayTimes[i];	//workTime
				//workday
				if(dayTimes[i] == 0){
					workDays += 5;
				}else{
					workDays += workdayMap.get(ros.getUserId()).get(i);
				}
				//
				//demand
				for(ShiftDemandVo sdv : llist.get(i)){
					if(sdv.getId() == shifts[i]){
						switch (lv) {
						case 1:
							sdv.setLv1(sdv.getLv1()+1);
							break;
						case 2:
							sdv.setLv2(sdv.getLv2()+1);
							break;
						case 3:
							sdv.setLv3(sdv.getLv3()+1);
							break;
						case 4:
							sdv.setLv4(sdv.getLv4()+1);
							break;
						case 5:
							sdv.setLv5(sdv.getLv5()+1);
							break;
						default:
							break;
						}
					}
				}
				//shift pref score
				for(ShiftScoreVo ssv : scoreMap.get(ros.getUserId())){
					if(ssv.getId() == shifts[i]){
						weekShiftPref += ssv.getScore();
					}
				}
			}
			
			
			if(weekTime < minTimeLimit || weekTime > maxTimeLimit){
				timeLimitOk = false;
				timeLimitBadUsers.add(ros.getUserId());
			}
			workTimes.add(weekTime);
			shiftPrefs.add(weekShiftPref/7);
			workdayPrefs.add(workDays/7);
		}
		
		Boolean demandOk = true;
		for(int i=0;i<7;i++){
			for(ShiftDemandVo sdv:llist.get(i)){
				if(sdv.getLv1()<sdv.getLv1rec() || sdv.getLv2()<sdv.getLv2rec() || sdv.getLv3()<sdv.getLv3rec()
						|| sdv.getLv4()<sdv.getLv4rec() || sdv.getLv5()<sdv.getLv5rec()){
					demandOk = false;
					break;
				}
			}
			if(!demandOk){
				break;
			}
		}
		
		//fill in
		Collections.sort(shiftPrefs);
		Collections.sort(workTimes);
		Collections.sort(workdayPrefs);
		int size = shiftPrefs.size();
		Double minShiftPref = shiftPrefs.get(0);
		Double avgShiftPref = 0.0;
		Double maxShiftPref = shiftPrefs.get(size-1);
		Double minWorkTime = workTimes.get(0);
		Double avgWorkTime = 0.0;
		Double maxWorkTime = workTimes.get(size-1);
		Double minWorkdayPref = workdayPrefs.get(0);
		Double avgWorkdayPref = 0.0;
		Double maxWorkdayPref = workdayPrefs.get(size-1);
		for(int i=0;i<size;i++){
			avgShiftPref += shiftPrefs.get(i);
			avgWorkTime += workTimes.get(i);
			avgWorkdayPref += workdayPrefs.get(i);
		}
		if(size>0){
			avgShiftPref /= size;
			avgWorkTime /= size;
			avgWorkdayPref /= size;
		}
		Double workTimeBalance = 0.0;
		if(maxWorkTime > 0){
			workTimeBalance = minWorkTime / maxWorkTime;
		}
		Double compScore = (minShiftPref + avgShiftPref + minWorkdayPref + avgWorkdayPref)/5 + workTimeBalance;
				
		RosterReportVo report = new RosterReportVo(timeLimitOk,demandOk,compScore,minShiftPref,avgShiftPref,maxShiftPref,
				minWorkTime,avgWorkTime,maxWorkTime,minWorkdayPref,avgWorkdayPref,maxWorkdayPref,llist);

		return report;
	}
	
	@Override
	public List<RosterReportVo> getRosterReportVo() {
		//get roster
		List<Roster> thisWeekRoster = template.query(GET_THIS_ROSTER_SQL, 
				(rs,rowNum) -> new Roster(rs.getInt(1),rs.getInt(2),rs.getInt(3),
						rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8)));
		List<Roster> nextWeekRoster = template.query(GET_NEXT_ROSTER_SQL, 
				(rs,rowNum) -> new Roster(rs.getInt(1),rs.getInt(2),rs.getInt(3),
						rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8)));
		
		List<RosterReportVo> result = new ArrayList();
		result.add(getRosterReportVo(thisWeekRoster));
		result.add(getRosterReportVo(nextWeekRoster));
		return result;
		
	}

	@Override
	public void updateRosterById(String which, RosterVo rv) {
		String sql;
		if(which.equals("thisWeek")){
			sql = UPDATE_THISWEEK_ROSTER_BYID_SQL;
		}else{
			sql = UPDATE_NEXTWEEK_ROSTER_BYID_SQL;
		}
		template.update(sql,
				ps -> {
					ps.setInt(1, rv.getMonId());
					ps.setInt(2, rv.getTusId());
					ps.setInt(3, rv.getWedId());
					ps.setInt(4, rv.getThuId());
					ps.setInt(5, rv.getFriId());
					ps.setInt(6, rv.getSatId());
					ps.setInt(7, rv.getSunId());
					ps.setInt(8, rv.getUserId());
				});
	}

	@Override
	public List<RosterReportVo> getNewRosterReportVo() {
		List<RosterReportVo> result = new ArrayList();
		List<Roster> recRos = new ArrayList();
		List<Roster> spmRos = new ArrayList();
		List<Roster> spaRos = new ArrayList();
		List<Roster> wtlRos = new ArrayList();
		List<Roster> wtsRos = new ArrayList();
		List<Roster> wpmRos = new ArrayList();
		List<Roster> wpaRos = new ArrayList();
		int size = recommend.size();
		for(int i=0;i<size;i++){
			recRos.add(new Roster(recommend.get(i).getUserId(),recommend.get(i).getMonId(),recommend.get(i).getTusId(),recommend.get(i).getWedId(),
					recommend.get(i).getThuId(),recommend.get(i).getFriId(),recommend.get(i).getSatId(),recommend.get(i).getSunId()));
			spmRos.add(new Roster(shiftPrefMin.get(i).getUserId(),shiftPrefMin.get(i).getMonId(),shiftPrefMin.get(i).getTusId(),shiftPrefMin.get(i).getWedId(),
					shiftPrefMin.get(i).getThuId(),shiftPrefMin.get(i).getFriId(),shiftPrefMin.get(i).getSatId(),shiftPrefMin.get(i).getSunId()));
			spaRos.add(new Roster(shiftPrefAvg.get(i).getUserId(),shiftPrefAvg.get(i).getMonId(),shiftPrefAvg.get(i).getTusId(),shiftPrefAvg.get(i).getWedId(),
					shiftPrefAvg.get(i).getThuId(),shiftPrefAvg.get(i).getFriId(),shiftPrefAvg.get(i).getSatId(),shiftPrefAvg.get(i).getSunId()));
			wtlRos.add(new Roster(workTimeLong.get(i).getUserId(),workTimeLong.get(i).getMonId(),workTimeLong.get(i).getTusId(),workTimeLong.get(i).getWedId(),
					workTimeLong.get(i).getThuId(),workTimeLong.get(i).getFriId(),workTimeLong.get(i).getSatId(),workTimeLong.get(i).getSunId()));
			wtsRos.add(new Roster(workTimeShort.get(i).getUserId(),workTimeShort.get(i).getMonId(),workTimeShort.get(i).getTusId(),workTimeShort.get(i).getWedId(),
					workTimeShort.get(i).getThuId(),workTimeShort.get(i).getFriId(),workTimeShort.get(i).getSatId(),workTimeShort.get(i).getSunId()));
			wpmRos.add(new Roster(workdayPrefMin.get(i).getUserId(),workdayPrefMin.get(i).getMonId(),workdayPrefMin.get(i).getTusId(),workdayPrefMin.get(i).getWedId(),
					workdayPrefMin.get(i).getThuId(),workdayPrefMin.get(i).getFriId(),workdayPrefMin.get(i).getSatId(),workdayPrefMin.get(i).getSunId()));
			wpaRos.add(new Roster(workdayPrefAvg.get(i).getUserId(),workdayPrefAvg.get(i).getMonId(),workdayPrefAvg.get(i).getTusId(),workdayPrefAvg.get(i).getWedId(),
					workdayPrefAvg.get(i).getThuId(),workdayPrefAvg.get(i).getFriId(),workdayPrefAvg.get(i).getSatId(),workdayPrefAvg.get(i).getSunId()));
			
		}
		for(int i=0;i<size;i++){
			System.out.println(recRos.get(i).getUserId());
			System.out.println(recRos.get(i).getMonShift());
			System.out.println(recRos.get(i).getTusShift());
			System.out.println(recRos.get(i).getWedShift());
		}
		result.add(getRosterReportVo(recRos));
		result.add(getRosterReportVo(spmRos));
		result.add(getRosterReportVo(spaRos));
		result.add(getRosterReportVo(wtlRos));
		result.add(getRosterReportVo(wtsRos));
		result.add(getRosterReportVo(wpmRos));
		result.add(getRosterReportVo(wpaRos));
		return result;
	}

	@Override
	public void updateNewRosterById(String which, RosterVo rv) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void generateRosters() {
		//mapper
		Map<Integer,String> typeMap= new HashMap<Integer,String>();
		Map<Integer,Double> timeMap= new HashMap<Integer,Double>();
		Map<Integer,String> userMap= new HashMap<Integer,String>();
		Map<Integer,Integer> levelMap= new HashMap<Integer,Integer>();
		Map<Integer,List<ShiftScoreVo>> scoreMap = new HashMap<Integer,List<ShiftScoreVo>>();
		Map<Integer,List<Double>> workdayMap= new HashMap<Integer,List<Double>>();
				
		List<ShiftType> tmpType = template.query(GET_SHIFT_TYPE_SQL, 
				(rs,rowNum) -> {
					typeMap.put(rs.getInt(1),rs.getString(2));
					Double totalTime = (rs.getTime(4).getHours() + rs.getTime(4).getMinutes()/60.0) 
							- (rs.getTime(3).getHours() + rs.getTime(3).getMinutes()/60.0);
					if(totalTime < 0){
						totalTime += 24;
					}
					timeMap.put(rs.getInt(1),totalTime);
					return new ShiftType();
				});
				
		List<User> tmpUser = template.query(GET_USER_SQL, 
				(rs,rowNum) -> {
					userMap.put(rs.getInt(1),rs.getString(2));
					levelMap.put(rs.getInt(1),rs.getInt(4));
					scoreMap.put(rs.getInt(1), getShiftScoreVoByUser(rs.getInt(1)));
					WorkDayVo wdv = getWorkDayVoByUser(rs.getInt(1));
					List<Double> tmp = Arrays.asList(wdv.getMon(),wdv.getTus(),wdv.getWed(),
							wdv.getThu(),wdv.getFri(),wdv.getSat(),wdv.getSun());
					workdayMap.put(rs.getInt(1), tmp);
					return new User();
				});
				
		//group
		List<List<RosterVo>> lvs = new ArrayList(); 
		lvs.add(template.query(GET_USER_BY_LEVEL_SQL, 
				ps -> ps.setInt(1,1),
				(rs,rowNum) -> {
					Integer id = rs.getInt(1);
					Integer defShiftId = 0;
					String defShiftName = "rest";
					return new RosterVo(id,userMap.get(id),defShiftName,defShiftName,defShiftName,defShiftName,defShiftName,defShiftName,defShiftName,
							0.0,defShiftId,defShiftId,defShiftId,defShiftId,defShiftId,defShiftId,defShiftId);
				}));
		lvs.add(template.query(GET_USER_BY_LEVEL_SQL, 
				ps -> ps.setInt(1,2),
				(rs,rowNum) -> {
					Integer id = rs.getInt(1);
					Integer defShiftId = 0;
					String defShiftName = "rest";
					return new RosterVo(id,userMap.get(id),defShiftName,defShiftName,defShiftName,defShiftName,defShiftName,defShiftName,defShiftName,
							0.0,defShiftId,defShiftId,defShiftId,defShiftId,defShiftId,defShiftId,defShiftId);
				}));
		lvs.add(template.query(GET_USER_BY_LEVEL_SQL, 
				ps -> ps.setInt(1,3),
				(rs,rowNum) -> {
					Integer id = rs.getInt(1);
					Integer defShiftId = 0;
					String defShiftName = "rest";
					return new RosterVo(id,userMap.get(id),defShiftName,defShiftName,defShiftName,defShiftName,defShiftName,defShiftName,defShiftName,
							0.0,defShiftId,defShiftId,defShiftId,defShiftId,defShiftId,defShiftId,defShiftId);
				}));
		lvs.add(template.query(GET_USER_BY_LEVEL_SQL, 
				ps -> ps.setInt(1,4),
				(rs,rowNum) -> {
					Integer id = rs.getInt(1);
					Integer defShiftId = 0;
					String defShiftName = "rest";
					return new RosterVo(id,userMap.get(id),defShiftName,defShiftName,defShiftName,defShiftName,defShiftName,defShiftName,defShiftName,
							0.0,defShiftId,defShiftId,defShiftId,defShiftId,defShiftId,defShiftId,defShiftId);
				}));
		lvs.add(template.query(GET_USER_BY_LEVEL_SQL, 
				ps -> ps.setInt(1,5),
				(rs,rowNum) -> {
					Integer id = rs.getInt(1);
					Integer defShiftId = 0;
					String defShiftName = "rest";
					return new RosterVo(id,userMap.get(id),defShiftName,defShiftName,defShiftName,defShiftName,defShiftName,defShiftName,defShiftName,
							0.0,defShiftId,defShiftId,defShiftId,defShiftId,defShiftId,defShiftId,defShiftId);
				}));
		//demand
		List<ShiftDemandVo> demands = getShiftDemandVo();
		
		
		//base
		int[] maxNum = {lvs.get(0).size(),lvs.get(1).size(),lvs.get(2).size(),lvs.get(3).size(),lvs.get(4).size()};
		for(int day=0;day<7;day++){
			int[] existNum = {0,0,0,0,0,0};
			for(int lv=0;lv<5;lv++){
				Collections.sort(lvs.get(lv));
			}
			for(ShiftDemandVo aShift : demands){
				int[] nums = {aShift.getLv1(),aShift.getLv2(),aShift.getLv3(),aShift.getLv4(),aShift.getLv5()};
				int shiftId = aShift.getId();
				int idx = 0;
				
				for(int lv=0;lv<5;lv++){
					for(idx=existNum[lv];idx< Math.min(existNum[lv]+nums[lv],maxNum[lv]);idx++){
						switch(day){
						case 0:
							lvs.get(lv).get(idx).setMonId(shiftId);
							lvs.get(lv).get(idx).setMonShift(typeMap.get(shiftId));
							break;
						case 1:
							lvs.get(lv).get(idx).setTusId(shiftId);
							lvs.get(lv).get(idx).setTusShift(typeMap.get(shiftId));
							break;
						case 2:
							lvs.get(lv).get(idx).setWedId(shiftId);
							lvs.get(lv).get(idx).setWedShift(typeMap.get(shiftId));
							break;
						case 3:
							lvs.get(lv).get(idx).setThuId(shiftId);
							lvs.get(lv).get(idx).setThuShift(typeMap.get(shiftId));
							break;
						case 4:
							lvs.get(lv).get(idx).setFriId(shiftId);
							lvs.get(lv).get(idx).setFriShift(typeMap.get(shiftId));
							break;
						case 5:
							lvs.get(lv).get(idx).setSatId(shiftId);
							lvs.get(lv).get(idx).setSatShift(typeMap.get(shiftId));
							break;
						case 6:
							lvs.get(lv).get(idx).setSunId(shiftId);
							lvs.get(lv).get(idx).setSunShift(typeMap.get(shiftId));
							break;
						default:
							break;
						}
						Double t = lvs.get(lv).get(idx).getTotalTime();
						lvs.get(lv).get(idx).setTotalTime(t + timeMap.get(shiftId));
					}
					existNum[lv] = Math.min(existNum[lv]+nums[lv],maxNum[lv]);
				}
			}			
		}
		
		//collect
		recommend = new ArrayList();
		for(int i=0;i<5;i++){
			recommend.addAll(lvs.get(i));
		}
		shiftPrefMin = recommend;
		shiftPrefAvg = recommend;
		workTimeLong = recommend;
		workTimeShort = recommend;
		workdayPrefMin = recommend;
		workdayPrefAvg = recommend;
	}
	
	@Override
	public List<RosterVo> getNewRosterVo(String which) {
		if(which.equals("recommend")){
			return recommend;
		}
		if(which.equals("shiftPrefMin")){
			return shiftPrefMin;
		}
		if(which.equals("shiftPrefAvg")){
			return shiftPrefAvg;
		}
		if(which.equals("workTimeLong")){
			return workTimeLong;
		}
		if(which.equals("workTimeShort")){
			return workTimeShort;
		}
		if(which.equals("workdayPrefMin")){
			return workdayPrefMin;
		}
		if(which.equals("workdayPrefAvg")){
			return workdayPrefAvg;
		}
		return null;
	}
	
	

	private Integer generateId(String sql,Integer startIdx){
		Integer newId = startIdx;
		List<Integer> ids = template.query(sql, 
				(rs,rowNum) -> new Integer(rs.getInt(1)));
		for(Integer id : ids){
			if(newId.equals(id)){
				newId++;
			}else{
				
				break;
			}
		}
		return newId;
	}

	

}
