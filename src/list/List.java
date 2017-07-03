package list;

import java.io.*;

/**
 * Created by bigbilii on 2017/6/13.
 *
 */
public class List {
    private int[] list; //数值序列

    public List(String fileName) {
        lodeFile(fileName);
    }

    private void lodeFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String temp;
            while ((temp = reader.readLine()) != null ){
                String[] s = temp.split(" ");
                list = new int[s.length];
                for (int i = 0; i < s.length; i++) {
                    list[i] = Integer.valueOf(s[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(reader != null){
                try{
                    reader.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
        }
    }

    public int[] getList() {
        return list;
    }
}
