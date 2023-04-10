package fastfood.util;

import java.awt.Color;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.JTextComponent;

public class Valid3 {
	private String loi;

    public Valid3() {
        this.loi = "";
    }

    public String getLoi() {
        return loi;
    }

    public void setLoi(String loi) {
        this.loi = loi;
    }

    public void sai(JTextComponent txt, String msg) throws Exception {
        txt.setBackground(Color.YELLOW);
        loi += msg;
        throw new Exception(msg);
    }
    
    public void sai(JTextComponent txt) {
    	txt.setBackground(Color.YELLOW);
    }

    public void dung(JTextComponent txt) {
        txt.setBackground(Color.WHITE);
    }

    public void isEmpty(JTextComponent txt, String name) throws Exception {
        if (txt.getText().trim().isEmpty()) {
            sai(txt, name + " cann't be empty");
        }
        dung(txt);
    }

    public void number(JTextComponent txt, String name) throws Exception {
        try {
            Double.valueOf(txt.getText());
        } catch (NumberFormatException num) {
            sai(txt, name + " has to be number");
        }
        dung(txt);
    }

    public void numberInteger(JTextComponent txt, String name) throws Exception {
        number(txt, name);
        try {
            Integer.valueOf(txt.getText());
        } catch (NumberFormatException num) {
            sai(txt, name + " has to be number integer");
        }
        dung(txt);
    }
    
    public void bigger(JTextComponent txt, String name, Double so) throws Exception{
        if(Double.valueOf(txt.getText().trim()) < so){
            sai(txt, name + " has to bigger than " + so);
        }
        dung(txt);
    }
    
    public void lower(JTextComponent txt, String name, Double so) throws Exception{
        if(Double.valueOf(txt.getText().trim()) > so){
            sai(txt, name + " has to lower than " + so);
        }
        dung(txt);
    }

    public void compare(JTextComponent txt, String name1, String name2,String value) throws Exception {
        if(!txt.getText().equals(value)){
            sai(txt, name1 + " != " +name2);
        }
        dung(txt);
    }
    
    public void compare(JTextComponent txt, String name, String value) throws Exception {
        if(!txt.getText().equals(value)){
            sai(txt, name + " incorrect");
        }
        dung(txt);
    }
    
    public void reMatch(JTextComponent txt, String name, String reString, String msgMalformed) throws Exception {
        if(!txt.getText().matches(reString)){
            sai(txt, name + " malformed. " + msgMalformed);
        }
        dung(txt);
    }

    public void reMatchLowerChar(JTextComponent txt, String name, String reString, String msgMalformed) throws Exception {
        String x = Normalizer.normalize(txt.getText().trim(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "").replaceAll("đ", "d");
        if(!x.matches(reString)){
            sai(txt, name + " malformed. " + msgMalformed);
        }
        dung(txt);
    }
    
    public void minLength(JTextComponent txt, String name, int min) throws Exception {
        if(txt.getText().length() < min){
            sai(txt, name + " has to bigger " + min + " character");
        }
        dung(txt);
    }
    
    public void maxLength(JTextComponent txt, String name, int max) throws Exception {
        if(txt.getText().length() > max){
            sai(txt, name + " has to lower " + max + " character");
        }
        dung(txt);
    }
    
    public void hasCharWriteUppercase(JTextComponent txt, String name, String msgMalformed) throws Exception {
        String x = txt.getText();
        if(x.equals(txt.getText().toLowerCase())){
            sai(txt, name + " malformed. " + msgMalformed);
        }
        dung(txt);
    }
    
    public void hasCharNumber(JTextComponent txt, String name, String msgMalformed) throws Exception {
        Map<String, String> map = importMapNumber();
        String x = txt.getText();
        for (int i = 0; i < x.length(); i++) {
            if (map.get(String.valueOf(x.charAt(i))) != null) {
                dung(txt);
                return;
            }
        }
        sai(txt, name + " malformed. " + msgMalformed);
    }

    public String reDate() {
        return "^([0-9]{4}[-/]?((0[13-9]|1[012])[-/]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-/]?31|02[-/]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00)[-/]?02[-/]?29)$";
    }

    public String rePhone() {
        return "^0[1-9]\\d{8}$";
    }

    public String reEmail() {
        return "^\\w{3,}+@\\w{3,}+(\\.\\w{2,}+){1,2}$";
    }

    public String reName() {
        return "^[0-9a-zA-Z ]{3,40}$";
    }
    
    public String reUser() {
        return "^[0-9a-zA-Z]{5,40}$";
    }

    public Map<String, String> importMapNumber() {
        Map<String, String> map = new HashMap<>();
        map.put("0", "0");
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.put("5", "5");
        map.put("6", "6");
        map.put("7", "7");
        map.put("8", "8");
        map.put("9", "9");
        return map;
    }
}
