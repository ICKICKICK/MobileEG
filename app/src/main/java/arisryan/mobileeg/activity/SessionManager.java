package arisryan.mobileeg.activity;

/**
 * Created by Aris Riyanto on 5/13/2017.
 */
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;

        import java.util.HashMap;

        import arisryan.mobileeg.API.KeyExchange;

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int mode = 0;

    private static final String pref_name = "crudpref";
    private static final String is_login = "islogin";
    public static final String kunci_email = "keyemail";
    public static final String kunci_password = "password";
    public static final String kunci_provider = "provider";
    public static final String kunci_token = KeyExchange.tokenfix;

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(pref_name, mode);
        editor = pref.edit();
    }

    public void createSession(String email, String password, String provider, String token){
        editor.putBoolean(is_login, true);
        editor.putString(kunci_email, email);
        editor.putString(kunci_password, password);
        editor.putString(kunci_provider, provider);
        editor.putString(kunci_token, KeyExchange.tokenfix);
        editor.commit();
    }

    public void checkLogin(){
        if (!this.is_login()){
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }else {
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    private boolean is_login() {
        return pref.getBoolean(is_login, false);
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(pref_name, pref.getString(pref_name, null));
        user.put(kunci_email, pref.getString(kunci_email, null));
        user.put(kunci_password, pref.getString(kunci_password, null));
        user.put(kunci_provider, pref.getString(kunci_provider, null));
        return user;
    }

}