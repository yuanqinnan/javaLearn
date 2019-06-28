package com.yuanqinnan.api.thread;


public class FiveThread extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 500000; i++) {
                if (this.isInterrupted()) {
                    System.out.println("已经是停止状态了!退出!");
                    throw new InterruptedException();
                }
                System.out.println("i=" + (i + 1));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("抛出了错误！");
        }
        System.out.println("666");
    }

    public static void main(String[] args) {
        try {
            FiveThread thread = new FiveThread();
            thread.start();
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }

}