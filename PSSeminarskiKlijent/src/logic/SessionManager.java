
package logic;

import common.domain.Radnik;

public class SessionManager {
    private static SessionManager instance = null;
    private Radnik ulogovani;

    public static SessionManager getInstance() {
        if(instance == null)
            instance = new SessionManager();
        return instance;
    }

    public void setUlogovani(Radnik ulogovani) {
        this.ulogovani = ulogovani;
    }
    
    public String getImePrezime(){
        if(ulogovani == null)
            return "nepoznato";
        return ulogovani.getIme()+" "+ulogovani.getPrezime();
    }
}
