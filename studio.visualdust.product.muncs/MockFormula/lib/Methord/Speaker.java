package MockFormula.lib.Methord;

public class Speaker {
    public static boolean speakWhenAnnouncement = true;
    public static boolean speakWhenQuestion = true;
    public static boolean speakWhenScreenRefresh = true;
    public static boolean speakWhenAcceptAnnounce = true;
    public static boolean speakWhenCancelAnnounce = true;
    public static boolean speakWhenAcceptQuestion = true;
    public static boolean speakWhenCancelQuestion = true;
    public static boolean speakWhenChangeOrder = true;
    public static boolean speakWhenChangeSpeaker = true;

    public static boolean speakControl = true;

    public static void speak(String text) {
        try {
            if (speakControl) {
                Runtime.getRuntime().exec("mshta vbscript:CreateObject(\"SAPI.SpVoice\").Speak(\"" + text + "\")(window.close)");
            }
        } catch (Exception e) {
            Exceptionoutput.outputException(e);
        }
    }
}
