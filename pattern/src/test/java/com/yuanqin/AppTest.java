package com.yuanqin;

import static org.junit.Assert.assertTrue;

import com.yuanqin.adapter.AudioPlayer;
import com.yuanqin.command.*;
import com.yuanqin.decker.*;
import com.yuanqin.factory.*;
import com.yuanqin.observer.CurrentConditionsDisplay;
import com.yuanqin.observer.ForecastDisplay;
import com.yuanqin.observer.StatisticsDispxlay;
import com.yuanqin.observer.WeatherData;
import com.yuanqin.singleton.Singleton;
import com.yuanqin.strategy.Duck;
import com.yuanqin.strategy.MallardDuck;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void strategy() {
        Duck mallardDuck = new MallardDuck();
        mallardDuck.performFly();
        mallardDuck.performquack();
    }

    @Test
    public void adapter() {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }

    @Test
    public void control() {
        RemoteControl remoteControl = new RemoteControl();
        Light roomLight = new Light("客厅灯");
        Light kitchLight = new Light("厨房灯");
        CeilingFan roomCeilingFan = new CeilingFan("客厅吊扇");
        CeilingFan kitchCeilingFan = new CeilingFan("厨房吊扇");
        //电灯相关命令
        LightOnCommand roomLightOnCommand = new LightOnCommand(roomLight);
        LightOnCommand kitchLightOnCommand = new LightOnCommand(kitchLight);
        LightOffCommand roomLightOffCommand = new LightOffCommand(roomLight);
        LightOffCommand kitchLightOffCommand = new LightOffCommand(kitchLight);
        //吊扇相关命令
        CeilingFanOnCommand roomCeilingFanOnCommand = new CeilingFanOnCommand(roomCeilingFan);
        CeilingFanOnCommand kitchCeilingFanOnCommand = new CeilingFanOnCommand(kitchCeilingFan);
        CeilingFanOffCommand roomCeilingFanOffCommand = new CeilingFanOffCommand(roomCeilingFan);
        CeilingFanOffCommand kitchCeilingFanOffCommand = new CeilingFanOffCommand(kitchCeilingFan);
        //将命令加载到卡槽中
        remoteControl.setCommand(0, roomLightOnCommand, roomLightOffCommand);
        remoteControl.setCommand(1, kitchLightOnCommand, kitchLightOffCommand);
        remoteControl.setCommand(2, roomCeilingFanOnCommand, roomCeilingFanOffCommand);
        remoteControl.setCommand(3, kitchCeilingFanOnCommand, kitchCeilingFanOffCommand);
        //使用遥控
        remoteControl.onButtonWasPressed(0);
        remoteControl.offButtonWasPressed(0);
        remoteControl.onButtonWasPressed(1);
        remoteControl.offButtonWasPressed(1);
        remoteControl.onButtonWasPressed(2);
        remoteControl.offButtonWasPressed(2);
        remoteControl.onButtonWasPressed(3);
        remoteControl.offButtonWasPressed(3);
    }

    @Test
    public void simpleControl() {
        //遥控器调用者
        SimpleRemoteControl control = new SimpleRemoteControl();
        //电灯
        Light light = new Light("客厅");
        //具体命令类
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        //设置命令
        control.setCommand(lightOnCommand);
        //命令方法
        control.buttonWasPressed();


    }

    @Test
    public void singleton() {
        Singleton singleton = Singleton.getInstance();
        singleton.showMsg();
    }

    @Test
    public void abstractFactory() {
        //辣工厂创建辣产品
        FoodFactory hotFoodFactoryoodFactory = new HotFoodFactory();
        Fish hotfish = hotFoodFactoryoodFactory.createFish();
        if (hotfish != null) {
            hotfish.prepare();
            hotfish.cook();
            hotfish.box();
        }
        System.out.println("-------------");
        Meat hotMeat = hotFoodFactoryoodFactory.createMeat();
        if (hotMeat != null) {
            hotMeat.prepare();
            hotMeat.cook();
            hotMeat.box();
        }
    }

    @Test
    public void factory() {
        //创建工厂
        Creator creator = new MeatCreator();
        //创建食物
        Food food = creator.CreateFoddFactory();
        if (food != null) {
            food.prepare();
            food.cook();
            food.box();
        }
    }

    @Test
    public void orderFood() {
        Food food = SimpleFoodFactory.createFood("FISH");
        if (food != null) {
            food.prepare();
            food.cook();
            food.box();
        }
    }

    @Test
    public void player() {
        //选择英雄
        Hero hero = new Lanbo("兰博");
        Skills q = new Skill_Q(hero, "纵火盛宴");
        Skills w = new Skill_W(q, "破碎护盾");
        Skills e = new Skill_E(w, "电子鱼叉");
        Skills r = new Skill_R(e, "恒温灼烧");
        //学习技能
        r.learnSkills();
    }

    @Test
    public void observer() {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentDisplay =
                new CurrentConditionsDisplay(weatherData);
        StatisticsDispxlay statisticsDisplay = new StatisticsDispxlay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
