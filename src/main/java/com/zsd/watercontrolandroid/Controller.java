package com.zsd.watercontrolandroid;

import com.zsd.watercontrolandroid.bluetooth.ConvertHex;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin2.message.GetAppletMessage;

/**
 * @author thomas
 * @version 1.0
 * @class Controller
 * @description TODO
 * @date 2020/9/6 8:51 上午
 */
@RestController
public class Controller {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public byte[] getTest() {
        String s = "123456";
        byte[] b =ConvertHex.getDataEncrypt(s.getBytes(), s.getBytes());
        return b;
    }

    @RequestMapping(value = "/libPath", method = RequestMethod.GET)
    public String getPath() {
        String s = "123456";
        return System.getProperty("java.library.path");
    }
}
