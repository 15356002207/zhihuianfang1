package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import aos.framework.core.id.AOSId;
import aos.framework.core.service.CDZBaseController;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.dao.AosParamsDao;
import aos.framework.dao.AosParamsPO;
import aos.framework.web.router.HttpModel;
import aos.system.common.utils.SystemCons;
import dao.CameraDao;
import dao.DeviceDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import po.CameraPO;
import po.DevicePO;
import utils.Request;
@Service
public class CameraService extends CDZBaseController {
	@Autowired
	DeviceDao deviceDao;
	@Autowired
	private CameraDao cameraDao;
	@Autowired
	private AosParamsDao aosParamsDao;
	/**
	 * charging_pile管理页面初始化
	 * 
	 * @param httpModel
	 * @return
	 */
	public void init(HttpModel httpModel) {
		httpModel.setViewPath("myproject/camera.jsp");
	}

	/**
	 * 查询charging_pile列表
	 * 
	 * @param httpModel
	 * @return
	 */
	public void listCamera(HttpModel httpModel) {
		Dto qDto = httpModel.getInDto();
		List<Dto> cameraDtos = sqlDao.list("Camera.listCamerasPage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(cameraDtos, qDto.getPageTotal()));
	}
	public void getAlarm(HttpModel httpModel) {
	
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		        public void run() {
		        	 System.out.println("xunhuan"); 
		        	 String s=Request.sendPost("https://open.ys7.com/api/lapp/token/get","appKey=7f139f95ccab4ad0be82630b443edb0b&appSecret=68309273efbb8cbe20bca9155cb225d5");
					 
					 JsonObject jsonObject = (JsonObject) new JsonParser().parse(s);
					// 获取accesstoken中的字符串
					 String accesstoken = jsonObject.get("data").getAsJsonObject().get("accessToken").getAsString();
				
		        	//String accesstoken = "at.dg0s8ytw4vrim4ml8b3t0whf6hkfaypi-6ihwk7fcfn-1svmsie-fuij3ju7n&deviceSerial=C26259492&status=2";
		    		// String url = "https://open.ys7.com/api/lapp/alarm/subscribe";
		    		 String url = "https://open.ys7.com/api/lapp/alarm/list";
		    		//String url = "https://open.ys7.com/api/lapp/alarm/list";
		    		String result = Request.sendPost(url, "accessToken=" + accesstoken+"&pageSize=50");
		    		
		    		  JSONObject str = JSONObject.fromObject(result); 
		    		  JSONArray data =str.getJSONArray("data"); 
		    		  long time1=System.currentTimeMillis();
		    		  
		    		  for(int i = 0; i < data.size(); i++) {
		    			  String t = data.getString(i); 
			    		  JSONObject we = JSONObject.fromObject(t); 
			    		  String rr = we.getString("alarmTime");
			    		  String alarm_type = we.getString("alarmType");
			    		  long time2 = Long.parseLong(rr); 
			    		  long time3=time1-time2;
			    		  System.out.println(time3); 
			    		  if (time3<60*1000&&(alarm_type.equals("10000")||alarm_type.equals("10001")||alarm_type.equals("10002")||alarm_type.equals("10003")||alarm_type.equals("10009")||alarm_type.equals("10010")||alarm_type.equals("10011")||alarm_type.equals("10015")||alarm_type.equals("10026")||alarm_type.equals("10027")||alarm_type.equals("10028")||alarm_type.equals("10033"))) {
			    			
			    			String camera_serial = we.getString("deviceSerial");
			    			Dto pDto= Dtos.newDto("camera_serial", camera_serial);
							CameraPO cameraPO = cameraDao.selectOne(pDto);
							String device_id = cameraPO.getDevice_id();
							if (device_id.length()==11) {
								try {
									Push.pushToSingle(device_id);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}else {
								Dto pDto1= Dtos.newDto("device_id", device_id);
								DevicePO devicePO = deviceDao.selectOne(pDto1);
								String phone = devicePO.getPhone();
								try {
									System.out.println(phone);
									Push.pushToSingle(phone);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
						}
		    			  
		    			  
		    			  
		    		  }
		    		
		    		  
		            System.out.println(data.size());
		        }
		}, 0 , 60*1000);
		
	}
	
	
	/**
	 * 查询charging_pile信息
	 * 
	 * @param httpModel
	 */
	public void getCamera(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		CameraPO cameraPO = cameraDao.selectByKey(inDto.getString("camera_id"));
		httpModel.setOutMsg(AOSJson.toJson(cameraPO));
	}

	/**
	 * 保存charging_pile
	 * 
	 * @param httpModel
	 * @return
	 */
	@Transactional
	public void saveCamera(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		CameraPO cameraPO = new CameraPO();
		cameraPO.copyProperties(inDto);
		cameraPO.setCamera_id(AOSId.appId(SystemCons.ID.SYSTEM));
		// cameraPO.setCreate_date(AOSUtils.getDateTime());
		cameraDao.insert(cameraPO);
		httpModel.setOutMsg("新增成功。");
	}

	/**
	 * 修改charging_pile
	 * 
	 * @param httpModel
	 * @return
	 */
	@Transactional
	public void updateCamera(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		CameraPO cameraPO = new CameraPO();
		cameraPO.copyProperties(inDto);
		cameraDao.updateByKey(cameraPO);
		httpModel.setOutMsg("修改成功。");
	}

	/**
	 * 删除charging_pile
	 * 
	 * @param httpModel
	 */
	@Transactional
	public void deleteCamera(HttpModel httpModel) {
		String[] selectionIds = httpModel.getInDto().getRows();
		if (null != selectionIds && selectionIds.length > 0) {
			for (String camera_id : selectionIds) {
				/*
				 * CameraPO cameraPO = new CameraPO(); cameraPO.setCp_id(id_);
				 * cameraPO.setIs_del(SystemCons.IS.YES); cameraDao.updateByKey(cameraPO);
				 */
				cameraDao.deleteByKey(camera_id);
			}
		} else {
			String camera_id = httpModel.getInDto().getString("camera_id");
			/*
			 * CameraPO cameraPO = new CameraPO(); cameraPO.setCp_id(cp_id);
			 * cameraPO.setIs_del(SystemCons.IS.YES); cameraDao.updateByKey(cameraPO);
			 */
			cameraDao.deleteByKey(camera_id);

		}
		httpModel.setOutMsg("删除成功。");
	}

	
	public void listUrl(HttpModel httpModel) {
		Dto qDto = httpModel.getInDto();

		Dto odto = Dtos.newDto();
		String device_id = qDto.getString("id");
		/* odto.put("hhhh", "44"); */

		Dto pDto = Dtos.newDto("device_id", device_id);
		
		Dto pDto1 = Dtos.newDto();
		int rows = cameraDao.rows(pDto1);
		pDto.put("limit", rows);// 默认查询出100个

		pDto.put("start", 0);
		String num;
		List<Dto> newListDtos = new ArrayList<Dto>();
		List<Dto> cameraDtos = sqlDao.list("Camera.listcameras", pDto);
		if (null != cameraDtos && !cameraDtos.isEmpty()) {
			num = String.valueOf(cameraDtos.size());
		for (Dto dto : cameraDtos) {
			Dto newDto = Dtos.newDto();
			Dto pDto3 = Dtos.newDto("key_", "access_token");
			AosParamsPO aosParamsPO = aosParamsDao.selectOne(pDto3);
			String access_token1 = aosParamsPO.getValue_();
			String serial = dto.getString("camera_serial");
			String url = "ezopen://VPOOOA@open.ys7.com/" + serial + "/1.hd.live" + access_token1;
			newDto.put("par", url);
				newDto.put("rtmp", dto.getString("rtmp_"));
				newDto.put("hls", dto.getString("hls_"));
				/* newDto.put("is_completed", ""); */
				newDto.put("num", num);
			newListDtos.add(newDto);

		}
			odto.put("data", newListDtos);

		} else {
			num = "0";
			Dto newDto = Dtos.newDto();
			newDto.put("par", "666");
			newDto.put("rtmp", "666");
			newDto.put("hls", "666");
			newDto.put("num", num);
			/* newDto.put("is_completed", ""); */

			newListDtos.add(newDto);
			odto.put("data", newListDtos);
		}



		httpModel.setOutMsg(AOSJson.toJson(odto));

	}

}
