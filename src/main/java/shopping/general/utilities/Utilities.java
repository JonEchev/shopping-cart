package shopping.general.utilities;

import shopping.general.generateerrors.ControllerMessages;

public class Utilities {

    public static String getValueMessage(String key) {
        return ControllerMessages.messages.getString(key);
    }

}
