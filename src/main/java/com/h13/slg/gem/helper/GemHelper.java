package com.h13.slg.gem.helper;

import com.h13.slg.config.co.GemCO;
import com.h13.slg.config.fetcher.GemConfigFetcher;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.pkg.co.UserPackageCO;
import com.h13.slg.pkg.helper.UserPackageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 宝石相关的功能类
 */
@Service
public class GemHelper {

    private final static int JOIN_GEM_COUNT = 2;
    private final static int JOIN_GEM_RESULT_COUNT = 1;

    @Autowired
    UserPackageHelper userPackageHelper;

    @Autowired
    GemConfigFetcher gemConfigFetcher;


    /**
     * 宝石合成
     */
    public void join(int uid, int gemId) throws RequestUnexpectedException {
        UserPackageCO userPackageCO = userPackageHelper.get(uid);
        Map<String, Integer> gem = userPackageCO.getGem();
        if (gem.get(gemId + "") < 2) {
            throw new RequestUnexpectedException(CodeConstants.Gem.DONT_HAVE_ENOUTH_GEM,
                    String.format("uid=%s, gemId=%s", uid, gemId));
        }

        userPackageHelper.subtractGem(uid, gemId, JOIN_GEM_COUNT);

        int nextGemId = getNextQualityGem(gemId);

        userPackageHelper.addGem(uid, nextGemId, JOIN_GEM_RESULT_COUNT);
    }


    /**
     * 获取当前的gemid的下一个品质的gemid
     *
     * @param gemId
     * @return
     */
    private int getNextQualityGem(int gemId) throws RequestUnexpectedException {
        GemCO gemCO = gemConfigFetcher.get(gemId + "");
        int quality = gemCO.getQuality();
        int nextGemId = gemId + 1;
        GemCO nextGemCO = gemConfigFetcher.get(nextGemId + "");
        if (nextGemCO != null) {
            return nextGemCO.getId();
        }
        throw new RequestUnexpectedException(CodeConstants.Gem.GEM_QUALITY_IS_TOP,
                String.format("gemId=%s", gemId));

    }


}
