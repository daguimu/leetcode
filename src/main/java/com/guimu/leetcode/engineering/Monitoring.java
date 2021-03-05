/*
 * Copyright (c) 2021 maoyan.com
 * All rights reserved.
 *
 */
package com.guimu.leetcode.engineering;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;

/**
 * @author Guimu
 * @date 2021/02/27
 */
public class Monitoring {

    // 可接受的文件类型
    private static final String FILE_SUFFIX = ".mp3";

    private static void playMp3(String path) throws UnsupportedAudioFileException, IOException {
        File file = new File(path);
        if (!file.exists() || !path.toLowerCase().endsWith(FILE_SUFFIX)) {
            throw new RuntimeException("文件不存在");

        }
        AudioInputStream stream;
        //使用 mp3spi 解码 mp3 音频文件
        MpegAudioFileReader mp = new MpegAudioFileReader();
        stream = mp.getAudioInputStream(file);
        AudioFormat baseFormat = stream.getFormat();
        //设定输出格式为pcm格式的音频文件
        AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
            baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2,
            baseFormat.getSampleRate(), false);
        // 输出到音频
        stream = AudioSystem.getAudioInputStream(format, stream);
        AudioFormat target = stream.getFormat();
        DataLine.Info dinfo = new DataLine.Info(SourceDataLine.class, target,
            AudioSystem.NOT_SPECIFIED);
        SourceDataLine line;
        int len;
        try {
            line = (SourceDataLine) AudioSystem.getLine(dinfo);
            line.open(target);
            line.start();
            byte[] buffer = new byte[1024];
            while ((len = stream.read(buffer)) > 0) {
                line.write(buffer, 0, len);
            }
            line.drain();
            line.stop();
            line.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            stream.close();
        }
    }

    public static void loopPlay(String filePath) {
        loopPlay(filePath, -1);
    }

    public static void loopPlay(String filePath, int count) {
        boolean flag = count < 0;
        while (flag || (count--) > 0) {
            try {
                playMp3(filePath);
            } catch (UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}