package com.yuanqin.adapter;

/**
 * Description: pattern
 * <p>
 * Created by yuanqn on 2019/1/2 22:55
 */
//MP4播放器
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }
}
