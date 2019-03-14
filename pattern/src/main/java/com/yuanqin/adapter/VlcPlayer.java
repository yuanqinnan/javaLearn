package com.yuanqin.adapter;

/**
 * Description: pattern
 * <p>
 * Created by yuanqn on 2019/1/2 22:44
 */
//VlC播放器
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }

    @Override
    public void playMp4(String fileName) {

    }
}
