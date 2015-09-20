package meghaduta.notifier;

import meghaduta.config.MDConfig;

public class NotiferFactory {
    public static Notifier get(MDConfig config) {
        if (config.getNotifierImpl().equals("file")) {
            return new FileNotifier(config.getNotifierFileName());
        }

        throw new RuntimeException("Invalid notifier type - " + config.getNotifierImpl());
    }
}
