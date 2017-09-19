package com.mybatisdao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mysqlmodel.Mumu;

public interface MumuMybatisMapper {
	
//	public int modifyMumuById(@Param(value = "id") int cid, @Param(value = "params") Map<String, Object> params);

	public List<Mumu> getMumuList(@Param(value = "params")HashMap<String, Object> params);


}
