package com.silence.generator.service.impl;

import com.silence.common.core.text.Convert;
import com.silence.generator.domain.Fans;
import com.silence.generator.mapper.FansMapper;
import com.silence.generator.service.IFansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 粉丝 服务层实现
 * 
 * @author silence
 * @date 2019-09-18
 */
@Service
public class FansServiceImpl implements IFansService
{
	@Autowired
	private FansMapper fansMapper;

	/**
     * 查询粉丝信息
     * 
     * @param id 粉丝ID
     * @return 粉丝信息
     */
    @Override
	public Fans selectFansById(String id)
	{
	    return fansMapper.selectFansById(id);
	}
	
	/**
     * 查询粉丝列表
     * 
     * @param fans 粉丝信息
     * @return 粉丝集合
     */
	@Override
	public List<Fans> selectFansList(Fans fans)
	{
	    return fansMapper.selectFansList(fans);
	}
	
    /**
     * 新增粉丝
     * 
     * @param fans 粉丝信息
     * @return 结果
     */
	@Override
	public int insertFans(Fans fans)
	{
	    return fansMapper.insertFans(fans);
	}
	
	/**
     * 修改粉丝
     * 
     * @param fans 粉丝信息
     * @return 结果
     */
	@Override
	public int updateFans(Fans fans)
	{
	    return fansMapper.updateFans(fans);
	}

	/**
     * 删除粉丝对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFansByIds(String ids)
	{
		return fansMapper.deleteFansByIds(Convert.toStrArray(ids));
	}
	
}
