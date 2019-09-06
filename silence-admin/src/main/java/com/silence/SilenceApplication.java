package com.silence;

import com.silence.common.config.Global;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author silence
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SilenceApplication
{
    public static void main(String[] args)
    {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(SilenceApplication.class, args);
        printKeyLoadMessage();
    }

    /**
     * 获取Key加载信息
     */
    public static boolean printKeyLoadMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n======================================================================\r\n");
        sb.append("\r\n            (♥◠‿◠)ﾉﾞ  Silence启动成功   ლ(´ڡ`ლ)ﾞ  \r\n");
        sb.append("\r\n    欢迎使用 " + Global.getName() + "  - Silence-Boot.Version : 3.4.RELEASE\r\n");
        sb.append("\r\n======================================================================\r\n");
        System.out.println(sb.toString());
        return true;
    }

}