import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Json_Converter {
    public static void main(String args[]) {
        String PLAYER = "profile|73241232|<Aamir><Hussain><Khan>|<Mumbai><<72.872075><19.075606>>|73241232.jpg**followers|54543342|<Anil><><Kapoor>|<Delhi><<23.23><12.07>>|54543342.jpg@@|12311334|<Amit><><Bansal>|<Bangalore><<><>>|12311334.jpg";
        String Ids = "id|name|first|second|third|location|name|coords|long|lat|imageId|followers|id|imageId|name|first|middle|last|location|name|coords|long|lat|id|imageId|name|first|middle|last|location|name|coords|long|lat";
        String[] id = Ids.split("\\|");
        int index = 0;
        StringBuilder json = new StringBuilder();
        json.append(" { \n ");
        Pattern replace = Pattern.compile("<>");
        Matcher replacer = replace.matcher(PLAYER);
        String output = replacer.replaceAll("<empty>");
        String[] test1 = output.split("\\|");
        for( int i = 1; i < test1.length; i++ )
        {
        	test1[i] = test1[i].replaceAll("\\*", "");
        	test1[i] = test1[i].replaceAll("\\@", "");
        }
        for ( int i = 1; i < test1.length; i++ )
        {   
            if( test1[i].contains("<") == true ) {
            Pattern pattern = Pattern.compile("\\<([a-zA-Z0-9d+\\.\\d{6}]+)\\>");
            Matcher matcher = pattern.matcher(test1[i]);
            json.append("\n" + '"' + id[index++] + '"' + " : { \n");
            while (matcher.find()) {
            	if(matcher.group(1).equals("empty")) {
            		json.append('"' + id[index++] + '"' + ":" + '"' + "" + '"' + ',' + '\n');
            	} else {
            	json.append('"' + id[index++] + '"' + ":" + '"' + matcher.group(1) + '"' + ',' + '\n');
                } }
            } else {
            	json.append('"' + id[index++] + '"' + " : " + '"' + test1[i] + '"' + ",\n");
            }
        }
        json.append(" } ");
        System.out.println(json);
    }
}
