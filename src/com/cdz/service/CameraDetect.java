/**
 * 
 */
package service;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iotplatform.client.NorthApiException;

import aos.framework.core.id.AOSId;
import aos.framework.core.service.CDZBaseController;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.dao.AosParamsDao;
import aos.framework.dao.AosParamsPO;
import aos.framework.web.router.HttpModel;
import aos.system.common.utils.SystemCons;
import dao.Alarm_descDao;
import dao.Alarm_logDao;
import dao.DeviceDao;
import po.Alarm_descPO;
import po.Alarm_logPO;
import po.DevicePO;
import utils.Constant;
import utils.HttpsUtil;
import utils.JsonUtil;
import utils.Request;
import utils.StreamClosedHttpResponse;

/**
 * @author wzlab
 *
 */
@Service
public class CameraDetect  {

	@Autowired
	static
	AosParamsDao aosParamsDao;
	
	@Autowired
	DeviceDao deviceDao;
	
	@Autowired
	Alarm_logDao alarm_logDao;
	
	@Autowired
	Alarm_descDao alarm_descDao;
	
	public static String serverIP = "180.101.147.89:8743";
	
	public static String appId ="VDU9XeS5QvYZxAgiLp2jK7wvDlQa";
	
	public static String secret = "K1aghh5VRTvbcCzlPqj5unz6cd0a";
	
	public static String nbAccessToken = "";
	public static String accessToken= "";
	public static String accessToken1= "";
	public static StreamClosedHttpResponse bodyQuery;

	
	
	
	
	public  void getAlarm() throws NorthApiException
    {    
		Dto pDto3 = Dtos.newDto("key_", "access_token");
		AosParamsPO aosParamsPO = aosParamsDao.selectOne(pDto3);
		String access_token1 = aosParamsPO.getValue_();
		System.out.println("保存日志进数据库...."+access_token1);

    }
	
	
	
}
