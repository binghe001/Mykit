package io.mykit.db.sync.provider;

public class Tool {
    public static String generateString(int length){
        if(length < 1) length = 6;
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String genStr = "";
        for(int index = 0; index < length; index++)
        {
            genStr = genStr + str.charAt((int) ((Math.random() * 100) % 26));
        }
//        System.out.println(genStr);
        return genStr;
    }
}
