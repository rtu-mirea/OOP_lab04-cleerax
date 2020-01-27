import java.io.Serializable;

public class lab2_1 implements Serializable{

    private String code;

    public lab2_1() { this.code = ""; }

    public lab2_1(String code) {
        this.code = code;
    }

    public void setCode(String code) { this.code = code; }

    public boolean chkMath() {
        return code.contains("import java.lang.Math;");
    }

    public String Methods() {
        String res = "";
        String func = "";
        int i = 0;
        while (i != -1) {
            i = code.indexOf("Math.", i);
            func = "";
            if (i != -1) {
                while (code.charAt(i) != '(') {
                    if (code.charAt(i) != '(')
                        func += code.charAt(i);
                    i++;
                }
            }
            if(!res.contains(func))
                res += func + ", ";
        }
        res = res.substring(0, res.length() - 2);
        return res;
    }

    public String vars() {
        String res = "";
        String line;
        int n = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '\n') {
                line = code.substring(n, i);
                n = i;
                if (line.contains("Math.")) {
                    line = line.strip();
                    String[] s = line.split(" ");
                    if (!(s[0].contains("=") || s[1].equals("="))) {
                        s[1] = s[1].replace("=", "");
                        res += s[0] + " " + s[1] + ", ";
                    }
                }
            }
        }
        res = res.substring(0, res.length() - 2);
        return res;
    }

    public String operators() {
        String res = "";
        String line;
        int n = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '\n') {
                line = code.substring(n, i);
                n = i;
                if (line.contains("parse") || line.contains("toString")) {
                    line = line.strip();
                    String[] s = line.split(" ");
                    for (String j : s) {
                        if (j.contains("parse") || j.contains("toString")) {
                            j = j.replace(";", "");
                            res += j + "\r\n";
                        }
                    }
                }
            }
        }
        res = res.substring(0, res.length() - 2);
        return res;
    }
}
