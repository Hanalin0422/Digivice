package application;

public class Random_Maker {
	public  String random_mak() {
        String s="";
         int b[] = new int[3];
         for(int i=0; i<3; i++) {
            b[i]=(int)(Math.random()*10);
            for(int j=0; j<i;j++) {
                if(b[i]==b[j]) {i--; break;}
            }    
         }
         for(int i=0;i<b.length;i++) {
             s+=b[i];
         }
         return s;
    }
}
