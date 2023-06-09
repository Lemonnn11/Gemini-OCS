package th.ac.mahidol.ict.utils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ExtractJWT {

    public static String JWTPayloadExtract(String token, String target){

        token.replace("Bearer", "");

        String[] str = token.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payload = new String(decoder.decode(str[1]));

        String[] all = payload.split(",");

        Map<String, String> map = new HashMap<String, String>();

        for (String key: all) {
            String[] keyValue = key.split(":");
            if(keyValue[0].equals(target)){
                int rem = 1;
                if(keyValue[1].endsWith("}")){
                    rem = 2;
                }
                keyValue[1] = keyValue[1].substring(0, keyValue[1].length() - rem);
                keyValue[1] = keyValue[1].substring(1);

                map.put(keyValue[0], keyValue[1]);
            }
        }
        System.out.println(map);
        System.out.println(map.get(target));
        if(map.containsKey(target)){
            return map.get(target);
        }
        return null;
    }

}
