package prolab3;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("suffix ağacı için metin giriniz : ");
        String a=sc.next();
        SuffixTree suffixTree = new SuffixTree(a);
        System.out.println("aramak istediğiniz metni giriniz : ");
        String metin=sc.next();
        boolean sonuc = suffixTree.search(metin);
       System.out.print("arama sonucu:" + sonuc + "\n");
    }
}
