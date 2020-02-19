package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.WechatOpenid;
import cn.hkxj.platform.pojo.WechatOpenidExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WechatOpenidMapper {
    long countByExample(WechatOpenidExample example);

    int deleteByExample(WechatOpenidExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WechatOpenid record);

    int insertSelective(WechatOpenid record);

    List<WechatOpenid> selectByExample(WechatOpenidExample example);

    WechatOpenid selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WechatOpenid record, @Param("example") WechatOpenidExample example);

    int updateByExample(@Param("record") WechatOpenid record, @Param("example") WechatOpenidExample example);

    int updateByPrimaryKeySelective(WechatOpenid record);

    int updateByPrimaryKey(WechatOpenid record);
}