package test;


public class XXX {
	public static void main(String[] args) {
		String str="111133335555777788";
		char[] ch=str.toCharArray();
		for(int i=6;i>=6&&i<=13;i++) {
			ch[i]=' ';
			
		}
		for(int i=0;i<str.length();i++) {
			System.out.print(ch[i]+"");
		}
	}
}
