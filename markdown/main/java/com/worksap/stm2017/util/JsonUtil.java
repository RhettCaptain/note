package com.worksap.stm2017.util;

public class JsonUtil {
	public static String jsonify(String... args){
		String json = "{";
		int len = args.length;
		if(len>=2){
			json = json + "\"" + args[0] + "\":\""
					+ args[1] + "\"";
		}
		for(int i=2;i<len;i=i+2){
			json = json + ",\"" + args[i] + "\":\""
					+ args[i+1] + "\"";
		}
		json += "}";
		return json;
	}
	public static void main(String[] args){
		System.out.println(JsonUtil.jsonify("firstname","rhett","sec","hh"));
		System.out.println(JsonUtil.jsonify("firstname","rhett"));
	}
}
