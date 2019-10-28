package com.silence.generator.mapper;

import com.silence.generator.domain.Fans;

import java.util.List;	

/**
 * 粉丝 数据层
 * 
 * @author silence
 * @date 2019-09-18
 */
public interface FansMapper 
{
	/**
     * 查询粉丝信息
     * 
     * @param id 粉丝ID
     * @return 粉丝信息
     */
	public Fans selectFansById(String id);
	
	/**
     * 查询粉丝列表
     * 
     * @param fans 粉丝信息
     * @return 粉丝集合
     */
	public List<Fans> selectFansList(Fans fans);
	
	/**
     * 新增粉丝
     * 
     * @param fans 粉丝信息
     * @return 结果
     */
	public int insertFans(Fans fans);
	
	/**
     * 修改粉丝
     * 
     * @param fans 粉丝信息
     * @return 结果
     */
	public int updateFans(Fans fans);
	
	/**
     * 删除粉丝
     * 
     * @param id 粉丝ID
     * @return 结果
     */
	public int deleteFansById(String id);
	
	/**
     * 批量删除粉丝
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFansByIds(String[] ids);
	
}