package com.el.returnmsg;

import com.el.returnmsg.utils.HttpsConnect;
import com.el.returnmsg.utils.HttpsUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class ReturnmsgApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(String.format("%-10s", "22") + "b");
//        URI url=new URI(' https://eng.sycommercial.com:8090/prod-api/warehouse/CmSendFee/bulkUpdate');
        try {
            String str= HttpsUtil.post("https://eng.sycommercial.com:8090/prod-api/warehouse/CmSendFee/bulkUpdate", "");
            System.out.println(str);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
