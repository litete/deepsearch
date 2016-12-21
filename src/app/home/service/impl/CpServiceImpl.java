package app.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.home.dao.CpMapper;
import app.home.model.Cp;
import app.home.service.CpService;

@Service("cpservice")
public class CpServiceImpl implements CpService {
	
	private CpMapper thismapper;

	public CpMapper getThismapper() {
		return thismapper;
	}

	@Autowired
	public void setThismapper(CpMapper thismapper) {
		this.thismapper = thismapper;
	}

	public List<Cp> selectcp() {
		return thismapper.selectAll();
	}

	@Override
	public int deletecp(int id) {
		return thismapper.deleteCpByPrimaryKey(id);
	}

	@Override
	public Cp find(String id) {
		return thismapper.selectCpByPrimaryKey(id);
	}

	@Override
	public Integer updateCp(Cp info) {
		return thismapper.updateByPrimaryKeySelectivecp(info);
	}

	@Override
	public Integer addCp(Cp cp) {
		// TODO Auto-generated method stub
		return thismapper.insertSelectivecp(cp);
	}

}
