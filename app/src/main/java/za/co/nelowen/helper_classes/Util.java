package za.co.nelowen.helper_classes;

import java.util.UUID;

public class Util {
    public static String generateGuid() {
        return UUID.randomUUID().toString();
    }
}
