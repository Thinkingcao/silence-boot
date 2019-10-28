package com.silence.generator.domain;


import com.silence.common.core.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

/**
 * 粉丝表 fans
 * 
 * @author silence
 * @date 2019-09-18
 */
public class Fans extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private String id;
	/** OPENID */
	private String openId;
	/**  */
	private String nickName;
	/** 性别 */
	private String sex;
	/** 国家 */
	private String country;
	/** 省份 */
	private String province;
	/** 城市 */
	private String city;
	/** 头像 */
	private String headimgurl;
	/** 联合ID */
	private String unionid;
	/** 真实姓名 */
	private String realName;
	/** 手机号 */
	private String phone;
	/** 是否关注（0:未关注，1:已关注） */
	private String isSubscribe;
	/** 关注时间 */
	private Date subscribe;

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setOpenId(String openId) 
	{
		this.openId = openId;
	}

	public String getOpenId() 
	{
		return openId;
	}
	public void setNickName(String nickName) 
	{
		this.nickName = nickName;
	}

	public String getNickName() 
	{
		return nickName;
	}
	public void setSex(String sex) 
	{
		this.sex = sex;
	}

	public String getSex() 
	{
		return sex;
	}
	public void setCountry(String country) 
	{
		this.country = country;
	}

	public String getCountry() 
	{
		return country;
	}
	public void setProvince(String province) 
	{
		this.province = province;
	}

	public String getProvince() 
	{
		return province;
	}
	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getCity() 
	{
		return city;
	}
	public void setHeadimgurl(String headimgurl) 
	{
		this.headimgurl = headimgurl;
	}

	public String getHeadimgurl() 
	{
		return headimgurl;
	}
	public void setUnionid(String unionid) 
	{
		this.unionid = unionid;
	}

	public String getUnionid() 
	{
		return unionid;
	}
	public void setRealName(String realName) 
	{
		this.realName = realName;
	}

	public String getRealName() 
	{
		return realName;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getPhone() 
	{
		return phone;
	}
	public void setIsSubscribe(String isSubscribe) 
	{
		this.isSubscribe = isSubscribe;
	}

	public String getIsSubscribe() 
	{
		return isSubscribe;
	}
	public void setSubscribe(Date subscribe) 
	{
		this.subscribe = subscribe;
	}

	public Date getSubscribe() 
	{
		return subscribe;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("openId", getOpenId())
            .append("nickName", getNickName())
            .append("sex", getSex())
            .append("country", getCountry())
            .append("province", getProvince())
            .append("city", getCity())
            .append("headimgurl", getHeadimgurl())
            .append("unionid", getUnionid())
            .append("realName", getRealName())
            .append("phone", getPhone())
            .append("isSubscribe", getIsSubscribe())
            .append("subscribe", getSubscribe())
            .toString();
    }
}
