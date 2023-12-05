public class Code {

    public Code(){}

    public String getSource(String currentLine){
        String sc;
        sc = currentLine.substring(currentLine.indexOf(" "));
        sc = sc.substring(0, sc.indexOf(" "));
        return sc;
    }
}
