package shopping.general.generateerrors;

import lombok.NoArgsConstructor;

import java.util.ResourceBundle;

@NoArgsConstructor
public class ControllerMessages {

    public static final ResourceBundle messages;

    static {
        messages = ResourceBundle.getBundle("shopping/labels/messages");
    }

}