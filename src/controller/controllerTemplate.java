package controller;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import controller.userLogin.currentUser;

import dao.query.queryParamsModel;
import json.jsonResult;
import pojo.area;
import pojo.interaction;
import pojo.news;

import pojo.pojoModel;
import pojo.resident;
import pojo.user;
import json.MyObjectMapper;

import servicesDao.serviceDaoTemplate;
import util.AppConfig;
import util.FileUtil;
import util.ListUtil;
import util.StringUtil;

public abstract class controllerTemplate<T extends pojoModel, serviceDao extends serviceDaoTemplate, InputMode extends queryParamsModel<T>> {
	@Autowired
	protected serviceDao serviceDao;
	@Autowired
	protected MyObjectMapper myObjectMapper;
	private Class modelInputClasz;

	protected Logger logger;

	public controllerTemplate() {
		super();
		if (logger == null) {
			logger = Logger.getLogger(this.getClass());
		}

		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.modelInputClasz = (Class<T>) pt.getActualTypeArguments()[2];
	}

	@RequestMapping("/getBasic")
	@ResponseBody
	public jsonResult getBasic(InputMode map) throws Exception {
		try {
			List<T> datas = serviceDao.getBasic(map);
			jsonResult json = new jsonResult("查询成功");
			if (datas != null && datas.size() > 0)
				json.add(datas);
			return json;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@RequestMapping("/getDetail")
	@ResponseBody
	public jsonResult getDetails(InputMode map) throws Exception {
		try {
			List<T> datas = serviceDao.getDetail(map);
			jsonResult json = new jsonResult("查询成功");
			if (datas != null && datas.size() > 0)
				json.add(datas);
			return json;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	// 用于保存前要做部分特殊操作，在子类中覆盖该方法就好了
	private Object deleteBeforDeal(T p) throws Exception {
		return null;
	}

	// 用于保存后要做部分特殊操作，在子类中覆盖该方法就好了
	private Object deleteAfterDeal(T p) throws Exception {
		return null;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public jsonResult delete(T p) throws Exception {
		try {
			int size = -1;
			if (p != null && p.getId() != null) {
				deleteBeforDeal(p);
				size = serviceDao.delete(p);
				deleteBeforDeal(p);
				if (size > 0)
					return createMessageJsonResult(p, "删除");
			}
			return new jsonResult(false, "删除失败");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	// 是否添加拼音，如果要添加就要覆盖该方法
	protected void addPY(T p) {
	};

	@RequestMapping("/add")
	@ResponseBody
	public jsonResult save(T p) throws Exception {
		addPY(p);
		return insert(p, 0);
	}

	// 默认的更新方式是有值更新，没有值不更新
	@RequestMapping("/update")
	@ResponseBody
	public jsonResult update(T p) throws Exception {
		addPY(p);
		return update(p, 1);
	}

	protected jsonResult updateAll(T p) throws Exception {
		addPY(p);
		return update(p, 0);
	}

	/* 删除相对路径文件或者目录 */
	protected boolean deleteRelativePathFile(String relativeRootPath) {
		File file = new File(AppConfig.getRootPath() + "/" + relativeRootPath);
		return FileUtil.delete(file);
	}

	/* 删除绝对路径文件或者目录 */
	protected boolean deleteAbsolutePathFile(String absolutPath) {
		File file = new File(absolutPath);
		return FileUtil.delete(file);
	}

	// 在WebRootpath目录下，在分类目录下 保存在 id目录下 图片，返回相对rootPath的路径
	private String saveFileReturnRelativePath(String WebRootpath, String typeRelativePath, String folde_id,
			String newName, MultipartFile mFile) throws Exception {
		try {
			if ((mFile != null) && (mFile.getInputStream() != null)) {
				return FileUtil.saveFileReturnRelativePath(WebRootpath, typeRelativePath + "/" + folde_id,
						newName + "_" + ((new Date())).getTime(), mFile.getName(), mFile.getInputStream());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return null;
	}

	/*
	 * protected photo saveDiskPhotoFile(Integer photoType, Integer fold_id,
	 * String newFileName, MultipartFile mFile) throws Exception { String
	 * WebRootpath = AppConfig.RootPath; String typeRelativePath = ""; switch
	 * (photoType) { case area.photoType: typeRelativePath =
	 * AppConfig.AreaRelative; break; case user.photoType: typeRelativePath =
	 * AppConfig.UserRelative; break; case resident.photoType: typeRelativePath
	 * = AppConfig.ResidentRelative; break; case interaction.photoType:
	 * typeRelativePath = AppConfig.interactionRootPath; break; case
	 * news.photoType: typeRelativePath = AppConfig.NewsRelative; break;
	 * default: throw new Exception(photoType + " 图片类型不存在"); } String path =
	 * saveFileReturnRelativePath(WebRootpath, typeRelativePath, fold_id + "",
	 * newFileName, mFile); photo photo = new photo(); photo.setType(photoType);
	 * photo.setPath(path); return photo; }
	 * 
	 * protected List<photo> saveDiskPhotosFiles(Integer photoType, Integer
	 * fold_id, String newFileName, MultipartFile[] mFiles) throws Exception {
	 * List<photo> photos = new ArrayList<photo>(0); photo photo = null; if
	 * (mFiles != null && mFiles.length > 0) { int index = 1; for (MultipartFile
	 * mFile : mFiles) photos.add(saveDiskPhotoFile(photoType, fold_id,
	 * newFileName + "_" + index, mFile)); index++; } return photos; }
	 */

	/*
	 * 删除图片 删除图片的数据库记录及物理记录
	 */
	/*
	 * protected boolean deletePhotoByFKObj(List<pojoModel> pojos, Integer
	 * photoType) throws Exception { if (ListUtil.isNotEmpty(pojos)) {
	 * photoQueryParams query = new photoQueryParams(); query.setFks(pojos);
	 * query.setType(photoType); // 删除数据库中的记录 photoServiceDao.deletes(query); //
	 * 删除物理文件 String photoTypeRootPath = getRootPathAtType(photoType); for
	 * (pojoModel p : pojos) { this.deleteAbsolutePathFile(photoTypeRootPath +
	 * "/" + p.getId()); } } return true; }
	 */

	protected String getRootPathAtType(Integer photoType) throws Exception {
		String WebRootpath = AppConfig.RootPath;
		String typeRelativePath = "";
		switch (photoType) {
		case area.photoType:
			typeRelativePath = AppConfig.AreaRelative;
			break;
		case user.photoType:
			typeRelativePath = AppConfig.UserRelative;
			break;
		case resident.photoType:
			typeRelativePath = AppConfig.ResidentRelative;
			break;
		case interaction.photoType:
			typeRelativePath = AppConfig.interactionRootPath;
			break;
		case news.photoType:
			typeRelativePath = AppConfig.NewsRelative;
			break;
		default:
			throw new Exception(photoType + " 图片类型不存在");
		}
		return WebRootpath + "/" + typeRelativePath;
	}

	protected jsonResult createMessageJsonResult(T pojo, String msg) {
		jsonResult json;
		if (pojo != null) {
			json = new jsonResult(msg + "成功");
			json.getDatas().add(pojo);
		} else {
			json = new jsonResult(false, msg + "失败");
		}
		return json;

	}

	// 把多个pojo对象添加到json中
	protected jsonResult createMessageJsonResult(List<T> pojos, String msg) {
		jsonResult json;
		if (pojos != null && pojos.size() > 0) {
			json = new jsonResult(msg + "成功");
			json.add(pojos);
		} else {
			json = new jsonResult(false, msg + "失败");
		}
		return json;

	}

	// 用于保存前要做部分特殊操作，在子类中覆盖该方法就好了
	protected Object insertBeforDeal(T p) throws Exception {
		return null;
	}

	// 用于保存后要做部分特殊操作，在子类中覆盖该方法就好了
	protected Object insertAfterDeal(T p) throws Exception {
		return null;
	}

	// type=0,用全值插入，type=1，用非空值插入
	protected jsonResult insert(T p, int type) throws Exception {
		try {
			int size = -1;
			insertBeforDeal(p);
			if (type == 0) {
				size = serviceDao.save(p);
			} else {
				size = serviceDao.save(p);
			}
			insertAfterDeal(p);
			if (size > 0)
				return createMessageJsonResult(p, "添加");
			return new jsonResult(false, "添加失败");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	protected jsonResult androidUpdateTemplate(InputMode im) throws Exception {
		return this.update(im.getObj());
	}

	protected jsonResult androidInsertTemplate(InputMode im) throws Exception {
		androidInsertTemplateBefor(im);
		;
		return this.save(im.getObj());
	}

	protected void androidInsertTemplateBefor(InputMode im) {

	}

	protected jsonResult androidDeleteTemplate(InputMode im) throws Exception {
		return this.delete(im.getObj());
	}

	/* 得到基本的分页内容 */
	@RequestMapping("/getPageBasic")
	@ResponseBody
	protected jsonResult getPagesBasic(InputMode im) throws Exception {
		return serviceDao.getPagesResultBasic(im);
	}

	/* 得到关联的的分页内容 */
	@RequestMapping("/getPageDetails")
	@ResponseBody
	public jsonResult getPagesDetail(InputMode im) throws Exception {
		return serviceDao.getPagesResultDetail(im);
	}

	// 子类覆盖，用于确定显示内容的方式
	abstract protected jsonResult query(InputMode im) throws Exception;

	// 用于保存前要做部分特殊操作，在子类中覆盖该方法就好了
	protected Object updateBeforDeal(T p) throws Exception {
		return null;
	}

	// 用于保存后要做部分特殊操作，在子类中覆盖该方法就好了
	protected Object updateAfterDeal(T p) throws Exception {
		return null;
	}

	// type=0,用全值插入，type=1，用非空值插入
	protected jsonResult update(T p, int type) throws Exception {
		try {
			int size = -1;
			updateBeforDeal(p);
			if (type == 0) {
				size = serviceDao.updateAll(p);
			} else {
				size = serviceDao.updateNoNull(p);
			}
			updateBeforDeal(p);
			if (size > 0)
				return createMessageJsonResult(p, "更新");
			return new jsonResult(false, "更新失败");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@RequestMapping("")
	@ResponseBody
	public jsonResult deal(@RequestParam("method") Integer method, @RequestParam("params") String params) throws Exception {
		if (StringUtil.isEmpty(params)) {
			return new jsonResult(false, "操作对象未传递");
		}
		InputMode data = (InputMode) myObjectMapper.readValue(params, modelInputClasz);
		System.err.println(myObjectMapper.writeValueAsString(data));
		jsonResult json = null;
		if (data.getUser() == null) {
			json = new jsonResult(false, "没有使用权限,请重新登录");
			return json;
		}
		// 移动用户进入web系统
		currentUser.login(data.getUser());
		Integer type = data.getUser().getType();
		if (method == null) {
			json = new jsonResult(false, "没有对应method操作");
		} else {
			if (method == 0) {
				// 添加
				if (type != 1 && type != 2) {
					json = new jsonResult(false, "没有使用权限,请重新登录");
					return json;
				}
				json = androidInsertTemplate(data);
				/*if (ListUtil.isEmpty(file)) {
					// 没有上传文件的处理机制
					json = androidInsertTemplate(data);
				} else {
					// 有上传文件的处理机制
					json = androidSaveOrUpdatePojoAndPhotoTemplate(data, file);
				}*/
			} else if (method == 1) {
				// 更新
				if (type != 1 && type != 2) {
					json = new jsonResult(false, "没有使用权限,请重新登录");
					return json;
				}
				json = androidUpdateTemplate(data);
				/*if (ListUtil.isEmpty(file)) {
					// 没有上传文件的处理机制
					json = androidUpdateTemplate(data);
				} else {
					// 有上传文件的处理机制
					json = androidSaveOrUpdatePojoAndPhotoTemplate(data, file);
				}*/
			} else if (method == 2) {
				// 删除
				if (type != 1 && type != 2) {
					json = new jsonResult(false, "没有使用权限,请重新登录");
					return json;
				}
				json = androidDeleteTemplate(data);
			} else if (method == 3) {
				// 查询
				json = query(data);
			} else if (method == 4) {
				json = getPagesDetail(data);
			} else {
				json = new jsonResult(false, "没有对应method操作");
			}
		}
		// 移动用户离开web系统
		currentUser.exit();
		return json;
	}

	// 此方法用于在子类中覆盖，
	// 要实现 保存data的getObj对象
	// 然后将files保存到硬盘中
	// 然后把图片关联上该getObj对象
	// 再保存图片信息
	protected boolean androidUploadPhotosSaveOrUpdate(InputMode data, MultipartFile[] file) throws Exception {
		return true;
	}

	protected jsonResult androidSaveOrUpdatePojoAndPhotoTemplate(InputMode data, MultipartFile[] file)
			throws Exception {
		androidUploadPhotosSaveOrUpdate(data, file);// 要在子类中覆盖该方法
		return androidUpdateTemplate(data);
	}

	public serviceDao getServiceDao() {
		return serviceDao;
	}

	public void setServiceDao(serviceDao serviceDao) {
		this.serviceDao = serviceDao;
	}

	public json.MyObjectMapper getMyObjectMapper() {
		return myObjectMapper;
	}

	public void setMyObjectMapper(json.MyObjectMapper myObjectMapper) {
		this.myObjectMapper = myObjectMapper;
	}

}
