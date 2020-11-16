import java.io.BufferedReader;
import java.io.FilterReader;
import java.io.IOException;

public class SentenceReader extends  FilterReader {
        String sentence="";
        StringBuilder sb=new StringBuilder();
        protected SentenceReader(BufferedReader in) {
            super(in);
        }
        @Override
        public int read() throws IOException {
            int c = super.read();
            sb.append((char)c);
            return c;
        }
        public String getSentence() {
                String retSb=sb.toString();
                retSb=retSb.trim();
                sb=new StringBuilder();
                retSb=removeTrailingCharacters(retSb);
            return retSb;
        }
        public static String removeTrailingCharacters(String s) {
            s=s.replace(".","");
            s=s.replace(",","");
            s=s.replace("!","");
            s=s.replace("?","");
            return s;
        }
}