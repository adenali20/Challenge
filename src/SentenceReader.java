import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;

public class SentenceReader extends BufferedReader {

        StringBuilder sentence=new StringBuilder();
        protected SentenceReader(FileReader in) {
            super(in);
        }
        @Override
        public int read() throws IOException {
            int c = super.read();
            sentence.append((char)c);
            return c;
        }

//    @Override
//    public String readLine() throws IOException {
//        return super.readLine();
//    }

    public String getSentence() {
                String retSb=sentence.toString();
                retSb=retSb.trim();
                sentence=new StringBuilder();

                retSb=removeTrailingCharacters(retSb);
            return retSb.toLowerCase();
        }
        public static String removeTrailingCharacters(String s) {
            s=s.replace(".","");
            s=s.replace(",","");
            s=s.replace("!","");
            s=s.replace("?","");
            return s;
        }
}