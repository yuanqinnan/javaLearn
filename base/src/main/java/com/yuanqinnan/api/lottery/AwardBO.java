package com.yuanqinnan.api.lottery;

import lombok.Data;

@Data
public class AwardBO {

    public AwardBO() {
    }

    public AwardBO(String awardTitle, double probability) {
        this.awardTitle = awardTitle;
        this.probability = probability;
    }


    /** 奖品名 **/
    private String awardTitle;
    /** 中奖概率 **/
    private double probability;
}