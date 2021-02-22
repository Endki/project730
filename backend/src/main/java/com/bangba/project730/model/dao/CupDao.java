package com.bangba.project730.model.dao;

import org.apache.ibatis.annotations.Mapper;
import com.bangba.project730.model.dto.CupDto;

@Mapper
public interface CupDao {
	
	public void addCup(CupDto cupDto) throws Exception;
	public CupDto searchCup(String type, String size) throws Exception;
	public int searchCupPK(String type, String size) throws Exception;
	
}
