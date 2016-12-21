package app.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.home.dao.ArctileBufferMapper;
import app.home.model.ArctileBuffer;
import app.home.service.ArctileBufferService;
@Service("arctilebuffer")
public class ArctileBufferServiceImpl implements ArctileBufferService {
	 private ArctileBufferMapper thismapper;

	public ArctileBufferMapper getThismapper() {
		return thismapper;
	}
    @Autowired
	public void setThismapper(ArctileBufferMapper thismapper) {
		this.thismapper = thismapper;
	}
    @Override
	public int insertarcilebuffer(ArctileBuffer arctileBuffer) {
		return thismapper.saveUseridAndArctileid(arctileBuffer);

	}
	@Override
	public int insertLoged(int userid, int arctileid, String date) {
		// TODO Auto-generated method stub
		return thismapper.insertLoged(userid, arctileid, date);
	}
	@Override
	public List<ArctileBuffer> selectIfRecommendArctile(int useriid) {
		// TODO Auto-generated method stub
		return thismapper.selectIfRecommendArctiled(useriid);
	}
}
