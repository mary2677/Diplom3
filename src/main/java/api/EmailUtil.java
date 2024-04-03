package api;

public class EmailUtil {
    public static String verifyCodeExtraction(String emailBody) {

        //"Ваш код для восстановления пароля: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx. В вашем приложении вставьте этот код вот так: /reset-password/"

        String srt = emailBody.split(" ")[5];
        srt = srt.substring(0, srt.length() - 1);
        return srt;
    }
}