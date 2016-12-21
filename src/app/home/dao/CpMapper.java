package app.home.dao;

import java.util.List;

import app.home.model.Cp;

public interface CpMapper {
	Cp selectCpByPrimaryKey(String id);

    int insertcp(Cp record);

    int insertSelectivecp(Cp record);

    Cp selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelectivecp(Cp record);

    int updateByPrimaryKey(Cp record);
    
    List<Cp> selectAll();

	int deleteCpByPrimaryKey(int id);
}
