package com.h13.slg.business.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.h13.slg.business.co.UserBusinessCO;
import com.h13.slg.business.helper.UserBusinessHelper;
import com.h13.slg.config.co.BusinessCO;
import com.h13.slg.config.fetcher.BusinessConfigFetcher;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.SlgConstants;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.hepler.UserStatusHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-12-8
 * Time: 下午5:58
 * To change this template use File | Settings | File Templates.
 */
@Service("BusinessService")
public class BusinessService {


    @Autowired
    private BusinessConfigFetcher businessConfigFetcher;

    @Autowired
    private UserPackageHelper userPackageHelper;

    @Autowired
    private UserBusinessHelper userBusinessHelper;

    @Autowired
    private UserStatusHelper userStatusHelper;


    public SlgData batchResearch(SlgRequestDTO requestDTO) throws RequestFatalException, RequestUnexpectedException {
        int uid = requestDTO.getUid();
        int step = new Integer(requestDTO.getArgs().get("step") + "");

        int nextStep = step + 1;

        // init
        BusinessCO businessCO = businessConfigFetcher.get(step + "");
        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);


        // 查看是否是当前的step
        if (step == SlgConstants.Business.TOP_STEP) {
            // 已经达到top
            throw new RequestUnexpectedException(CodeConstants.Business.HAVE_GOT_TOP_STEP,
                    String.format("step=%s", step));
        }

        int needGold = businessCO.getGoldOpen() * 5;

        if (needGold > userStatusCO.getGold()) {
            throw new RequestUnexpectedException(CodeConstants.Business.GOLD_IS_NOT_ENOUGH,
                    String.format("step=%s", step));
        }

        UserBusinessCO userBusinessCO = userBusinessHelper.get(uid);


        if (userBusinessCO.getStep() != step - 1) {
            //
            throw new RequestUnexpectedException(CodeConstants.Business.STEP_IS_NOT_CORRECT,
                    String.format("step=%s", step));
        }

        if (userBusinessCO.getPass() != SlgConstants.Business.PASS_CARD) {
            throw new RequestUnexpectedException(CodeConstants.Business.CAN_NOT_PASS,
                    String.format("step=%s", step));
        }


        // 获取gem的随机
        List<Integer> gemIndexList = createGemIndexList();
        // 随机9个位置中的一个
        List<Integer> cardIndexList = createCardIndexList();

        List<Integer> result = newDefaultCardList();

        // 先确定此次能不能获得数据
        Random random = new Random();
        int randomData = random.nextInt(100);
        int count = 0;
        if (randomData < businessCO.getCardPass()) {
            // 失败
            count = 5;
            userBusinessCO.setPass(SlgConstants.Business.NOT_PASS_STATUS);
        } else {
            count = 4;
            int passCardIndex = getAndRemoveIndex(cardIndexList);
            result.set(passCardIndex, SlgConstants.Business.PASS_CARD);
            userBusinessCO.setPass(SlgConstants.Business.PASS_STATUS);
        }


        for (int i = 1; i <= count; i++) {
            int gemIndex = getAndRemoveIndex(gemIndexList);
            int index = getAndRemoveIndex(cardIndexList);

            String value = SlgBeanUtils.getProperty(businessCO, "gem" + gemIndex);
            List<String> data = Splitter.on(":").splitToList(value);
            int gemId = getGemId(data);

            userPackageHelper.addGem(uid, gemId, 1);

            result.set(index, gemId);
        }

        // 检测是否已经到最后一个步了，最后一步的话需要重置
        if (step != SlgConstants.Business.TOP_STEP) {
            userBusinessCO.setStep(nextStep);
            userBusinessCO.setResult(result);
        } else {
            userBusinessCO.setStep(0);
            userBusinessCO.setResult(newDefaultCardList());
        }

        userBusinessHelper.update(userBusinessCO);

        // 去掉金币
        int currentGold = userStatusCO.getGold();
        userStatusCO.setGold(currentGold - needGold);
        userStatusHelper.updateUserStatus(userStatusCO);

        return SlgData.getData().add("result", result)
                .add("cost",needGold);
    }


    private List<Integer> newDefaultCardList() {
        return new ArrayList<Integer>() {{
            add(SlgConstants.Business.DEFAULT_CARD);
            add(SlgConstants.Business.DEFAULT_CARD);
            add(SlgConstants.Business.DEFAULT_CARD);
            add(SlgConstants.Business.DEFAULT_CARD);
            add(SlgConstants.Business.DEFAULT_CARD);
            add(SlgConstants.Business.DEFAULT_CARD);
            add(SlgConstants.Business.DEFAULT_CARD);
            add(SlgConstants.Business.DEFAULT_CARD);
            add(SlgConstants.Business.DEFAULT_CARD);
        }};
    }

    private int getAndRemoveIndex(List<Integer> list) {
        Random random = new Random();
        int i = random.nextInt(list.size());
        return list.remove(i);
    }


    private int getGemId(List<String> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return new Integer(list.get(index));
    }

    private List<Integer> createGemIndexList() {
        return new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
            add(8);
        }};
    }

    private List<Integer> createCardIndexList() {
        return new ArrayList<Integer>() {{
            add(0);
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
            add(8);
        }};

    }
}
