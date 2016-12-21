package app.home.service;

import java.util.List;

import app.home.model.Cp;

public interface CpService {
	List<Cp> selectcp();
	int deletecp(int id);
	Cp find(String id);
	Integer updateCp(Cp info);
	Integer addCp(Cp cp);
}
