package com.silence.generator.controller;

import com.silence.common.annotation.Log;
import com.silence.common.core.controller.BaseController;
import com.silence.common.core.domain.AjaxResult;
import com.silence.common.core.page.TableDataInfo;
import com.silence.common.enums.BusinessType;
import com.silence.common.utils.poi.ExcelUtil;
import com.silence.generator.domain.Fans;
import com.silence.generator.service.IFansService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 粉丝 信息操作处理
 * 
 * @author silence
 * @date 2019-09-18
 */
@Controller
@RequestMapping("/system/fans")
public class FansController extends BaseController
{
    private String prefix = "system/fans";
	
	@Autowired
	private IFansService fansService;
	
	@RequiresPermissions("system:fans:view")
	@GetMapping()
	public String fans()
	{
	    return prefix + "/fans";
	}
	
	/**
	 * 查询粉丝列表
	 */
	@RequiresPermissions("system:fans:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Fans fans)
	{
		startPage();
        List<Fans> list = fansService.selectFansList(fans);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出粉丝列表
	 */
	@RequiresPermissions("system:fans:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Fans fans)
    {
    	List<Fans> list = fansService.selectFansList(fans);
        ExcelUtil<Fans> util = new ExcelUtil<Fans>(Fans.class);
        return util.exportExcel(list, "fans");
    }
	
	/**
	 * 新增粉丝
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存粉丝
	 */
	@RequiresPermissions("system:fans:add")
	@Log(title = "粉丝", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Fans fans)
	{		
		return toAjax(fansService.insertFans(fans));
	}

	/**
	 * 修改粉丝
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		Fans fans = fansService.selectFansById(id);
		mmap.put("fans", fans);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存粉丝
	 */
	@RequiresPermissions("system:fans:edit")
	@Log(title = "粉丝", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Fans fans)
	{		
		return toAjax(fansService.updateFans(fans));
	}
	
	/**
	 * 删除粉丝
	 */
	@RequiresPermissions("system:fans:remove")
	@Log(title = "粉丝", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(fansService.deleteFansByIds(ids));
	}
	
}
