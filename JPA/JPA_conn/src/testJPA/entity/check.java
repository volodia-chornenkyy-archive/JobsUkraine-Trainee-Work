package testJPA.entity;

public class check {

	public static void main(String[] args) {
    	check c = new check();
    	Package pack = c.getClass().getPackage();
        String packageName = pack.getName();
        System.out.println(packageName);
	}

}
