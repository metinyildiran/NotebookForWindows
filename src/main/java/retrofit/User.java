package retrofit;

public class User {

    private static String email, idToken, userUID;

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static String getIdToken() {
        return idToken;
    }

    public static void setIdToken(String idToken) {
        User.idToken = idToken;
    }

    public static String getUserUID() {
        return userUID;
    }

    public static void setUserUID(String userUID) {
        User.userUID = userUID;
    }
}
