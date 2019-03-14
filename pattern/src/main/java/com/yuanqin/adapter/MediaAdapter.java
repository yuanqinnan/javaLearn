package com.yuanqin.adapter;

/**
 * Description: pattern
 * <p>
 * Created by yuanqn on 2019/1/2 22:56
 */
//适配器类
public class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType){
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMediaPlayer=new VlcPlayer();
        }else{
            advancedMediaPlayer=new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMediaPlayer.playVlc(fileName);
        }else{
            advancedMediaPlayer.playMp4(fileName);
        }
    }
}
