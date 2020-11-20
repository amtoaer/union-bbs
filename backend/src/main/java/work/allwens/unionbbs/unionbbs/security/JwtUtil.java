package work.allwens.unionbbs.unionbbs.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;

public class JwtUtil {

    public static boolean verify(String token, long id, String secret) {
        try {
            Algorithm algo = Algorithm.HMAC256(secret);
            var verifier = JWT.require(algo).withClaim("id", id).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static long getId(String token) {
        try {
            return JWT.decode(token).getClaim("id").asLong();
        } catch (Exception e) {
            return -1;
        }
    }

    public static String sign(long id, String secret) {
        try {
            var algo = Algorithm.HMAC256(secret);
            return JWT.create().withClaim("id", id).sign(algo);
        } catch (Exception e) {
            return null;
        }
    }
}
