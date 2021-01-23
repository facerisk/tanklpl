
package com.lpl.tank;

import javax.sound.sampled.*;
import java.io.IOException;

public class Audio1 extends Thread {
    private AudioFormat audioFormat = null;
    private SourceDataLine sourceDataLine = null;
    private DataLine.Info dataLine_info = null;

    private AudioInputStream audioInputStream = null;
    @Override
    public void run() {
        try {
            byte[] b = new byte[1024];
            int len = 0;
            sourceDataLine.open(audioFormat, 1024);
            sourceDataLine.start();
            //System.out.println(audioInputStream.markSupported());
            audioInputStream.mark(12358946);
            while ((len = audioInputStream.read(b)) > 0) {
                sourceDataLine.write(b, 0, len);
            }
            audioInputStream.reset();

            sourceDataLine.drain();
            sourceDataLine.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Audio1(String fileName) {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(Audio.class.getClassLoader().getResource(fileName));
            audioFormat = audioInputStream.getFormat();
            dataLine_info = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLine_info);
            //FloatControl volctrl=(FloatControl)sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
            //volctrl.setValue(-40);//

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
