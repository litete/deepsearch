package app.home.controller;

import app.home.Singleton.SingleTon;
import app.home.function.CosineDistance;
import app.home.function.CosineDistance.Vector;
import app.home.model.*;
import app.home.service.ArctileBufferService;
import app.home.service.ArctileService;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/arctile")
public class ArctileController extends BaseController {
    private ArctileService arctileService;
    private ArctileBufferService arctileBufferService;

    public ArctileBufferService getArctileBufferService() {
        return arctileBufferService;
    }

    @Autowired
    public void setArctileBufferService(ArctileBufferService arctileBufferService) {
        this.arctileBufferService = arctileBufferService;
    }

    public ArctileService getArctileService() {
        return arctileService;
    }

    @Autowired
    public void setArctileService(ArctileService arctileService) {
        this.arctileService = arctileService;
    }

    //有vector时的推荐文章
    @RequestMapping("/haveVector")
    private void haveVector(HttpServletRequest request/*,@RequestParam int userid,@RequestParam int arctileid,
                @RequestParam String updatetime*/) {
        Log log = (Log) request.getAttribute("log");
        int arctileid = log.getArctileid();
        int userid = log.getUserid();
        String updatetime = log.getUpdatetime();

        CosineDistance cosineDistance = SingleTon.getcosineDistance();
        //更新用户vector
        System.out.println("进入selectArctileVectorById方法");
        String arctileVector = arctileService.selectArctileVectorById(arctileid);
        //判空???????????????????????????????????????????????????????
        if (arctileVector == null) {
            return;
        }
        System.out.println("arctileid:" + arctileid);
        System.out.println("arctileVector:," + arctileVector);
        String[] arctileVectorString = new String[300];
        arctileVectorString = arctileVector.split(" ");
        double[] arctileVectorDouble = new double[300];
        for (int i = 0; i < arctileVectorString.length; i++) {
            arctileVectorDouble[i] = Double.parseDouble(arctileVectorString[i]);
        }
        //更新用户向量
        //将用户的向量更新
        String userVectorString = doubleToString(arctileVectorDouble);
        //启动更新用户向量的语句
        User user = new User();
        user.setUser_id(userid);
        user.setVector(userVectorString);
        //MyThead myThead=new MyThead(user);
        // 启动更新推荐文章
        startRecommendArctile(user);
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //用户的向量A
        CosineDistance.Vector arctileVectors = cosineDistance.new Vector();
        arctileVectors.value = arctileVectorDouble;
        Vector vectorA = cosineDistance.updateUserVector(arctileVectors);
        //查询出还没有看过的文章
        List<ArctileFew> outReadList = arctileService.selectArctileOutRead(userid);
        //测试用的一段+++++++++++++++++++++++++++++++++++++++
        for (int i = 0; i < outReadList.size(); i++) {
            ArctileFew arc = outReadList.get(i);
            //System.out.print(arc.getArctile_id()+"....");
        }
        System.out.println();
        String[] outRead = new String[outReadList.size()];

        // 1-余弦的余数的数组
        double[] residueDouble = new double[outRead.length];

        // 初始化返回结果的List
        List<ArctileCountResult> arctileCountResults = new ArrayList();
        //ArctileCountResult arctileCountResult = SingleTon.getArctileCountResult();
        for (int i = 0; i < outReadList.size(); i++) {
            double[] outReadDouble = new double[outRead.length];
            ArctileFew arctileFew = outReadList.get(i);
            System.out.println("arctileFew.getArctile_id():" + arctileFew.getArctile_id());
            // 长度待定
            outRead[i] = arctileFew.getVector();
            if (outRead[i] == null || outRead[i] == "") {
                outRead[i] = " ";

            }

            System.out.println("我已经运行到这了");
            System.out.println("i:" + i);
            System.out.println(outRead[i] + ",");
            String[] outReadString = new String[301];
            // 将字符串分割
            // 报空字符串异常
            outReadString = outRead[i].split(" ");
            for (int j = 0; j < outReadString.length; j++) {
                if (outReadString[j] != null && outReadString[j] != "") {
                    outReadDouble[j] = Double.parseDouble(outReadString[j]);
                } else {
                    continue;
                }
            }
            System.out.println(outReadDouble);
            CosineDistance.Vector vectorB = cosineDistance.new Vector();
            vectorB.value = outReadDouble;
            double distanceResultDouble = cosineDistance.calculateConsineDistance(vectorA, vectorB);
            if (Double.isNaN(distanceResultDouble)) {
                distanceResultDouble = Double.MAX_VALUE;
            }
            System.out.println(distanceResultDouble + "========");
            residueDouble[i] = 1 - distanceResultDouble;
            ArctileCountResult arctileCountResult = new ArctileCountResult();
            arctileCountResult.setArctileid(arctileFew.getArctile_id());
            arctileCountResult.setResidueDouble(residueDouble[i]);
            arctileCountResult.setWeight(arctileFew.getWeight());
            arctileCountResults.add(arctileCountResult);
        }
        //目前的问题是arctileCountResults数组只存储了最后一组
        //打印出数组，做测试===================================
        for (int i = 0; i < arctileCountResults.size(); i++) {
            ArctileCountResult arc = arctileCountResults.get(i);
            System.out.println("这段代码能不能啊");
            System.out.println(arc.getArctileid() + ", " + arc.getResidueDouble() + ", " + arc.getWeight());
        }
        //将越接近1的数遍历出来，数量为50
        Collections.sort(arctileCountResults, new Comparator<ArctileCountResult>() {

            @Override
            public int compare(ArctileCountResult o1, ArctileCountResult o2) {
                if (o2.getResidueDouble() > o1.getResidueDouble()) {
                    return 1;
                }
                if (o2.getResidueDouble() < o1.getResidueDouble()) {
                    return -1;
                }
                return 0;
            }

        });
        List<ArctileCountResult> arctileFinallyResults = new ArrayList();
        for (int i = 0; i < 50; i++) {
            arctileFinallyResults.add(arctileCountResults.get(i));
        }
        //测试用过
        System.out.println("arctileFinallyResults.size():" + arctileFinallyResults.size());
        for (int i = 0; i < arctileFinallyResults.size(); i++) {
            ArctileCountResult arctileCountResult = arctileFinallyResults.get(i);
            System.out.println("arctileCountResult.getArctileid():" + arctileCountResult.getArctileid());
        }
        //吧weight划分
        Collections.sort(arctileFinallyResults, new Comparator<ArctileCountResult>() {
            @Override
            public int compare(ArctileCountResult o1, ArctileCountResult o2) {
                if (o1.getWeight() < o2.getWeight()) {
                    return 1;
                }
                if (o1.getWeight() < o2.getWeight()) {
                    return -1;
                }
                return 0;
            }
        });

        //将最后要推荐的文章处理 前20篇
        List<ArctileLittle> arctileLastResultList = new ArrayList();
        for (int i = 0; i < 20; i++) {
            //arctileLastResult.add(arctileFinallyResults.get(i));
            ArctileCountResult arctileCountresult = arctileFinallyResults.get(i);
            int arctileCountid = arctileCountresult.getArctileid();
            double weight = arctileCountresult.getWeight();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String date = df.format(new Date());// new Date()为获取当前系统时间
            ArctileBuffer arctileBuffer = new ArctileBuffer();
            arctileBuffer.setArctileid(arctileCountid);
            System.out.println("arctileBuffer.setArctileid:" + arctileBuffer.getArctileid());
            arctileBuffer.setUserid(userid);
            arctileBuffer.setArctileweight(weight);
            arctileBuffer.setUpdatetime(date);
            int j = arctileBufferService.insertarcilebuffer(arctileBuffer);
            System.out.println(j + "////////////////////////////////////");
        }
    }

    //启动更新推荐文章
    public void startRecommendArctile(User user) {
        int userid = user.getUser_id();
        String userVectorString = user.getVector();
        System.out.println("Userid是:" + userid);
        user.setUser_id(userid);
        user.setVector(userVectorString);
        int i = arctileService.updateUserVector(user);
    }
//		public class MyThead implements Runnable{
//               private User user;
//               public MyThead(User user){
//            	   this.user=user;
//               }
//			@Override
//			public void run() {
//				//User user=new User();
//				int userid=user.getUser_id();
//				String userVectorString=user.getVector();
//				System.out.println("Userid是:"+userid);
//				user.setUser_id(userid);
//				user.setVector(userVectorString);
//				arctileService.updateUserVector(user);
//				try {
//					Thread.sleep(30000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	            //30秒一次
//				Scheduleddo scheduleddo=new Scheduleddo();
//				System.out.println("Userid是:"+user.getUser_id());
//				scheduleddo.updateScheduled(user);

    //	}}

    //将double数组转为String
    private String doubleToString(double[] arctileVectorDouble) {
        String[] s = new String[300];
        for (int i = 0; i < arctileVectorDouble.length; i++) {
            s[i] = arctileVectorDouble[i] + " ";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length; i++) {
            sb.append(s[i]);
        }
        String ss = sb.toString();
        return ss;
    }

    //查询除用户已经看过的文章
    private String selectUserLoged(int userid, int arctileid) {
        List<Integer> logdList = arctileService.selectloged(userid);
        if (logdList.size() == 0) {
            return "";
        } else {
            boolean b = logdList.contains(arctileid);
            if (b == true) {
                //如果存在就什么都不做了
            }
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < logdList.size(); i++) {
                buffer.append(",").append(logdList.get(i));
            }
            System.out.println(buffer);
            StringBuffer logbuffer = new StringBuffer();
            System.out.println("logbuffer:" + logbuffer);
            if (buffer.substring(0, 1).equals(",")) {
                logbuffer = buffer.delete(0, 1);
            }
            String haveRead = logbuffer.toString();
            System.out.println(haveRead);
            return haveRead;

        }

    }

    //没有vector时的推荐文章
    @RequestMapping("/nullVector")
    private void nullVector(HttpServletResponse response) {
        List<ArctileLittle> list = arctileService.SelectRecommendArctile();

        ObjectMapper obj = SingleTon.getObjectMapper();
        try {
            String nullVectorArctileListjson = obj.writeValueAsString(list);
            if (nullVectorArctileListjson != null) {
                response.getWriter().write(nullVectorArctileListjson);
            } else {
                response.getWriter().write("请求失败");
            }
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    //没有推荐文章时的给前端的文章
    @RequestMapping("/noArctileBuffer")
    @ResponseBody
    private String nullVector(@RequestParam int userid) {
        List<ArctileLittle> outReadList = new ArrayList();
        List<ArctileLittleAndUserId> resultList = new ArrayList<ArctileLittleAndUserId>();

        //查询出还没有看过的文章
        outReadList = arctileService.selectArctileOutReadWhenNoArctole(userid);
        System.out.println("outReadList的size是" + outReadList.size());
        for (int i = 0; i < outReadList.size(); i++) {
            ArctileLittle arctileLittle = outReadList.get(i);
            ArctileLittleAndUserId arctileLittleAndUserId = new ArctileLittleAndUserId();
			arctileLittleAndUserId.setArctileid(arctileLittle.getArctileid());
            arctileLittleAndUserId.setThumbnail_url(arctileLittle.getThumbnail_url());
            arctileLittleAndUserId.setTitle(arctileLittle.getTitle());
            arctileLittleAndUserId.setUrl(arctileLittle.getUrl());
            arctileLittleAndUserId.setUserid(userid);
            resultList.add(arctileLittleAndUserId);
        }
        HashMap<String, List> map = new HashMap();
        map.put("arctile", resultList);
        //把推荐文章写到log表里
        Log log = new Log();
        log.setUserid(userid);
        System.out.println("真的到这了");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间
        log.setUpdatetime(date);

        for (int i = 0; i < outReadList.size(); i++) {
            ArctileLittle arctileLittle = outReadList.get(i);
            log.setArctileid(arctileLittle.getArctileid());
            int ii = arctileService.insertLogByRecommendarctile(log);
            System.out.println("ii:" + ii);
        }
        ObjectMapper obj = SingleTon.getObjectMapper();
        String nullVectorArctilemapjson = null;
        try {
            nullVectorArctilemapjson = obj.writeValueAsString(map);
            //System.out.println(nullVectorArctilemapjson);
            if (nullVectorArctilemapjson != null) {
            } else {
                return "请求失败";
            }
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(nullVectorArctilemapjson);
        return nullVectorArctilemapjson;
    }

    //根据id查询要传给前端的属性
    @RequestMapping("/arctileRecommend")
    @ResponseBody
    private String arctileRecommend(HttpServletRequest request, HttpServletResponse response) {
        List arctileBufferidlist = (List) request.getAttribute("arctileBufferidlist");

        int userid = (Integer) request.getAttribute("userid");
        System.out.println("userid:" + userid);
        System.out.println(arctileBufferidlist.size() + "hahaaha1");
        List<ArctileLittleAndUserId> arctileLittlelist = new ArrayList();
        //HashMap<String, ArctileRecommend>map=new HashMap<>();
        HashMap<String, List> map = new HashMap();
        for (int i = 0; i < arctileBufferidlist.size(); i++) {
            System.out.println(arctileBufferidlist.get(i) + "////");
            int arctileid = (Integer) arctileBufferidlist.get(i);
            ArctileLittle arctileLittle = arctileService.arctileRecommend(arctileid);
            ArctileLittleAndUserId arctileLittleAndUserId=new ArctileLittleAndUserId();
            arctileLittleAndUserId.setUrl(arctileLittle.getUrl());
            arctileLittleAndUserId.setTitle(arctileLittle.getTitle());
            arctileLittleAndUserId.setThumbnail_url(arctileLittle.getTitle());
            arctileLittleAndUserId.setArctileid(arctileLittle.getArctileid());
            arctileLittleAndUserId.setUserid(userid);
            arctileLittlelist.add(arctileLittleAndUserId);
            //map.put("arctile", arctileRecommend);
            System.out.println(userid + "........" + arctileid);
            int ii = arctileService.deleteSendArctileByUserid(userid, arctileid);
            System.out.println(ii + "===========");
        }
        map.put("arctile", arctileLittlelist);
        ObjectMapper obj = SingleTon.getObjectMapper();
        String arctileRecommendStr = null;
        try {
            arctileRecommendStr = obj.writeValueAsString(map);
            //System.out.println(arctileRecommendStr);
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("arctileRecommendStr" + arctileRecommendStr);
        return arctileRecommendStr;
    }


}


