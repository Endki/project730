package com.bangba.project730.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.bangba.project730.model.dto.AlcoholDto;

@Mapper
public interface AlcoholDao {
	
	public void addAlcohol(AlcoholDto alcoholDto) throws Exception;
	public List<AlcoholDto> searchAlcohol(String searchtxt) throws Exception;
	public AlcoholDto searchAlcoholbyPK(int pk_alcohol) throws Exception;
	public int searchAlcoholPK(String name) throws Exception;
	public List<AlcoholDto> searchBaseAlcohol() throws Exception;
	
}
