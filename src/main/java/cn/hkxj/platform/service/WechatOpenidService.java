package cn.hkxj.platform.service;

import cn.hkxj.platform.mapper.MiniProgramOpenidMapper;
import cn.hkxj.platform.mapper.WechatOpenidMapper;
import cn.hkxj.platform.pojo.MiniProgramOpenid;
import cn.hkxj.platform.pojo.MiniProgramOpenidExample;
import cn.hkxj.platform.pojo.WechatOpenid;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YXH
 * @date 2020/2/12 - 19:08
 */
@Service("wechatOpenidService")
public class WechatOpenidService {
    @Resource
    private WechatOpenidMapper wechatOpenidMapper;
    @Resource
    private MiniProgramOpenidMapper miniProgramOpenidMapper;

    public void insertByWechatOpenid(){
       List<MiniProgramOpenid> miniProgramOpenids = miniProgramOpenidMapper.selectByExample(new MiniProgramOpenidExample());
       WechatOpenid wechatOpenid = new WechatOpenid();
       System.out.println("====================开始插入");
       for (MiniProgramOpenid mini:miniProgramOpenids
             ) {
           //wechatOpenid.setId(miniProgramOpenids.get(i).getId());
            wechatOpenid.setOpenid(mini.getOpenid());
            wechatOpenid.setAccount(mini.getAccount());
            wechatOpenid.setGmtCreate(mini.getGmtCreate());
            wechatOpenid.setGmtModified(mini.getGmtModified());
            wechatOpenid.setIsBind(mini.getIsBind());
            wechatOpenid.setAppid("wx05f7264e83fa40e9");
            wechatOpenidMapper.insert(wechatOpenid);
        }
        System.out.println("======================完成插入");
    }
}
