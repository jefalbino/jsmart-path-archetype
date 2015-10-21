package ${package}.util;

import org.apache.commons.codec.binary.Base32;

import java.security.SecureRandom;
import java.util.Random;

public class RoomUtils {

    private static final Random random = new SecureRandom();

    private static final Base32 base32 = new Base32();

    public static String getShortName() {
        return base32Random(6);
    }

    private static String base32Random(int numBytes) {
        final byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);

        String base32String;
        synchronized (base32) {
            base32String = base32.encodeAsString(bytes);
        }
        return base32String.toLowerCase().replace("=", "");
    }
}
