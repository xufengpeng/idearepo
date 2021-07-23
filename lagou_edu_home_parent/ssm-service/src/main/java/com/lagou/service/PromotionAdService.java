package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;

public interface PromotionAdService {
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo);
    public void savePromotionAd(PromotionAd promotionAd);
    public void updatePromotionAd(PromotionAd promotionAd);
    public PromotionAd findPromotionAdById(Integer id);
    public void updatePromotionAdStatus(Integer id,Integer status);
}
