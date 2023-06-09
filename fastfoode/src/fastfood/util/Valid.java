package fastfood.util;

import java.awt.Color;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;

public class Valid {

    private String loi;

    public Valid() {
        this.loi = "";
    }

    public String getLoi() {
        return loi;
    }

    public void setLoi(String loi) {
        this.loi = loi;
    }

    public boolean sai(JTextComponent txt, JLabel lbl, String msg) {
        txt.setBackground(Color.YELLOW);
        lbl.setText(msg);
        loi += msg;
        return false;
    }

    public boolean dung(JTextComponent txt, JLabel lbl) {
        txt.setBackground(Color.WHITE);
        lbl.setText("");
        return true;
    }

    public boolean isEmpty(JTextComponent txt, JLabel lbl, String name) {
        String x = txt.getText().trim();
        return x.isEmpty() ? sai(txt, lbl, name + " cann't be empty") : dung(txt, lbl);
    }

    public boolean compare(JTextComponent txt, JLabel lbl, String msg, String value) {
        return txt.getText().equals(value) ? dung(txt, lbl) : sai(txt, lbl, msg);
    }

    private boolean reMatch(JTextComponent txt, JLabel lbl, String name, String reString) {
        String x = txt.getText().trim();
        return x.matches(reString) ? dung(txt, lbl) : sai(txt, lbl, name + " malformed");
    }

    private boolean length(JTextComponent txt, JLabel lbl, String name, int length) {
        return txt.getText().length() < length ? sai(txt, lbl, name + " length has to bigger than " + length) : dung(txt, lbl);
    }

    public boolean reMatchLower(JTextComponent txt, JLabel lbl, String name, String reString) {
        String x = Normalizer.normalize(txt.getText().trim(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "").toLowerCase().replaceAll("đ", "d");
        return x.matches(reString) ? dung(txt, lbl) : sai(txt, lbl, name + " malformed");
    }

    public boolean number(JTextComponent txt, JLabel lbl, String name) {
        try {
            Double.parseDouble(txt.getText());
        } catch (NumberFormatException num) {
            return sai(txt, lbl, name + " has to be number");
        }
        return dung(txt, lbl);
    }

    public boolean numberInteger(JTextComponent txt, JLabel lbl, String name) {
        try {
            Double.parseDouble(txt.getText());
            try {
                Integer.parseInt(txt.getText());
            } catch (NumberFormatException num) {
                return sai(txt, lbl, name + " has to be number integer");
            }
        } catch (NumberFormatException num) {
            return sai(txt, lbl, name + " has to be number");
        }
        return dung(txt, lbl);
    }

    public boolean lower(JTextComponent txt, JLabel lbl, String name, Double so) {
        double x = Double.parseDouble(txt.getText().trim());
        return x < so ? dung(txt, lbl) : sai(txt, lbl, name + " has to lower than " + so);
    }

    public boolean lowerEqual(JTextComponent txt, JLabel lbl, String name, Double so) {
        double x = Double.parseDouble(txt.getText().trim());
        return x <= so ? dung(txt, lbl) : sai(txt, lbl, name + " has to lower than " + so);
    }

    public boolean bigger(JTextComponent txt, JLabel lbl, String name, Double so) {
        double x = Double.parseDouble(txt.getText().trim());
        return x > so ? dung(txt, lbl) : sai(txt, lbl, name + " has to bigger than " + so);
    }

    public boolean biggerEqual(JTextComponent txt, JLabel lbl, String name, Double so) {
        double x = Double.parseDouble(txt.getText().trim());
        return x >= so ? dung(txt, lbl) : sai(txt, lbl, name + " has to bigger than " + so);
    }

    public boolean multiReMatch(JTextComponent txt, JLabel lbl, String name, String re) {
        return isEmpty(txt, lbl, name) ? reMatch(txt, lbl, name, re) : false;
    }

    public boolean multiNumber(JTextComponent txt, JLabel lbl, String name) {
        return isEmpty(txt, lbl, name) ? number(txt, lbl, name) : false;
    }

    public boolean multiNumberInteger(JTextComponent txt, JLabel lbl, String name) {
        return isEmpty(txt, lbl, name) ? numberInteger(txt, lbl, name) : false;
    }

    public boolean multiBiggerEqual(JTextComponent txt, JLabel lbl, String name, double number) {
        return multiNumber(txt, lbl, name) ? biggerEqual(txt, lbl, name, number) : false;
    }

    public boolean multiLowerEqual(JTextComponent txt, JLabel lbl, String name, double number) {
        return multiNumber(txt, lbl, name) ? lowerEqual(txt, lbl, name, number) : false;
    }

    public boolean multiBiggerAndLower(JTextComponent txt, JLabel lbl, String name, double bigger, double lower) {
        return multiBiggerEqual(txt, lbl, name, bigger) ? lowerEqual(txt, lbl, name, lower) : false;
    }

    public boolean multiBiggerEqual(JTextComponent txt, JLabel lbl, String name, int number) {
        return multiNumberInteger(txt, lbl, name) ? biggerEqual(txt, lbl, name, (double) number) : false;
    }

    public boolean multiBiggerAndLower(JTextComponent txt, JLabel lbl, String name, int bigger, int lower) {
        return multiBiggerEqual(txt, lbl, name, bigger) ? lowerEqual(txt, lbl, name, (double) lower) : false;
    }

    public boolean multiLenght(JTextComponent txt, JLabel lbl, String name) {
        return isEmpty(txt, lbl, name) ? getLenght(txt, lbl) : sai(txt, lbl, name);
    }

    public boolean multiLength(JTextComponent txt, JLabel lbl, String name, int length) {
        return isEmpty(txt, lbl, name) ? length(txt, lbl, name, length) : false;
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
    
    public String reUser(){
        return "^[0-9a-zA-Z]{6,40}$";
    }

    public boolean getLenght(JTextComponent txt, JLabel lbl) {
        return txt.getText().length() <= 50 ? dung(txt, lbl) : false;
    }

    public boolean minLength(JTextComponent txt, JLabel lbl, String name, int min) {
        return txt.getText().length() >= min ? dung(txt, lbl) : sai(txt, lbl, name + " has to bigger " + min + " character");
    }

    public boolean maxLength(JTextComponent txt, JLabel lbl, String name, int max) {
        return txt.getText().length() <= max ? dung(txt, lbl) : sai(txt, lbl, name + " has to lower " + max + " character");
    }

    public boolean multiMinLength(JTextComponent txt, JLabel lbl, String name, int min) {
        return isEmpty(txt, lbl, name) ? minLength(txt, lbl, name, min) : false;
    }

    public boolean multiMinMaxLength(JTextComponent txt, JLabel lbl, String name, int min, int max) {
        return multiMinLength(txt, lbl, name, min) ? maxLength(txt, lbl, name, max) : false;
    }

    private boolean reMatch(JTextComponent txt, JLabel lbl, String name, String reString, String msgMalformed) {
        String x = txt.getText();
        return x.matches(reString) ? dung(txt, lbl) : sai(txt, lbl, name + " malformed. " + msgMalformed);
    }
    
    

    public boolean multiReMatch(JTextComponent txt, JLabel lbl, String name, String re, String msgMalformed) {
        return isEmpty(txt, lbl, name) ? reMatch(txt, lbl, name, re, msgMalformed) : false;
    }

    public boolean hasCharWriteUppercase(JTextComponent txt, JLabel lbl, String name, String msgMalformed) {
        String x = txt.getText();
        return !x.equals(txt.getText().trim().toLowerCase()) ? dung(txt, lbl) : sai(txt, lbl, name + " malformed. " + msgMalformed);
    }

    public boolean hasCharNumber(JTextComponent txt, JLabel lbl, String name, String msgMalformed) {
        Map<String, String> map = importMapNumber();
        String x = txt.getText();
        for (int i = 0; i < x.length(); i++) {
            if (map.get(String.valueOf(x.charAt(i))) != null) {
                return dung(txt, lbl);
            }
        }

        return sai(txt, lbl, name + " malformed. " + msgMalformed);
    }

    public boolean multiReMatch(JTextComponent txt, JLabel lbl, String name, String re, String msgMalformed, int number) {
        /* number
            1 check has char number
            2 check has char uppercase
            3 check all
         */
        if (!multiReMatch(txt, lbl, name, re, msgMalformed)) {
            return false;
        }

        if (number == 1) {
            if (hasCharNumber(txt, lbl, name, msgMalformed)) {
                return true;
            }
        } else if (number == 2) {
            if (hasCharWriteUppercase(txt, lbl, name, msgMalformed)) {
                return true;
            }
        } else {
            if (hasCharNumber(txt, lbl, name, msgMalformed)) {
                if (hasCharWriteUppercase(txt, lbl, name, msgMalformed)) {
                    return true;
                }
                System.out.println("test.Valid.multiReMatch()");
            }
            System.out.println("test.Valid.multiReMatch()asdffd");
        }

        return false;
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
