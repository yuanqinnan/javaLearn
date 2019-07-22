package com.yuanqn.lottery;

import com.yuanqn.api.lottery.AwardBO;
import com.yuanqn.api.lottery.LotteryUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LotteryTest {

    @Test
    public void test() {
        List<AwardBO> awardList = new ArrayList();
        awardList.add(new AwardBO("10个积分", 0.40d));
        awardList.add(new AwardBO("30个积分", 0.30d));
        awardList.add(new AwardBO("800个积分", 0.05d));
        awardList.add(new AwardBO("京东E卡100元", 0.0075d));
        awardList.add(new AwardBO("50元话费", 0.0000d));
        awardList.add(new AwardBO("谢谢参与", 0.2425d));
        Map<String, Integer> result = new HashMap<String, Integer>();
        for (int i = 0; i < 1000000; i++) {
            AwardBO award = LotteryUtil.lottery(awardList);
            String title = award.getAwardTitle();
            Integer count = result.get(title);
            result.put(title, count == null ? 1 : count + 1);
        }

        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            System.out.println(entry.getKey() + ", count=" + entry.getValue() + ", reate=" + entry.getValue() / 1000000d);
        }
    }
}